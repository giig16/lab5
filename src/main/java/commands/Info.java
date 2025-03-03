package commands;

import managers.CollectionManager;
import java.time.format.DateTimeFormatter;
/**
 * Команда "info", выводящая информацию о коллекции:
 * <ul>
 *     <li>Тип коллекции</li>
 *     <li>Дата инициализации (формат {@code yyyy-MM-dd HH:mm})</li>
 *     <li>Количество элементов</li>
 * </ul>
 */
public class Info implements Command{
    /**Менеджер коллекции*/
    private CollectionManager collectionManager;
    /**Конструктор*/
    public Info(CollectionManager collectionManager){
            this.collectionManager = collectionManager;
        }
        /**Пустой конструктор*/
        public Info(){}
    /**Метод выполнения*/
    public void execute(String argument) {
        String collectionType = "LinkedHashSet";
        String initTime = collectionManager.getInitTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        int size = collectionManager.getCities().size();
        System.out.println("Информация о коллекции:");
        System.out.println("Тип: " + collectionType);
        System.out.println("Дата инициализации: " + initTime);
        System.out.println("Количество элементов: " + size);
    }
    /**Описание*/
    public String descr() {
        return "info – вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.) \n";
    }
}
