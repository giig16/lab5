package commands;

import managers.CSVManager;
import managers.CollectionManager;
import managers.FileManager;

/**
 * Команда save, сохраняющая текущее состояние коллекции в CSV-файл
 */
public class Save implements Command {
    /**Менеджер коллекции*/
    private CollectionManager collectionManager;
    /**Менеджер csv*/
    private FileManager fileManager;
    /**Конструктор*/
    public Save(CollectionManager collectionManager,FileManager fileManager){
        this.collectionManager = collectionManager;
        this.fileManager = fileManager;
    }
    /**Пустой конструктор*/
    public Save(){}
    /**Метод выполнения*/
    public void execute(String argument) {
        fileManager.writeInCollection(collectionManager.getCities());
        System.out.println("Коллекция сохранена в файл");
    }
    /**Описание*/
    public String descr() {
        return "save – сохранить коллекцию в файл \n";
    }
}
