package task;

import java.util.Objects;

public class Task {
    protected String name;
    protected String info;
    protected TaskStatus status;
    protected int id;

    public Task(String name, String info, TaskStatus status) {
        this.name = name;
        this.info = info;
        this.status = status;
    }

    public Task(String name, String info) {
        this(name, info, TaskStatus.NEW);
    }

    public TaskStatus getStatus() {
        return this.status;
    }

    public String getInfo() {
        return this.info;
    }

    public String getName() {
        return this.name;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
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
        return id == task.id && Objects.equals(name, task.name) && Objects.equals(info, task.info) && status == task.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, info, status, id);
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
