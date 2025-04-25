/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package virtualAgenda.database;

import io.github.cdimascio.dotenv.Dotenv;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author FELIPECANTINI
 */
public class Connect {
   
    private static final Dotenv dotenv = Dotenv.load();
    
    private static Connection connection;
    
    private static final String URL = dotenv.get("DB_URL");
    private static final String DB_USER = dotenv.get("DB_USER");
    private static final String DB_PASSWORD = dotenv.get("DB_PASSWORD");
    
    public static Connection connect() {
        
        try {
            
            if (connection == null || connection.isClosed()) {
                
                connection = DriverManager.getConnection(URL, DB_USER, DB_PASSWORD);
                createUserTable();
                createAgendaTable();
                System.out.println("Connected to the database!");
            }
            
        } catch (SQLException e) {
            
            //System.out.println("ERRO: " + e.getMessage());
            throw new RuntimeException("Error: ", e);
        }
        
        return connection;
    }
    
    private static void createUserTable() {
        
        String sql = "CREATE TABLE IF NOT EXISTS users ("
                + "id INT AUTO_INCREMENT PRIMARY KEY, "
                + "username VARCHAR(255) NOT NULL UNIQUE, "
                + "password VARCHAR(255) NOT NULL)";
        
        try (Statement stmt = connection.createStatement()) {
            
            stmt.execute(sql);
            
        } catch (SQLException e) {
            
            throw new RuntimeException("Error: ", e);
        }
    }
    
        private static void createAgendaTable() {
        
            String sql = "CREATE TABLE IF NOT EXISTS agenda ("
            + "id INT AUTO_INCREMENT PRIMARY KEY, "
            + "name VARCHAR(255) NOT NULL, "
            + "hour VARCHAR(10) NOT NULL, "
            + "date VARCHAR(20) NOT NULL, "
            + "idUser INT, "
            + "FOREIGN KEY (idUser) REFERENCES users(id)"
            + ")";
        
        try (Statement stmt = connection.createStatement()) {
            
            stmt.execute(sql);
            
        } catch (SQLException e) {
            
            throw new RuntimeException("Error: ", e);
        }
    }
}
