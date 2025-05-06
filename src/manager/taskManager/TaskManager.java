package manager.taskManager;

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

    Task getTaskById(Task task);

    Subtask getSubtaskById(Subtask subtask);

    Epic getEpicById(Epic epic);

    void addNewTask(Task task);

    void addNewSubtask(Subtask subtask) throws IllegalArgumentException;

    void addNewEpic(Epic epic);

    void deleteTaskById(Task task);

    void deleteSubtaskById(Subtask subtask);

    void deleteEpicById(Epic epic);

    void updateTask(int taskId, Task task);

    void updateSubtask(int subtaskId, Subtask subtask);

    void updateEpic(int epicId, Epic epic);

    ArrayList<Subtask> getEpicsTasks(int epicId);

    List<Task> getHistory();
}
