package managers;

import commands.*;
import java.util.HashMap;
import java.util.Scanner;

public class Invoker {
    private HashMap<String, Command> commands = new HashMap<>();
    private Scanner sc = new Scanner(System.in);

    private boolean isScriptExist = false;

    public Invoker(CollectionManager cm, FileManager fileManager, DBManager dbManager) {
        commands.put("help", new Help());
        commands.put("add", new Add(cm, this));
        commands.put("exit", new Exit());
        commands.put("show", new Show(cm));
        commands.put("info", new Info(cm));
        commands.put("update_by_id", new UpdateID(cm, dbManager));
        commands.put("remove_by_id", new RemoveByID(cm, dbManager));
        commands.put("clear", new Clear(cm, dbManager));
        commands.put("save", new Save(cm, fileManager)); // файл — если сохраняешь в CSV
        commands.put("execute_script", new ExecuteScriptFileName(this));
        commands.put("add_if_min", new AddIfMin(cm, dbManager));
        commands.put("remove_greater", new RemoveGreater(cm, dbManager));
        commands.put("remove_lower", new RemoveLower(cm, dbManager));
        commands.put("average_of_meters_above_sea_level", new AverageOfMetersAboveSeaLevel(cm));
        commands.put("group_counting_by_area", new GroupCountingByArea(cm));
        commands.put("print_unique_meters_above_sea_level", new PrintUniqueMetersAboveSeaLevel(cm));
    }



    public boolean getScript() {
        return isScriptExist;
    }

    public void setScriptExistion(boolean isScriptExist) {
        this.isScriptExist = isScriptExist;
    }

    public void processRunner(String input) {
        String[] parts = input.trim().split(" ", 2);
        String commandName = parts[0];
        String argument = parts.length > 1 ? parts[1] : null;

        Command command = commands.get(commandName);

        if (command != null) {
            command.execute(argument);
        } else {
            System.out.println("Ошибка: неизвестная команда '" + commandName + "'.");
        }
    }
}
