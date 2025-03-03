package managers;

import model.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;


/**
 * Класс, управляющий коллекцией объектов {@link City}.
 * <p>
 * Хранит {@code LinkedHashSet<City>} и обеспечивает операции добавления,
 * удаления и поиска городов. Также ведёт учёт времени инициализации.
 */
public class CollectionManager {
    /**
     * Основная коллекция, содержащая объекты {@link City}.
     */
    private LinkedHashSet<City> cities = new LinkedHashSet<>();
    /**Менеджер csv*/
    private  CSVManager csvManager;
    /**Время инициализации коллекции*/
    private final ZonedDateTime initTime;
    /**Конструктор*/
    public CollectionManager(CSVManager csvManager){
        this.csvManager = csvManager;
        initTime = ZonedDateTime.now();
    }
    /**Геттер для времени инициализации*/
    public ZonedDateTime getInitTime(){
        return initTime;
    }
    /**
     * Добавляет объект {@link City} в коллекцию и сразу сохраняет изменения в CSV.
     *
     * @param city объект {@link City} для добавления
     * @return {@code true}, если город был успешно добавлен
     */
    public  boolean addToSet (City city){
        cities.add(city);
        csvManager.writeInCollection(cities);
        return true;
    }
    /**Геттер для коллекции*/
    public LinkedHashSet<City> getCities(){
        return cities;
    }

    /**Метод, печатающий коллекцию*/
    public void printCities(){
        for(City c: cities){
            System.out.println(c.toString());
        }
    }

    /**Сеттер для коллекции*/
    public void setCities(LinkedHashSet<City> cities) {
        this.cities = cities;
    }

    /**Возвращает размер коллекции*/
    public int isEmpty() {
        return cities.size();
    }
    /**Очищает коллекцию*/
    public void clearCollection(){
        cities.clear();
    }



    /**Очищает все элементы коллекции, которые круче чем переданный в параметры*/
    public void clearCollectionGreater(String refCity){
        City city = findCityByName(refCity);


        if (city == null) {
            System.out.println("Ошибка: Город с названием '" + refCity + "' не найден.");
            return;
        }

        Iterator<City> iterator = cities.iterator();
        boolean exist = false;

        List<String> delitedCities = new ArrayList<>();

        while (iterator.hasNext()){
            City nextCity= iterator.next();
            if (nextCity.compareTo(city)>0){
                iterator.remove();
                delitedCities.add(nextCity.getName());
                exist = true;
                System.out.println("Удалены все города превышающие " + city.getName());
            }
        }
        if (!exist){
            System.out.println("Нет городов превышающих " + city.getName());
        }else{
            System.out.println("(Вот эти  – " + String.join(", ", delitedCities)+")");
        }



    }
    /**Влзвращает city по имени*/
    private City findCityByName(String name) {
        for (City city : cities) {
            if (city.getName().equalsIgnoreCase(name)) {
                return city;
            }
        }
        return null;
    }
    /**Возвращает city по id*/
    private City findCityById(int id) {
        for (City city : cities) {
            if (city.getId().equals(id)) {
                return city;
            }
        }
        return null;
    }



    /**Очищает все элементы коллекции, которые [e;t чем переданный в параметры*/
    public void clearCollectionLower(String refCity){
        City city = findCityByName(refCity);


        if (city == null) {
            System.out.println("Ошибка: Город с названием '" + refCity + "' не найден.");
            return;
        }

        Iterator<City> iterator = cities.iterator();
        boolean exist = false;

        List<String> delitedCities = new ArrayList<>();

        while (iterator.hasNext()){
            City nextCity= iterator.next();
            if (nextCity.compareTo(city)<0){
                iterator.remove();
                delitedCities.add(nextCity.getName());
                exist = true;
                System.out.println("Удалены все города меньшие чем " + city.getName());
            }
        }
        if (!exist){
            System.out.println("Нет городов меньше чем " + city.getName());
        }else{
            System.out.println("(Вот эти  – " + String.join(", ", delitedCities)+")");
        }
    }
    /**Удаляет из коллекции объект по id*/
    public void clearById(String deletedCity){
        if (deletedCity == null){
            System.out.println("Ошибка: введите название города");
        }else{
        int intDeletedCity = Integer.parseInt(deletedCity);
        City city =findCityById(intDeletedCity);

            if (city == null) {
                System.out.println("Ошибка: Город с id '" + deletedCity + "' не найден.");
                return;
            }
        cities.remove(city);
        System.out.println("Удален город - "+deletedCity);}


    }
    /**Метод для удаления объекта для его обновления*/
    public void clearForUpdateById(String deletedCity){
        if (deletedCity == null){
            System.out.println("Ошибка: введите название города");
        }else{
            int intDeletedCity = Integer.parseInt(deletedCity);
            City city =findCityById(intDeletedCity);

            if (city == null) {
                System.out.println("Ошибка: Город с id '" + deletedCity + "' не найден.");
                return;
            }
            cities.remove(city);
            System.out.println("Можете обновить город '"+deletedCity+"'");}


    }


