package commands;
/**
 * Интерфейс, описывающий базовую структуру любой команды,
 * которую можно выполнить в системе.
 */
public interface Command {
     /**
      * Выполняет команду с заданным строковым аргументом.
      * <p>
      * Каждая реализация команды сама решает, как использовать этот аргумент.
      * Если команде не нужны данные, аргумент может игнорироваться.
      *
      * @param argument строковый аргумент команды (может быть {@code null} или пустым)
      */
     void execute(String argument);
     /**
      * Возвращает краткое текстовое описание (или справку) для команды.
      *
      * @return строка, описывающая назначение и формат использования команды
      */
     String descr();
}
