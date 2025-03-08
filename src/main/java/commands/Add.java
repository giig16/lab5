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
    public void execute(String argument){
        boolean isScriptUsed = invoker.getScript();

        //для командной строки
        if(!isScriptUsed) {

            /*
            можно не вводить значение и самому создать
            город с параметрами которые ты вставляешь
            */

            if (argument == null) {
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

                /*
                можно поставить количество вводимых городов,
                они создадутся сами с рандомными параметрами
                */

            } else {
                int value;
                try {
                    value = Integer.parseInt(argument);
                    if (value <= 0) {
                        System.out.println("Ошибка: количество создаваемых городов должно быть положительным");
                        return;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Ошибка: аргумент комманды должен быть целочисленным");
                    return;
                }
                for (int i = 0; i < value; i++) {
                    City city = collectionManager.createRandomCity();
                    if (city.validate()) {
                        collectionManager.addToSet(city);
                        System.out.println("Рандомный город" + (i + 1) + " добавлен в коллекцию");
                    } else {
                        System.out.println("Рандомный город" + (i + 1) + " не прошёл валидацию");
                    }
                }
            }

            //для Script
        }else {

            /*
            в скрипте похожая картина,
            только тут уже нельзя создавать город со своими параметрами,
            если ты не пишешь количество городов то их будет создаваться ровно 4001
            */

            if (argument == null) {
                int value =4001;
                for (int i = 0; i < value; i++) {
                    City city = collectionManager.createRandomCity();
                    if (city.validate()) {
                        collectionManager.addToSet(city);
                        System.out.println("Рандомный город" + (i + 1) + " добавлен в коллекцию");
                    } else {
                        System.out.println("Рандомный город" + (i + 1) + " не прошёл валидацию");
                    }
                }

                /*
                либо если в скрипте прописано число городов
                то будет добавлено value городов в коллекцию
                */

            } else {
                int value;
                try {
                    value = Integer.parseInt(argument);
                    if (value <= 0) {
                        System.out.println("Ошибка: количество создаваемых городов должно быть положительным");
                        return;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Ошибка: аргумент комманды должен быть целочисленным");
                    return;
                }
                for (int i = 0; i < value; i++) {
                    City city = collectionManager.createRandomCity();
                    if (city.validate()) {
                        collectionManager.addToSet(city);
                        System.out.println("Рандомный город" + (i + 1) + " добавлен в коллекцию");
                    } else {
                        System.out.println("Рандомный город" + (i + 1) + " не прошёл валидацию");
                    }
                }


            }

        }
    }




}
