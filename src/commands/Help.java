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


    Command help = new Help();
    Command exit = new Exit();
    Command =new ();
    Command =new ();
    Command =new ();
    Command =new ();
    Command =new ();
    Command =new ();
    Command =new ();
    Command =new ();
    Command =new ();
    Command =new ();
    Command =new ();
    Command =new ();
    Command =new ();
    Command =new ();


}
