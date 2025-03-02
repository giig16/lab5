package commands;

import com.sun.tools.javac.Main;
import managers.CSVManager;
import managers.CollectionManager;
import model.*;

import java.sql.SQLOutput;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import static java.lang.Long.parseLong;


public class Add implements Command {
    private CollectionManager collectionManager;
    private CSVManager csvManager;
    public Add(CollectionManager collectionManager, CSVManager csvManager) {
        this.collectionManager = collectionManager;
        this.csvManager = csvManager;
    }

    public CollectionManager getCollectionManager() {
        return collectionManager;
    }


    public Add(){}
    public String descr(){
        return "add {element} – добавить новый элемент в коллекцию \n";
    }

    public void execute(){
        City city = createCity1();
        collectionManager.addToSet(city);
        csvManager.writeInCollection(collectionManager.getCities());
        System.out.println("Город добавлен в коллекцию");
    }


    public static City createCity1(){
        Scanner scanner = new Scanner(System.in);
        // name

        System.out.println("Введите название города...");
        String name = scanner.nextLine();
        while(name.isEmpty()){
            System.out.println("Название не может быть пустым."+"\n"+"Введите название города...");
            name = scanner.nextLine().trim();
        }
        // coordinates
        System.out.println("Введит кооринаты:"+"\n"+"x:");
        long x;
        while (true) {
            String input1 = scanner.nextLine().trim();
            if (input1.isEmpty()) {
                System.out.println("Координата не может быть пустой. Введите число...");
                continue;
            }
            try {
                x = Long.parseLong(input1);
                break; // Выход из цикла, если ввод корректен
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: координата должна быть числом. Повторите ввод:");
            }
        }
        System.out.println("Y:");
        double y;
        while (true) {
            String input2 = scanner.nextLine().trim();
            if (input2.isEmpty()) {
                System.out.println("Координата не может быть пустой. Введите число...");
                continue;
            }
            try {
                y = Double.parseDouble(input2);
                break; // Выход из цикла, если ввод корректен
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: координата должна быть числом. Повторите ввод:");
            }
        }
        Coordinates coordinates = new Coordinates(x, y);
        // zoneddatetime
        ZonedDateTime creationDate = ZonedDateTime.now();
        String crDate = creationDate.toString();
        // area
        double area;
        while (true) {
            System.out.println("Введите площадь города:");
            String input3 = scanner.nextLine().trim();

            if (input3.isEmpty()) {
                System.out.println("Ошибка: Площадь не может быть пустой. Введите число.");
                continue; // Повторяем ввод
            }

            try {
                area = Double.parseDouble(input3);
                if (area <= 0) {
                    System.out.println("Ошибка: Площадь должна быть положительным числом. Повторите ввод.");
                    continue;
                }
                break; // Выход из цикла, если ввод корректен
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: Введите корректное число.");
            }
        }
        // population
        long population;
        while (true) {
            System.out.println("Введите численность населения города:");
            String input4 = scanner.nextLine().trim();

            if (input4.isEmpty()) {
                System.out.println("Ошибка: Численность населения не может быть пустой. Введите число.");
                continue; // Повторяем ввод
            }

            try {
                population = Long.parseLong(input4);
                if (population <= 0) {
                    System.out.println("Ошибка: Численность населения должна быть положительным числом. Повторите ввод.");
                    continue;
                }
                break; // Выход из цикла, если ввод корректен
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: Введите корректное целое число.");
            }
        }

        // meters above sea level
        long metersAboveSeaLevel;

        while (true) {
            System.out.println("Введите абсолютную высоту города:");

            String input5 = scanner.nextLine().trim();

            if (input5.isEmpty()) {
                System.out.println("Абсолютная высота не может быть пустой. Введите число...");
                continue;
            }

            try {
                metersAboveSeaLevel = Long.parseLong(input5);

                if (metersAboveSeaLevel < 0) {
                    System.out.println("Ошибка: Введите корректное число.");
                    continue;
                }

                break; // если число корректное, выходим из цикла
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: Введите корректное число.");
            }
        }

        // establishmentDate
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        ZonedDateTime establishmentDate = null;
        System.out.println("Введите дату основания города в формате yyyy-MM-dd HH:mm или не вводите ничего");
        while(true){
            String input6 = scanner.nextLine();
            if(input6.isEmpty()){
                break;
            }
            try{
                LocalDateTime lDT = LocalDateTime.parse(input6,formatter);
                establishmentDate = lDT.atZone(ZoneId.systemDefault());
                break;
            }catch(DateTimeParseException e){
                System.out.println("Ошибка: формат ввода неверен. Формат: yyyy-MM-dd HH:mm или ничего не вводите");

            }
        }
        // government
        Government government = null; // переменная для хранения выбранного enum

        while (government == null) {
            System.out.println("Введите форму правления (ARISTOCRACY, STRATOCRACY, TELLUROCRACY): ");
            String input7 = scanner.nextLine().trim().toUpperCase();

            try {
                government = Government.valueOf(input7);
            } catch (IllegalArgumentException e) {
                System.out.println("Ошибка: нет такого варианта. Повторите ввод.\n");
            }
        }
        // standard of living
        StandardOfLiving standardOfLiving = null;
        while(standardOfLiving==null){
            System.out.println("Введите уровень жизни в городе (VERY_HIGH, HIGH, NIGHTMARE)");
            String input8 = scanner.nextLine().trim().toUpperCase();
            try{
                standardOfLiving = StandardOfLiving.valueOf(input8);
            } catch (IllegalArgumentException e) {
                System.out.println("Ошибка: нет такого варианта. Повторите ввод.\n");
            }
        }
        // governor
        System.out.println("Введите возраст губернатора города...");
        String input9 = scanner.nextLine();
        while(input9.isEmpty()){
            System.out.println("Введите число, возраст не может быть пустым");
            input9 = scanner.nextLine().trim();
        }
        Long age = null;
        Human governor = null;

        try{ age = Long.parseLong(input9);
            governor = new Human(age);
        }
        catch(NumberFormatException e){
            System.out.println("Введите число");
        }



        City city = new City(name, coordinates,  creationDate, area, population, metersAboveSeaLevel, establishmentDate, government, standardOfLiving, governor);
        return city;

    }


}
