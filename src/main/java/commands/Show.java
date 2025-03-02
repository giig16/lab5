package commands;

import managers.CollectionManager;

public class Show implements Command{
    private CollectionManager collectionManager;
    public Show(CollectionManager collectionManager){
        this.collectionManager = collectionManager;
    }
    public String descr(){
        return "show - вывести все элементы коллекции \n";
    }
    public void execute(){
        if (collectionManager.isEmpty()==0){
            System.out.println("Коллекция пуста");}
        else{collectionManager.printCities();}
    }
    public Show(){}
}
