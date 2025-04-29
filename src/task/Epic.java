package task;

import java.util.ArrayList;

public class Epic extends Task {

    private final ArrayList<Subtask> subtaskArrayList;

    public Epic(String name, String info, TaskStatus status) {
        super(name, info, status);
        this.subtaskArrayList = new ArrayList<>();
    }

    public Epic(String name, String info, TaskStatus status, ArrayList<Subtask> subtaskArrayList, int id) {
        super(name, info, status);
        this.subtaskArrayList = subtaskArrayList;
        setId(id);
    }

    public ArrayList<Subtask> getSubtaskArrayList() {
        return subtaskArrayList;
    }

    @Override
    public String toString() {
        return "Epic{" +
                "name='" + name + '\'' +
                ", info='" + info + '\'' +
                ", status=" + status +
                ", id=" + id +
                '}';
    }
}
