/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.surpriseme.test;

import com.surpriseme.DAOImpl.UserDAOImpl;
import com.surpriseme.entities.User;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ankur
 */
public class Main {
    
    
    public static void main(String[] args) {
        UserDAOImpl user=new UserDAOImpl();
        User u=new User();        
        
        u.setUsername("abc");
        u.setPassword("abc");
        u.setEmail("abc@abc.com");
        u.setFirstname("abc");
        u.setLastname("abc");
        u.setDob(new Date());
        u.setState("guj");
        u.setCity("gnagar");
        u.setCountry("india");
        u.setIsactive(false);
        u.setTimeofregistration(new Date());
        try {
            user.add(u);
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
}
