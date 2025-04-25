/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package virtualAgenda.controller;

import virtualAgenda.dao.UserDAO;
import virtualAgenda.model.User;

/**
 *
 * @author FELIPECANTINI
 */
public class UserController {
    
    private UserDAO userDAO = new UserDAO();
    
    public boolean registerUser(String username, String password) {
        
        User user = new User(username, password);
        
        return userDAO.registerUser(user);
    }
    
    public User verifyLogin(String username, char[] charPassword) {
        
        String password = new String(charPassword).trim();
        User user = new User(username, password);
        
        if (userDAO.verifyLogin(user)) {
            return user;
        }
        
        return null;
    }
}
