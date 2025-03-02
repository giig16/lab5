package commands;

import managers.CollectionManager;

import java.time.format.DateTimeFormatter;

public class Info implements Command{
        private CollectionManager collectionManager;
        public Info(CollectionManager collectionManager){
            this.collectionManager = collectionManager;
        }
        public Info(){}
    public void execute(String argument) {
        String collectionType = "LinkedHashSet";
        String initTime = collectionManager.getInitTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        int size = collectionManager.getCities().size();
        System.out.println("Информация о коллекции:");
        System.out.println("Тип: " + collectionType);
        System.out.println("Дата инициализации: " + initTime);
        System.out.println("Количество элементов: " + size);
    }

    public String descr() {
        return "info – вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.) \n";
    }
}
