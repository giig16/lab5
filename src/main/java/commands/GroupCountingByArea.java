package commands;

import managers.CollectionManager;

public class GroupCountingByArea implements Command{
    private CollectionManager collectionManager;

    public GroupCountingByArea(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }
    public GroupCountingByArea(){}




    public void execute(String argument) {
        collectionManager.groupCitiesByArea();
    }

    public String descr() {
        return "group_counting_by_area – сгруппировать элементы коллекции по значению поля area, вывести количество элементов в каждой группе \n";
    }
}
