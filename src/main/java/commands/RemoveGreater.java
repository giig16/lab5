package commands;

import managers.CollectionManager;
import model.City;


/**
 * Команда "remove_greater", удаляющая из коллекции все элементы,
 * которые превышают заданный (по логике {@link City#compareTo(City)})
 */
public class RemoveGreater implements Command{
    public String descr() {
        return "remove_greater {element} – удалить из коллекции все элементы, превышающие заданный \n";
    }

    private CollectionManager collectionManager;

    public RemoveGreater(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }
    public RemoveGreater(){}




    public void execute(String argument) {
        collectionManager.clearCollectionGreater(argument);
        City.setGlobalIDCounter(City.getGlobalIDCounter()-1);
    }
}
