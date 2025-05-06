package manager.historyManager;

import task.Task;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class InMemoryHistoryManager implements HistoryManager {
    private final LinkedList<Task> browsingHistory;

    public InMemoryHistoryManager() {
        this.browsingHistory = new LinkedList<>();
    }

    //Не до конца понял, что делать с доступом метода. Перенести все реализации менеджеров в один пакет?
    @Override
    public void add(Task task) {
        if (browsingHistory.size() == 10) {
            browsingHistory.removeFirst();
        }

        browsingHistory.add(task);
    }

    @Override
    public List<Task> getHistory() {
        List<Task> list = new ArrayList<>();
        for (int i = browsingHistory.size() - 1; i >= 0; i--) {
            list.add(browsingHistory.get(i));
        }

        return list;
    }
}
