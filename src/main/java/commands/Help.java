package commands;

import java.util.ArrayList;
import java.util.List;

/**
 * Команда "help", выводящая список всех доступных команд и их краткое описание.
 * <p>
 * Класс реализует интерфейс {@link Command} и хранит во внутреннем списке
 * ссылки на различные команды. При выполнении команды "help" выводятся
 * описания всех этих команд (результат их метода {@code descr()})
 */
public class Help implements Command {
    /**Описание*/
    public String descr() {
        return "\"help — помощь, вывод справки по доступным командам и формату их использования\";";
    }
    /**Метод выполнения*/
    public void execute(String argument) {
        System.out.println("Показываем справку помощи:");
        for (Command command : commands) {
            System.out.println(command.descr());
        }
    }
    /**Список комманд*/
    public ArrayList<Command> commands = new ArrayList<>();


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
    public ArrayList<Command> getCommands() { return commands; }


}