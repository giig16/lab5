package commands;

import com.sun.tools.javac.Main;
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
    public Add(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;

    }
    public String descr(){
        return "add {element} - добавить новый элемент в коллекцию";
    }

    public void execute(){

        collectionManager.createCity1();
    }


    public static City createCity2(Scanner scanner){
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
        System.out.println("Введите площадь города...");
        String input3 = scanner.nextLine();
        while(input3.isEmpty()){
            System.out.println("Площадь не может быть пустой"+"\n"+"Введите число...");
            input3 = scanner.nextLine().trim();
        }
        Double area = Double.parseDouble(input3);
        // population
        System.out.println("Введите численность населения города...");
        String input4 = scanner.nextLine();
        while(input4.isEmpty()){
            System.out.println("Численность населения не может быть пустой"+"\n"+"Введите число...");
            input4 = scanner.nextLine().trim();
        }
        Long population = Long.parseLong(input4);
        // meters above sea level
        System.out.println("Введите абсолютную высоту города");
        String input5 = scanner.nextLine();
        while(input5.isEmpty()){
            System.out.println("Абсолютная высота не может быть пустой"+"\n"+"Введите число...");
            input5 = scanner.nextLine().trim();
        }
        long metersAboveSeaLevel = Long.parseLong(input5);
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
        Long age = Long.parseLong(input9);
        Human governor = new Human(age);


        City city = new City(name, coordinates,  creationDate, area, population, metersAboveSeaLevel, establishmentDate, government, standardOfLiving, governor);
        return city;

    }


}
