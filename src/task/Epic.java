package task;

import java.util.ArrayList;
import java.util.Objects;

public class Epic extends Task {

    private final ArrayList<Subtask> subtaskArrayList;

    public Epic(String name, String info, TaskStatus status) {
        super(name, info, status);
        this.subtaskArrayList = new ArrayList<>();
    }

    public Epic(String name, String info) {
        super(name, info);
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

    public void addSubtaskToEpicsSubtaskList(Subtask subtask) {
        subtaskArrayList.add(subtask);
    }

    public void deleteSubtaskFromEpicsSubtaskList(Subtask subtask) {
        subtaskArrayList.remove(subtask);
    }

    public void clearSubtasks() {
        subtaskArrayList.clear();
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;
        Epic epic = (Epic) object;
        return Objects.equals(subtaskArrayList, epic.subtaskArrayList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), subtaskArrayList);
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
