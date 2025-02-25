package commands;

public class Help implements Command{
    public String descr(){
        return "\"help — помощь, вывод справки по доступным командам и формату их использования\";";
    }

    public void execute(){
        System.out.println("показываем справку помощи ...");
    }
}
