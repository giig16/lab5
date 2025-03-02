package commands;

import managers.CollectionManager;
/**
 * Команда show, выводящая все элементы коллекции
 */
public class Show implements Command{
    private CollectionManager collectionManager;
    public Show(CollectionManager collectionManager){
        this.collectionManager = collectionManager;
    }
    public String descr(){
        return "show - вывести все элементы коллекции \n";
    }
    public void execute(String argument){
        if (collectionManager.isEmpty()==0){
            System.out.println("Коллекция пуста");}
        else{collectionManager.printCities();}
    }
    public Show(){}
}
