package commands;

import managers.CollectionManager;
import managers.FileManager;

/**
 * Команда save, сохраняющая текущее состояние коллекции в файл
 */
public class Save implements Command {

    /** Менеджер коллекции */
    private CollectionManager collectionManager;

    /** Менеджер файлов */
    private FileManager fileManager;

    /** Конструктор с параметрами */
    public Save(CollectionManager collectionManager, FileManager fileManager) {
        this.collectionManager = collectionManager;
        this.fileManager = fileManager;
    }

    /** Пустой конструктор */
    public Save() {}

    /** Метод выполнения команды */
    @Override
    public void execute(String argument) {
        if (collectionManager == null || fileManager == null) {
            System.out.println("Ошибка: менеджеры коллекции или файлов не инициализированы.");
            return;
        }
        fileManager.writeInCollection(collectionManager.getCities());
        System.out.println("Коллекция сохранена в файл.");
    }

    /** Описание команды */
    @Override
    public String descr() {
        return "save – сохранить коллекцию в файл\n";
    }

    /** Сеттеры для менеджеров */
    public void setCollectionManager(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    public void setFileManager(FileManager fileManager) {
        this.fileManager = fileManager;
    }
}
