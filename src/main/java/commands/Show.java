package commands;

import managers.CollectionManager;

/**
 * Команда show, выводящая все элементы коллекции
 */
public class Show implements Command {

    /** Менеджер коллекции */
    private CollectionManager collectionManager;

    /** Конструктор с параметрами */
    public Show(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    /** Пустой конструктор */
    public Show() {}

    /** Метод выполнения команды */
    @Override
    public void execute(String argument) {
        if (collectionManager == null) {
            System.out.println("Ошибка: менеджер коллекции не инициализирован.");
            return;
        }

        if (collectionManager.isEmpty() == 0) {
            System.out.println("Коллекция пуста.");
        } else {
            collectionManager.printCities();
        }
    }

    /** Описание команды */
    @Override
    public String descr() {
        return "show – вывести все элементы коллекции\n";
    }

    /** Сеттер для менеджера коллекции */
    public void setCollectionManager(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }
}
