/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.surpriseme.DAOImpl;

import com.surpriseme.DAO.UserSuggestionsDAO;
import com.surpriseme.entities.ArticleLinks;
import com.surpriseme.entities.User;
import com.surpriseme.entities.UserSuggestions;
import com.surpriseme.utils.DBConnection;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;
import org.apache.log4j.Priority;

/**
 *
 * @author ankur
 */
public class UserSuggestionsDAOImpl implements UserSuggestionsDAO {

    CallableStatement cstmt;
    DBConnection con;
    private static final Logger logger = Logger.getLogger(UserDAOImpl.class);

    @Override
    public Integer saveOrUpdate(UserSuggestions entity) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void saveOrUpdateAll(List<UserSuggestions> entities) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public UserSuggestions findById(Integer key) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<UserSuggestions> getAll() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean delete(Integer key) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean deleteAll(List<UserSuggestions> entities) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /*@Override
     public List<UserSuggestions> getUserSuggestions(Integer userid) throws SQLException {
     List<UserSuggestions> retval = null;
     ResultSet rs = null;
     PreparedStatement pstmt = null;

     try {

     con = new DBConnection();

     if (con.connect()) {

     String sql = "select * from usersuggestions where userid=? and isviewed=false";
     pstmt = con.getConnection().prepareStatement(sql);
     pstmt.setInt(1, userid);

     rs = con.customQuery(pstmt);

     while (rs.next()) {

     UserSuggestions userSuggestions = new UserSuggestions();

     userSuggestions.setId(rs.getInt("id"));
     userSuggestions.setUserid(rs.getInt("userid"));
     userSuggestions.setFriendid(rs.getInt("friendid"));
     userSuggestions.setArticleid(rs.getInt("articleid"));
     userSuggestions.setIsviewed(rs.getBoolean("isviewed"));
     userSuggestions.setTimestamp(rs.getTimestamp("timestamp"));

     retval.add(userSuggestions);
     }

     }

     } catch (ClassNotFoundException ex) {
     logger.log(Priority.ERROR, ex.toString());
     } catch (SQLException ex) {
     throw ex;
     } finally {
     con.disconnect();
     }
     return retval;
     }*/
    @Override
    public boolean suggestArticle(int userid, int friendid, int articleid) throws SQLException {
        ResultSet rs = null;
        boolean retval = false;

        try {

            con = new DBConnection();
            if (con.connect()) {

                cstmt = (CallableStatement) con.getConnection().prepareCall("{call sp_ins_usersuggestions(?,?,?,?,?,?,?,?,?)}");

                cstmt.setInt("p_userid", userid);
                cstmt.setInt("p_friendid", friendid);
                cstmt.setInt("p_articleid", articleid);
                cstmt.setBoolean("p_isviewed", false);
                cstmt.setTimestamp("p_timestamp", new Timestamp(new Date().getTime()));

                rs = con.saveOrUpdate(cstmt);

            }

            retval = true;


        } catch (ClassNotFoundException ex) {
            logger.log(Priority.ERROR, ex.toString());
        } catch (SQLException e) {
            throw e;
        } finally {
            cstmt.close();
            con.disconnect();
        }

        return retval;
    }

    @Override
    public List<User> getUserSuggestions(Integer userid) throws SQLException {

        ResultSet rs = null;
        PreparedStatement pstmt = null;
        String query = null;
        String query_update = null;
        List<User> retval = null;
        Boolean updated = false;
        try {

            con = new DBConnection();

            if (con.connect()) {
                retval = new ArrayList<User>();
                // String sql = "select * from usersuggestions where userid=? and isviewed=false";
                //  pstmt = con.getConnection().prepareStatement(sql);
                // pstmt.setInt(1, userid);
                query = "SELECT * from user inner join usersuggestions on user.userid  = usersuggestions.friendid where usersuggestions.isviewed =   0 and usersuggestions.friendid !=" + userid;
                pstmt = con.getConnection().prepareStatement(query);
                rs = con.customQuery(pstmt);

                query_update = "UPDATE usersuggestions SET isviewed=1 WHERE isviewed=0 and userid=" + userid;

                updated = con.executeQuery(query_update);

                while (rs.next()) {

                    User user = new User();
                    System.out.println("hbh" + rs.getString("userid"));

                    user.setUserid(rs.getInt("userid"));
                    user.setUsername(rs.getString("username"));
                    user.setPassword(rs.getString("password"));
                    user.setEmail(rs.getString("email"));
                    user.setFirstname(rs.getString("firstname"));
                    user.setLastname(rs.getString("lastname"));
                    user.setDob(rs.getDate("dob"));
                    user.setState(rs.getString("state"));
                    user.setCity(rs.getString("city"));
                    user.setCountry(rs.getString("country"));
                    user.setIsactive(rs.getBoolean("isactive"));
                    user.setTimeofregistration(rs.getDate("timeofregistration"));

                    retval.add(user);
                }

            }

        } catch (ClassNotFoundException ex) {
            logger.log(Priority.ERROR, ex.toString());
        } catch (SQLException ex) {
            throw ex;
        } finally {
            con.disconnect();
        }
        return retval;
    }

    @Override
    public List<ArticleLinks> getSuggestedLinks(Integer userId) throws SQLException {

        ResultSet rs = null;
        PreparedStatement pstmt = null;
        String query = null;
        String query_update = null;
        List<ArticleLinks> returl = null;
        Boolean updated = false;
        try {

            con = new DBConnection();

            if (con.connect()) {
                returl = new ArrayList<ArticleLinks>();
                // String sql = "select * from usersuggestions where userid=? and isviewed=false";
                //  pstmt = con.getConnection().prepareStatement(sql);
                // pstmt.setInt(1, userid);
                query = "SELECT * from articlelinks inner join usersuggestions on articlelinks.articleid  = usersuggestions.articleid where usersuggestions.isviewed =   0 and usersuggestions.friendid !=" + userId;
                pstmt = con.getConnection().prepareStatement(query);
                rs = con.customQuery(pstmt);


                while (rs.next()) {

                    ArticleLinks links = new ArticleLinks();

                    links.setArticleid(rs.getInt("articleid"));
                    links.setArticleurl(rs.getString("articleurl"));
                    links.setSourceid(rs.getInt("sourceid"));

                    returl.add(links);
                }

            }

        } catch (ClassNotFoundException ex) {
            logger.log(Priority.ERROR, ex.toString());
        } catch (SQLException ex) {
            throw ex;
        } finally {
            con.disconnect();
        }
        return returl;
    }
}
