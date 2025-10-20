package manager;

import manager.history.HistoryManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import task.Epic;
import task.Task;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InMemoryHistoryManagerTest {

    private static HistoryManager historyManager;
    private static Epic epic;
    private static Epic epic1;
    private static Epic epic2;
    private static Task task;

    @BeforeEach
    public void setHistoryManager() {
        Managers managers = new Managers();
        historyManager = Managers.getDefaultHistory();
        epic = new Epic("test", "test");
        epic.setId(1);
        task = new Task("task", "task");
        task.setId(2);
        epic1 = new Epic("test", "test");
        epic.setId(3);

    }

    @Test
    public void shouldCheckHistoryAfter0GetOperations() {
        assertEquals(new ArrayList<>(), historyManager.getHistory(), "history инициализируется неверно");
    }

    @Test
    public void shouldCheckHistoryAfter3GetOperations() {
        ArrayList<Task> arrayList = new ArrayList<>(List.of(task, epic, epic1));

        historyManager.add(task);
        historyManager.add(epic);
        historyManager.add(epic1);

        assertEquals(arrayList, historyManager.getHistory(),
                "3 элемента в history хранятся неверно");
    }

    @Test
    public void shouldCheckHistoryAfter10GetOperations() {
        ArrayList<Task> arrayList = new ArrayList<>(List.of(task, epic));

        for (int i = 0; i < 2; i++) {
            historyManager.add(task);
            historyManager.add(epic);
        }

        assertEquals(arrayList, historyManager.getHistory(),
                "2 элемента в history перезаписываются неверно");
    }

    @Test
    public void shouldCheckHistoryAfter15GetOperations() {
        for (int i = 0; i < 12; i++) {
            Task task = new Task("task", "task");
            task.setId(i);
            historyManager.add(task);
        }

        assertEquals(12, historyManager.getHistory().size(),
                "убрано ограничение на хранение 10 элементов");
    }

    @Test
    public void shouldCheckHistoryAfterRewritingFirstElement() {
        ArrayList<Task> arrayList = new ArrayList<>(List.of(task, epic, epic1));

        historyManager.add(task);
        historyManager.add(epic);
        historyManager.add(epic1);

        historyManager.add(epic1);

        assertEquals(arrayList, historyManager.getHistory(),
                "3 элемента в history хранятся неверно");
    }

    @Test
    public void shouldCheckHistoryAfterRewritingMidElement() {
        ArrayList<Task> arrayList = new ArrayList<>(List.of(task, epic1, epic));

        historyManager.add(task);
        historyManager.add(epic);
        historyManager.add(epic1);

        historyManager.add(epic);

        assertEquals(arrayList, historyManager.getHistory(),
                "3 элемента в history хранятся неверно");
    }

    @Test
    public void shouldCheckHistoryAfterRewritingLastElement() {
        ArrayList<Task> arrayList = new ArrayList<>(List.of(epic, epic1, task));

        historyManager.add(task);
        historyManager.add(epic);
        historyManager.add(epic1);

        historyManager.add(task);

        assertEquals(arrayList, historyManager.getHistory(),
                "3 элемента в history хранятся неверно");
    }
}
