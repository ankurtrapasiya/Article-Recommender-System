/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.surpriseme.DAOImpl;

import com.surpriseme.DAO.UserDAO;
import com.surpriseme.entities.User;
import java.util.List;

/**
 *
 * @author ankur
 */
public class UserDAOImpl  extends GenericDAOImpl<User, Integer> implements UserDAO{

    @Override
    public boolean blockUser(Integer userid) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean unblockUser(Integer userid) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<User> searchUser(String firstName, String lastName, String email) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean addToCircle(Integer userId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean removeFromCircle(Integer userId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    
}
