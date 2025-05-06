package task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import task.Subtask;
import task.TaskStatus;

public class SubtaskTest {
    private static Subtask subtask;
    private static Subtask equalSubtask;
    private static Subtask notEqualSubtask;

    @BeforeAll
    public static void setupParams() {
        subtask = new Subtask(1, "test", "test", TaskStatus.NEW);
        equalSubtask = new Subtask(1, "test", "test", TaskStatus.NEW);
        notEqualSubtask = new Subtask(1, "notEqualTest", "notEqualTest", TaskStatus.NEW);

        subtask.setId(1);
        equalSubtask.setId(1);
        notEqualSubtask.setId(2);
    }

    @Test
    public void shouldGetEpicsId1() {
        assertEquals(1, subtask.getEpicsId());
    }

    @Test
    public void shouldSetNewEpicsId2() {
        assertEquals(1, subtask.getEpicsId());
    }

    @Test
    public void shouldReturnTrueWithEqualSubtasks() {
        assertEquals(equalSubtask, subtask);
    }

    @Test
    public void shouldReturnFalseWithNotEqualSubtasks() {
        assertNotEquals(notEqualSubtask, subtask);
    }
}