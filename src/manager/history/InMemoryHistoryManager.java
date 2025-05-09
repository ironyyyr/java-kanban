package manager.history;

import task.Task;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class InMemoryHistoryManager implements HistoryManager {
    private final LinkedList<Task> browsingHistory;

    public InMemoryHistoryManager() {
        this.browsingHistory = new LinkedList<>();
    }

    //Только в конце понял, что менеджер - отдельный объект.
    //Сомневался, что должен быть публичным метод в TaskManager-e получения истории
    @Override
    public void add(Task task) {
        if (browsingHistory.size() == 10) {
            browsingHistory.removeFirst();
        }

        browsingHistory.add(task);
    }

    @Override
    public List<Task> getHistory() {
        return new ArrayList<>(browsingHistory.reversed());
    }
}
