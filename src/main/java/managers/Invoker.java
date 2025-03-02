package managers;

import commands.Add;
import commands.*;


import java.util.HashMap;
import java.util.Scanner;

public class Invoker {
    public static HashMap<String, Command> commands = new HashMap<>();
    public Invoker(CollectionManager cm, CSVManager csvManager){
    commands.put("help", new Help());
    commands.put("add", new Add(cm));
    commands.put("exit", new Exit());
    commands.put("show", new Show(cm));
    commands.put("info", new Info());
    commands.put("update_id", new UpdateID());
    commands.put("remove_by_id", new RemoveByID(cm));
    commands.put("clear", new Clear(cm));
    commands.put("save", new Save());
    commands.put("execute_script file_name", new ExecuteScriptFileName());
    commands.put("add_if_min", new AddIfMin());
    commands.put("remove_greater", new RemoveGreater(cm));
    commands.put("remove_lower", new RemoveLower(cm));
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
        String[] parts = input.split(" ", 2);
        String commandName = parts[0]; // Название команды
        String argument = parts.length > 1 ? parts[1] : null; // Аргумент (если есть)

        Command command = commands.get(commandName);

        if (command != null) {
            if (argument != null) {
                command.execute(argument); // Передаём аргумент в команду
            } else {
                command.execute(argument); // Вызываем команду без аргументов
            }
        } else {
            System.out.println("Ошибка: неизвестная команда '" + commandName + "'.");
        }
    }



        /*Command command = commands.get(input);
        if(command!=null){
            command.execute();
        }else{
            System.out.println("введите правильное название комманды");
        }*/
}






