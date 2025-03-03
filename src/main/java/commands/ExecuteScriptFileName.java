package commands;

import managers.Invoker;

import java.io.*;
/**
 * Команда "execute_script", позволяющая считать и выполнить набор команд из указанного файла.
 * <p>
 * Файл должен содержать строки с командами в том же формате,
 * как если бы пользователь вводил их интерактивно.
 * Каждая строка передаётся в метод {@link Invoker#processRunner(String)}
 */
public class ExecuteScriptFileName implements Command{
    /**Вызывающий элемент*/
    private Invoker invoker;
    /**Конструктор*/
    public ExecuteScriptFileName(Invoker invoker){
        this.invoker = invoker;
    }
    /**Пустой конструктор*/
    public ExecuteScriptFileName(){}
    /**Выполнение*/
    @Override
    public void execute(String argument) {
        try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(argument)))){
            String line;
            while((line = bufferedReader.readLine())!=null){
                invoker.processRunner(line.trim());
            }
        } catch (IOException e) {
            System.out.println("Ошибка при чтении скрипта "+ e.getMessage());
        }
    }

    /**Описание*/
    public String descr() {
        return "execute_script file_name – считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме. \n";
    }
}
