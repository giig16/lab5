import managers.CollectionManager;
import managers.Invoker;
import model.City;

import java.util.LinkedHashSet;
import java.util.Scanner;

public class Main{
       //public static LinkedHashSet<City> cities = new LinkedHashSet<>();
    public static void main(String[] args) {
        CollectionManager cm = new CollectionManager();
        Invoker invoker = new Invoker(cm);
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите комманду");
        while(sc.hasNextLine()){
            String input = sc.nextLine().trim();
            invoker.processRunner(input);
            System.out.println("Введите следующую комманду");
        }
    }
}