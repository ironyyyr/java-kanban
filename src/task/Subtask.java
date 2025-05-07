package task;

import java.util.Objects;

public class Subtask extends Task {
    private int epicsId;

    public Subtask(int epicsId, String name, String info, TaskStatus status) {
        super(name, info, status);
        this.epicsId = epicsId;
    }

    public Subtask(int epicsId, String name, String info) {
        super(name, info);
        this.epicsId = epicsId;
    }

    public int getEpicsId() {
        return epicsId;
    }

    public void setEpicsId(int epicsId) {
        this.epicsId = epicsId;
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;
        Subtask subtask = (Subtask) object;
        return epicsId == subtask.epicsId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), epicsId);
    }

    @Override
    public String toString() {
        return "Subtask{" +
                "name='" + name + '\'' +
                ", info='" + info + '\'' +
                ", status=" + status +
                ", epicsId=" + epicsId +
                ", id=" + id +
                '}';
    }
}
