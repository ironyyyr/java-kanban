package task;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import task.Epic;
import task.Subtask;
import task.TaskStatus;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class EpicTest {
    private static Epic epic;
    private static Epic equalEpic;
    private static Epic notEqualEpic;
    private static Subtask subtask1;
    private static Subtask subtask2;
    private static ArrayList<Subtask> arrayListWithSubtask1;
    private static ArrayList<Subtask> arrayListWithSubtask1AndSubtask2;

    @BeforeAll
    public static void setEpicSubtasks() {
        epic = new Epic("test", "test", TaskStatus.NEW);
        equalEpic = new Epic("test", "test", TaskStatus.NEW);
        notEqualEpic = new Epic("test", "test", TaskStatus.NEW);
        subtask1 = new Subtask(1, "test", "test", TaskStatus.NEW);
        subtask2 = new Subtask(1, "test", "test", TaskStatus.NEW);

        epic.setId(1);
        equalEpic.setId(1);
        notEqualEpic.setId(2);
        subtask1.setId(2);
        subtask2.setId(3);
        epic.getSubtaskArrayList().add(subtask1);

        arrayListWithSubtask1 = new ArrayList<>(List.of(subtask1));
        arrayListWithSubtask1AndSubtask2 = new ArrayList<>(List.of(subtask1, subtask2));
    }

    @BeforeEach
    public void setEpicsSubtasksArrayList() {
        epic.clearSubtasks();
        epic.getSubtaskArrayList().add(subtask1);
    }

    @Test
    public void shouldGiveSubtasksArrayList() {
        assertEquals(arrayListWithSubtask1, epic.getSubtaskArrayList());
    }

    @Test
    public void shouldAddSubtaskToSubtasksArrayList() {
        assertEquals(arrayListWithSubtask1, epic.getSubtaskArrayList());

        epic.addSubtaskToEpicsSubtaskList(subtask2);
        assertEquals(arrayListWithSubtask1AndSubtask2, epic.getSubtaskArrayList());
    }

    @Test
    public void shouldDeleteSubtaskFromSubtasksArrayList() {
        epic.deleteSubtaskFromEpicsSubtaskList(subtask1);
        assertEquals(new ArrayList<>(), epic.getSubtaskArrayList());
    }

    @Test
    public void shouldCleanSubtasksArrayList() {
        epic.clearSubtasks();
        assertEquals(new ArrayList<>(), epic.getSubtaskArrayList());
    }

    @Test
    public void shouldReturnTrueWithEqualEpic() {
        assertEquals(equalEpic, epic);
    }

    @Test
    public void shouldReturnFalseWithNotEqualEpic() {
        assertNotEquals(notEqualEpic, epic);
    }

    //Компилятор не дает добавить из-за того, что по умолчанию addSubtaskToEpicsSubtaskList принимает Epic
//    @Test
//    public void shouldThrowIllegalArgumentExceptionWhenTryingAddEpicToEpicsSubtaskArrayList() {
//        assertThrows(IllegalArgumentException.class, () -> epic.addSubtaskToEpicsSubtaskList(epic));
//    }
}
