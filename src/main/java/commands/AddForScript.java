package commands;

import managers.CollectionManager;
import model.City;

public class AddForScript implements Command{
    private CollectionManager collectionManager;
    public AddForScript(CollectionManager collectionManager){
        this.collectionManager = collectionManager;
    }
    public AddForScript(){}
    public CollectionManager getCollectionManager(){
        return collectionManager;
    }

    public String descr(){
        return "добавить элемент рандомный в коллекцию, но команда доступна только в скрипте";
    }

    @Override
    public void execute(String argument) {
        int value;
        try{value = Integer.parseInt(argument);
            if(value<=0){
                System.out.println("Ошибка: количество создаваемых городов должно быть положительным");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Ошибка: аргумент комманды должен быть целочисленным");
            return;
        }
        for(int i =0;i<value;i++){
            City city = collectionManager.createRandomCity();
            if(city.validate()){
                collectionManager.addToSet(city);
                System.out.println("Рандомный город"+(i+1)+ " добавлен в коллекцию");
            }else{
                System.out.println("Рандомный город"+(i+1)+ " не прошёл валидацию");
            }
        }

    }
}