    /**Метод сравнения*/
    public boolean toCompare(City refCity){
        boolean smallerExist = false;
        for (City city:cities){
            if(refCity.compareTo(city)<0){
                smallerExist=true;
            }
        }
        return smallerExist;
    }

    /**Группировка объектов по площади*/
    public void groupCitiesByArea() {
        Map<Double, Integer> groupsByArea = new HashMap<>();


        for (City city : cities) {
            double area = city.getArea();
            groupsByArea.put(area, groupsByArea.getOrDefault(area, 0) + 1);
        }

        for (Map.Entry<Double, Integer> group : groupsByArea.entrySet()) {
            System.out.println("Площадь: " + group.getKey() + "\nКоличество городов – " + group.getValue());
        }
    }

    /**Геттер для уникальной абсолютной высоты*/
    public void getUniqueMetersAboveSeaLevel(){

        /*Set<Long> uniqueMeters = new HashSet<>();

        for (City city : cities) {
            uniqueMeters.add(city.getMetersAboveSeaLevel()); // Добавляем высоту в Set
        }

        System.out.println("Уникальные значения metersAboveSeaLevel:");
        for (Long height : uniqueMeters) {
            System.out.println(height);
        }*/


        cities.stream()
                .map(City::getMetersAboveSeaLevel)
                .distinct()
                .forEach(System.out::println);

    }
    /**Возвращает среднее значение абсолютной высоты*/
    public void getAverageMetersSeaLvl(){
        double average =cities.stream()
                .mapToLong(City::getMetersAboveSeaLevel)
                .average()
                .orElse(0);
        System.out.println(average);

    }

    /**Метод создания города*/
    public City createCity() {
        Scanner scanner = new Scanner(System.in);
        // name
        System.out.println("Введите название города...");
        String name = scanner.nextLine().trim();
        while (name.isEmpty()) {
            System.out.println("Название не может быть пустым.\nВведите название города...");
            name = scanner.nextLine().trim();
        }

        // coordinates
        System.out.println("Введите координату x (число):");
        long x;
        while (true) {
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                System.out.println("Ошибка: x не может быть пустым. Повторите ввод:");
                continue;
            }
            try {
                x = Long.parseLong(input);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: x должна быть числом. Повторите ввод:");
            }
        }

