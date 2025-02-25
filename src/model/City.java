package model;



import utility.Element;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;


public class City  implements Comparable<City>  {
    private Integer id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private java.time.ZonedDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Double area; //Значение поля должно быть больше 0, Поле не может быть null
    private long population; //Значение поля должно быть больше 0
    private Long metersAboveSeaLevel;
    private java.time.ZonedDateTime establishmentDate;
    private Government government; //Поле не может быть null
    private StandardOfLiving standardOfLiving; //Поле не может быть null
    private Human governor; //Поле не может быть null


    public City(Integer id, String name, Coordinates coordinates, ZonedDateTime creationDate, Double area, long population, Long metersAboveSeaLevel, ZonedDateTime establishmentDate, Government government, StandardOfLiving standardOfLiving, Human governor) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.area = area;
        this.population = population;
        this.metersAboveSeaLevel = metersAboveSeaLevel;
        this.establishmentDate = establishmentDate;
        this.government = government;
        this.standardOfLiving = standardOfLiving;
        this.governor = governor;
    }
    public City(){}

    public Integer getId() {
        return id;
    }
    public String getName(){
        return  name;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public ZonedDateTime getCreationDate() {
        return creationDate;
    }

    public Double getArea() {
        return area;
    }

    public long getPopulation() {
        return population;
    }

    public Long getMetersAboveSeaLevel() {
        return metersAboveSeaLevel;
    }

    public ZonedDateTime getEstablishmentDate() {
        return establishmentDate;
    }

    public Government getGovernment() {
        return government;
    }

    public StandardOfLiving getStandardOfLiving() {
        return standardOfLiving;
    }

    public Human getGovernor() {
        return governor;
    }

    @Override
    public int compareTo(City city){
        int result = Double.compare(this.getArea(),city.getArea());
        if(result == 0){
            result = Long.compare(this.getPopulation(),city.getPopulation());
        }
        if(result ==0){
            result = Long.compare(this.getMetersAboveSeaLevel(), city.getMetersAboveSeaLevel());
        }
        if(result == 0){
            result = getCoordinates().compareTo(city.getCoordinates());
        }
        if(result ==0){
            result = getGovernor().compareTo(city.getGovernor());
        }
        return result;
    }

    @Override
    public String toString(){
        return "city{\"id\": " + id + ", " +
                "\"name\": \"" + name + "\", " +
                "\"coordinates\": \"" + coordinates + "\", " +
                "\"creationDate\" = \"" + creationDate.format(DateTimeFormatter.ISO_DATE_TIME) + "\", " +
                "\"area\": " + area  + ", " +
                "\"population\" = \"" + population + "\", " +
                "\"metersAboveSeaLevel\": \"" + metersAboveSeaLevel + "\", " +
                "\"establishmentDate\": \"" + establishmentDate + "\", "+
                "\"government\": \"" + government + "\", " +
                "\"standardOfLiving\": \""  + standardOfLiving + "\", " +
                "\"governor\": \"" + governor + "\", "  ;
    }

}

