package epic;

import task.Task;
import task.TaskStatus;

public class Epic extends Task {
    public Epic(boolean isEpic, String name, String info, TaskStatus status) {
        super(isEpic, name, info, status);
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
