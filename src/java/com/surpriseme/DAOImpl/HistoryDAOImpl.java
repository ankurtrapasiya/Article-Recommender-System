/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.surpriseme.DAOImpl;

import com.surpriseme.DAO.HistoryDAO;
import com.surpriseme.entities.Userhistory;
import com.surpriseme.utils.Duration;
import java.util.Date;
import java.util.List;

/**
 *
 * @author ankur
 */
public class HistoryDAOImpl extends GenericDAOImpl<Userhistory, Integer> implements HistoryDAO {

    @Override
    public List<Userhistory> getAllHistory(Integer userid) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Userhistory> getAllHistory(Integer userid, Date from, Date to) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Userhistory> getAllHistory(Integer userid, Duration duration) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
