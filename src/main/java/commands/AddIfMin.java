package commands;

import managers.CollectionManager;
import managers.DBManager;
import managers.Invoker;
import model.City;

/**
 * Команда "add_if_min", добавляющая новый {@link City} в коллекцию,
 * если он «меньше» всех существующих элементов.
 */
public class AddIfMin implements Command {
    private CollectionManager collectionManager;
    private Invoker invoker;
    private DBManager dbManager;
    private String currentUser;

    public AddIfMin(CollectionManager collectionManager, DBManager dbManager, String currentUser) {
        this.collectionManager = collectionManager;
        this.invoker = invoker;
        this.dbManager = dbManager;
        this.currentUser = currentUser;
    }

    public AddIfMin() {}

    @Override
    public void execute(String argument) {
        boolean isScriptUsed = invoker.getScript();
        City city;

        if (isScriptUsed && argument != null && argument.contains(",")) {
            city = collectionManager.parseCityFromScript(argument, currentUser);
        } else {
            city = collectionManager.createCity(currentUser);
        }

        if (city == null || !city.validate()) {
            System.out.println("Ошибка: не удалось создать или валидировать город.");
            return;
        }

        if (collectionManager.getCities().isEmpty() || collectionManager.toCompare(city)) {
            boolean success = collectionManager.addToSet(city);
            if (success) {
                System.out.println("Город добавлен в коллекцию (он минимальный).");
            } else {
                System.out.println("Ошибка при добавлении города в базу данных.");
            }
        } else {
            System.out.println("Город не добавлен, так как он не минимальный.");
        }
    }

    @Override
    public String descr() {
        return "add_if_min – добавить новый элемент в коллекцию, если его значение меньше, чем у наименьшего элемента коллекции.\n";
    }
}
