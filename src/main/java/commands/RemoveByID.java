package commands;

import managers.CollectionManager;
import managers.DBManager;
import model.City;

public class RemoveByID implements Command {
    private CollectionManager collectionManager;
    private DBManager dbManager;
    private String currentUser;

    public RemoveByID(CollectionManager collectionManager, DBManager dbManager, String currentUser) {
        this.collectionManager = collectionManager;
        this.dbManager = dbManager;
        this.currentUser = currentUser;
    }

    public RemoveByID() {}

    @Override
    public void execute(String argument) {
        if (argument == null || !argument.matches("\\d+")) {
            System.out.println("Ошибка: укажите id числом.");
            return;
        }

        int id = Integer.parseInt(argument);

        String owner = dbManager.getOwnerByCityId(id);
        if (owner == null) {
            System.out.println("Ошибка: город с таким id не найден.");
            return;
        }

        if (!owner.equals(currentUser)) {
            System.out.println("Ошибка: вы не являетесь владельцем этого города.");
            return;
        }

        collectionManager.clearById(argument);
        System.out.println("Город успешно удалён.");
    }

    @Override
    public String descr() {
        return "remove_by_id id – удалить элемент коллекции по его id (если вы его владелец)\n";
    }
}
