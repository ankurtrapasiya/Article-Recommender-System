/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.surpriseme.DAO;

import com.surpriseme.entities.Userhistory;
import com.surpriseme.utils.Duration;
import java.util.Date;
import java.util.List;

/**
 *
 * @author ankur
 */
public interface HistoryDAO extends GenericDAO<Userhistory, Integer>{
    
    List<Userhistory> getAllHistory(Integer userid);
    
    List<Userhistory> getAllHistory(Integer userid,Date from,Date to);
    
    List<Userhistory> getAllHistory(Integer userid,Duration duration);
    
}
