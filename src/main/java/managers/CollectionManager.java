package managers;

import model.City;
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
}
