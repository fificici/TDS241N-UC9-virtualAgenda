/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package virtualAgenda.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import virtualAgenda.database.Connect;
import virtualAgenda.model.Agenda;

/**
 *
 * @author FELIPECANTINI
 */
public class AgendaDAO {
    
       public void addAgenda(Agenda agenda) {
        
        String sql = "INSERT INTO agenda (idUser, name, hour, date) VALUES (?, ?, ?, ?)";
        
        try (Connection conn = Connect.connect(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, agenda.getIdUser());
            stmt.setString(2, agenda.getName());
            stmt.setString(3, agenda.getHour());
            stmt.setString(4, agenda.getDate());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Succesfully inserted!");
            
        } catch (SQLException e) {
            
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }
        
        public ArrayList<Agenda> listAgenda(int idUser) {
            
            ArrayList<Agenda> agendas = new ArrayList<>();
            
            String sql = "SELECT * FROM agenda WHERE idUser = ?";
            
            try (Connection conn = Connect.connect(); PreparedStatement stmt = conn.prepareStatement(sql)) {
                
                stmt.setInt(1,idUser);
                ResultSet rs = stmt.executeQuery();
                
                while (rs.next()) {
                    
                    int idUsers = rs.getInt("idUser");
                    String names = rs.getString("name");
                    String hours = rs.getString("hour");
                    String dates= rs.getString("date");
                    int id = rs.getInt("id");
                    
                    Agenda agenda = new Agenda(idUsers, names, hours, dates);
                    
                    agenda.setId(id);
                    agendas.add(agenda);

                }
                
            } catch (SQLException e) {
                
                JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
            }
            
            return agendas;
        } 
        
        public void deleteAgenda(int id) {
            
            String sql = "DELETE FROM agenda WHERE id = ?";
        
            try (Connection conn = Connect.connect(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            
            int rowsDeleted = stmt.executeUpdate();
            
            if (rowsDeleted > 0) { 

                JOptionPane.showMessageDialog(null, "Successfully deleted!");
                
            } else {

                JOptionPane.showMessageDialog(null, "Error!");
                
            }
            
            } catch (Exception e) {
            
               JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
            }
        }
        
        public void updateAgenda(String id, String newName, String newHour, String newDate) {
        
        String sql = "UPDATE agenda SET name = ?, hour = ?, date = ? WHERE id = ?";

        try {

            try (Connection conn = Connect.connect(); PreparedStatement stmt = conn.prepareStatement(sql)) {
                
                stmt.setString(1, newName);
                
                stmt.setString(2, newHour);
                
                stmt.setString(3, newDate);
                
                stmt.setString(4, id);

                int rowsUpdated = stmt.executeUpdate();

                if (rowsUpdated > 0) {

                    JOptionPane.showMessageDialog(null, "Successfully updated!");
                    
                    
                } else {

                    JOptionPane.showMessageDialog(null, "Error!");
                }
                
            } catch (Exception e) {

                 JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
                
            }
        } catch (Exception e) {

             JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }

    }
        
    public String[] searchAgenda(String id) {
        
        String[] arrayAgenda = new String[4]; 
        
        String sql = "SELECT id, name, hour, date FROM agenda WHERE id = ?";

        try (Connection conn = Connect.connect(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                
                if (rs.next()) {
                    
                    int idResult = rs.getInt("id");
                    
                    arrayAgenda[0] = Integer.toString(idResult);
                    arrayAgenda[1] = rs.getString("name");
                    arrayAgenda[2] = rs.getString("hour");
                    arrayAgenda[3] = rs.getString("date");
                    
                } else {
                    
                    JOptionPane.showMessageDialog(null, "Error!");
                }
            }
            
        } catch (Exception e) {
            
            JOptionPane.showMessageDialog(null, "Searching error: " + e.getMessage());
        }

        return arrayAgenda;
    }
}
