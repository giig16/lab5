package utility;
/**
 * Интерфейс, определяющий метод валидации для объектов.
 * <p>
 * Классы, реализующие этот интерфейс, должны предоставлять логику проверки корректности своих полей.
 */
public interface Validatable {
    /** Метод валидации*/
    boolean validate();
}