        System.out.println("Введите координату y (число):");
        double y;
        while (true) {
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                System.out.println("Ошибка: y не может быть пустым. Повторите ввод:");
                continue;
            }
            try {
                y = Double.parseDouble(input);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: y должна быть числом. Повторите ввод:");
            }
        }
        Coordinates coordinates = new Coordinates(x, y);

        //creationDate
        ZonedDateTime creationDate = ZonedDateTime.now();

        // area
        System.out.println("Введите площадь города (число):");
        Double area = null;
        while (true) {
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                System.out.println("Ошибка: Площадь не может быть пустой. Повторите ввод:");
                continue;
            }
            try {
                area = Double.parseDouble(input);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: Площадь должна быть числом. Повторите ввод:");
            }
        }

        // population
        System.out.println("Введите численность населения (число):");
        long population;
        while (true) {
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                System.out.println("Ошибка: Численность населения не может быть пустой. Повторите ввод:");
                continue;
            }
            try {
                population = Long.parseLong(input);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: Введите корректное целое число.");
            }
        }

        // metersAboveSeaLevel
        System.out.println("Введите абсолютную высоту города (число):");
        long metersAboveSeaLevel;
        while (true) {
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                System.out.println("Ошибка: Значение не может быть пустым. Повторите ввод:");
                continue;
            }
            try {
                metersAboveSeaLevel = Long.parseLong(input);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: Введите корректное число.");
            }
        }

        // establishmentDate
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        ZonedDateTime establishmentDate = null;
        System.out.println("Введите дату основания города в формате yyyy-MM-dd HH:mm или оставьте пустым:");
        while (true) {
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                break;
            }
            try {
                LocalDateTime lDT = LocalDateTime.parse(input, formatter);
                establishmentDate = lDT.atZone(ZoneId.systemDefault());
                break;
            } catch (DateTimeParseException e) {
                System.out.println("Ошибка: формат ввода неверен. Формат: yyyy-MM-dd HH:mm или пустая строка.");
            }
        }

        // Government
        Government government = null;
        while (government == null) {
            System.out.println("Введите форму правления (ARISTOCRACY, STRATOCRACY, TELLUROCRACY): ");
            String input = scanner.nextLine().trim().toUpperCase();
            try {
                government = Government.valueOf(input);
            } catch (IllegalArgumentException e) {
                System.out.println("Ошибка: нет такого варианта. Повторите ввод.\n");
            }
        }

        // StandardOfLiving
        StandardOfLiving standardOfLiving = null;
        while (standardOfLiving == null) {
            System.out.println("Введите уровень жизни (VERY_HIGH, HIGH, NIGHTMARE):");
            String input = scanner.nextLine().trim().toUpperCase();
            try {
                standardOfLiving = StandardOfLiving.valueOf(input);
            } catch (IllegalArgumentException e) {
                System.out.println("Ошибка: нет такого варианта. Повторите ввод.\n");
            }
        }


        // Governor
        System.out.println("Введите возраст губернатора (число):");
        Long age = null;
        while (true) {
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                System.out.println("Ошибка: возраст не может быть пустым. Повторите ввод:");
                continue;
            }
            try {
                age = Long.parseLong(input);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: введите корректное число.");

            }
        }
        Human governor = new Human(age);

        //  City
        City city = new City(
                name,
                coordinates,
                creationDate,
                area,
                population,
                metersAboveSeaLevel,
                establishmentDate,
                government,
                standardOfLiving,
                governor
        );


        return city;
    }

    private static final String LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static final Random RANDOM = new Random();


    public static String generateRandomName() {
        StringBuilder name = new StringBuilder();
        for (int i = 0; i < 5; i++) {
            name.append(LETTERS.charAt(RANDOM.nextInt(LETTERS.length())));
        }
        return name.toString();
    }


    private static final String[] government = {
            "ARISTOCRACY", "STRATOCRACY", "TELLUROCRACY"
    };

    public static String getRandomGovernment() {
        Random random = new Random();
        return government[random.nextInt(government.length)];
    }

    private static final String[] standardOfLiving = {
            "VERY_HIGH", "HIGH", "NIGHTMARE"
    };

    public static String getRandomStandartsOfLiving() {
        Random random = new Random();
        return standardOfLiving[random.nextInt(standardOfLiving.length)];
    }






    public City createRandomCity(){

        //name
        String name =generateRandomName();

        //coordinates
        double dobX = Math.random()*18;
        double dobY = Math.random()*18;
        int x =(int) dobX;
        int y = (int) dobY;
        Coordinates coordinates = new Coordinates(x, y);

        //creationDate
        ZonedDateTime creationDate = ZonedDateTime.now();

        //area
        double area = Math.random()*100;

        //population
        double dobPopulation = Math.random()*100;
        int population = (int) dobPopulation;

        //metersAboveSeaLevel
        double dobmetersAboveSeaLevel = Math.random()*100;
        long metersAboveSeaLevel = (int) dobmetersAboveSeaLevel;

        //establishmentDate
        ZonedDateTime establishmentDate =null;

        //government
        String government52=getRandomGovernment();
        Government government = Government.valueOf(government52);

        //standardOfLiving
        String standardOfLiving1=getRandomStandartsOfLiving();
        StandardOfLiving standardOfLiving =StandardOfLiving.valueOf(standardOfLiving1);

        //governor
        double dobGovernor = Math.random()*100;
        long age = (int) dobGovernor;
        Human governor = new Human(age);

        //Createcity
        City city = new City(
                name,
                coordinates,
                creationDate,
                area,
                population,
                metersAboveSeaLevel,
                establishmentDate,
                government,
                standardOfLiving,
                governor
        );
        return city;
    }



}



