package manager.taskManager;

import static org.junit.jupiter.api.Assertions.*;

import manager.Managers;
import manager.historyManager.HistoryManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import task.Epic;
import task.Subtask;
import task.Task;
import task.TaskStatus;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class InMemoryTaskManagerTest {

    private static TaskManager taskManager;

    private static Epic epic1;
    private static Epic updateEpic1;
    private static Subtask subtask11;
    private static Subtask updatedSubtask11;
    private static Subtask subtask12;

    private static Epic epic2;
    private static Subtask subtask21;

    private static Task task1;
    private static Task updatedTask1;
    private static Task task2;

    @BeforeAll
    public static void setupManager() {
        epic1 = new Epic("epic1", "Test epic1", TaskStatus.NEW);
        updateEpic1 = new Epic("UpdateEpic1", "Test epic1", TaskStatus.NEW);
        epic2 = new Epic("epic2", "Test epic2", TaskStatus.NEW);

        subtask11 = new Subtask(1, "Subtask11", "Test subtask11", TaskStatus.NEW);
        updatedSubtask11 = new Subtask(1, "UpdatedSubtask11", "Test subtask11", TaskStatus.DONE);
        subtask12 = new Subtask(1, "Subtask12", "Test subtask12", TaskStatus.IN_PROGRESS);
        subtask21 = new Subtask(1, "Subtask21", "Test subtask21", TaskStatus.NEW);

        task1 = new Task("task1", "task1 info", TaskStatus.DONE);
        updatedTask1 = new Task("UpdatedTask1", "task1 info", TaskStatus.DONE);
        task2 = new Task("task2", "task2 info", TaskStatus.NEW);

        taskManager = new InMemoryTaskManager();

        taskManager.addNewEpic(epic1);
        taskManager.addNewSubtask(subtask11);

        taskManager.addNewTask(task1);
    }

    @BeforeAll
    public static void shouldReturnHistoryOfGetMethod() {
        LinkedList<Task> list = new LinkedList<>();

        assertEquals(list, taskManager.getHistory(), "некорректное отображение пустой истории");

        taskManager.getEpicById(epic1);
        list.add(epic1);
        taskManager.getSubtaskById(subtask11);
        list.add(subtask11);
        taskManager.getTaskById(task1);
        list.add(task1);
        taskManager.getSubtaskById(subtask11);
        list.add(subtask11);


        assertEquals(list.reversed(), taskManager.getHistory(), "задачи в истории записаны корректно");

        taskManager.updateEpic(epic1.getId(), new Epic("test111", "", TaskStatus.NEW));
        taskManager.updateTask(task1.getId(), new Task("task1111", ""));

        assertEquals(list.reversed(), taskManager.getHistory(),
                "объекты в истории изменяются при изменении объектов снаружи");

        taskManager.getEpicById(epic1);
        list.add(epic1);
        taskManager.getSubtaskById(subtask11);
        list.add(subtask11);
        taskManager.getTaskById(task1);
        list.add(task1);
        taskManager.getSubtaskById(subtask11);
        list.add(subtask11);
        taskManager.getEpicById(epic1);
        list.add(epic1);
        taskManager.getSubtaskById(subtask11);
        list.add(subtask11);
        taskManager.getTaskById(task1);
        list.add(task1);
        taskManager.getSubtaskById(subtask11);
        list.add(subtask11);
        taskManager.getSubtaskById(subtask11);
        list.add(subtask11);
        taskManager.getSubtaskById(subtask11);
        list.add(subtask11);
        taskManager.getTaskById(task1);
        list.add(task1);
        taskManager.getTaskById(task1);
        list.add(task1);
        taskManager.getTaskById(task1);
        list.add(task1);

        int i = 0;
        for (Task task : list.reversed()) {
            assertEquals(task, taskManager.getHistory().get(i),
                    "элементы при количестве записей в истории > 10 не равны");
            if (++i >= 9) {
                break;
            }
        }
    }

    @Test
    public void shouldAddNewTaskInMemoryTaskManagerWithTask1() {
        ArrayList<Task> arrayList = new ArrayList<>(List.of(task1));
        assertEquals(arrayList, taskManager.getAllTasks(), "Список задач не пуст!");

        taskManager.addNewTask(task2);
        arrayList.add(task2);
        assertEquals(arrayList, taskManager.getAllTasks(), "Списки задач не совпадают");
    }

    @Test
    public void shouldAddNewEpicInMemoryTaskManager() {
        ArrayList<Epic> arrayList = new ArrayList<>(List.of(epic1));
        assertEquals(arrayList, taskManager.getAllEpics(), "В списке эпиков нет epic1");

        taskManager.addNewEpic(epic2);
        arrayList.add(epic2);
        assertEquals(arrayList, taskManager.getAllEpics(), "Список эпиков не совпадает");

        taskManager.deleteEpicById(epic2);
    }

    @Test
    public void shouldAddAndDeleteNewSubtaskInEpicInMemoryTaskManager() {
        ArrayList<Subtask> arrayList = new ArrayList<>(List.of(subtask11));
        assertEquals(arrayList, taskManager.getAllSubtasks(), "Список подзадач не пуст");

        taskManager.addNewSubtask(subtask12);
        arrayList.add(subtask12);
        assertEquals(arrayList, taskManager.getAllSubtasks(), "Список подзадач не совпадает");

        taskManager.deleteSubtaskById(subtask12);
        arrayList.remove(subtask12);
        assertEquals(arrayList, taskManager.getAllSubtasks(), "В эпике есть лишние подзадачи");
    }

    @Test
    public void shouldGetTask1ById() {
        assertEquals(task1, taskManager.getTaskById(task1), "Задачи не равны");
    }

    @Test
    public void shouldGetSubtask11ById() {
        assertEquals(subtask11, taskManager.getSubtaskById(subtask11), "Подзадачи не равны");
    }

    @Test
    public void shouldGetEpic1ById() {
        assertEquals(epic1, taskManager.getEpicById(epic1), "Эпики не равны");
    }

    @Test
    public void shouldUpdateTask1() {
        assertEquals(task1.getName(), taskManager.getTaskById(task1).getName());

        taskManager.updateTask(task1.getId(), updatedTask1);
        assertEquals(task1.getId(), taskManager.getTaskById(task1).getId(), "id задачи изменился");
        assertEquals(updatedTask1.getName(), taskManager.getTaskById(task1).getName(),
                "name задачи не изменился");

        taskManager.updateTask(task1.getId(), task1);
    }

    @Test
    public void shouldUpdateSubtask11() {
        assertEquals(subtask11.getName(), taskManager.getSubtaskById(subtask11).getName(),
                "Info в подзадачах не равны");

        taskManager.updateSubtask(subtask11.getId(), updatedSubtask11);
        assertEquals(subtask11.getId(), taskManager.getSubtaskById(subtask11).getId(),
                "id подзадачи изменился");

        assertEquals(updatedSubtask11.getName(), taskManager.getSubtaskById(subtask11).getName(),
                "name подзадачи не изменился");

        taskManager.updateSubtask(subtask11.getId(), subtask11);
    }

    @Test
    public void shouldUpdateEpic1() {
        assertEquals(epic1.getName(), taskManager.getEpicById(epic1).getName(),
                "Info в эпиках не равны");

        taskManager.updateEpic(epic1.getId(), updateEpic1);
        assertEquals(epic1.getId(), taskManager.getEpicById(epic1).getId(),
                "id эпика изменился");

        assertEquals(updateEpic1.getName(), taskManager.getEpicById(epic1).getName(),
                "name эпика не изменился");

        taskManager.updateEpic(epic1.getId(), epic1);
    }

    @Test
    public void shouldUpdateEpicStatusWithSubtaskStatus() {
        assertEquals(TaskStatus.NEW, taskManager.getEpicById(epic1).getStatus(), "статус эпика не актуален");

        taskManager.addNewSubtask(subtask12);
        assertEquals(TaskStatus.IN_PROGRESS, taskManager.getEpicById(epic1).getStatus(),
                "статус эпика не актуален после добавления подзадачи со статусом не NEW");

        taskManager.deleteSubtaskById(subtask12);

        assertEquals(TaskStatus.NEW, taskManager.getEpicById(epic1).getStatus(),
                "статус эпика не актуален после удаления подзадачи");

        taskManager.updateSubtask(subtask11.getId(), new Subtask(1, "", "", TaskStatus.DONE));
        assertEquals(TaskStatus.DONE, taskManager.getEpicById(epic1).getStatus(),
                "статус эпика не изменяется после изменения статуса задачи");
        taskManager.updateSubtask(subtask11.getId(), subtask11);
    }

    @Test
    public void shouldGiveEpicSubtasks() {
        ArrayList<Subtask> arrayList = new ArrayList<>(List.of(subtask11));
        assertEquals(arrayList, taskManager.getEpicsTasks(epic1.getId()),
                "метод некорректно возвращает подзадачи эпика по id эпика");
    }

    @Test
    public void shouldDeleteTaskById() {
        assertEquals(new ArrayList<>(List.of(task1)), taskManager.getAllTasks(), "в менеджере нет задач");

        taskManager.deleteTaskById(task1);
        assertEquals(new ArrayList<>(), taskManager.getAllTasks(), "метод удаления задач работает некорректно");

        taskManager.addNewTask(task1);
    }

    @Test
    public void shouldDeleteEpicById() {
        taskManager.addNewEpic(epic2);
        subtask21.setEpicsId(epic2.getId());
        taskManager.addNewSubtask(subtask21);

        taskManager.deleteEpicById(epic2);
        assertFalse(taskManager.getAllEpics().contains(epic2), "метод не удаляет эпик");
        assertFalse(taskManager.getAllSubtasks().contains(subtask21),
                "метод не удаляет подзадачи при удалении эпика");
    }

    @Test
    public void shouldThrowExceptionWithIllegalEpicsIdInSubtask() {
        Subtask subtask = new Subtask(999, "Test", "Description", TaskStatus.NEW);

        IllegalArgumentException exception = assertThrowsExactly(
                IllegalArgumentException.class,
                () -> taskManager.addNewSubtask(subtask)
        );

        assertEquals("Нельзя добавить подзадачу: эпик с id=999 он не существует",
                exception.getMessage());
    }

    @Test
    public void shouldCheckTaskDataAfterAddInManager() {
        taskManager.addNewTask(task2);
        assertEquals(task2.getName(), taskManager.getTaskById(task2).getName(),
                "поле name задач после добавления в менеджер не совпадают");
        assertEquals(task2.getInfo(), taskManager.getTaskById(task2).getInfo(),
                "поле info задач после добавления в менеджер не совпадают");
        assertEquals(task2.getStatus(), taskManager.getTaskById(task2).getStatus(),
                "поле status задач после добавления в менеджер не совпадают");

        taskManager.deleteTaskById(task2);
    }
}
