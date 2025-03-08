package managers;

import commands.Add;
import commands.*;
import java.util.HashMap;
import java.util.Scanner;
/**
 * Класс, отвечающий за хранение и вызов команд по их названиям.
 * <p>
 * Содержит {@code HashMap} строковых ключей (названий команд) и соответствующих объектов
 */
public class Invoker {
    /**Мапа команд*/
    public static HashMap<String, Command> commands = new HashMap<>();
    /**Конструктор*/
    public Invoker(CollectionManager cm, CSVManager csvManager){
    commands.put("help", new Help());
    commands.put("add", new Add(cm,this));
    commands.put("exit", new Exit());
    commands.put("show", new Show(cm));
    commands.put("info", new Info(cm));
    commands.put("update_id", new UpdateID(cm));
    commands.put("remove_by_id", new RemoveByID(cm));
    commands.put("clear", new Clear(cm));
    commands.put("save", new Save(cm,csvManager));
    commands.put("execute_script", new ExecuteScriptFileName(this));
    commands.put("add_if_min", new AddIfMin(cm));
    commands.put("remove_greater", new RemoveGreater(cm));
    commands.put("remove_lower", new RemoveLower(cm));
    commands.put("average_of_meters_above_sea_level", new AverageOfMetersAboveSeaLevel(cm));
    commands.put("group_counting_by_area", new GroupCountingByArea(cm));
    commands.put("print_unique_meters_above_sea_level", new PrintUniqueMetersAboveSeaLevel(cm));
    }
    /**Сканнер*/
    Scanner sc = new Scanner(System.in);
    public void scanNext() {
        String line;
        while(sc.hasNextLine()){
            line = sc.nextLine();
            String[] tokens = line.split("\\s+");
        }
    }




    /**Поле булеан, для понимания используется команда через скрипт или нет*/
    private boolean isScriptExist = false;

    /**Геттер использования скрипта*/
    public boolean getScript(){
        return isScriptExist;
    }
    /**Сеттер использования скрипта*/
    public void setScriptExistion(boolean isScriptExist){
        this.isScriptExist=isScriptExist;
    }















    /**
     * Основной метод, который разбирает строку на название команды и аргумент,
     * а затем вызывает соответствующую команду из {@link #commands}.
     * <p>
     * Формат строки: {@code "<commandName> <argument>"} (аргумент опционален).
     * Если команда найдена, вызывается {@code command.execute(argument)},
     * в противном случае выводится сообщение об ошибке
     *
     * @param input строка, введённая пользователем
     */
    public void processRunner(String input){
        String[] parts = input.split(" ", 2);
        String commandName = parts[0];
        String argument = parts.length > 1 ? parts[1] : null;

        Command command = commands.get(commandName);

        if (command != null) {
            if (argument != null) {
                command.execute(argument);
            } else {
                command.execute(argument);
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






