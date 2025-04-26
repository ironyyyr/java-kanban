package manager;

public class Printer {
    public static void printMenu() {
        System.out.println("1. Получить список всех задач");
        System.out.println("2. Удалить все задачи");
        System.out.println("3. Получить задачу по id");
        System.out.println("4. Создать задачу");
        System.out.println("5. Обновить задачу");
        System.out.println("6. Удалить задачу по ID");
    }

    public static void printMenuAboutEpic() {
        System.out.println("Ваша задача Epic?");
        System.out.println("1. Да");
        System.out.println("2. Нет");
    }
}
