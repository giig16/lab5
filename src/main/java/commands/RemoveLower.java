package commands;

import managers.CollectionManager;
import model.City;

public class RemoveLower implements Command{
    private CollectionManager collectionManager;

    public RemoveLower(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }
    public RemoveLower(){}


    public void execute(City city) {
        collectionManager.compareTo(city);
    }

    public String descr() {
        return "remove_lower {element} – удалить из коллекции все элементы, меньшие, чем заданный \n";
    }
}
