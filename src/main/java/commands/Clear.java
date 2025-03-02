package commands;

import managers.CollectionManager;
import model.City;

public class Clear implements Command{
    private CollectionManager collectionManager;

    public Clear(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }
    public Clear(){}

    public String descr() {
        return "clear – очистить коллекцию \n";
    }

    public void execute() {
        collectionManager.clearCollection();
        City.setGlobalIDCounter(1);
        System.out.println("Коллекция очищена.");
    }


}



