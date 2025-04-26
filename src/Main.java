import epic.Epic;
import manager.Manager;
import manager.Printer;
import subtrack.Subtask;
import task.Task;
import task.TaskStatus;

import java.util.HashMap;
import java.util.Scanner;

public class Main {
    static final int GET_ALL_TASKS = 1;
    static final int DELETE_ALL_TASKS = 2;
    static final int GET_TASK_BY_ID = 3;
    static final int CREATE_NEW_TASK = 4;
    static final int UPDATE_TASK = 5;
    static final int DELETE_TASK_BY_ID = 6;
    static final int GET_ALL_TASKS_FROM_EPIC = 7;

    public static void main(String[] args) {
        HashMap<Integer, Task> taskList = new HashMap<Integer, Task>();
        HashMap<Integer, Subtask> subtaskList = new HashMap<Integer, Subtask>();
        HashMap<Integer, Epic> epicList = new HashMap<Integer, Epic>();
        Manager manager = new Manager(epicList, subtaskList, taskList);

        Epic epic1 = new Epic(true, "epic1", "Test epic1", TaskStatus.NEW);
        Subtask subtask11 = new Subtask(true, 2114304115, "Subtask11", "Test subtask11",
                TaskStatus.NEW);
        Subtask subtask12 = new Subtask(true, 2114304115, "Subtask12", "Test subtask12",
                TaskStatus.IN_PROGRESS);

        Epic epic2 = new Epic(true, "epic2", "Test epic2", TaskStatus.NEW);
        Subtask subtask21 = new Subtask(true, 2114334867, "Subtask21", "Test subtask21",
                TaskStatus.NEW);

        Task task1 = new Task("task1", "task1 info", TaskStatus.DONE);
        Task task2 = new Task("task2", "task2 info", TaskStatus.NEW);
        manager.addNewTask(epic1);
        manager.addNewTask(subtask11);
        manager.addNewTask(subtask12);

        manager.addNewTask(epic2);
        manager.addNewTask(subtask21);

        manager.addNewTask(task1);
        manager.addNewTask(task2);

        for (Task tmpTasks : manager.getAllTasks()) {
            System.out.println(tmpTasks);
        }

        System.out.println();

        manager.updateTask(subtask12.getId(), new Subtask(true, epic2.getId(), "Subtask12",
                "Subtask12 updated", TaskStatus.NEW));
        manager.updateTask(task1.getId(), new Task("task1", "task1 updated", TaskStatus.IN_PROGRESS));
        manager.updateTask(task2.getId(), new Task("task2", "task2 updated", TaskStatus.DONE));

        for (Task tmpTasks : manager.getAllTasks()) {
            System.out.println(tmpTasks);
        }

        System.out.println();

        manager.deleteTaskById(epic1);
        manager.deleteTaskById(task1);

        for (Task tmpTasks : manager.getAllTasks()) {
            System.out.println(tmpTasks);
        }
    }

    private int askUser() {
        Printer.printMenu();
        Scanner scanner = new Scanner(System.in);
        int userChoice = scanner.nextInt();
        if (userChoice == GET_TASK_BY_ID) {
            Printer.printMenuAboutEpic();
            int doesGetEpicSubtasks = scanner.nextInt();
            if (doesGetEpicSubtasks == 1) {
                return GET_ALL_TASKS_FROM_EPIC;
            }
        }
        return userChoice;
    }
}
