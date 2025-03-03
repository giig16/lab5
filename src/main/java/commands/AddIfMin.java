package commands;

import managers.CollectionManager;
import model.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
/**
 * Команда "add_if_min", добавляющая новый {@link City} в коллекцию,
 * если он «меньше» (согласно {@link City#compareTo(City)}) всех существующих элементов.
 */
public class AddIfMin implements Command{
    /**Менеджер коллекции*/
    private CollectionManager collectionManager;
    /**Конструктор*/
    public AddIfMin(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }
    /**Пустой конструктор*/
    public AddIfMin(){}




    /**Выполнение*/
    public void execute(String argument) {

            City city = collectionManager.createCity();
            if(collectionManager.toCompare(city)){
            if (city.validate()) {
                collectionManager.addToSet(city);
                //csvManager.writeInCollection(collectionManager.getCities());
                System.out.println("Город добавлен в коллекцию");

            } else {
                System.out.println("Город не прошёл валидацию. Повторите ввод.");
            }}else{
                System.out.println("Город который вы создали превышает наименьший город коллекции");
            }

    }
    /**Описание*/
    public String descr() {
        return "add_if_min – добавить новый элемент в коллекцию, если его значение меньше, чем у наименьшего элемента этой коллекции \n";
    }

}
