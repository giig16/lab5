package managers;

import model.City;

import java.time.ZonedDateTime;
import java.util.*;



public class CollectionManager {
    private LinkedHashSet<City> cities = new LinkedHashSet<>();
    private  CSVManager csvManager;
    private final ZonedDateTime initTime;
    public CollectionManager(CSVManager csvManager){
        this.csvManager = csvManager;
        initTime = ZonedDateTime.now();
    }
    public ZonedDateTime getInitTime(){
        return initTime;
    }
    public  boolean addToSet (City city){
        cities.add(city);
        csvManager.writeInCollection(cities);
        return true;
    }
    public LinkedHashSet<City> getCities(){
        return cities;
    }

    public void printCities(){
        for(City c: cities){
            System.out.println(c.toString());
        }
    }


    public void setCities(LinkedHashSet<City> cities) {
        this.cities = cities;
    }


    public int isEmpty() {
        return cities.size();
    }

    public void clearCollection(){
        cities.clear();
    }




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
    private City findCityByName(String name) {
        for (City city : cities) {
            if (city.getName().equalsIgnoreCase(name)) {
                return city;
            }
        }
        return null;
    }

    private City findCityById(int id) {
        for (City city : cities) {
            if (city.getId().equals(id)) {
                return city;
            }
        }
        return null;
    }




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

    public void clearById(String deletedCity){
        int intDeletedCity = Integer.parseInt(deletedCity);
        City city =findCityById(intDeletedCity);
        cities.remove(city);
    }





}

