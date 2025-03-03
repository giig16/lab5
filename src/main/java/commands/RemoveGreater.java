package commands;

import managers.CollectionManager;
import model.City;


/**
 * Команда "remove_greater", удаляющая из коллекции все элементы,
 * которые превышают заданный (по логике {@link City#compareTo(City)})
 */
public class RemoveGreater implements Command{
    /**Описание*/
    public String descr() {
        return "remove_greater {element} – удалить из коллекции все элементы, превышающие заданный \n";
    }
    /**Менеджер коллекции*/
    private CollectionManager collectionManager;
    /**Конструктор*/
    public RemoveGreater(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }
    /**Пустой конструктор*/
    public RemoveGreater(){}


    /**Метод выполнения*/
    public void execute(String argument) {
        collectionManager.clearCollectionGreater(argument);
        City.setGlobalIDCounter(City.getGlobalIDCounter()-1);
    }
}
