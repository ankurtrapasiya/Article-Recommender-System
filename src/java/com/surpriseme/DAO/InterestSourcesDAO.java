/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.surpriseme.DAO;

import com.surpriseme.entities.InterestSources;
import com.surpriseme.entities.Source;
import com.surpriseme.helper.InterestSourcesPK;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author ankur
 */
public interface InterestSourcesDAO extends GenericDAO<InterestSources, InterestSourcesPK> {
    List<Source> getSourcesOfIntereset(Integer interestid) throws SQLException;
}
