package commands;

import com.sun.tools.javac.Main;
import managers.CollectionManager;
import model.City;




public class Add implements Command {
    private CollectionManager collectionManager;
    public Add(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;

    }
    public String descr(){
        return "add {element} - добавить новый элемент в коллекцию";
    }

    public void execute(){
        City c = new City();
        collectionManager.addToSet(c);
    }
}
