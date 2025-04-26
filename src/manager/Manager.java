package manager;

import epic.Epic;
import subtrack.Subtask;
import task.Task;
import task.TaskStatus;

import java.util.ArrayList;
import java.util.HashMap;

public class Manager {
    final HashMap<Integer, Epic> epicsList;
    final HashMap<Integer, Subtask> subtaskList;
    final HashMap<Integer, Task> taskList;

    public Manager(HashMap<Integer, Epic> epicsList, HashMap<Integer, Subtask> subtaskList, HashMap<Integer,
            Task> taskList) {
        this.epicsList = epicsList;
        this.subtaskList = subtaskList;
        this.taskList = taskList;
    }

    public ArrayList<Task> getAllTasks() {
        ArrayList<Task> allTasks = new ArrayList<Task>();
        allTasks.addAll(epicsList.values());
        allTasks.addAll(subtaskList.values());
        allTasks.addAll(taskList.values());
        return allTasks;
    }

    public void cleanTaskTracker() {
        epicsList.clear();
        subtaskList.clear();
        taskList.clear();
    }

    public Task getTaskById(Task task) {
        if (task.isEpic()) {
            return epicsList.get(task.getId());
        }

        if (task.isSubtask()) {
            return subtaskList.get(task.getId());
        }

        return taskList.get(task.getId());
    }


    public void addNewTask(Task task) {
        if (task instanceof Epic) {
            epicsList.put(task.getId(), (Epic) task);
        } else if (task instanceof Subtask) {
            subtaskList.put(task.getId(), (Subtask) task);
            updateEpicStatus(task.getEpicsId());
        } else {
            taskList.put(task.getId(), task);
        }
    }

    private void updateEpicStatus(int epicId) {
        int totalNew = 0;
        int totalDone = 0;
        int totalTasksInEpic = 0;
        Epic currentEpic = epicsList.get(epicId);
        if (currentEpic == null) {
            System.out.println("Такого эпика не существует. EpicId: " + epicId);
            return;
        }

        if (subtaskList.isEmpty()) {
            epicsList.put(epicId, new Epic(currentEpic.isEpic(), currentEpic.getName(), currentEpic.getInfo(),
                    TaskStatus.NEW));
        }
        for (Subtask subtask : subtaskList.values()) {
            if (subtask.getEpicsId() == epicId) {
                if (subtask.getStatus().equals(TaskStatus.NEW)) {
                    totalNew++;
                }

                if (subtask.getStatus().equals(TaskStatus.DONE)) {
                    totalDone++;
                }
                totalTasksInEpic++;
            }
        }
        if (totalTasksInEpic == totalNew) {
            epicsList.put(epicId, new Epic(currentEpic.isEpic(), currentEpic.getName(), currentEpic.getInfo(),
                    TaskStatus.NEW));
            return;
        }

        if (totalTasksInEpic == totalDone) {
            epicsList.put(epicId, new Epic(currentEpic.isEpic(), currentEpic.getName(), currentEpic.getInfo(),
                    TaskStatus.DONE));
            return;
        }

        epicsList.put(epicId, new Epic(currentEpic.isEpic(), currentEpic.getName(), currentEpic.getInfo(),
                TaskStatus.IN_PROGRESS));
    }

    public void deleteTaskById(Task task) {
        if (task instanceof Epic) {
            epicsList.remove(task.getId());
            ArrayList<Subtask> subtasksToDelete = new ArrayList<Subtask>();
            for (Subtask subtask : subtaskList.values()) {
                if (subtask.getEpicsId() == task.getId()) {
                    Task newTask = new Task(subtask.getName(), subtask.getInfo(), subtask.getStatus());
                    taskList.put(newTask.getId(), newTask);
                    subtasksToDelete.add(subtask);
                }
            }

            for (Subtask subtask : subtasksToDelete) {
                subtaskList.remove(subtask.getId());
            }
            return;
        }

        if (task instanceof Subtask) {
            subtaskList.remove(task.getId());
            updateEpicStatus(task.getEpicsId());
            return;
        }

        taskList.remove(task.getId());
    }

    public void updateTask(int taskId, Task task) {
        if (task instanceof Epic) {
            Epic searchedEpic = epicsList.get(taskId);
            searchedEpic.setInfo(task.checkProperty(task.getInfo(), searchedEpic.getInfo()));
            searchedEpic.setName(task.checkProperty(task.getName(), searchedEpic.getName()));
            System.out.println("Epic: " + searchedEpic.getId() + " успешно обновлен");
            return;
        }

        if (task instanceof Subtask) {
            Subtask searchedSubtask = subtaskList.get(taskId);
            searchedSubtask.setInfo(task.checkProperty(task.getInfo(), searchedSubtask.getInfo()));
            searchedSubtask.setName(task.checkProperty(task.getName(), searchedSubtask.getName()));
            searchedSubtask.setEpicsId(task.getEpicsId());
            searchedSubtask.setStatus(task.getStatus());
            updateEpicStatus(task.getEpicsId());
            System.out.println("Subtask: " + taskId + " успешно обновлена");
            return;
        }

        Task searchedTask = taskList.get(taskId);
        searchedTask.setName(task.checkProperty(task.getName(), searchedTask.getName()));
        searchedTask.setInfo(task.checkProperty(task.getInfo(), searchedTask.getInfo()));
        searchedTask.setStatus(task.getStatus());
        System.out.println("Task: " + taskId + " успешно обновлена");
    }

    public ArrayList<Subtask> getEpicsTasks(Epic epic) {
        ArrayList<Subtask> subtasksResultList = new ArrayList<Subtask>();
        for (Subtask subtask : subtaskList.values()) {
            if (subtask.getEpicsId() == epic.getId() && subtask.isEpic()) {
                subtasksResultList.add(subtask);
            }
        }
        return subtasksResultList;
    }
}