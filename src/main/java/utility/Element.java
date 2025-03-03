package utility;
/**
 * Абстрактный класс, представляющий базовый элемент с уникальным идентификатором.
 * <p>
 * Хранит поле {@code id} для конкретного элемента.
 */
public abstract class Element{
        /**Поле id*/
        protected Integer id;
        /**Геттер для id*/
        public Integer getId(){
            return id;
        }
}
