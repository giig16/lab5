package commands;

import managers.Invoker;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Команда "execute_script", позволяющая считать и выполнить команды из указанного файла.
 * <p>
 * Файл должен содержать команды в формате обычного пользовательского ввода.
 * Каждая строка передаётся в метод {@link Invoker#processRunner(String)}.
 */
public class ExecuteScriptFileName implements Command {
    /** Вызывающий элемент */
    private Invoker invoker;

    /** Конструктор с параметром */
    public ExecuteScriptFileName(Invoker invoker) {
        this.invoker = invoker;
    }

    /** Пустой конструктор */
    public ExecuteScriptFileName() {}
    private static final Set<String> executedScripts = new HashSet<>();
    /** Выполнение команды */
    @Override
    public void execute(String argument) {
        if (argument == null || argument.trim().isEmpty()) {
            System.out.println("Ошибка: не указан путь к файлу.");
            return;
        }

        if (executedScripts.contains(argument)) {
            System.out.println("Скрипт уже выполняется: " + argument + " — рекурсивный вызов предотвращён.");
            return;
        }

        executedScripts.add(argument); // защита от повторного запуска

        File file = new File(argument);
        if (!file.exists() || !file.isFile()) {
            System.out.println("Ошибка: файл не найден или указан неверный путь -> " + argument);
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                invoker.processRunner(line.trim());
            }
        } catch (IOException e) {
            System.out.println("Ошибка при чтении файла скрипта: " + e.getMessage());
        } finally {
            executedScripts.remove(argument); // чтобы можно было запустить снова потом
        }
    }

    /** Описание команды */
    @Override
    public String descr() {
        return "execute_script file_name – считать и исполнить скрипт из указанного файла. В скрипте команды указываются так же, как вводятся вручную.\n";
    }
}
