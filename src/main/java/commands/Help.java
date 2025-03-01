package commands;

import java.util.ArrayList;

public class Help implements Command {
    public String descr() {
        return "\"help — помощь, вывод справки по доступным командам и формату их использования\";";
    }

    public void execute() {
        System.out.println("Показываем справку помощи:");
        for (Command command : commands) {
            System.out.println(command.descr());
        }
    }

    private ArrayList<Command> commands = new ArrayList<>();


    {
        commands.add(new AverageOfMetersAboveSeaLevel());
        commands.add(new Clear());
        commands.add(new Add());
        commands.add(new AddIfMin());
        commands.add(new ExecuteScriptFileName());
        commands.add(new Exit());
        commands.add(new GroupCountingByArea());
        commands.add(new Info());
        commands.add(new PrintUniqueMetersAboveSeaLevel());
        commands.add(new RemoveByID());
        commands.add(new RemoveGreater());
        commands.add(new RemoveLower());
        commands.add(new Save());
        commands.add(new Show());
        commands.add(new UpdateID());
    }


}