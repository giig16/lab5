package managers;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import static managers.DBManager.getConnection;

public class AuthorisationManager {
    private String login;
    public boolean authorize(Scanner scanner) {
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



    public String getLogin(){
        return login;
    }

    public void printLogin(AuthorisationManager authorisationManager){
        System.out.println(authorisationManager.getLogin());
    }

    private boolean login(Scanner scanner) {
        System.out.println("Введите логин:");
        String login = scanner.nextLine();

        System.out.println("Введите пароль:");
        String password = scanner.nextLine();

        String sql = "SELECT * FROM Users WHERE username = ? AND password = ?";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, login);
            pstmt.setString(2, hashPassword(password));

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                System.out.println("Авторизация успешна! Добро пожаловать, " + login + "!");
                this.login = login;
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

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, newLogin);
            pstmt.setString(2, hashPassword(newPassword));

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



    public void isAuthorised(Scanner scanner) {
        while (true) {
            if (authorize(scanner)) {
                System.out.println("Теперь можно работать с коллекцией!");
                break; // Выход из цикла после успешной авторизации
            } else {
                System.out.println("Попробуйте еще раз");
            }
        }
    }
    private String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            byte[] hashBytes = md.digest(password.getBytes());

            // Переводим байты в строку шестнадцатеричного формата
            StringBuilder sb = new StringBuilder();
            for (byte b : hashBytes) {
                sb.append(String.format("%02x", b));
            }

            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("SHA-1 algorithm not found", e);
        }
    }
}
