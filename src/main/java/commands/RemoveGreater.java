package commands;

import managers.CollectionManager;
import model.City;

import java.util.Scanner;

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
