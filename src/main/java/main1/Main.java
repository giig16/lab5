package main1;


import managers.CSVManager;

import managers.CollectionManager;
import managers.Invoker;
import model.City;

import java.io.File;
import java.util.LinkedHashSet;
import java.util.Scanner;

public class Main{
       //public static LinkedHashSet<City> cities = new LinkedHashSet<>();
    public static void main(String[] args) {
        String filePath = "src/main/resources/cities.csv";
        //File csvFile = new File("/Users/marknorkin/Desktop/java/lab5/cities.csv");
        CSVManager csvManager = new CSVManager(filePath);
        CollectionManager cm = new CollectionManager(csvManager);

        Invoker invoker = new Invoker(cm,csvManager);
        Scanner sc = new Scanner(System.in);
        LinkedHashSet<City> initialSet = csvManager.readCollectionFromFile();
        cm.setCities(initialSet);
        System.out.println(new File(filePath).getAbsolutePath());
        System.out.println("Введите комманду");

        while(sc.hasNextLine()){
            String input = sc.nextLine().trim();
            invoker.processRunner(input);
            System.out.println("Введите следующую комманду");


        }
    }
}