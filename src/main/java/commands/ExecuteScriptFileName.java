package commands;

public class ExecuteScriptFileName implements Command{
    public void execute(String argument) {

    }

    public String descr() {
        return "execute_script file_name – считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме. \n";
    }
}
