package commands;

import managers.CollectionManager;

import java.util.Scanner;

public class RemoveGreater implements Command{
    public String descr() {
        return "remove_greater {element} – удалить из коллекции все элементы, превышающие заданный \n";
    }

    private CollectionManager collectionManager;

    public RemoveGreater(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }
    public RemoveGreater(){}



    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите");
    }









}
