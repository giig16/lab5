package model;

import utility.Validatable;
/**
 * Класс, представляющий координаты в двумерном пространстве.
 * <p>
 * Содержит целочисленную координату {@code x} и вещественную координату {@code y}.
 * Максимальное значение поля {@code y} по условию  19,
 * однако фактически метод {@link #validate()} проверяет ограничение для {@code x} (x  19).
 * <p>
 * Класс реализует интерфейс {@link Validatable}, позволяя проверить корректность значений координат.
 */
public class Coordinates implements Validatable  {
    /**Поле координаты x*/
    private long x;
    /**Поле координаты y*/
    private double y; //Максимальное значение поля: 19
    /**Конструктор*/
    public Coordinates(long x,double y){
        this.x =x;
        this.y =y;
    }
    /**Геттер координаты x*/
    public long getX() {
        return x;
    }
    /**Геттер координаты y*/
    public double getY() {
        return y;
    }
    /**Переопределённый toString*/
    @Override
    public String toString(){
        return x + ";" + y;
    }
    /**Метод сравнения двух координат*/
    public int compareTo(Coordinates coordinates){
        long thisDistanceFromStart = this.getX()*this.getX()+(long)(this.y*this.y);
        long coordinatesDistanceFromStart = coordinates.getX()* coordinates.getX()+(long)(coordinates.getY()* coordinates.getY());
        return Long.compare(thisDistanceFromStart,coordinatesDistanceFromStart);
    }
    /**Валидация*/
    @Override
    public boolean validate(){
        if(this.getX()>=19){return false;}
        else{return true;}
    }


}
