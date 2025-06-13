package commands;

import managers.CollectionManager;
import managers.DBManager;
import model.City;

import java.util.Iterator;

/**
 * Команда "remove_greater", удаляющая все города больше заданного,
 * если пользователь является их владельцем.
 */
public class RemoveGreater implements Command {
    private CollectionManager collectionManager;
    private DBManager dbManager;
    private String currentUser;

    public RemoveGreater(CollectionManager collectionManager, DBManager dbManager, String currentUser) {
        this.collectionManager = collectionManager;
        this.dbManager = dbManager;
        this.currentUser = currentUser;
    }

    public RemoveGreater() {}

    @Override
    public void execute(String argument) {
        if (collectionManager == null || dbManager == null) {
            System.out.println("Ошибка: менеджеры не инициализированы.");
            return;
        }

        if (argument == null || argument.isEmpty()) {
            System.out.println("Ошибка: нужно указать название города для сравнения.");
            return;
        }

        City referenceCity = collectionManager.findCityByName(argument);
        if (referenceCity == null) {
            System.out.println("Город '" + argument + "' не найден в коллекции.");
            return;
        }


        Iterator<City> iterator = collectionManager.getCities().iterator();
        boolean anyRemoved = false;

        while (iterator.hasNext()) {
            City city = iterator.next();
            if (city.compareTo(referenceCity) > 0 && city.getOwner().equals(currentUser)) {
                dbManager.removeCityById(city.getId());
                iterator.remove();
                anyRemoved = true;
                System.out.println("Удалён город с id " + city.getId());
            }
        }

        if (!anyRemoved) {
            System.out.println("Нет подходящих городов для удаления.");
        }
    }

    @Override
    public String descr() {
        return "remove_greater {element} – удалить из коллекции все элементы, превышающие заданный (если они принадлежат вам)\n";
    }
}
