package commands;

import managers.CollectionManager;

/**
 * Команда "group_counting_by_area", группирующая элементы коллекции
 * по значению поля {@code area} и выводящая количество элементов в каждой группе.
 */
public class GroupCountingByArea implements Command {

    /** Менеджер коллекции */
    private CollectionManager collectionManager;

    /** Конструктор с параметром */
    public GroupCountingByArea(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    /** Пустой конструктор */
    public GroupCountingByArea() {}

    /** Выполнение команды */
    @Override
    public void execute(String argument) {
        collectionManager.groupCitiesByArea();
    }

    /** Описание команды */
    @Override
    public String descr() {
        return "group_counting_by_area – сгруппировать элементы коллекции по значению поля area и вывести количество элементов в каждой группе.\n";
    }
}
