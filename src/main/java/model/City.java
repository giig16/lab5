package model;

import utility.Element;
import utility.Validatable;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Класс, представляющий город.
 */
public class City extends Element implements Comparable<City>, Validatable {
    private static int globalIDCounter = 1;

    private Integer id;
    private String name;
    private Coordinates coordinates;
    private ZonedDateTime creationDate;
    private Double area;
    private long population;
    private Long metersAboveSeaLevel;
    private ZonedDateTime establishmentDate;
    private Government government;
    private StandardOfLiving standardOfLiving;
    private Human governor;
    private String owner; // ➔ новое поле: владелец города (пользователь)

    // --- Конструкторы ---

    /**
     * Основной конструктор с указанием владельца.
     */
    public City(String name, Coordinates coordinates, ZonedDateTime creationDate, Double area, long population,
                Long metersAboveSeaLevel, ZonedDateTime establishmentDate, Government government,
                StandardOfLiving standardOfLiving, Human governor, String owner) {
        this.id = globalIDCounter++;
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
        this.owner = owner;
    }

    /** Пустой конструктор */
    public City() {}

    // --- Геттеры и сеттеры ---

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public static void setGlobalIDCounter(int value) {
        City.globalIDCounter = value;
    }

    public static int getGlobalIDCounter() {
        return globalIDCounter;
    }

    public String getName() {
        return name;
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

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    // --- Переопределённые методы ---

    @Override
    public boolean validate() {

        if (this.getId() == null || this.getId() <= 0) return false;
        if (name == null || name.isEmpty()) return false;
        if (coordinates == null|| !coordinates.validate()) return false;
        if (creationDate == null) return false;
        if (area == null || area <= 0) return false;
        if (population <= 0) return false;
        if (government == null) return false;
        if (standardOfLiving == null) return false;
        if (governor == null||governor.getAge()<=0) return false;
        return true;
    }

    @Override
    public int compareTo(City other) {
        int result = Double.compare(this.area, other.area);
        if (result == 0) result = Long.compare(this.population, other.population);
        if (result == 0) result = Long.compare(this.metersAboveSeaLevel, other.metersAboveSeaLevel);
        if (result == 0) result = this.coordinates.compareTo(other.coordinates);
        if (result == 0) result = this.governor.compareTo(other.governor);
        return result;
    }

    @Override
    public String toString() {
        return "City{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", coordinates=" + coordinates +
                ", creationDate=" + creationDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")) +
                ", area=" + area +
                ", population=" + population +
                ", metersAboveSeaLevel=" + metersAboveSeaLevel +
                ", establishmentDate=" + (establishmentDate != null ? establishmentDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")) : "N/A") +
                ", government=" + government +
                ", standardOfLiving=" + standardOfLiving +
                ", governor=" + governor +
                ", owner='" + owner + '\'' +
                '}';
    }
}
