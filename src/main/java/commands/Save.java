package commands;

import managers.CSVManager;
import managers.CollectionManager;
/**
 * Команда save, сохраняющая текущее состояние коллекции в CSV-файл
 */
public class Save implements Command {
    private CollectionManager collectionManager;
    private CSVManager csvManager;
    public Save(CollectionManager collectionManager,CSVManager csvManager){
        this.collectionManager = collectionManager;
        this.csvManager = csvManager;
    }
    public Save(){}
    public void execute(String argument) {
        csvManager.writeInCollection(collectionManager.getCities());
        System.out.println("Коллекция сохранена в файл");
    }

    public String descr() {
        return "save – сохранить коллекцию в файл \n";
    }
}
