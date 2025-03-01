package commands;

import java.util.ArrayList;

public class Help implements Command{
    public String descr(){
        return "\"help — помощь, вывод справки по доступным командам и формату их использования\";";
    }

    public void execute(){
        System.out.println("Показываем справку помощи:");
        for (Command command : command) {
            command.execute();  // Вызываем execute() у каждого объекта
        }
    }

    private ArrayList<Command> command= new ArrayList<>();


    Command averageOfMetersAboveSeaLevel = new AverageOfMetersAboveSeaLevel();
    Command clear = new Clear();
    Command add=new Add();
    Command addIfMin=new AddIfMin();
    Command executeScriptFileName=new ExecuteScriptFileName();
    Command exit=new Exit();
    Command groupCountingByArea=new GroupCountingByArea();
    Command help=new Help();
    Command info=new Info();
    Command printUniqueMetersAboveSeaLevel=new PrintUniqueMetersAboveSeaLevel();
    Command removeById=new RemoveByID();
    Command removeGreater=new RemoveGreater();
    Command removeLower=new RemoveLower();
    Command save=new Save();
    Command show=new Show();
    Command updateID=new UpdateID();
}
