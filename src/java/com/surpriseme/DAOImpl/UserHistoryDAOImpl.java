/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.surpriseme.DAOImpl;

import com.surpriseme.DAO.UserHistoryDAO;
import com.surpriseme.entities.UserHistory;
import com.surpriseme.helper.UserHistoryPK;
import com.surpriseme.utils.Duration;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 *
 * @author ankur
 */
public class UserHistoryDAOImpl implements UserHistoryDAO{

    @Override
    public List<UserHistory> getAllHistory(Integer userid) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<UserHistory> getAllHistory(Integer userid, Date from, Date to) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<UserHistory> getAllHistory(Integer userid, Duration duration) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Integer saveOrUpdate(UserHistory entity) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void saveOrUpdateAll(List<UserHistory> entities) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public UserHistory findById(UserHistoryPK key) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<UserHistory> getAll() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean delete(UserHistoryPK key) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean deleteAll(List<UserHistory> entities) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
