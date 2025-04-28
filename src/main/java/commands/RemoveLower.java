package commands;

import managers.CollectionManager;
import managers.DBManager;
import model.City;

import java.util.Iterator;

/**
 * Команда "remove_lower", удаляющая все города меньше заданного,
 * если пользователь является их владельцем.
 */
public class RemoveLower implements Command {
    private CollectionManager collectionManager;
    private DBManager dbManager;

    public RemoveLower(CollectionManager collectionManager, DBManager dbManager) {
        this.collectionManager = collectionManager;
        this.dbManager = dbManager;
    }

    public RemoveLower() {}

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

        String currentUser = collectionManager.getCurrentUser();
        Iterator<City> iterator = collectionManager.getCities().iterator();
        boolean anyRemoved = false;

        while (iterator.hasNext()) {
            City city = iterator.next();
            if (city.compareTo(referenceCity) < 0 && city.getOwner().equals(currentUser)) {
                dbManager.removeById(city.getId());
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
        return "remove_lower {element} – удалить из коллекции все элементы, меньшие заданного (если они принадлежат вам)\n";
    }
}
