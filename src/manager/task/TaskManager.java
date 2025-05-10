package manager.task;

import task.Epic;
import task.Subtask;
import task.Task;

import java.util.ArrayList;
import java.util.List;

public interface TaskManager {
    ArrayList<Task> getAllTasks();

    ArrayList<Subtask> getAllSubtasks();

    ArrayList<Epic> getAllEpics();

    void cleanAllTasks();

    void cleanAllSubtasks();

    void cleanAllEpics();

    Task getTaskById(int taskId);

    Subtask getSubtaskById(int subtaskId);

    Epic getEpicById(int epicId);

    void addNewTask(Task task);

    void addNewSubtask(Subtask subtask);

    void addNewEpic(Epic epic);

    void deleteTaskById(int taskId);

    void deleteSubtaskById(int subtaskId);

    void deleteEpicById(int epicId);

    void updateTask(int taskId, Task task);

    void updateSubtask(int subtaskId, Subtask subtask);

    void updateEpic(int epicId, Epic epic);

    ArrayList<Subtask> getEpicsTasks(int epicId);

    List<Task> getHistory();
}
