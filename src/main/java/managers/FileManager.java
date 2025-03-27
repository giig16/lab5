package managers;

import model.City;

import java.util.LinkedHashSet;

public abstract class FileManager {
    protected String filePath;
    public FileManager(String filePath){
        this.filePath = filePath;
    };
    public abstract LinkedHashSet<City> readCollectionFromFile();
    public abstract void writeInCollection(LinkedHashSet<City> cities);
}
