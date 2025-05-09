package manager;

import static org.junit.jupiter.api.Assertions.*;

import manager.task.InMemoryTaskManager;
import manager.task.TaskManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import task.Epic;
import task.Subtask;
import task.Task;
import task.TaskStatus;

import java.util.ArrayList;
import java.util.List;

//Общий вопрос, не знаю куда оставлять комменты.
//Будет ли на курсе опция ревьюить в гите, чтобы через комменты к pr-у, а не в платформе яндекса?
public class InMemoryTaskManagerTest {

    private static TaskManager taskManager;
    private static Epic epic1;
    private static Subtask subtask11;
    private static Task task1;

    @BeforeEach
    public void setupManager() {
        epic1 = new Epic("epic1", "Test epic1", TaskStatus.NEW);
        subtask11 = new Subtask(1, "Subtask11", "Test subtask11", TaskStatus.NEW);
        task1 = new Task("task1", "task1 info", TaskStatus.DONE);

        taskManager = new InMemoryTaskManager();
        taskManager.addNewEpic(epic1);
        taskManager.addNewSubtask(subtask11);
        taskManager.addNewTask(task1);
    }

    @Test
    public void shouldAddNewTaskInMemoryTaskManagerWithTask1() {
        ArrayList<Task> arrayList = new ArrayList<>(List.of(task1));
        assertEquals(arrayList, taskManager.getAllTasks(), "Список задач не пуст!");
        Task task2 = new Task("test", "test");

        taskManager.addNewTask(task2);
        arrayList.add(task2);
        assertEquals(arrayList, taskManager.getAllTasks(), "Списки задач не совпадают");
    }

    @Test
    public void shouldAddNewEpicInMemoryTaskManager() {
        ArrayList<Epic> arrayList = new ArrayList<>(List.of(epic1));
        assertEquals(arrayList, taskManager.getAllEpics(), "В списке эпиков нет epic1");

        Epic epic2 = new Epic("test", "test");
        taskManager.addNewEpic(epic2);
        arrayList.add(epic2);
        assertEquals(arrayList, taskManager.getAllEpics(), "Список эпиков не совпадает");
    }

    @Test
    public void shouldAddNewSubtaskInEpicInMemoryTaskManager() {
        ArrayList<Subtask> arrayList = new ArrayList<>(List.of(subtask11));
        assertEquals(arrayList, taskManager.getAllSubtasks(), "Список подзадач не пуст");

        Subtask subtask12 = new Subtask(epic1.getId(), "test", "test");
        taskManager.addNewSubtask(subtask12);
        arrayList.add(subtask12);
        assertEquals(arrayList, taskManager.getAllSubtasks(), "Список подзадач не совпадает");
    }

    @Test
    public void shouldDeleteNewSubtaskInEpicInMemoryTaskManager() {
        ArrayList<Subtask> arrayList = new ArrayList<>(List.of(subtask11));
        assertEquals(arrayList, taskManager.getAllSubtasks(), "Список подзадач не пуст");

        taskManager.deleteSubtask(subtask11);
        arrayList.remove(subtask11);
        assertEquals(arrayList, taskManager.getAllSubtasks(), "В эпике есть лишние подзадачи");
    }

    @Test
    public void shouldGetTask1ById() {
        assertEquals(task1, taskManager.getTaskById(task1.getId()), "Задачи не равны");
    }

    @Test
    public void shouldGetSubtask11ById() {
        assertEquals(subtask11, taskManager.getSubtaskById(subtask11.getId()), "Подзадачи не равны");
    }

    @Test
    public void shouldGetEpic1ById() {
        assertEquals(epic1, taskManager.getEpicById(epic1.getId()),
                "Эпики не равны");
    }

    @Test
    public void shouldUpdateTask1() {
        assertEquals(task1.getName(), taskManager.getTaskById(task1.getId()).getName());

        Task updatedTask1 = new Task("updatedTask", "updatedTask");
        taskManager.updateTask(task1.getId(), updatedTask1);
        assertEquals(task1.getId(), taskManager.getTaskById(task1.getId()).getId(), "id задачи изменился");
        assertEquals(updatedTask1.getName(), taskManager.getTaskById(task1.getId()).getName(),
                "name задачи не изменился");
    }

    @Test
    public void shouldUpdateSubtask11() {
        assertEquals(subtask11.getName(), taskManager.getSubtaskById(subtask11.getId()).getName(),
                "Info в подзадачах не равны");

        Subtask updatedSubtask11 = new Subtask(epic1.getId(), "updatedSubtask", "updatedSubtask");
        taskManager.updateSubtask(subtask11.getId(), updatedSubtask11);
        assertEquals(subtask11.getId(), taskManager.getSubtaskById(subtask11.getId()).getId(),
                "id подзадачи изменился");

        assertEquals(updatedSubtask11.getName(), taskManager.getSubtaskById(subtask11.getId()).getName(),
                "name подзадачи не изменился");
    }

