package managers;

import model.City;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Scanner;

import static commands.Add.createCity1;

public class CollectionManager {
    private LinkedHashSet<City> cities = new LinkedHashSet<>();
    private  CSVManager csvManager;
    public CollectionManager(CSVManager csvManager){
        this.csvManager = csvManager;
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
        while (iterator.hasNext()){
            City nextCity= iterator.next();
            if (nextCity.compareTo(city)>0){
                iterator.remove();
                exist = true;
                System.out.println("Удален город " + city.getName());
            }
        }
        if (!exist){
            System.out.println("Нет городов превышающих " + city.getName());
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





}

