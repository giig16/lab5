package commands;

public interface Command {
     void execute(String argument);
     String descr();
}
