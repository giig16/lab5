package managers;

import model.*;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

/**
 * Класс, управляющий коллекцией объектов {@link City}.
 */
public class CollectionManager {
    private LinkedHashSet<City> cities = new LinkedHashSet<>();
    private final ZonedDateTime initTime;
    private DBManager dbManager;
    //private String currentUser;

    public CollectionManager(DBManager dbManager) {
        this.dbManager = dbManager;
        this.initTime = ZonedDateTime.now();
        loadCollection();
    }

    private void loadCollection() {
        cities = dbManager.loadAllCities();
        System.out.println("Коллекция успешно загружена из базы данных.");
    }

    public void loadCollectionFromDatabase() {
        try {
            cities = dbManager.loadAllCities();
            System.out.println("Коллекция успешно загружена из базы данных. Загружено " + cities.size() + " городов.");
        } catch (Exception e) {
            System.out.println("Ошибка загрузки коллекции: " + e.getMessage());
        }
    }

    /*public void setCurrentUser(String username) {
        this.currentUser = username;
    }
    public String getCurrentUser() {
        return currentUser;
    }**/


    public ZonedDateTime getInitTime() {
        return initTime;
    }

    public LinkedHashSet<City> getCities() {
        return cities;
    }

    public boolean addToSet(City city) {
        try {
            int newId = dbManager.add(city);
            city.setId(newId);
            cities.add(city);
            return true;
        } catch (SQLException e) {
            System.out.println("Ошибка добавления города: " + e.getMessage());
            return false;
        }
    }

    public void printCities() {
        for (City city : cities) {
            System.out.println(city);
        }
    }

    public int isEmpty() {
        return cities.size();
    }

    public void clearCollection() {
        dbManager.clear();
        cities.clear();
        System.out.println("Коллекция успешно очищена.");
    }

    public City findCityByName(String name) {
        for (City city : cities) {
            if (city.getName().equalsIgnoreCase(name)) {
                return city;
            }
        }
        return null;
    }

    public City findCityById(int id) {
        for (City city : cities) {
            if (city.getId() == id) {
                return city;
            }
        }
        return null;
    }

    public void clearCollectionGreater(String refCity) {
        City city = findCityByName(refCity);
        if (city == null) {
            System.out.println("Город с таким названием не найден.");
            return;
        }
        List<Integer> idsToDelete = new ArrayList<>();
        for (City c : cities) {
            if (c.compareTo(city) > 0) {
                idsToDelete.add(c.getId());
            }
        }
        for (Integer id : idsToDelete) {
            dbManager.removeById(id);
        }
        cities.removeIf(c -> idsToDelete.contains(c.getId()));
    }

    public void clearCollectionLower(String refCity) {
        City city = findCityByName(refCity);
        if (city == null) {
            System.out.println("Город с таким названием не найден.");
            return;
        }
        List<Integer> idsToDelete = new ArrayList<>();
        for (City c : cities) {
            if (c.compareTo(city) < 0) {
                idsToDelete.add(c.getId());
            }
        }
        for (Integer id : idsToDelete) {
            dbManager.removeById(id);
        }
        cities.removeIf(c -> idsToDelete.contains(c.getId()));
    }

    public void clearById(String idStr) {
        if (idStr == null || !idStr.matches("\\d+")) {
            System.out.println("Введите корректный id города");
            return;
        }
        int id = Integer.parseInt(idStr);
        dbManager.removeById(id);
        cities.removeIf(c -> c.getId() == id);
        System.out.println("Город с id " + id + " удалён.");
    }

    public boolean clearForUpdateById(String idStr) {
        if (idStr == null || !idStr.matches("\\d+")) {
            System.out.println("Введите корректный id города");
            return false;
        }
        int id = Integer.parseInt(idStr);
        City city = findCityById(id);
        if (city == null) {
            System.out.println("Город с id " + id + " не найден.");
            return false;
        }
        cities.remove(city);
        return true;
    }

    public boolean toCompare(City refCity) {
        return cities.stream().anyMatch(c -> refCity.compareTo(c) < 0);
    }

    public void groupCitiesByArea() {
        Map<Double, Integer> groups = new HashMap<>();
        for (City city : cities) {
            groups.put(city.getArea(), groups.getOrDefault(city.getArea(), 0) + 1);
        }
        groups.forEach((area, count) -> System.out.println("Площадь: " + area + ", количество городов: " + count));
    }

    public void getUniqueMetersAboveSeaLevel() {
        cities.stream()
                .map(City::getMetersAboveSeaLevel)
                .distinct()
                .forEach(System.out::println);
    }

    public void getAverageMetersSeaLvl() {
        double average = cities.stream()
                .mapToLong(City::getMetersAboveSeaLevel)
                .average()
                .orElse(0);
        System.out.println(average);
    }



