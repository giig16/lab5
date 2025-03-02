package commands;

import managers.CollectionManager;
import model.City;
/**
 * Команда "clear", очищающая коллекцию.
 * <p>
 * После очистки коллекции сбрасывает глобальный счётчик {@link City} в значение 1.
 */
public class Clear implements Command{
    private CollectionManager collectionManager;

    public Clear(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }
    public Clear(){}

    public String descr() {
        return "clear – очистить коллекцию \n";
    }

    public void execute(String argument) {
        collectionManager.clearCollection();
        City.setGlobalIDCounter(1);
        System.out.println("Коллекция очищена.");
    }


}



