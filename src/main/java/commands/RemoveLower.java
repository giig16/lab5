package commands;

import managers.CollectionManager;
import model.City;

public class RemoveLower implements Command{
    private CollectionManager collectionManager;

    public RemoveLower(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }
    public RemoveLower(){}

    public void execute(String argument) {
        collectionManager.clearCollectionLower(argument);
    }

    public void execute() {
        System.out.println("Ошибка: команда 'remove_lower' требует аргумент (название города).");
    }

    public String descr() {
        return "remove_lower {element} – удалить из коллекции все элементы, меньшие, чем заданный \n";
    }
}
