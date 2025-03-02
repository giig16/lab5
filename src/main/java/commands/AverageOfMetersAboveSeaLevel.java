package commands;

import managers.CollectionManager;
/**
 * Команда "average_of_meters_above_sea_level", вычисляющая среднее значение поля
 * {@code metersAboveSeaLevel} у всех объектов в коллекции
 */
public class AverageOfMetersAboveSeaLevel implements Command{
    private CollectionManager collectionManager;

    public AverageOfMetersAboveSeaLevel(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }
    public AverageOfMetersAboveSeaLevel(){}




    public void execute(String argument) {
        collectionManager.getAverageMetersSeaLvl();

    }

    public String descr() {
        return "average_of_meters_above_sea_level – вывести среднее значение поля metersAboveSeaLevel для всех элементов коллекции \n";
    }
}
