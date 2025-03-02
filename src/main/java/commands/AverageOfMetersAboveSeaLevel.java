package commands;

public class AverageOfMetersAboveSeaLevel implements Command{
    public void execute(String argument) {
        System.out.println("ЭЯйца алишераЭ");

    }

    public String descr() {
        return "average_of_meters_above_sea_level – вывести среднее значение поля metersAboveSeaLevel для всех элементов коллекции \n";
    }
}
