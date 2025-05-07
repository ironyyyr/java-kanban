package manager.task;

import manager.Managers;
import manager.history.HistoryManager;
import task.Epic;
import task.Subtask;
import task.Task;
import task.TaskStatus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InMemoryTaskManager implements TaskManager {
    private final HashMap<Integer, Epic> epicsList;
    private final HashMap<Integer, Subtask> subtaskList;
    private final HashMap<Integer, Task> taskList;
    private int id = 1;
    private final HistoryManager historyManager;

    public InMemoryTaskManager() {
        this.epicsList = new HashMap<>();
        this.subtaskList = new HashMap<>();
        this.taskList = new HashMap<>();
        this.historyManager = Managers.getDefaultHistory();
    }

    @Override
    public List<Task> getHistory() {
        return historyManager.getHistory();
    }

    private int getId() {
        return id++;
    }

    @Override
    public ArrayList<Task> getAllTasks() {
        return new ArrayList<Task>(taskList.values());
    }

    @Override
    public ArrayList<Subtask> getAllSubtasks() {
        return new ArrayList<Subtask>(subtaskList.values());
    }

    @Override
    public ArrayList<Epic> getAllEpics() {
        return new ArrayList<Epic>(epicsList.values());
    }

    @Override
    public void cleanAllTasks() {
        taskList.clear();
    }

    @Override
    public void cleanAllSubtasks() {
        for (Epic epic : epicsList.values()) {
            epic.clearSubtasks();
        }
        subtaskList.clear();
    }

    @Override
    public void cleanAllEpics() {
        subtaskList.clear();
        epicsList.clear();
    }

    @Override
    public Task getTaskById(int taskId) {
        historyManager.add(taskList.get(taskId));
        return taskList.get(taskId);
    }

    @Override
    public Subtask getSubtaskById(int subtaskId) {
        historyManager.add(subtaskList.get(subtaskId));
        return subtaskList.get(subtaskId);
    }

    @Override
    public Epic getEpicById(int epicId) {
        historyManager.add(epicsList.get(epicId));
        return epicsList.get(epicId);
    }

    @Override
    public void addNewTask(Task task) {
        task.setId(getId());
        taskList.put(task.getId(), task);
    }

    @Override
    public void addNewSubtask(Subtask subtask) throws IllegalArgumentException {
        if (!epicsList.containsKey(subtask.getEpicsId())) {
            throw new IllegalArgumentException("Нельзя добавить подзадачу: эпик с id="
                    + subtask.getEpicsId() + " он не существует");
        }

        subtask.setId(getId());
        subtaskList.put(subtask.getId(), subtask);

        Epic updatingEpic = epicsList.get(subtask.getEpicsId());
        updatingEpic.addSubtaskToEpicsSubtaskList(subtask);
        updateEpicStatus(updatingEpic);
    }

    @Override
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

    @Override
    public void deleteTask(Task task) {
        taskList.remove(task.getId());
    }

    @Override
    public void deleteSubtask(Subtask subtask) {
        subtaskList.remove(subtask.getId());
        epicsList.get(subtask.getEpicsId()).deleteSubtaskFromEpicsSubtaskList(subtask);
        Epic updatingEpic = epicsList.get(subtask.getEpicsId());
        updateEpicStatus(updatingEpic);
    }

    @Override
    public void deleteEpic(Epic epic) {
        epicsList.remove(epic.getId());

        for (Subtask subtask : epic.getSubtaskArrayList()) {
            subtaskList.remove(subtask.getId());
        }
    }

    @Override
    public void updateTask(int taskId, Task task) {
        Task searchedTask = taskList.get(taskId);
        searchedTask.setName(task.checkProperty(task.getName(), searchedTask.getName()));
        searchedTask.setInfo(task.checkProperty(task.getInfo(), searchedTask.getInfo()));
        searchedTask.setStatus(task.getStatus());
        System.out.println("Task: " + taskId + " успешно обновлена");
    }

    @Override
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

    @Override
    public void updateEpic(int epicId, Epic epic) {
        Epic searchedEpic = epicsList.get(epicId);
        searchedEpic.setInfo(epic.checkProperty(epic.getInfo(), searchedEpic.getInfo()));
        searchedEpic.setName(epic.checkProperty(epic.getName(), searchedEpic.getName()));
        System.out.println("Epic: " + epicId + " успешно обновлен");
    }

    @Override
    public ArrayList<Subtask> getEpicsTasks(int epicId) {
        return epicsList.get(epicId).getSubtaskArrayList();
    }
}