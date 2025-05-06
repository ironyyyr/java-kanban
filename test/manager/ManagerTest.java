package manager;

import manager.historyManager.HistoryManager;
import manager.taskManager.TaskManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import task.Epic;
import task.Task;
import task.TaskStatus;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ManagerTest {
    private static Managers managers;
    private static TaskManager taskManager1;
    private static TaskManager taskManager2;
    private static HistoryManager historyManager;

    private static Epic epic1;
    private static Task task1;

    @BeforeAll
    public static void setManagers() {
        managers = new Managers();
        taskManager1 = managers.getDefault();
        taskManager2 = managers.getDefault();
        historyManager = Managers.getDefaultHistory();

        epic1 = new Epic("epic1", "Test epic1", TaskStatus.NEW);
        task1 = new Task("task1", "task1 info", TaskStatus.DONE);
    }

    @Test
    public void shouldReturnInitializedManagerCopies() {


        assertNotNull(taskManager1, "объект менеджера задач инициализируется null-ом");
        assertNotNull(taskManager2, "объект менеджера задач инициализируется null-ом");
        assertNotNull(historyManager, "объект менеджера истории инициализируется null-ом");

        taskManager1.addNewTask(task1);
        assertEquals(new ArrayList<>(List.of(task1)), taskManager1.getAllTasks(),
                "задачи в инстанцию менеджера добавляются некорректно");
        historyManager.add(task1);
        assertEquals(new ArrayList<>(List.of(task1)), historyManager.getHistory(),
                "история записывается некорректно");

        taskManager2.addNewEpic(epic1);
        assertEquals(new ArrayList<>(List.of(epic1)), taskManager2.getAllEpics(),
                "эпики в инстанцию менеджера добавляются некорректно");
        historyManager.add(epic1);
        assertEquals(new ArrayList<>(List.of(task1, epic1)), historyManager.getHistory().reversed(),
                "история записывается некорректно");

        taskManager1.cleanAllTasks();
        assertEquals(new ArrayList<>(), taskManager1.getAllTasks(),
                "отчистка менеджера происходит некорректно");
        taskManager2.cleanAllEpics();
        assertEquals(new ArrayList<>(), taskManager2.getAllEpics(),
                "отчистка менеджера происходит некорректно");
    }
}
