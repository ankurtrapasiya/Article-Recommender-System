/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.surpriseme.DAO;

import com.surpriseme.entities.Interest;
import com.surpriseme.entities.Source;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author ankur
 */
public interface InterestDAO extends GenericDAO<Interest, Integer> {

    List<Integer> getUsersOfInterest(Integer interestid) throws SQLException;

    List<Source> getSourcesOfIntereset(Integer interestid) throws SQLException;
}
