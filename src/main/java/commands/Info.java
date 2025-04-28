package commands;

import managers.CollectionManager;
import java.time.format.DateTimeFormatter;

/**
 * Команда "info", выводящая информацию о коллекции:
 * <ul>
 *     <li>Тип коллекции</li>
 *     <li>Дата инициализации (формат {@code yyyy-MM-dd HH:mm})</li>
 *     <li>Количество элементов</li>
 * </ul>
 */
public class Info implements Command {

    /** Менеджер коллекции */
    private CollectionManager collectionManager;

    /** Конструктор с менеджером коллекции */
    public Info(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    /** Пустой конструктор */
    public Info() {}

    /** Метод выполнения команды */
    @Override
    public void execute(String argument) {
        if (collectionManager == null) {
            System.out.println("Ошибка: менеджер коллекции не инициализирован.");
            return;
        }
        String collectionType = "LinkedHashSet";
        String initTime = collectionManager.getInitTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        int size = collectionManager.getCities().size();
        System.out.println("Информация о коллекции:");
        System.out.println("Тип: " + collectionType);
        System.out.println("Дата инициализации: " + initTime);
        System.out.println("Количество элементов: " + size);
    }

    /** Описание команды */
    @Override
    public String descr() {
        return "info – вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)\n";
    }

    /** Сеттер для инициализации менеджера коллекции после создания объекта */
    public void setCollectionManager(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }
}
