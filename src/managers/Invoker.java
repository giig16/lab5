package managers;

import commands.Add;
import commands.*;


import java.util.HashMap;
import java.util.Scanner;

public class Invoker {
    public static HashMap<String, Command> commands = new HashMap<>();
    public Invoker(CollectionManager cm){
    commands.put("help", new Help());
    commands.put("add", new Add(cm));
    commands.put("exit", new Exit());
    }

    Scanner sc = new Scanner(System.in);
    public void scanNext() {
        String line;
        while(sc.hasNextLine()){
            line = sc.nextLine();
            String[] tokens = line.split("\\s+");
        }
    }
    public void processRunner(String input){
        Command command = commands.get(input);
        if(command!=null){
            command.execute();
        }else{
            System.out.println("введите правильное название комманды");
        }
    }


}
