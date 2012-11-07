/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.surpriseme.DAO;

import com.surpriseme.entities.ArticleLinks;
import com.surpriseme.entities.User;
import com.surpriseme.entities.UserSuggestions;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author ankur
 */
public interface UserSuggestionsDAO extends GenericDAO<UserSuggestions, Integer> {

   /* List<UserSuggestions> getUserSuggestions(Integer userid) throws SQLException;*/

    boolean suggestArticle(int userid, int friendid, int articleid) throws SQLException;

    List<User> getUserSuggestions(Integer userid) throws SQLException;

    List<ArticleLinks> getSuggestedLinks(Integer userId) throws SQLException;
}
