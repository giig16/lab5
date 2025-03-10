package commands;

import managers.CollectionManager;
import model.City;
/**
 * Команда "remove_lower", удаляющая из коллекции все города,
 * которые меньше заданного (по логике {@link City#compareTo(City)})
 */
public class RemoveLower implements Command{
    /**Менеджер коллекции*/
    private CollectionManager collectionManager;
    /**Конструктор*/
    public RemoveLower(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }
    /**Пустой конструктор*/
    public RemoveLower(){}
    /**Метод выполнения*/
    public void execute(String argument) {
        collectionManager.clearCollectionLower(argument);
        City.setGlobalIDCounter(City.getGlobalIDCounter());
    }


    /**Описание*/
    public String descr() {
        return "remove_lower {element} – удалить из коллекции все элементы, меньшие, чем заданный \n";
    }
}
