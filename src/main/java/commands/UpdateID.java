package commands;

import managers.CollectionManager;
import model.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
/**
 * Команда "update_id", позволяющая обновить существующий элемент коллекции по его {@code id}
 * <p>
 * Логика команды:
 * <ol>
 *     <li>Сначала удаляется элемент с переданным {@code id} (метод {@link CollectionManager#clearForUpdateById(String)})</li>
 *     <li>Затем пользователь заново вводит данные для нового {@link City}, который будет иметь тот же {@code id}</li>
 *     <li>Проверяется валидность нового объекта (через {@link City#validate()}) Если всё корректно, он добавляется в коллекцию</li>
 * </ol>
 */
public class UpdateID implements Command{
    /**Менеджер коллекции*/
    private CollectionManager collectionManager;
    /**Конструктор*/
    public UpdateID(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }
    /**Пустой конструктор*/
    public UpdateID(){}





    /**Метод исполнения команды*/
    public void execute(String argument) {
        collectionManager.clearForUpdateById(argument);
        if (argument == null || !argument.matches("\\d+")) {

        }else{
        try{
            int id = Integer.parseInt(argument);
        while (true) {
            City city = collectionManager.createCity();
            if (city.validate()) {
                collectionManager.addToSet(city);
                city.setId(id);
                //csvManager.writeInCollection(collectionManager.getCities());
                System.out.println("Город обновлен");
                break;
            } else {
                System.out.println("Город не прошёл валидацию. Повторите ввод.");
            }}}
        catch(NumberFormatException e){
            System.out.println("Ошибка: некорректный ввод");
        }
        }







    }

    /**Возвращает описание команды*/
    public String descr() {
        return "update_by_id id {element} – обновить значение элемента коллекции, id которого равен заданному \n";
    }
}
