package manager;

import manager.historyManager.HistoryManager;
import manager.historyManager.InMemoryHistoryManager;
import manager.taskManager.InMemoryTaskManager;
import manager.taskManager.TaskManager;

public class Managers {
    public TaskManager getDefault() {
        return new InMemoryTaskManager();
    }

    public static HistoryManager getDefaultHistory() {
        return new InMemoryHistoryManager();
    }
}
