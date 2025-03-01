package commands;

public class RemoveGreater implements Command{
    public void execute() {

    }

    public String descr() {
        return "remove_greater {element} – удалить из коллекции все элементы, превышающие заданный \n";
    }
}
