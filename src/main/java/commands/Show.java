package commands;

import managers.CollectionManager;
/**
 * Команда show, выводящая все элементы коллекции
 */
public class Show implements Command{
    /**Менеджер коллекции*/
    private CollectionManager collectionManager;
    /**Конструктор*/
    public Show(CollectionManager collectionManager){
        this.collectionManager = collectionManager;
    }
    /**Описание*/
    public String descr(){
        return "show - вывести все элементы коллекции \n";
    }
    /**Метод выполнения*/
    public void execute(String argument){
        if (collectionManager.isEmpty()==0){
            System.out.println("Коллекция пуста");}
        else{collectionManager.printCities();}
    }
    /**Пустой конструктор*/
    public Show(){}
}
