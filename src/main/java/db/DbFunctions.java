package db;

import javax.swing.plaf.nimbus.State;
import java.sql.*;

public class DbFunctions {
    public Connection connect_to_db(String dbname,String user,String pass){
        Connection conn = null;
        try{
            Class.forName("org.postgresql.Driver");
            conn= DriverManager.getConnection("jdbc:postgresql://localhost:5432/"+dbname,user,pass);
            if(conn!=null){
                System.out.println("Connection Established");
            }
            else{
                System.out.println("Connection Failed");
            }

        }catch(Exception e){
            System.out.println(e);
        }
        return conn;
    }
    public void createTable(Connection conn, String tableName){
        Statement statement;
                try{
                    String query = "create table if not exists "+tableName+"(id serial primary key , name text not null, x bigint not null, y double precision not null, creation_date TIMESTAMPTZ NOT NULL, area DOUBLE PRECISION NOT NULL, population BIGINT NOT NULL, meters_above_sea_level BIGINT, establishment_date TIMESTAMPTZ, government TEXT NOT NULL, standard_of_living TEXT NOT NULL,governor_age BIGINT NOT NULL);";
                    statement=conn.createStatement();
                    statement.executeUpdate(query);
                    System.out.println("Таблица создана");
                }catch(Exception e){
                    System.out.println(e);
                }
    }
    /*public void insertRow(Connection conn, String tableName, String name,String address){
        Statement statement;
        try{
            String query= String.format("insert into %s(name,address) values('%s','%s');",tableName,name,address);
            statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Row inserted");
        }catch(Exception e){
            System.out.println(e);
        }
    }
    public void readData(Connection conn, String tableName){
        Statement statement;
        ResultSet rs = null;
        try{
            String query = String.format("select * from %s",tableName);
            statement=conn.createStatement();
            rs=statement.executeQuery(query);
            while(rs.next()){
                System.out.print(rs.getString("empid")+" ");
                System.out.print(rs.getString("name")+" ");
                System.out.println(rs.getString("address")+" ");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public void updateName(Connection conn, String tableName,String oldName, String newName){
        Statement statement;
        try{
            String query = String.format("update %s set name='%s' where name='%s'",tableName, newName,oldName);
            statement=conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Data Updated");
        }catch(Exception e){
            System.out.println(e);
        }
    }
    public void searchByName(Connection conn, String tableName, String name){
        Statement statement;
        ResultSet rs = null;
        try{
            String query = String.format("select * from %s where name='%s'",tableName,name);
            statement=conn.createStatement();
            rs=statement.executeQuery(query);
            while(rs.next()){
                System.out.print(rs.getString("empid")+" ");
                System.out.print(rs.getString("name")+" ");
                System.out.println(rs.getString("address"));
            }
        }catch(Exception e){
            System.out.println(e);
        }
    }
    public void searchById(Connection conn, String tableName, int id){
        Statement statement;
        ResultSet rs = null;
        try{
            String query = String.format("select * from %s where empid='%s'",tableName,id);
            statement=conn.createStatement();
            rs=statement.executeQuery(query);
            while(rs.next()){
                System.out.print(rs.getString("empid")+" ");
                System.out.print(rs.getString("name")+" ");
                System.out.println(rs.getString("address"));
            }
        }catch(Exception e){
            System.out.println(e);
        }
    }
    public void deleteRowByName(Connection conn, String tableName, String name){
        Statement statement;
        try{
            String query = String.format("delete  from %s where name='%s'",tableName,name);
            statement = conn.createStatement();
            statement.executeUpdate(query);
        }catch(Exception e){
            System.out.println(e);
        }
    }
    public void deleteRowById(Connection conn, String tableName, int id){
        Statement statement;
        try{
            String query = String.format("delete  from %s where empid='%s'",tableName,id);
            statement = conn.createStatement();
            statement.executeUpdate(query);
        }catch(Exception e){
            System.out.println(e);
        }
    }
    public void deleteTable(Connection conn, String tableName){
        Statement statement;
        try{
            String query = String.format("drop table %s",tableName);
            statement=conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("table deleted");
        }catch(Exception e){
            System.out.println(e);
        }
    }**/
}
