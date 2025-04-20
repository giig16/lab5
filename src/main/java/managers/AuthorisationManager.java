package managers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class AuthorisationManager {

    public boolean authorize() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("У вас уже есть аккаунт? (да/нет)");
        String answer = scanner.nextLine().trim().toLowerCase();

        if (answer.equals("да")) {
            return login(scanner);
        } else if (answer.equals("нет")) {
            register(scanner);
            return login(scanner);
        } else {
            System.out.println("Непонятный ответ. Доступ запрещён.");
            return false;
        }
    }

    private boolean login(Scanner scanner) {
        System.out.println("Введите логин:");
        String login = scanner.nextLine();

        System.out.println("Введите пароль:");
        String password = scanner.nextLine();

        String sql = "SELECT * FROM Users WHERE username = ? AND password = ?";

        try (Connection conn = DBManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, login);
            pstmt.setString(2, password);

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                System.out.println("Авторизация успешна! Добро пожаловать, " + login + "!");
                return true;
            } else {
                System.out.println("Неверный логин или пароль. Доступ запрещён.");
                return false;
            }

        } catch (SQLException e) {
            System.out.println("Ошибка при авторизации: " + e.getMessage());
            return false;
        }
    }

    private void register(Scanner scanner) {
        System.out.println("Регистрация нового пользователя:");
        System.out.print("Придумайте логин: ");
        String newLogin = scanner.nextLine().trim();

        System.out.print("Придумайте пароль: ");
        String newPassword = scanner.nextLine().trim();

        String sql = "INSERT INTO Users (username, password) VALUES (?, ?)";

        try (Connection conn = DBManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, newLogin);
            pstmt.setString(2, newPassword);
            pstmt.executeUpdate();
            System.out.println("Регистрация успешна! Теперь войдите под своим логином.");

        } catch (SQLException e) {
            if (e.getMessage().toLowerCase().contains("unique")) {
                System.out.println("Такой логин уже существует. Попробуйте другой.");
                register(scanner); // если логин занят — повторная регистрация
            } else {
                System.out.println("Ошибка при регистрации: " + e.getMessage());
            }
        }
    }
    public void isAuthorised(){
        while (true) {
            if (authorize()) {
                System.out.println("Теперь можно работать с коллекцией!");
                break; // Выход из цикла после успешной авторизации
            } else {
                System.out.println("Попробуйте еще раз");
            }
        }
    }
}
