package commands;
/**
 * Команда "exit", завершающая работу программы без сохранения изменений в файл.
 * <p>
 * При выполнении команда выводит сообщение и вызывает {@code System.exit(0)},
 * тем самым немедленно останавливая выполнение приложения.
 */
public class Exit implements Command{
    public void execute(String argument){
        System.out.println("выход из программы... ");
        System.exit(0);
    }
    public String descr(){
        return "exit – завершить программу (без сохранения в файл) \n";
    }
}
