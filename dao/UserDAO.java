/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package virtualAgenda.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.mindrot.jbcrypt.BCrypt;
import virtualAgenda.model.User;
import virtualAgenda.database.Connect;

/**
 *
 * @author FELIPECANTINI
 */
public class UserDAO {
    

    
    public boolean registerUser(User user) {
        
        String sql = "INSERT INTO users (username, password) VALUES (?, ?)";
        
        String username = user.getUsername();
        String password = user.getPassword();
        
        String passwordHash = BCrypt.hashpw(password, BCrypt.gensalt());
        
        try (Connection conn = Connect.connect(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, username);
            stmt.setString(2, passwordHash);
            
            stmt.executeUpdate();
            
            return true;
            
        } catch (SQLException e) {
            
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean verifyLogin(User user) {
        
        String sql = "SELECT password FROM users WHERE username = ?";

        try (Connection conn = Connect.connect(); 
                
            PreparedStatement stmt = conn.prepareStatement(sql)) {
        
            stmt.setString(1, user.getUsername());
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                
                String passwordHash = rs.getString("password");
                
                return BCrypt.checkpw(user.getPassword(), passwordHash);
            }
        } catch (SQLException e) {
            
            e.printStackTrace();
        }
        
    return false;
    }
}
