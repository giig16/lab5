package commands;

import managers.CollectionManager;
/**
 * Команда "average_of_meters_above_sea_level", вычисляющая среднее значение поля
 * {@code metersAboveSeaLevel} у всех объектов в коллекции
 */
public class AverageOfMetersAboveSeaLevel implements Command{
    /** Поле менеджера коллекции*/
    private CollectionManager collectionManager;
    /** Конструктор*/
    public AverageOfMetersAboveSeaLevel(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }
    /** Пустой конструктор*/
    public AverageOfMetersAboveSeaLevel(){}


    /**
     * Метод исполнения
     * @param argument строковый аргумент команды (может быть {@code null} или пустым)
     */
    public void execute(String argument) {
        collectionManager.getAverageMetersSeaLvl();

    }
    /**Описание команды*/
    public String descr() {
        return "average_of_meters_above_sea_level – вывести среднее значение поля metersAboveSeaLevel для всех элементов коллекции \n";
    }
}
