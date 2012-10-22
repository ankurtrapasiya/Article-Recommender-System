/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.surpriseme.DAO;

import com.surpriseme.entities.User;
import java.util.List;

/**
 *
 * @author ankur
 */
public interface UserDAO extends GenericDAO<User, Integer> {

    boolean blockUser(Integer userid);
    
    boolean unblockUser(Integer userid);      
    
    List<User> searchUser(String firstName,String lastName, String email);
    
    boolean addToCircle(Integer userId);
    
    boolean removeFromCircle(Integer userId);
        
}
