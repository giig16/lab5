package commands;

import managers.CollectionManager;
import model.City;
/**
 * Команда "remove_by_id", удаляющая элемент из коллекции по его id
 */
public class RemoveByID implements Command{
    /**Менеджер коллекции*/
    private CollectionManager collectionManager;
    /**Конструктор*/
    public RemoveByID(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }
    /**Пустой конструктор*/
    public RemoveByID(){}

    /**Метод выполнения*/
    public void execute(String argument) {
        collectionManager.clearById(argument);
        City.setGlobalIDCounter(City.getGlobalIDCounter()-1);
    }
    /**Описание*/
    public String descr() {
        return "remove_by_id id – удалить элемент из коллекции по его id \n";
    }
}
