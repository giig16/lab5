package commands;

import managers.CollectionManager;

/**
 * Команда "average_of_meters_above_sea_level", вычисляющая среднее значение поля
 * {@code metersAboveSeaLevel} у всех объектов в коллекции.
 */
public class AverageOfMetersAboveSeaLevel implements Command {
    /** Менеджер коллекции */
    private CollectionManager collectionManager;

    /** Конструктор с менеджером коллекции */
    public AverageOfMetersAboveSeaLevel(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    /** Пустой конструктор */
    public AverageOfMetersAboveSeaLevel() {}

    /** Метод исполнения команды */
    @Override
    public void execute(String argument) {
        collectionManager.getAverageMetersSeaLvl();
    }

    /** Описание команды */
    @Override
    public String descr() {
        return "average_of_meters_above_sea_level – вывести среднее значение поля metersAboveSeaLevel для всех элементов коллекции \n";
    }
}
