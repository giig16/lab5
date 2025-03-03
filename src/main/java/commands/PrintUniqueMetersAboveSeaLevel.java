package commands;

import managers.CollectionManager;
/**
 * Команда "print_unique_meters_above_sea_level", выводящая уникальные значения поля
 * {@code metersAboveSeaLevel} у всех объектов в коллекции
 * <p>
 * При выполнении вызывает метод {@link CollectionManager#getUniqueMetersAboveSeaLevel()},
 * который обрабатывает коллекцию и выводит (или возвращает) уникальные значения
 */
public class PrintUniqueMetersAboveSeaLevel implements Command{
    /**Менеджер коллекции*/
    private CollectionManager collectionManager;
    /**Конструктор*/
    public PrintUniqueMetersAboveSeaLevel(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }
    /**Пустой конструктор*/
    public PrintUniqueMetersAboveSeaLevel(){}


    /**Метод выполнения*/
    public void execute(String argument) {
        collectionManager.getUniqueMetersAboveSeaLevel();
    }
    /**Описание*/
    public String descr() {
        return "print_unique_meters_above_sea_level – вывести уникальные значения поля metersAboveSeaLevel всех элементов в коллекции \n";
    }
}
