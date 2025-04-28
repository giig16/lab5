package commands;

import managers.CollectionManager;
import managers.DBManager;
import model.City;

/**
 * Команда "update_by_id", обновляющая город по id,
 * если текущий пользователь является владельцем.
 */
public class UpdateID implements Command {
    private CollectionManager collectionManager;
    private DBManager dbManager;

    public UpdateID(CollectionManager collectionManager, DBManager dbManager) {
        this.collectionManager = collectionManager;
        this.dbManager = dbManager;
    }

    public UpdateID() {}

    @Override
    public void execute(String argument) {
        if (collectionManager == null || dbManager == null) {
            System.out.println("Ошибка: менеджеры не инициализированы.");
            return;
        }

        if (argument == null || !argument.matches("\\d+")) {
            System.out.println("Ошибка: укажите id числом.");
            return;
        }

        int id = Integer.parseInt(argument);

        City oldCity = collectionManager.findCityById(id);
        if (oldCity == null) {
            System.out.println("Город с id " + id + " не найден.");
            return;
        }

        String currentUser = collectionManager.getCurrentUser();
        String owner = dbManager.getOwnerByCityId(id);

        if (owner == null) {
            System.out.println("Ошибка: город не найден в базе данных.");
            return;
        }

        if (!owner.equals(currentUser)) {
            System.out.println("Ошибка: вы не являетесь владельцем этого города. Обновление запрещено.");
            return;
        }

        System.out.println("Создайте новый город для обновления:");

        while (true) {
            City newCity = collectionManager.createCity();
            if (newCity.validate()) {
                newCity.setId(id); // сохраняем старый id
                dbManager.updateId(id, newCity);
                collectionManager.getCities().remove(oldCity);
                collectionManager.getCities().add(newCity);
                System.out.println("Город успешно обновлён!");
                break;
            } else {
                System.out.println("Ошибка валидации города. Повторите ввод.");
            }
        }
    }

    @Override
    public String descr() {
        return "update_by_id id {element} – обновить значение элемента коллекции по его id (если он ваш)";
    }
}
