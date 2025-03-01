package commands;

public class Exit implements Command{
    public void execute(){
        System.out.println("выход из программы... ");
        System.exit(0);
    }
    public String descr(){
        return "exit – завершить программу (без сохранения в файл) \n";
    }
}
