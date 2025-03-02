package commands;

public class Exit implements Command{
    public void execute(String argument){
        System.out.println("выход из программы... ");
        System.exit(0);
    }
    public String descr(){
        return "exit – завершить программу (без сохранения в файл) \n";
    }
}
