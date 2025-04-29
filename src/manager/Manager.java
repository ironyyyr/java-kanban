package manager;

import task.Epic;
import task.Subtask;
import task.Task;
import task.TaskStatus;

import java.util.ArrayList;
import java.util.HashMap;

public class Manager {
    private final HashMap<Integer, Epic> epicsList;
    private final HashMap<Integer, Subtask> subtaskList;
    private final HashMap<Integer, Task> taskList;
    private int id = 0;

    public Manager() {
        this.epicsList = new HashMap<>();
        this.subtaskList = new HashMap<>();
        this.taskList = new HashMap<>();
    }

    private int getId() {
        id++;
        return id;
    }

    public ArrayList<Task> getAllTasks() {
        return new ArrayList<Task>(taskList.values());
    }

    public ArrayList<Subtask> getAllSubtasks() {
        return new ArrayList<Subtask>(subtaskList.values());
    }

    public ArrayList<Epic> getAllEpics() {
        return new ArrayList<Epic>(epicsList.values());
    }

    public void cleanAllTasks() {
        taskList.clear();
    }

    public void cleanAllSubtasks() {
        for (Subtask subtask : subtaskList.values()) {
            epicsList.get(subtask.getEpicsId()).getSubtaskArrayList().remove(subtask);
            updateEpicStatus(epicsList.get(subtask.getEpicsId()));
        }
        subtaskList.clear();
    }

    public void cleanAllEpics() {
        subtaskList.clear();
        epicsList.clear();
    }

    public Task getTaskById(Task task) {
        return taskList.get(task.getId());
    }

    public Subtask getSubtaskById(Subtask subtask) {
        return subtaskList.get(subtask.getId());
    }

    public Epic getTaskById(Epic epic) {
        return epicsList.get(epic.getId());
    }

    public void addNewTask(Task task) {
        task.setId(getId());
        taskList.put(task.getId(), task);
    }

    public void addNewSubtask(Subtask subtask) {
        subtask.setId(getId());
        subtaskList.put(subtask.getId(), subtask);

        Epic updatingEpic = epicsList.get(subtask.getEpicsId());
        updatingEpic.getSubtaskArrayList().add(subtask);
        updateEpicStatus(updatingEpic);
    }

    public void addNewEpic(Epic epic) {
        epic.setId(getId());
        epicsList.put(epic.getId(), epic);
    }

    private void updateEpicStatus(Epic epic) {
        int totalNew = 0;
        int totalDone = 0;
        int totalTasksInEpic = epic.getSubtaskArrayList().size();

        if (epic.getSubtaskArrayList().isEmpty()) {
            epic.setStatus(TaskStatus.NEW);
            return;
        }
        for (Subtask subtask : epic.getSubtaskArrayList()) {
            if (subtask.getStatus().equals(TaskStatus.NEW)) {
                totalNew++;
            }

            if (subtask.getStatus().equals(TaskStatus.DONE)) {
                totalDone++;
            }
        }

        if (totalTasksInEpic == totalNew) {
            epic.setStatus(TaskStatus.NEW);
            return;
        }

        if (totalTasksInEpic == totalDone) {
            epic.setStatus(TaskStatus.DONE);
            return;
        }

        epic.setStatus(TaskStatus.IN_PROGRESS);
    }

    public void deleteTaskById(Task task) {
        taskList.remove(task.getId());
    }

    public void deleteSubtaskById(Subtask subtask) {
        subtaskList.remove(subtask.getId());
        epicsList.get(subtask.getEpicsId()).getSubtaskArrayList().remove(subtask);
        Epic updatingEpic = epicsList.get(subtask.getEpicsId());
        updateEpicStatus(updatingEpic);
    }

    public void deleteEpicById(Epic epic) {
        epicsList.remove(epic.getId());

        for (Subtask subtask : epic.getSubtaskArrayList()) {
            subtaskList.remove(subtask.getId());
        }
    }

    public void updateTask(int taskId, Task task) {
        Task searchedTask = taskList.get(taskId);
        searchedTask.setName(task.checkProperty(task.getName(), searchedTask.getName()));
        searchedTask.setInfo(task.checkProperty(task.getInfo(), searchedTask.getInfo()));
        searchedTask.setStatus(task.getStatus());
        System.out.println("Task: " + taskId + " успешно обновлена");
    }

    public void updateSubtask(int subtaskId, Subtask subtask) {
        Subtask searchedSubtask = subtaskList.get(subtaskId);
        searchedSubtask.setInfo(subtask.checkProperty(subtask.getInfo(), searchedSubtask.getInfo()));
        searchedSubtask.setName(subtask.checkProperty(subtask.getName(), searchedSubtask.getName()));
        searchedSubtask.setEpicsId(subtask.getEpicsId());
        searchedSubtask.setStatus(subtask.getStatus());

        Epic searchedEpic = epicsList.get(subtask.getEpicsId());
        updateEpicStatus(searchedEpic);
        System.out.println("Subtask: " + subtaskId + " успешно обновлена");
    }

    public void updateEpic(int epicId, Epic epic) {
        Epic searchedEpic = epicsList.get(epicId);
        searchedEpic.setInfo(epic.checkProperty(epic.getInfo(), searchedEpic.getInfo()));
        searchedEpic.setName(epic.checkProperty(epic.getName(), searchedEpic.getName()));
        System.out.println("Epic: " + epicId + " успешно обновлен");
        return;
    }

    public ArrayList<Subtask> getEpicsTasks(Epic epic) {
        return epic.getSubtaskArrayList();
    }
}