package model;

import utility.Validatable;
/**
 * Класс, представляющий координаты в двумерном пространстве.
 * <p>
 * Содержит целочисленную координату {@code x} и вещественную координату {@code y}.
 * Максимальное значение поля {@code y} по условию  19,
 * однако фактически метод {@link #validate()} проверяет ограничение для {@code x} (x < 19).
 * <p>
 * Класс реализует интерфейс {@link Validatable}, позволяя проверить корректность значений координат.
 */
public class Coordinates implements Validatable  {
    private long x;
    private double y; //Максимальное значение поля: 19
    public Coordinates(long x,double y){
        this.x =x;
        this.y =y;
    }

    public long getX() {
        return x;
    }

    public double getY() {
        return y;
    }
    @Override
    public String toString(){
        return x + ";" + y;
    }
    public int compareTo(Coordinates coordinates){
        long thisDistanceFromStart = this.getX()*this.getX()+(long)(this.y*this.y);
        long coordinatesDistanceFromStart = coordinates.getX()* coordinates.getX()+(long)(coordinates.getY()* coordinates.getY());
        return Long.compare(thisDistanceFromStart,coordinatesDistanceFromStart);
    }
    @Override
    public boolean validate(){
        if(this.getX()>=19){return false;}
        else{return true;}
    }


}
