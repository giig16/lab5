package main1;


import managers.CSVManager;

import managers.CollectionManager;
import managers.Invoker;
import model.City;
import java.util.LinkedHashSet;
import java.util.Scanner;
/**
 * Главный класс, запускающий приложение по работе с коллекцией {@link City}.
 * <p>
 * 1. Инициализирует {@link CSVManager} с путём к CSV-файлу.<br>
 * 2. Создаёт {@link CollectionManager} для хранения и управления коллекцией городов.<br>
 * 3. Создаёт {@link Invoker}, регистрирующий все доступные команды.<br>
 * 4. Считывает существующую коллекцию из CSV-файла и передаёт её в {@link CollectionManager}.<br>
 * 5. Запускает цикл чтения команд из консоли.
 */
public class Main {

    public static void main(String[] args) {
        String filePath = System.getenv("CITIES_FILE");
        //String filePath = "src/main/resources/cities.csv";
        if (filePath == null || filePath.isEmpty()) {
            filePath = "src/main/resources/cities.csv";
        }

        CSVManager csvManager = new CSVManager(filePath);
        CollectionManager cm = new CollectionManager(csvManager);

        Invoker invoker = new Invoker(cm, csvManager);
        Scanner sc = new Scanner(System.in);
        LinkedHashSet<City> initialSet = csvManager.readCollectionFromFile();
        cm.setCities(initialSet);

        System.out.println("Введите комманду");

        while (sc.hasNextLine()) {
            String input = sc.nextLine().trim();
            invoker.processRunner(input);
            System.out.println("Введите следующую команду");
        }
    }
}