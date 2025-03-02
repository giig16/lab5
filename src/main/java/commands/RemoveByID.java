package commands;

import managers.CollectionManager;
import model.City;
/**
 * Команда "remove_by_id", удаляющая элемент из коллекции по его id
 */
public class RemoveByID implements Command{
    private CollectionManager collectionManager;

    public RemoveByID(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }
    public RemoveByID(){}


    public void execute(String argument) {
        collectionManager.clearById(argument);
        City.setGlobalIDCounter(City.getGlobalIDCounter()-1);
    }

    public String descr() {
        return "remove_by_id id – удалить элемент из коллекции по его id \n";
    }
}