    public City createRandomCity(String currentUser) {
        String name = generateRandomName();
        Coordinates coordinates = new Coordinates((long) (Math.random() * 100), Math.random() * 100);
        ZonedDateTime creationDate = ZonedDateTime.now();
        double area = Math.random() * 1000 + 1;
        long population = (long) (Math.random() * 10000 + 1);
        long metersAboveSeaLevel = (long) (Math.random() * 500);
        ZonedDateTime establishmentDate = null;
        Government government = Government.valueOf(getRandomGovernment());
        StandardOfLiving sol = StandardOfLiving.valueOf(getRandomStandardOfLiving());
        Human governor = new Human((long) (Math.random() * 100 + 1));

        return new City(name, coordinates, creationDate, area, population, metersAboveSeaLevel, establishmentDate, government, sol, governor, currentUser);
    }

    public City createCity(String currentUser) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите название города:");
        String name = scanner.nextLine().trim();
        while (name.isEmpty()) {
            System.out.println("Название не может быть пустым:");
            name = scanner.nextLine().trim();
        }

        long x = askLong(scanner, "Введите координату x:");
        double y = askDouble(scanner, "Введите координату y:");
        double area = askDouble(scanner, "Введите площадь:");
        long population = askLong(scanner, "Введите население:");
        long metersAboveSeaLevel = askLong(scanner, "Введите высоту над уровнем моря:");

        ZonedDateTime establishmentDate = null;
        System.out.println("Введите дату основания (yyyy-MM-dd HH:mm) или оставьте пустым:");
        String estInput = scanner.nextLine().trim();
        if (!estInput.isEmpty()) {
            try {
                establishmentDate = LocalDateTime.parse(estInput, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")).atZone(ZoneId.systemDefault());
            } catch (DateTimeParseException e) {
                System.out.println("Неверный формат даты. Дата основания будет пропущена.");
            }
        }

        Government government = askEnum(scanner, Government.class, "Введите форму правления (ARISTOCRACY, STRATOCRACY, TELLUROCRACY):");
        StandardOfLiving sol = askEnum(scanner, StandardOfLiving.class, "Введите уровень жизни (VERY_HIGH, HIGH, NIGHTMARE):");
        long governorAge = askLong(scanner, "Введите возраст губернатора:");
        Human governor = new Human(governorAge);

        Coordinates coordinates = new Coordinates(x, y);
        return new City(name, coordinates, ZonedDateTime.now(), area, population, metersAboveSeaLevel, establishmentDate, government, sol, governor, currentUser);
    }

    public City parseCityFromScript(String scriptLine, String currentUser) {
        String[] params = scriptLine.trim().split(",");
        if (params.length < 9) {
            System.out.println("Недостаточно параметров для создания города.");
            return null;
        }
        try {
            String name = params[0].trim();
            double area = Double.parseDouble(params[1].trim());
            long population = Long.parseLong(params[2].trim());
            long coordX = Long.parseLong(params[3].trim());
            double coordY = Double.parseDouble(params[4].trim());
            long metersAboveSeaLevel = Long.parseLong(params[5].trim());
            Government government = Government.valueOf(params[6].trim().toUpperCase());
            StandardOfLiving sol = StandardOfLiving.valueOf(params[7].trim().toUpperCase());
            long governorAge = Long.parseLong(params[8].trim());

            return new City(
                    name,
                    new Coordinates(coordX, coordY),
                    ZonedDateTime.now(),
                    area,
                    population,
                    metersAboveSeaLevel,
                    null,
                    government,
                    sol,
                    new Human(governorAge),
                    currentUser
            );
        } catch (Exception e) {
            System.out.println("Ошибка при разборе города из скрипта: " + e.getMessage());
            return null;
        }
    }



    private static final String LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static final Random RANDOM = new Random();

    private static String generateRandomName() {
        StringBuilder name = new StringBuilder();
        for (int i = 0; i < 5; i++) {
            name.append(LETTERS.charAt(RANDOM.nextInt(LETTERS.length())));
        }
        return name.toString();
    }

    private static String getRandomGovernment() {
        String[] gov = {"ARISTOCRACY", "STRATOCRACY", "TELLUROCRACY"};
        return gov[RANDOM.nextInt(gov.length)];
    }

    private static String getRandomStandardOfLiving() {
        String[] sol = {"VERY_HIGH", "HIGH", "NIGHTMARE"};
        return sol[RANDOM.nextInt(sol.length)];
    }

    private static long askLong(Scanner scanner, String prompt) {
        System.out.println(prompt);
        while (true) {
            try {
                return Long.parseLong(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Введите целое число.");
            }
        }
    }

    private static double askDouble(Scanner scanner, String prompt) {
        System.out.println(prompt);
        while (true) {
            try {
                return Double.parseDouble(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Введите число с точкой.");
            }
        }
    }

    private static <T extends Enum<T>> T askEnum(Scanner scanner, Class<T> enumClass, String prompt) {
        System.out.println(prompt);
        while (true) {
            try {
                return Enum.valueOf(enumClass, scanner.nextLine().trim().toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println("Неверное значение. Повторите:");
            }
        }
    }
}
