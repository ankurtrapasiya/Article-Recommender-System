/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.surpriseme.DAO;

import com.surpriseme.entities.Source;
import java.sql.SQLException;

/**
 *
 * @author ankur
 */
public interface SourceDAO extends GenericDAO<Source, Integer>{
    
    Source getSoureFromFeedUrl(String feedUrl) throws SQLException;
    
}
