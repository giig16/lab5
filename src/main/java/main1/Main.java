package main1;

import managers.*;
import model.City;

import java.util.Scanner;

/**
 * Главный класс, запускающий приложение по работе с коллекцией {@link City}.
 */
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {

            DBManager dbManager = new DBManager();
            FileManager fileManager = new CSVManager("52");
            CollectionManager collectionManager = new CollectionManager(dbManager);

            //dbManager.clearUsersTable();
            //dbManager.clearCitiesTable();

            dbManager.isTableExist(dbManager.getConnection());
            dbManager.addOwnerColumnIfNotExists();

            AuthorisationManager authorisationManager = new AuthorisationManager();

            authorisationManager.isAuthorised(scanner);
            String currentUser = authorisationManager.getLogin();
            Invoker invoker = new Invoker(collectionManager, fileManager, dbManager, currentUser);

            collectionManager.loadCollectionFromDatabase();


            System.out.println("Введите команду:");


            while (scanner.hasNextLine()) {
                String input = scanner.nextLine().trim();
                if (!input.isEmpty()) {
                    invoker.processRunner(input);
                }
                System.out.println("Введите следующую команду:");
            }
        } catch (Exception e) {
            System.out.println("Ошибка запуска программы: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
