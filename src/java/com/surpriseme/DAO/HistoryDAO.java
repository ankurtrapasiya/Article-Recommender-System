/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.surpriseme.DAO;

import com.surpriseme.entities.UserHistory;
import com.surpriseme.utils.Duration;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 *
 * @author ankur
 */
public interface HistoryDAO extends GenericDAO<UserHistory, Integer>{
    
    List<UserHistory> getAllHistory(Integer userid) throws SQLException;
    
    List<UserHistory> getAllHistory(Integer userid,Date from,Date to) throws SQLException;
    
    List<UserHistory> getAllHistory(Integer userid,Duration duration) throws SQLException;
    
}
