package commands;

import managers.CollectionManager;
import model.City;
/**
 * Команда "clear", очищающая коллекцию.
 * <p>
 * После очистки коллекции сбрасывает глобальный счётчик {@link City} в значение 1.
 */
public class Clear implements Command{
    /**Менеджер коллекции*/
    private CollectionManager collectionManager;
    /**Конструктор*/
    public Clear(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }
    /**Пустой конструктор*/
    public Clear(){}
    /**Описание*/
    public String descr() {
        return "clear – очистить коллекцию \n";
    }
    /**Выполнение*/
    public void execute(String argument) {
        collectionManager.clearCollection();
        City.setGlobalIDCounter(1);
        System.out.println("Коллекция очищена.");
    }


}



