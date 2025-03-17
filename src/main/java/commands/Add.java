package commands;


import managers.CSVManager;
import managers.CollectionManager;
import managers.Invoker;
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
    public Add(CollectionManager collectionManager, Invoker invoker) {
        this.collectionManager = collectionManager;
        this.invoker=invoker;
    }
    /**Геттер для менеджера коллекции*/
    public CollectionManager getCollectionManager() {
        return collectionManager;
    }
    /**Вызывающий элемент*/
    private Invoker invoker;
    /**Конструктор*/
    public Add(Invoker invoker){
        this.invoker = invoker;
    }




    /**
     *  Пустой конструктор
     */
    public Add(){}
    public String descr(){
        return "add {element} – добавить новый элемент в коллекцию \n";
    }

    /**Выполнение*/
    public void execute(String argument) {
        boolean isScriptUsed = invoker.getScript();


        if (isScriptUsed && argument != null && argument.contains(",")) {
            City parsedCity = collectionManager.parseCityFromScript(argument);
            if (parsedCity != null) {
                collectionManager.addToSet(parsedCity);
                System.out.println("Город успешно добавлен из скрипта!");
            } else {
                System.out.println("Ошибка: не удалось распарсить город из скрипта.");
            }
            return;
        }


        if (!isScriptUsed && argument == null) {
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
            return;
        }

        try {
            int value = Integer.parseInt(argument);
            if (value <= 0) {
                System.out.println("Ошибка: количество создаваемых городов должно быть положительным");
                return;
            }
            for (int i = 0; i < value; i++) {
                City city = collectionManager.createRandomCity();
                if (city.validate()) {
                    collectionManager.addToSet(city);
                    System.out.println("Рандомный город " + (i + 1) + " добавлен в коллекцию");
                } else {
                    System.out.println("Рандомный город " + (i + 1) + " не прошёл валидацию");
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("Ошибка: аргумент команды должен быть целым числом или списком параметров через запятую.");
        }
    }



}