/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package virtualAgenda.controller;

import java.util.ArrayList;
import virtualAgenda.dao.AgendaDAO;
import virtualAgenda.model.Agenda;

/**
 *
 * @author FELIPECANTINI
 */
public class AgendaController {
    
    AgendaDAO agendaDAO = new AgendaDAO();
    
    public String addAgenda(String name, int idUser, String date, String hour) {
        
        try {
            
            Agenda agenda = new Agenda(idUser, name, hour, date);
            
            agendaDAO.addAgenda(agenda);
            
            return "Succesfully added";
            
        } catch (NumberFormatException e) {
        
            return "Error: " + e.getMessage();
        }
    }
    
    public ArrayList<String> listAgenda(int idUser) {
        
        ArrayList<String> listAgenda = new ArrayList<>();
                
        try {
                      
            for (Agenda agenda: agendaDAO.listAgenda(idUser)) {
                
                String details = agenda.getName() + " | " + agenda.getDate() + " | " + agenda.getHour() +  " | ID: " + agenda.getId();
                
                listAgenda.add(details);
            }
            
            
            
        } catch (NumberFormatException e) {
        
            System.out.println("Error: " + e.getMessage());
        }
        
        return listAgenda;
    }
    
    public String deleteAgenda(int index, int idUser) {
        
        try {
            ArrayList<Agenda> agendas = agendaDAO.listAgenda(idUser);

        
            if (index >= 0 && index < agendas.size()) {
                
                int idAgenda = agendas.get(index).getId(); 
                
                agendaDAO.deleteAgenda(idAgenda); 
                
                return "Succesfully deleted";
                
            } else {
                
                return "Error!";
            }
            
        } catch (Exception e) {
            
            return "Error: " + e.getMessage();
        }
    }
    
    public String updateAgenda(int index, int idUser, String newName, String newDate, String newHour) {
    
        try {
            ArrayList<Agenda> agendas = agendaDAO.listAgenda(idUser);

        
                if (index >= 0 && index < agendas.size()) {
                
                    int idAgenda = agendas.get(index).getId(); 
                
                    agendaDAO.updateAgenda(idAgenda, newName, newHour, newDate); 
                
                    return "Succesfully updated";
                
                } else {
                
                return "Error!";
            }
            
        } catch (Exception e) {
            
            return "Error: " + e.getMessage();
        }
    }
    
    public String[] searchAgenda(int index) {
        
        String[] arrayAgenda = agendaDAO.searchAgenda(index);
        
        return arrayAgenda;
    }
}
