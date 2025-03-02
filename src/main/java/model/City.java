package model;



import utility.Element;
import utility.Validatable;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Класс, представляющий город.
 * Хранит информацию о названии, координатах, населении и т.д.
 */
public class City extends Element implements Comparable<City>,Validatable {
    private static int globalIDCounter = 1;
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

    /**
     * Основной конструктор, создающий {@code City} со всеми необходимыми полями.
     * Значение поля {@code id} генерируется автоматически на основе {@link #globalIDCounter}.
     *
     * @param name               Название города (не может быть {@code null} или пустым).
     * @param coordinates        Координаты города (не могут быть {@code null}).
     * @param creationDate       Дата и время создания (не может быть {@code null}).
     * @param area               Площадь города (не может быть {@code null} и должна быть больше 0).
     * @param population         Численность населения (должна быть больше 0).
     * @param metersAboveSeaLevel Высота над уровнем моря (может быть {@code null}).
     * @param establishmentDate  Дата основания города (может быть {@code null}).
     * @param government         Форма правления (не может быть {@code null}).
     * @param standardOfLiving   Уровень жизни (не может быть {@code null}).
     * @param governor           Губернатор (не может быть {@code null}; его возраст должен быть > 0).
     */
    public City(String name, Coordinates coordinates, ZonedDateTime creationDate, Double area, long population, Long metersAboveSeaLevel, ZonedDateTime establishmentDate, Government government, StandardOfLiving standardOfLiving, Human governor) {
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
    /**
     * Устанавливает глобальный счётчик ID (используется, если нужно подстроить нумерацию).
     * @param value Новое значение глобального счётчика.
     */
    public static void setGlobalIDCounter(int value) {
        City.globalIDCounter = value;
    }
    public static int getGlobalIDCounter(){
        return City.globalIDCounter;
    }
    /**
     * Сравнивает объекты City
     * @return 1, если первый больше, второго
     *  -1, если наоборот
     *  0, если объекты равны
     */
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


    /**
     * Валидирует правильность полей.
     * @return true, если все верно, иначе false
     */
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

    /**
     * возвращает всю информацию об объекте City
     * @return String
     */
    @Override
    public String toString(){
        return "City{" +
                "id=" + id.toString() +
                ", name='" + name.toString() + '\'' +
                ", coordinates=" + coordinates.toString() +
                ", creationDate=" + creationDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")) +
                ", area=" + area +
                ", population=" + population +
                ", metersAboveSeaLevel=" + metersAboveSeaLevel +
                ", establishmentDate=" + (establishmentDate != null ? establishmentDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")).toString() : "N/A") +
                ", government=" + government.toString() +
                ", standardOfLiving=" + standardOfLiving.toString() +
                ", age_of_governor=" + governor.toString() +
                '}';
    }

}

