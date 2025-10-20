import manager.task.InMemoryTaskManager;
import task.Epic;
import task.Subtask;
import task.Task;

public class Main {
    public static void main(String[] args) {
        InMemoryTaskManager inMemoryTaskManager = new InMemoryTaskManager();

        inMemoryTaskManager.addNewTask(new Task("task1", "task1"));
        inMemoryTaskManager.addNewTask(new Task("task2", "task2"));

        inMemoryTaskManager.addNewEpic(new Epic("epic1", "epic1"));
        inMemoryTaskManager.addNewSubtask(new Subtask(3, "subtask1", "subtask1"));
        inMemoryTaskManager.addNewSubtask(new Subtask(3, "subtask2", "subtask2"));
        inMemoryTaskManager.addNewSubtask(new Subtask(3, "subtask3", "subtask3"));

        inMemoryTaskManager.addNewEpic(new Epic("epic3", "epic3"));

        inMemoryTaskManager.getTaskById(1);
        inMemoryTaskManager.getEpicById(3);
        inMemoryTaskManager.getEpicById(7);

        System.out.println(inMemoryTaskManager.getHistory());

        inMemoryTaskManager.getEpicById(3);

        System.out.println(inMemoryTaskManager.getHistory());

        inMemoryTaskManager.getSubtaskById(4);
        inMemoryTaskManager.getSubtaskById(6);
        inMemoryTaskManager.getSubtaskById(4);

        System.out.println(inMemoryTaskManager.getHistory());

        inMemoryTaskManager.deleteTaskById(1);

        System.out.println(inMemoryTaskManager.getHistory());

        inMemoryTaskManager.deleteEpicById(3);

        System.out.println(inMemoryTaskManager.getHistory());
    }
}
