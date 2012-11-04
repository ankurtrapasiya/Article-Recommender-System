/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.surpriseme.DAOImpl;

import com.surpriseme.DAO.UserSuggestionsDAO;
import com.surpriseme.entities.UserSuggestions;
import com.surpriseme.utils.DBConnection;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
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

    @Override
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
    }

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
}
