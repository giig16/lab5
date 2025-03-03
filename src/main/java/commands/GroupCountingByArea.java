package commands;

import managers.CollectionManager;
/**
 * Команда "group_counting_by_area", которая группирует элементы коллекции
 * по значению поля {@code area} и выводит количество элементов в каждой группе.
 */
public class GroupCountingByArea implements Command{
    /**Менеджер коллекции*/
    private CollectionManager collectionManager;
    /**Конструктор*/
    public GroupCountingByArea(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }
    /**Пустой конструктор*/
    public GroupCountingByArea(){}




    /**Метод выполнения команды*/
    public void execute(String argument) {
        collectionManager.groupCitiesByArea();
    }
    /**Описание*/
    public String descr() {
        return "group_counting_by_area – сгруппировать элементы коллекции по значению поля area, вывести количество элементов в каждой группе \n";
    }
}
