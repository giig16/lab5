package commands;

import managers.CollectionManager;

public class Show implements Command{
    private CollectionManager collectionManager;
    public Show(CollectionManager collectionManager){
        this.collectionManager = collectionManager;
    }
    public String descr(){
        return "show - вывести все элементы коллекции";
    }
    public void execute(){
        collectionManager.printCities();
    }
}
