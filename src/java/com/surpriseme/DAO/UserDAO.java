/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.surpriseme.DAO;

import com.surpriseme.entities.BlockedUsers;
import com.surpriseme.entities.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author ankur
 */
public interface UserDAO extends GenericDAO<User, Integer> {

    ResultSet blockUser(BlockedUsers entity) throws SQLException;
    
    ResultSet unblockUser(BlockedUsers userid) throws SQLException;      
    
    List<User> searchUser(String firstName,String lastName, String email) throws SQLException;
    
    ResultSet addToCircle(Integer friendId,Integer userId) throws SQLException;
    
    ResultSet removeFromCircle(Integer friendId,Integer userId) throws SQLException;
        
}
