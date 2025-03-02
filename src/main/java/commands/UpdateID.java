package commands;

public class UpdateID implements Command{
    public void execute(String argument) {

    }

    public String descr() {
        return "update id {element} – обновить значение элемента коллекции, id которого равен заданному \n";
    }
}