    @Test
    public void shouldUpdateEpic1() {
        assertEquals(epic1.getName(), taskManager.getEpicById(epic1.getId()).getName(),
                "Info в эпиках не равны");

        Epic updateEpic1 = new Epic("updatedEpic", "updatedEpic");
        taskManager.updateEpic(epic1.getId(), updateEpic1);
        assertEquals(epic1.getId(), taskManager.getEpicById(epic1.getId()).getId(),
                "id эпика изменился");

        assertEquals(updateEpic1.getName(), taskManager.getEpicById(epic1.getId()).getName(),
                "name эпика не изменился");
    }

    @Test
    public void shouldUpdateEpicStatusWithSubtaskStatus() {
        assertEquals(TaskStatus.NEW, taskManager.getEpicById(epic1.getId()).getStatus(), "статус эпика не актуален");

        Subtask subtask12 = new Subtask(epic1.getId(), "test", "test", TaskStatus.IN_PROGRESS);
        taskManager.addNewSubtask(subtask12);
        assertEquals(TaskStatus.IN_PROGRESS, taskManager.getEpicById(epic1.getId()).getStatus(),
                "статус эпика не актуален после добавления подзадачи со статусом не NEW");

        taskManager.deleteSubtask(subtask12);

        assertEquals(TaskStatus.NEW, taskManager.getEpicById(epic1.getId()).getStatus(),
                "статус эпика не актуален после удаления подзадачи");

        taskManager.updateSubtask(subtask11.getId(), new Subtask(1, "", "", TaskStatus.DONE));
        assertEquals(TaskStatus.DONE, taskManager.getEpicById(epic1.getId()).getStatus(),
                "статус эпика не изменяется после изменения статуса задачи");
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

        taskManager.deleteTask(task1);
        assertEquals(new ArrayList<>(), taskManager.getAllTasks(), "метод удаления задач работает некорректно");
    }

    @Test
    public void shouldDeleteEpicById() {
        taskManager.deleteEpic(epic1);
        assertFalse(taskManager.getAllEpics().contains(epic1), "метод не удаляет эпик");
        assertFalse(taskManager.getAllSubtasks().contains(subtask11),
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
        taskManager.addNewTask(task1);
        taskManager.updateTask(task1.getId(), new Task("updatedTask", "updatedTask"));
        assertEquals(task1.getName(), taskManager.getTaskById(task1.getId()).getName(),
                "поле name задач после добавления в менеджер не совпадают");
        assertEquals(task1.getInfo(), taskManager.getTaskById(task1.getId()).getInfo(),
                "поле info задач после добавления в менеджер не совпадают");
        assertEquals(task1.getStatus(), taskManager.getTaskById(task1.getId()).getStatus(),
                "поле status задач после добавления в менеджер не совпадают");

    }

    @Test
    public void shouldCleanAllTasks() {
        assertEquals(new ArrayList<>(List.of(task1)), taskManager.getAllTasks(),
                "в менеджере нет task1");

        taskManager.cleanAllTasks();
        assertEquals(new ArrayList<>(), taskManager.getAllTasks(), "метод удаляет задачи некорректно");
    }

    @Test
    public void shouldCleanAllSubtasks() {
        assertEquals(new ArrayList<>(List.of(subtask11)), taskManager.getAllSubtasks(),
                "в менеджере нет subtask11");

        taskManager.deleteSubtask(subtask11);
        assertEquals(new ArrayList<>(), taskManager.getAllSubtasks(), "метод удаляет подзадачи некорректно");
        for (Epic epic : taskManager.getAllEpics()) {
            for (Subtask subtask : epic.getSubtaskArrayList()) {
                assertNotEquals(subtask, subtask11, "метод не удалил подзадачи из массивов подзадач эпиков");
            }
        }
    }

    @Test
    public void shouldCleanAllEpics() {
        assertEquals(new ArrayList<>(List.of(epic1)), taskManager.getAllEpics());

        taskManager.cleanAllEpics();
        assertEquals(new ArrayList<>(), taskManager.getAllEpics(), "менеджер некорректно отчищает эпики");
        assertEquals(new ArrayList<>(), taskManager.getAllSubtasks(),
                "при чистке эпиков не удаляются подзадачи эпиков");
    }
}
