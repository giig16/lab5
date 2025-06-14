package commands;

import managers.CollectionManager;
import managers.DBManager;
import model.City;

/**
 * Команда "clear", удаляющая из коллекции все города,
 * принадлежащие текущему пользователю.
 */
public class Clear implements Command {
    private CollectionManager collectionManager;
    private DBManager dbManager;
    private String currentUser;

    public Clear(CollectionManager collectionManager, DBManager dbManager, String currentUser) {
        this.collectionManager = collectionManager;
        this.dbManager = dbManager;
        this.currentUser = currentUser;
    }

    public Clear() {}

    @Override
    public String descr() {
        return "clear – удалить из коллекции все элементы, принадлежащие текущему пользователю\n";
    }

    @Override
    public void execute(String argument) {
        if (collectionManager == null || dbManager == null) {
            System.out.println("Ошибка: менеджеры не инициализированы.");
            return;
        }


        boolean anyRemoved = false;

        for (City city : new java.util.ArrayList<>(collectionManager.getCities())) {
            if (city.getOwner().equals(currentUser)) {
                dbManager.removeCityById(city.getId());
                collectionManager.getCities().remove(city);
                anyRemoved = true;
            }
        }

        if (anyRemoved) {
            System.out.println("Все ваши города успешно удалены из коллекции и базы данных.");
        } else {
            System.out.println("У вас не было городов для удаления.");
        }
    }
}
