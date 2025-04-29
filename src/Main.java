import task.Epic;
import manager.Manager;
import task.Subtask;
import task.Task;
import task.TaskStatus;

import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Manager manager = new Manager();

        Epic epic1 = new Epic("epic1", "Test epic1", TaskStatus.NEW);
        Subtask subtask11 = new Subtask(1, "Subtask11", "Test subtask11",
                TaskStatus.NEW);
        Subtask subtask12 = new Subtask(1, "Subtask12", "Test subtask12",
                TaskStatus.IN_PROGRESS);

        Epic epic2 = new Epic("epic2", "Test epic2", TaskStatus.NEW);
        Subtask subtask21 = new Subtask(4, "Subtask21", "Test subtask21",
                TaskStatus.NEW);

        Task task1 = new Task("task1", "task1 info", TaskStatus.DONE);
        Task task2 = new Task("task2", "task2 info", TaskStatus.NEW);
        manager.addNewEpic(epic1);
        manager.addNewSubtask(subtask11);
        manager.addNewSubtask(subtask12);

        manager.addNewEpic(epic2);
        manager.addNewSubtask(subtask21);

        manager.addNewTask(task1);
        manager.addNewTask(task2);

        for (Task tmpTasks : manager.getAllTasks()) {
            System.out.println(tmpTasks);
        }
        for (Epic tmpEpics : manager.getAllEpics()) {
            System.out.println(tmpEpics);
        }

        for (Subtask tmpSubtask : manager.getAllSubtasks()) {
            System.out.println(tmpSubtask);
        }

        System.out.println();

        manager.updateSubtask(subtask12.getId(), new Subtask(epic1.getId(), "Subtask12",
                "Subtask12 updated", TaskStatus.NEW));
        manager.updateTask(task1.getId(), new Task("task1", "task1 updated", TaskStatus.IN_PROGRESS));
        manager.updateTask(task2.getId(), new Task("task2", "task2 updated", TaskStatus.DONE));

        for (Task tmpTasks : manager.getAllTasks()) {
            System.out.println(tmpTasks);
        }

        for (Epic tmpEpics : manager.getAllEpics()) {
            System.out.println(tmpEpics);
        }

        for (Subtask tmpSubtask : manager.getAllSubtasks()) {
            System.out.println(tmpSubtask);
        }

        System.out.println();

        manager.deleteEpicById(epic1);
        manager.deleteTaskById(task1);

        for (Task tmpTasks : manager.getAllTasks()) {
            System.out.println(tmpTasks);
        }
        for (Epic tmpEpics : manager.getAllEpics()) {
            System.out.println(tmpEpics);
        }
        for (Subtask tmpSubtask : manager.getAllSubtasks()) {
            System.out.println(tmpSubtask);
        }
    }
}
