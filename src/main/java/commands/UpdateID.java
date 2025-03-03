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
 * Команда "update_id", позволяющая обновить существующий элемент коллекции по его {@code id}
 * <p>
 * Логика команды:
 * <ol>
 *     <li>Сначала удаляется элемент с переданным {@code id} (метод {@link CollectionManager#clearForUpdateById(String)})</li>
 *     <li>Затем пользователь заново вводит данные для нового {@link City}, который будет иметь тот же {@code id}</li>
 *     <li>Проверяется валидность нового объекта (через {@link City#validate()}) Если всё корректно, он добавляется в коллекцию</li>
 * </ol>
 */
public class UpdateID implements Command{
    /**Менеджер коллекции*/
    private CollectionManager collectionManager;
    /**Конструктор*/
    public UpdateID(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }
    /**Пустой конструктор*/
    public UpdateID(){}





    /**Метод исполнения команды*/
    public void execute(String argument) {
        collectionManager.clearForUpdateById(argument);
        if(argument==null){

        }else{
        int id = Integer.parseInt(argument);
        while (true) {
            City city = createCity1();
            if (city.validate()) {
                collectionManager.addToSet(city);
                city.setId(id);
                //csvManager.writeInCollection(collectionManager.getCities());
                System.out.println("Город обновлен");
                break;
            } else {
                System.out.println("Город не прошёл валидацию. Повторите ввод.");
            }}
        }







    }

    /**Возвращает описание команды*/
    public String descr() {
        return "update id {element} – обновить значение элемента коллекции, id которого равен заданному \n";
    }










    /**Метод создания города, как в Add*/
    public static City createCity1() {
        Scanner scanner = new Scanner(System.in);
        // name
        System.out.println("Введите название города...");
        String name = scanner.nextLine().trim();
        while (name.isEmpty()) {
            System.out.println("Название не может быть пустым.\nВведите название города...");
            name = scanner.nextLine().trim();
        }

        // coordinates
        System.out.println("Введите координату x (число):");
        long x;
        while (true) {
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                System.out.println("Ошибка: x не может быть пустым. Повторите ввод:");
                continue;
            }
            try {
                x = Long.parseLong(input);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: x должна быть числом. Повторите ввод:");
            }
        }

        System.out.println("Введите координату y (число):");
        double y;
        while (true) {
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                System.out.println("Ошибка: y не может быть пустым. Повторите ввод:");
                continue;
            }
            try {
                y = Double.parseDouble(input);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: y должна быть числом. Повторите ввод:");
            }
        }
        Coordinates coordinates = new Coordinates(x, y);

        //creationDate
        ZonedDateTime creationDate = ZonedDateTime.now();

        // area
        System.out.println("Введите площадь города (число):");
        Double area = null;
        while (true) {
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                System.out.println("Ошибка: Площадь не может быть пустой. Повторите ввод:");
                continue;
            }
            try {
                area = Double.parseDouble(input);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: Площадь должна быть числом. Повторите ввод:");
            }
        }

        // population
        System.out.println("Введите численность населения (число):");
        long population;
        while (true) {
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                System.out.println("Ошибка: Численность населения не может быть пустой. Повторите ввод:");
                continue;
            }
            try {
                population = Long.parseLong(input);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: Введите корректное целое число.");
            }
        }

        // metersAboveSeaLevel
        System.out.println("Введите абсолютную высоту города (число):");
        long metersAboveSeaLevel;
        while (true) {
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                System.out.println("Ошибка: Значение не может быть пустым. Повторите ввод:");
                continue;
            }
            try {
                metersAboveSeaLevel = Long.parseLong(input);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: Введите корректное число.");
            }
        }

        // establishmentDate
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        ZonedDateTime establishmentDate = null;
        System.out.println("Введите дату основания города в формате yyyy-MM-dd HH:mm или оставьте пустым:");
        while (true) {
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                break;
            }
            try {
                LocalDateTime lDT = LocalDateTime.parse(input, formatter);
                establishmentDate = lDT.atZone(ZoneId.systemDefault());
                break;
            } catch (DateTimeParseException e) {
                System.out.println("Ошибка: формат ввода неверен. Формат: yyyy-MM-dd HH:mm или пустая строка.");
            }
        }

        // Government
        Government government = null;
        while (government == null) {
            System.out.println("Введите форму правления (ARISTOCRACY, STRATOCRACY, TELLUROCRACY): ");
            String input = scanner.nextLine().trim().toUpperCase();
            try {
                government = Government.valueOf(input);
            } catch (IllegalArgumentException e) {
                System.out.println("Ошибка: нет такого варианта. Повторите ввод.\n");
            }
        }

        // StandardOfLiving
        StandardOfLiving standardOfLiving = null;
        while (standardOfLiving == null) {
            System.out.println("Введите уровень жизни (VERY_HIGH, HIGH, NIGHTMARE):");
            String input = scanner.nextLine().trim().toUpperCase();
            try {
                standardOfLiving = StandardOfLiving.valueOf(input);
            } catch (IllegalArgumentException e) {
                System.out.println("Ошибка: нет такого варианта. Повторите ввод.\n");
            }
        }


        // Governor
        System.out.println("Введите возраст губернатора (число):");
        Long age = null;
        while (true) {
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                System.out.println("Ошибка: возраст не может быть пустым. Повторите ввод:");
                continue;
            }
            try {
                age = Long.parseLong(input);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: введите корректное число.");

            }
        }
        Human governor = new Human(age);

        //  City
        City city = new City(
                name,
                coordinates,
                creationDate,
                area,
                population,
                metersAboveSeaLevel,
                establishmentDate,
                government,
                standardOfLiving,
                governor
        );


        return city;
    }
}
