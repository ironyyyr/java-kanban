package manager;

import manager.history.HistoryManager;
import manager.task.TaskManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ManagerTest {

    private static TaskManager taskManager;
    private static HistoryManager historyManager;

    @BeforeAll
    public static void setManagers() {
        Managers managers = new Managers();
        taskManager = managers.getDefault();
        historyManager = Managers.getDefaultHistory();
    }

    @Test
    public void shouldReturnInitializedManagerCopies() {
        assertNotNull(taskManager, "объект менеджера задач инициализируется null-ом");
        assertNotNull(historyManager, "объект менеджера истории инициализируется null-ом");

    }
}
