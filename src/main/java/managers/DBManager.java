package managers;

import model.*;

import java.sql.*;
import java.time.ZoneId;
import java.util.LinkedHashSet;

public class DBManager {
    private static final String URL = "jdbc:postgresql://localhost:5432/lab5";
    private static final String USER = "postgres";
    private static final String PASSWORD = "12345";

    /** Подключение к базе данных */
    public Connection connect_to_db(String dbname, String user, String pass) {
        Connection conn = null;
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/" + dbname, user, pass);
        } catch (Exception e) {
            System.out.println("Ошибка подключения к базе данных: " + e.getMessage());
        }
        return conn;
    }

    /** Проверка существования таблиц users и cities, создание при необходимости */
    public void isTableExist(Connection conn) {
        if (conn != null) {
            try {
                DatabaseMetaData dbm = conn.getMetaData();
                try (ResultSet tables = dbm.getTables(null, null, "users", new String[]{"TABLE"})) {
                    if (!tables.next()) {
                        System.out.println("Создаём таблицу пользователей...");
                        createTableUsers(conn, "users");
                    }
                }
                try (ResultSet tables = dbm.getTables(null, null, "cities", new String[]{"TABLE"})) {
                    if (!tables.next()) {
                        System.out.println("Создаём таблицу городов...");
                        createTableCities(conn, "cities");
                    }
                }
            } catch (Exception e) {
                System.out.println("Ошибка при проверке таблиц: " + e.getMessage());
            }
        }
    }

    /** Создание таблицы cities */
    public void createTableCities(Connection conn, String tableName) {
        try (Statement statement = conn.createStatement()) {
            String query = "CREATE TABLE IF NOT EXISTS " + tableName + " (" +
                    "id SERIAL PRIMARY KEY, " +
                    "name TEXT NOT NULL, " +
                    "x BIGINT NOT NULL, " +
                    "y DOUBLE PRECISION NOT NULL, " +
                    "creation_date TIMESTAMPTZ NOT NULL, " +
                    "area DOUBLE PRECISION NOT NULL, " +
                    "population BIGINT NOT NULL, " +
                    "meters_above_sea_level BIGINT, " +
                    "establishment_date TIMESTAMPTZ, " +
                    "government TEXT NOT NULL, " +
                    "standard_of_living TEXT NOT NULL, " +
                    "governor_age BIGINT NOT NULL" +
                    ");";
            statement.executeUpdate(query);
            System.out.println("Таблица городов создана.");
        } catch (Exception e) {
            System.out.println("Ошибка создания таблицы городов: " + e.getMessage());
        }
    }

    /** Создание таблицы users */
    public void createTableUsers(Connection conn, String tableName) {
        try (Statement statement = conn.createStatement()) {
            String query = "CREATE TABLE IF NOT EXISTS " + tableName + " (" +
                    "id SERIAL PRIMARY KEY, " +
                    "username TEXT UNIQUE NOT NULL, " +
                    "password TEXT NOT NULL" +
                    ");";
            statement.executeUpdate(query);
            System.out.println("Таблица пользователей создана.");
        } catch (Exception e) {
            System.out.println("Ошибка создания таблицы пользователей: " + e.getMessage());
        }
    }

    /** Получить подключение */
    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("PostgreSQL Driver не найден!", e);
        }
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    /** Загрузить все города */
    public LinkedHashSet<City> loadAllCities() {
        LinkedHashSet<City> cities = new LinkedHashSet<>();
        String sql = "SELECT * FROM cities";

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                City city = new City(
                        rs.getString("name"),
                        new Coordinates(rs.getLong("x"), rs.getDouble("y")),
                        rs.getTimestamp("creation_date").toLocalDateTime().atZone(ZoneId.systemDefault()),
                        rs.getDouble("area"),
                        rs.getLong("population"),
                        rs.getLong("meters_above_sea_level"),
                        rs.getTimestamp("establishment_date") != null ? rs.getTimestamp("establishment_date").toLocalDateTime().atZone(ZoneId.systemDefault()) : null,
                        Government.valueOf(rs.getString("government")),
                        StandardOfLiving.valueOf(rs.getString("standard_of_living")),
                        new Human(rs.getLong("governor_age")),
                        rs.getString("owner")      // <--- ДОБАВИТЬ В КОНСТРУКТОР!
                );

                city.setId(rs.getInt("id"));
                cities.add(city);
            }
        } catch (SQLException e) {
            System.out.println("Ошибка загрузки городов: " + e.getMessage());
        }
        return cities;
    }

    public void clearCitiesTable() {
        String sql = "TRUNCATE TABLE cities RESTART IDENTITY;";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.executeUpdate();
            System.out.println("Таблица cities очищена.");
        } catch (SQLException e) {
            System.out.println("Ошибка при очистке базы данных: " + e.getMessage());
        }
    }
    /** Полная очистка таблицы пользователей */
    public void clearUsersTable() {
        String sql = "TRUNCATE TABLE users RESTART IDENTITY CASCADE;";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.executeUpdate();
            System.out.println("Таблица users успешно очищена.");
        } catch (SQLException e) {
            System.out.println("Ошибка при очистке таблицы пользователей: " + e.getMessage());
        }
    }



    public String getOwnerByCityId(int id) {
        String sql = "SELECT owner FROM cities WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("owner");
                }
            }
        } catch (SQLException e) {
            System.out.println("Ошибка при получении владельца города: " + e.getMessage());
        }
        return null;
    }


    /** Добавить город и вернуть id */
    public int add(City city) throws SQLException {
        String sql = "INSERT INTO cities (name, x, y, creation_date, area, population, meters_above_sea_level, establishment_date, government, standard_of_living, governor_age, owner) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) RETURNING id;";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, city.getName());
            stmt.setLong(2, city.getCoordinates().getX());
            stmt.setDouble(3, city.getCoordinates().getY());
            stmt.setTimestamp(4, Timestamp.valueOf(city.getCreationDate().toLocalDateTime()));
            stmt.setDouble(5, city.getArea());
            stmt.setLong(6, city.getPopulation());
            stmt.setLong(7, city.getMetersAboveSeaLevel());
            if (city.getEstablishmentDate() != null) {
                stmt.setTimestamp(8, Timestamp.valueOf(city.getEstablishmentDate().toLocalDateTime()));
            } else {
                stmt.setNull(8, Types.TIMESTAMP);
            }
            stmt.setString(9, city.getGovernment().name());
            stmt.setString(10, city.getStandardOfLiving().name());
            stmt.setLong(11, city.getGovernor().getAge());
            stmt.setString(12, city.getOwner());

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");
            } else {
                throw new SQLException("Ошибка при добавлении города: id не получен.");
            }
        }
    }

    /** Удалить город по id */
    public void removeById(int id) {
        String sql = "DELETE FROM cities WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Ошибка удаления города: " + e.getMessage());
        }
    }

    public void addOwnerColumnIfNotExists() {
        String checkColumnSQL = "SELECT column_name FROM information_schema.columns WHERE table_name='cities' and column_name='owner';";
        String addColumnSQL = "ALTER TABLE cities ADD COLUMN owner TEXT NOT NULL DEFAULT 'admin';";

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(checkColumnSQL)) {

            if (!rs.next()) {
                stmt.executeUpdate(addColumnSQL);
                System.out.println("Поле owner добавлено в таблицу cities.");
            } else {
                System.out.println("Поле owner уже существует.");
            }

        } catch (SQLException e) {
            System.out.println("Ошибка при добавлении поля owner: " + e.getMessage());
        }
    }


    /** Очистить таблицу городов */
    public void clear() {
        String sql = "DELETE FROM cities";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Ошибка очистки городов: " + e.getMessage());
        }
    }

    /** Удалить города меньше заданного по площади */
    public void removeLower(City referenceCity) {
        String sql = "DELETE FROM cities WHERE area < ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDouble(1, referenceCity.getArea());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Ошибка удаления городов (меньше по площади): " + e.getMessage());
        }
    }

    /** Удалить города больше заданного по площади */
    public void removeGreater(City referenceCity) {
        String sql = "DELETE FROM cities WHERE area > ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDouble(1, referenceCity.getArea());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Ошибка удаления городов (больше по площади): " + e.getMessage());
        }
    }

    /** Обновить город по id */
    public void updateId(int id, City newCity) {
        String sql = "UPDATE cities SET " +
                "name = ?, x = ?, y = ?, creation_date = ?, area = ?, population = ?, " +
                "meters_above_sea_level = ?, establishment_date = ?, government = ?, standard_of_living = ?, governor_age = ? " +
                "WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, newCity.getName());
            stmt.setLong(2, newCity.getCoordinates().getX());
            stmt.setDouble(3, newCity.getCoordinates().getY());
            stmt.setTimestamp(4, Timestamp.valueOf(newCity.getCreationDate().toLocalDateTime()));
            stmt.setDouble(5, newCity.getArea());
            stmt.setLong(6, newCity.getPopulation());
            stmt.setLong(7, newCity.getMetersAboveSeaLevel());
            if (newCity.getEstablishmentDate() != null) {
                stmt.setTimestamp(8, Timestamp.valueOf(newCity.getEstablishmentDate().toLocalDateTime()));
            } else {
                stmt.setNull(8, Types.TIMESTAMP);
            }
            stmt.setString(9, newCity.getGovernment().name());
            stmt.setString(10, newCity.getStandardOfLiving().name());
            stmt.setLong(11, newCity.getGovernor().getAge());
            stmt.setInt(12, id);

            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Ошибка обновления города: " + e.getMessage());
        }
    }
}
