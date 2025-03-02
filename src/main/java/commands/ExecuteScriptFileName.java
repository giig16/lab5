package commands;

import managers.Invoker;

import java.io.*;

public class ExecuteScriptFileName implements Command{
    private Invoker invoker;
    public ExecuteScriptFileName(Invoker invoker){
        this.invoker = invoker;
    }
    public ExecuteScriptFileName(){}
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

    public String descr() {
        return "execute_script file_name – считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме. \n";
    }
}
