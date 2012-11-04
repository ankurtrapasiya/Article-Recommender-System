/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.surpriseme.DAO;

import com.surpriseme.entities.Tag;
import java.sql.SQLException;

/**
 *
 * @author ankur
 */
public interface TagDAO extends GenericDAO<Tag, Integer>{
    
    Integer checkIfTagExist(String tagname) throws SQLException;
}
