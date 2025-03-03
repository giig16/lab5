package commands;

import managers.CSVManager;
import managers.CollectionManager;
/**
 * Команда save, сохраняющая текущее состояние коллекции в CSV-файл
 */
public class Save implements Command {
    /**Менеджер коллекции*/
    private CollectionManager collectionManager;
    /**Менеджер csv*/
    private CSVManager csvManager;
    /**Конструктор*/
    public Save(CollectionManager collectionManager,CSVManager csvManager){
        this.collectionManager = collectionManager;
        this.csvManager = csvManager;
    }
    /**Пустой конструктор*/
    public Save(){}
    /**Метод выполнения*/
    public void execute(String argument) {
        csvManager.writeInCollection(collectionManager.getCities());
        System.out.println("Коллекция сохранена в файл");
    }
    /**Описание*/
    public String descr() {
        return "save – сохранить коллекцию в файл \n";
    }
}
