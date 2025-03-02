package commands;

import managers.CollectionManager;

public class RemoveByID implements Command{
    private CollectionManager collectionManager;

    public RemoveByID(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }
    public RemoveByID(){}


    public void execute(String argument) {
        collectionManager.clearById(argument);
    }

    public String descr() {
        return "remove_by_id id – удалить элемент из коллекции по его id \n";
    }
}
