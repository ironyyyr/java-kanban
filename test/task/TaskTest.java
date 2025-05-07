package task;

import org.junit.jupiter.api.Test;
import task.Task;
import task.TaskStatus;

import static org.junit.jupiter.api.Assertions.*;

public class TaskTest {
    private final Task task = new Task("test", "test");
    private final Task equalTask = new Task("test", "test");
    private final Task notEqualTask = new Task("test1", "test2");

    @Test
    public void shouldGiveNewTaskStatus() {
        assertEquals(TaskStatus.NEW, task.getStatus());
    }

    @Test
    public void shouldGetTaskInfo() {
        assertEquals("test", task.getInfo());
    }

    @Test
    public void shouldGetTaskName() {
        assertEquals("test", task.getName());
    }

    @Test
    public void shouldSetNewTaskStatus() {
        task.setStatus(TaskStatus.DONE);
        assertEquals(TaskStatus.DONE, task.getStatus());
    }

    @Test
    public void shouldSetNewTaskName() {
        String string = "newTaskName";
        task.setName(string);
        assertEquals(string, task.getName());
    }

    @Test
    public void shouldSetNewTaskInfo() {
        String string = "newTaskInfo";
        task.setInfo(string);
        assertEquals(string, task.getInfo());
    }

    @Test
    public void shouldSetNewTaskId1() {
        int taskId = 1;
        task.setId(taskId);
        assertEquals(1, task.getId());
    }

    @Test
    public void shouldCheckPropertyTrue() {
        assertEquals("newTaskName", task.checkProperty("newTaskName", task.getName()));
    }

    @Test
    public void shouldCheckPropertyFalse() {
        assertEquals(task.getName(), task.checkProperty("", task.getName()));
    }

    @Test
    public void shouldCheckTasksWithSameIdTrue() {
        task.setId(1);
        equalTask.setId(1);
        assertEquals(task, equalTask);
    }

    @Test
    public void shouldCheckTasksWithSameIdFalse() {
        notEqualTask.setId(2);
        assertNotEquals(task, notEqualTask);
    }

    @Test
    public void shouldCheckEqualTasks() {
        assertEquals(task, equalTask);
    }

    @Test
    public void shouldCheckNotEqualTasks() {
        assertNotEquals(task, notEqualTask);
    }
}
