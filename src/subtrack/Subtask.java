package subtrack;

import task.Task;
import task.TaskStatus;

public class Subtask extends Task {

    public Subtask(boolean isSubtask, int epicsId, String name, String info, TaskStatus status) {
        super(isSubtask, epicsId, name, info, status);
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
