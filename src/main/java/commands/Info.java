package commands;

public class Info implements Command{

    public void execute(String argument) {

    }

    public String descr() {
        return "info – вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.) \n";
    }
}
