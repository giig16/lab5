package commands;

import managers.CollectionManager;

/**
 * Команда "print_unique_meters_above_sea_level", выводящая уникальные значения поля
 * {@code metersAboveSeaLevel} у всех объектов коллекции.
 */
public class PrintUniqueMetersAboveSeaLevel implements Command {

    /** Менеджер коллекции */
    private CollectionManager collectionManager;

    /** Конструктор с менеджером коллекции */
    public PrintUniqueMetersAboveSeaLevel(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    /** Пустой конструктор */
    public PrintUniqueMetersAboveSeaLevel() {}

    /** Метод выполнения команды */
    @Override
    public void execute(String argument) {
        if (collectionManager == null) {
            System.out.println("Ошибка: менеджер коллекции не инициализирован.");
            return;
        }
        collectionManager.getUniqueMetersAboveSeaLevel();
    }

    /** Описание команды */
    @Override
    public String descr() {
        return "print_unique_meters_above_sea_level – вывести уникальные значения поля metersAboveSeaLevel всех элементов коллекции.\n";
    }

    /** Сеттер для установки менеджера коллекции */
    public void setCollectionManager(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }
}
