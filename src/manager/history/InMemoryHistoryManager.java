package manager.history;

import task.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class InMemoryHistoryManager implements HistoryManager {
    private static class Node {
        public Task data;
        public Node next;
        public Node prev;

        public Node(Task data, Node prev) {
            this.data = data;
            this.prev = prev;
            this.next = null;
        }
    }

    private final LinkedList<Node> browsingHistory;
    private final HashMap<Integer, Node> browsingNodes;

    public InMemoryHistoryManager() {
        this.browsingHistory = new LinkedList<>();
        this.browsingNodes = new HashMap<>();
    }

    @Override
    public void add(Task task) {
        Node node = new Node(task, null);

        linkLast(node);

        browsingNodes.put(task.getId(), node);
    }

    public void linkLast(Node node) {
        if (browsingNodes.containsKey(node.data.getId())) {
            remove(node.data.getId());
        }

        if (!browsingHistory.isEmpty()) {
            node.prev = browsingHistory.getLast();
            browsingHistory.getLast().next = node;
        }

        browsingHistory.add(node);
    }

    @Override
    public List<Task> getHistory() {
        return getTasks();
    }

    public List<Task> getTasks() {
        if (browsingHistory.isEmpty()) {
            return new ArrayList<>();
        }

        Node task = browsingHistory.getLast();
        Node prevTask = task.prev;

        ArrayList<Task> tasksList = new ArrayList<>();
        tasksList.add(task.data);

        while (prevTask != null) {
            tasksList.add(prevTask.data);
            prevTask = prevTask.prev;
        }

        return tasksList.reversed();
    }

    public void removeNode(Node node) {
        Node nextNode = node.next;
        Node prevNode = node.prev;

        if (nextNode != null) {
            nextNode.prev = prevNode;
        }

        if (prevNode != null) {
            prevNode.next = nextNode;
        }

        node.next = null;
        node.prev = null;
    }

    @Override
    public void remove(int id) {
        removeNode(browsingNodes.get(id));
        browsingNodes.remove(id);
    }
}
