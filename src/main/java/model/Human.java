package model;
/**
 * Класс, представляющий человека с полем возраста.
 * <p>
 * Поле {@code age} должно быть больше 0.
 */
public class Human {
    private Long age; //Значение поля должно быть больше 0
    public Human(Long age){
        this.age = age;
    }

    public Long getAge() {
        return age;
    }
    public int compareTo(Human h){
        return Long.compare(this.getAge(),h.getAge());
    }
    @Override
    public String toString(){
        return getAge().toString();
    }
}
