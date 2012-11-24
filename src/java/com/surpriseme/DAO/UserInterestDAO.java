/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.surpriseme.DAO;

import com.surpriseme.entities.Article;
import com.surpriseme.entities.Interest;
import com.surpriseme.entities.UserInterest;
import com.surpriseme.helper.UserInterestPK;
import com.surpriseme.utils.Category;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author ankur
 */
public interface UserInterestDAO extends GenericDAO<UserInterest, UserInterestPK> {

    boolean addInterestToUser(Integer interestid, Integer userid) throws SQLException;

    boolean removeInterestFromUser(Integer interestid, Integer userid) throws SQLException;

    List<Interest> getUserInterests(Integer userid, boolean include) throws SQLException;

    HashMap<Category, List<Article>> suggestArticle(int userid, int interestid) throws SQLException;

    List<Integer> getUsersOfInterest(Integer interestid) throws SQLException;
    
}
