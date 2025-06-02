package commands;

import managers.CollectionManager;
import managers.Invoker;
import model.City;

public class Add implements Command {
    private CollectionManager collectionManager;
    private Invoker invoker;
    private String currentUser;

    public Add(CollectionManager collectionManager, Invoker invoker, String currentUser) {
        this.collectionManager = collectionManager;
        this.invoker = invoker;
        this.currentUser = currentUser;

    }

    public Add() {}

    @Override
    public String descr() {
        return "add {element} – добавить новый элемент в коллекцию \n";
    }

    @Override
    public void execute(String argument) {
        boolean isScriptUsed = invoker.getScript();

        if (isScriptUsed && argument != null && argument.contains(",")) {
            City parsedCity = collectionManager.parseCityFromScript(argument, currentUser);
            if (parsedCity != null && parsedCity.validate()) {
                boolean success = collectionManager.addToSet(parsedCity);
                if (success) {
                    System.out.println("Город успешно добавлен из скрипта!");
                } else {
                    System.out.println("Ошибка добавления города из скрипта в базу данных. Введите следующую команду.");
                }
            } else {
                System.out.println("Ошибка: не удалось распарсить или валидировать город из скрипта. Введите следующую команду.");
            }
            return;
        }

        if (!isScriptUsed && (argument == null || argument.isEmpty())) {
            while (true) {
                City city = collectionManager.createCity(currentUser);
                if (city.validate()) {
                    boolean success = collectionManager.addToSet(city);
                    if (success) {
                        System.out.println("Город добавлен в коллекцию");
                        break;
                    } else {
                        System.out.println("Ошибка при добавлении города в базу данных. Введите следующую команду.");
                        break;
                    }
                } else {
                    System.out.println("Город не прошёл валидацию. Введите следующую команду.");
                    break;
                }
            }
            return;
        }

        try {
            int value = Integer.parseInt(argument);
            if (value <= 0) {
                System.out.println("Ошибка: количество создаваемых городов должно быть положительным числом. Введите следующую команду.");
                return;
            }
            for (int i = 0; i < value; i++) {
                City city = collectionManager.createRandomCity(currentUser);
                if (city.validate()) {
                    boolean success = collectionManager.addToSet(city);
                    if (success) {
                        System.out.println("Рандомный город " + (i + 1) + " добавлен в коллекцию");
                    } else {
                        System.out.println("Ошибка добавления рандомного города " + (i + 1) + " в базу данных. Введите следующую команду.");
                    }
                } else {
                    System.out.println("Рандомный город " + (i + 1) + " не прошёл валидацию. Введите следующую команду.");
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("Ошибка: аргумент команды должен быть целым числом или списком параметров через запятую. Введите следующую команду.");
        }
    }
}
