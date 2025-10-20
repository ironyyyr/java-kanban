package manager.history;

import task.Task;

import java.util.ArrayList;
import java.util.HashMap;
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

    private Node head;
    private Node tail;
    private final HashMap<Integer, Node> browsingNodes;

    public InMemoryHistoryManager() {
        this.head = null;
        this.tail = null;
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

        if (tail == null && head == null) {
            tail = node;
            head = node;
        } else {
            tail.next = node;
            node.prev = tail;
            tail = node;
        }
    }

    @Override
    public List<Task> getHistory() {
        return getTasks();
    }

    public List<Task> getTasks() {
        if (tail == null && head == null) {
            return new ArrayList<>();
        }

        ArrayList<Task> tasksList = new ArrayList<>();
        tasksList.add(head.data);

        Node currNode = head;

        while (currNode.next != null) {

            tasksList.add(currNode.next.data);
            currNode = currNode.next;
        }

        return tasksList;
    }

    public void removeNode(Node node) {
        if (node == null) {
            return;
        }

         if (tail == head) {
             tail = null;
             head = null;
             return;
         }

         if (node == tail) {
             tail = node.prev;
             tail.next = null;
             return;
         }

         if (node == head) {
             head = node.next;
             head.prev = null;
             return;
         }

         Node prevNode = node.prev;
         Node nextNode = node.next;

         prevNode.next = nextNode;
         nextNode.prev = prevNode;
    }

    @Override
    public void remove(int id) {
        removeNode(browsingNodes.get(id));
        browsingNodes.remove(id);
    }
}
