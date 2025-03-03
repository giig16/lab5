package commands;


import managers.CSVManager;
import managers.CollectionManager;
import model.*;


import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;


/**
 * Команда "add", позволяющая добавить новый {@link City} в коллекцию.
 * <p>
 * При выполнении команда запрашивает у пользователя данные для создания объекта {@code City},
 * проверяет валидность (через {@link City#validate()}), и, если город корректен,
 * добавляет его в коллекцию.
 */
public class Add implements Command {
    /**Менеджер коллкции*/
    private CollectionManager collectionManager;
    /**csv менеджер*/
    private CSVManager csvManager;
    /**Конструктор*/
    public Add(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }
    /**Геттер для менеджера коллекции*/
    public CollectionManager getCollectionManager() {
        return collectionManager;
    }

    /**
     *  Пустой конструктор
     */
    public Add(){}
    public String descr(){
        return "add {element} – добавить новый элемент в коллекцию \n";
    }

    /**Выполнение*/
    public void execute(String argument){
        while (true) {
            City city = collectionManager.createCity();
            if (city.validate()) {
                collectionManager.addToSet(city);

                System.out.println("Город добавлен в коллекцию");
                break;
            } else {
                System.out.println("Город не прошёл валидацию. Повторите ввод.");
            }
        }

    }




}
