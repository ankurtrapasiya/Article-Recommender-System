/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.surpriseme.DAO;

import com.surpriseme.entities.BlockedUsers;
import com.surpriseme.entities.Interest;
import com.surpriseme.entities.User;
import com.surpriseme.entities.UserActivation;
import com.surpriseme.entities.UserSuggestions;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author ankur
 */
public interface UserDAO extends GenericDAO<User, Integer> {
           
    List<User> searchUser(String firstName, String lastName, String email) throws SQLException;

    User findByUsername(String username) throws SQLException;

    boolean insertIntoUserActivation(UserActivation ua) throws SQLException;

    User isValidUser(String username, String password) throws SQLException;

    boolean isEmailAvailable(String email) throws SQLException;

    boolean isUsernameAvailable(String username) throws SQLException;
}
