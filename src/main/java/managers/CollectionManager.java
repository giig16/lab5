package managers;

import model.City;
import java.util.LinkedHashSet;
import java.util.Scanner;

import static commands.Add.createCity1;

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

    public void printCities(){
        for(City c: cities){
            System.out.println(c.toString());
        }
    }


    public int isEmpty() {
        return cities.size();
    }

    public void clearCollection(){
        cities.clear();
    }




    public void clearCollectionLower(City city){
        int a = city.getId();
        for (City city1 : cities){

        }
    }




}

