public class Main {
    public static void main(String[] args) {
       /* InMemoryTaskManager inMemoryTaskManager = new InMemoryTaskManager();

        Epic epic1 = new Epic("epic1", "Test epic1", TaskStatus.NEW);
        Subtask subtask11 = new Subtask(1, "Subtask11", "Test subtask11",
                TaskStatus.NEW);
        Subtask subtask12 = new Subtask(1, "Subtask12", "Test subtask12",
                TaskStatus.IN_PROGRESS);

        Epic epic2 = new Epic("epic2", "Test epic2", TaskStatus.NEW);
        Subtask subtask21 = new Subtask(4, "Subtask21", "Test subtask21",
                TaskStatus.NEW);

        Task task1 = new Task("task1", "task1 info", TaskStatus.DONE);
        Task task2 = new Task("task2", "task2 info", TaskStatus.NEW);
        inMemoryTaskManager.addNewEpic(epic1);
        inMemoryTaskManager.addNewSubtask(subtask11);
        inMemoryTaskManager.addNewSubtask(subtask12);

        inMemoryTaskManager.addNewEpic(epic2);
        inMemoryTaskManager.addNewSubtask(subtask21);

        inMemoryTaskManager.addNewTask(task1);
        inMemoryTaskManager.addNewTask(task2);

        for (Task tmpTasks : inMemoryTaskManager.getAllTasks()) {
            System.out.println(tmpTasks);
        }
        for (Epic tmpEpics : inMemoryTaskManager.getAllEpics()) {
            System.out.println(tmpEpics);
        }

        for (Subtask tmpSubtask : inMemoryTaskManager.getAllSubtasks()) {
            System.out.println(tmpSubtask);
        }

        System.out.println();

        inMemoryTaskManager.updateSubtask(subtask12.getId(), new Subtask(epic1.getId(), "Subtask12",
                "Subtask12 updated", TaskStatus.NEW));
        inMemoryTaskManager.updateTask(task1.getId(), new Task("task1", "task1 updated",
                TaskStatus.IN_PROGRESS));
        inMemoryTaskManager.updateTask(task2.getId(), new Task("task2", "task2 updated",
                TaskStatus.DONE));

        for (Task tmpTasks : inMemoryTaskManager.getAllTasks()) {
            System.out.println(tmpTasks);
        }

        for (Epic tmpEpics : inMemoryTaskManager.getAllEpics()) {
            System.out.println(tmpEpics);
        }

        for (Subtask tmpSubtask : inMemoryTaskManager.getAllSubtasks()) {
            System.out.println(tmpSubtask);
        }

        System.out.println();

        inMemoryTaskManager.deleteEpicById(epic1);
        inMemoryTaskManager.deleteTaskById(task1);

        for (Task tmpTasks : inMemoryTaskManager.getAllTasks()) {
            System.out.println(tmpTasks);
        }
        for (Epic tmpEpics : inMemoryTaskManager.getAllEpics()) {
            System.out.println(tmpEpics);
        }
        for (Subtask tmpSubtask : inMemoryTaskManager.getAllSubtasks()) {
            System.out.println(tmpSubtask);
        } */
    }
}
