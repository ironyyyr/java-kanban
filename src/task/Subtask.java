package task;

public class Subtask extends Task {
    private int epicsId;

    public Subtask(int epicsId, String name, String info, TaskStatus status) {
        super(name, info, status);
        this.epicsId = epicsId;
    }

    public int getEpicsId() {
        return epicsId;
    }

    public void setEpicsId(int epicsId) {
        this.epicsId = epicsId;
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
