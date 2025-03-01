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
    commands.put("show", new Show(cm));
    commands.put("info", new Info());
    commands.put("update_id", new UpdateID());
    commands.put("remove_by_id", new RemoveByID());
    commands.put("clear", new Clear(cm));
    commands.put("save", new Save());
    commands.put("execute_script file_name", new ExecuteScriptFileName());
    commands.put("add_if_min", new AddIfMin());
    commands.put("remove_greater", new RemoveGreater());
    commands.put("remove_lower", new RemoveLower());
    commands.put("average_of_meters_above_sea_level", new AverageOfMetersAboveSeaLevel());
    commands.put("group_counting_by_area", new GroupCountingByArea());
    commands.put("print_unique_meters_above_sea_level", new PrintUniqueMetersAboveSeaLevel());
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
