package model;
/**
 * Класс, представляющий человека с полем возраста.
 * <p>
 * Поле {@code age} должно быть больше 0.
 */
public class Human {
    /**Возраст*/
    private Long age; //Значение поля должно быть больше 0
    /**Конструктор*/
    public Human(Long age){
        this.age = age;
    }
    /**Геттер для возраста*/
    public Long getAge() {
        return age;
    }
    /**Сравнение объектов Human*/
    public int compareTo(Human h){
        return Long.compare(this.getAge(),h.getAge());
    }
    /**Переопределённый toString*/
    @Override
    public String toString(){
        return getAge().toString();
    }
}
