package task;

import java.util.Objects;

public class Task {
    protected String name;
    protected String info;
    protected TaskStatus status;
    protected boolean isEpic = false;
    protected boolean isSubtask = false;
    protected int epicsId;
    protected final int id;

    public void setEpicsId(int epicsId) {
        this.epicsId = epicsId;
    }

    public boolean isEpic() {
        return isEpic;
    }

    public boolean isSubtask() {
        return isSubtask;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public String getInfo() {
        return info;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public int getEpicsId() {
        return epicsId;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public Task(boolean isEpic, String name, String info, TaskStatus status) {
        this.name = name;
        this.info = info;
        this.status = status;
        this.isEpic = isEpic;
        this.id = Objects.hash(name, info, status, isEpic);
    }

    public Task(boolean isSubtask, int epicsId, String name, String info, TaskStatus status) {
        this.isSubtask = isSubtask;
        this.status = status;
        this.info = info;
        this.name = name;
        this.epicsId = epicsId;
        this.id = Objects.hash(isSubtask, status, info, name, epicsId);
    }

    public Task(String name, String info, TaskStatus status) {
        this.name = name;
        this.info = info;
        this.status = status;
        this.id = Objects.hash(name, info, status);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) return false;
        Task task = (Task) object;
        return isEpic == task.isEpic && isSubtask == task.isSubtask && epicsId == task.epicsId && id == task.id &&
                Objects.equals(name, task.name) && Objects.equals(info, task.info) && status == task.status;
    }

    public String checkProperty(String newString, String prevString) {
        if (!newString.isEmpty()) {
            return newString;
        }
        return prevString;
    }

    @Override
    public String toString() {
        return "Task{" +
                "name='" + name + '\'' +
                ", info='" + info + '\'' +
                ", status=" + status +
                ", id=" + id +
                '}';
    }
}
