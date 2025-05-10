package manager;

import manager.history.HistoryManager;
import manager.task.InMemoryTaskManager;
import manager.task.TaskManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import task.Epic;
import task.Subtask;
import task.Task;
import task.TaskStatus;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class InMemoryHistoryManagerTest {

    private static HistoryManager historyManager;
    private static Epic epic;
    private static Task task;

    @BeforeEach
    public void setHistoryManager() {
        Managers managers = new Managers();
        historyManager = Managers.getDefaultHistory();
        epic = new Epic("test", "test");
        task = new Task("task", "task");

    }

    @Test
    public void shouldCheckHistoryAfter0GetOperations() {
        assertEquals(new ArrayList<>(), historyManager.getHistory(), "history инициализируется неверно");
    }

    @Test
    public void shouldCheckHistoryAfter6GetOperations() {
        ArrayList<Task> arrayList = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            historyManager.add(task);
            historyManager.add(epic);
            arrayList.add(task);
            arrayList.add(epic);
        }

        assertEquals(arrayList, historyManager.getHistory().reversed(),
                "6 элементов в history хранятся неверно");
    }

    @Test
    public void shouldCheckHistoryAfter10GetOperations() {
        ArrayList<Task> arrayList = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            historyManager.add(task);
            historyManager.add(epic);
            arrayList.add(task);
            arrayList.add(epic);
        }

        assertEquals(arrayList, historyManager.getHistory().reversed(),
                "10 элементов в history хранятся неверно");
    }

    @Test
    public void shouldCheckHistoryAfter15GetOperations() {
        for (int i = 0; i < 12; i++) {
            historyManager.add(task);
        }

        assertEquals(10, historyManager.getHistory().size(), "элементов в history больше, чем 10");
    }
}
