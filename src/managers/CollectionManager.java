package managers;

import model.City;
import java.util.LinkedHashSet;
import java.util.Scanner;

import static commands.Add.createCity2;

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
    public void createCity1(){
        Scanner sc = new Scanner(System.in);
        City city = createCity2(sc);
        addToSet(city);
        System.out.println("Город добавлен в коллекцию");
    }
}
