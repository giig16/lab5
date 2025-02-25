package managers;

import model.City;

import java.util.LinkedHashSet;

public class CollectionManager {
    private LinkedHashSet<City> cities = new LinkedHashSet<>();
    public CollectionManager(){
    }
    public  boolean addToSet (City city){
        cities.add(city);
        return true;
    }
    public LinkedHashSet<City> getCities(){
        return cities;
    }
}
