package managers;
import com.opencsv.*;
import model.*;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.ZonedDateTime;
import java.util.LinkedHashSet;
import java.util.List;

public class CSVManager {
    private String filePath;
    //private File file;
    public CSVManager(String filePath){
        this.filePath = filePath;
    }
    /*public CSVManager(File file){
        this.file = file;
    }*/
    public LinkedHashSet<City> readCollectionFromFile(){
            LinkedHashSet<City> cities = new LinkedHashSet<>();
            long maxId = 0;
            try(CSVReader reader = new CSVReader(new InputStreamReader(new FileInputStream(filePath), StandardCharsets.UTF_8))){
                List<String[]> lines = reader.readAll();
                for(String[] columns:lines) {
                    Integer id = Integer.parseInt(columns[0]);
                    String name = columns[1];
                    Coordinates coordinates = new Coordinates(Long.parseLong(columns[2]),Double.parseDouble(columns[3]));
                    ZonedDateTime creationDate = ZonedDateTime.parse(columns[4]);
                    Double area = Double.parseDouble(columns[5]);
                    long population = Long.parseLong(columns[6]);
                    Long metersAboveSeaLevel = Long.parseLong(columns[7]);
                    ZonedDateTime establishmentDate = null;
                    if(!columns[8].isEmpty()){
                     establishmentDate = ZonedDateTime.parse(columns[8]);
                    }
                    Government government = Government.valueOf(columns[9]);
                    StandardOfLiving standardOfLiving = StandardOfLiving.valueOf(columns[10]);
                    Human governor = new Human(Long.parseLong(columns[11]));
                    City city = new City(name,coordinates,creationDate,area,population,metersAboveSeaLevel,establishmentDate,government,standardOfLiving,governor);
                    if(id>maxId){
                        maxId = id;
                    }
                    city.setId(id);
                    cities.add(city);
                }
            }catch (FileNotFoundException e) {
                System.out.println("Файл не найден: " + filePath);

            } catch (IOException e) {
                System.out.println("Ошибка чтения: " + e.getMessage());

            } catch (Exception e) {
                System.out.println("Ошибка при парсинге CSV: " + e.getMessage());

            }
            int currentGlobalId = City.getGlobalIDCounter();
            if(currentGlobalId<=maxId){
            City.setGlobalIDCounter((int)(maxId+1));
            }
            return cities;
    }
    public void writeInCollection(LinkedHashSet<City> cities){
        try(CSVWriter writer = new CSVWriter(new OutputStreamWriter(new BufferedOutputStream(new FileOutputStream(filePath,false)),StandardCharsets.UTF_8))){
            for(City city:cities){
            String[] line = new String[]{
            String.valueOf(city.getId()),
            city.getName(),
            String.valueOf(city.getCoordinates().getX()),
            String.valueOf(city.getCoordinates().getY()),
            city.getCreationDate().toString(),
            String.valueOf(city.getArea()),
            String.valueOf(city.getPopulation()),
            String.valueOf(city.getMetersAboveSeaLevel()),
            city.getEstablishmentDate() == null ? "" : city.getEstablishmentDate().toString(),
            city.getGovernment().toString(),
            city.getStandardOfLiving().toString(),
            String.valueOf(city.getGovernor().getAge())};
            writer.writeNext(line);

            }
        }catch (IOException e) {
            System.out.println("Ошибка записи CSV: " + e.getMessage());
        }
    }



}
