package managers;

import model.City;

import java.time.ZonedDateTime;
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
}

