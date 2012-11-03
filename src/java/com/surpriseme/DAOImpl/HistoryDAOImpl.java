/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.surpriseme.DAOImpl;

import com.surpriseme.DAO.HistoryDAO;
import com.surpriseme.entities.User;
import com.surpriseme.entities.UserHistory;
import com.surpriseme.helper.UserHistoryPK;
import com.surpriseme.utils.DBConnection;
import com.surpriseme.utils.Duration;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.apache.log4j.Logger;
import org.apache.log4j.Priority;

/**
 *
 * @author ankur
 */
public class HistoryDAOImpl implements HistoryDAO {

    CallableStatement cstmt;
    DBConnection con;
    private static final Logger logger = Logger.getLogger(UserDAOImpl.class);

    @Override
    public List<UserHistory> getAllHistory(Integer userid) throws SQLException {
        List<UserHistory> retval = null;
        ResultSet rs = null;
        PreparedStatement pstmt = null;

        try {

            con = new DBConnection();

            if (con.connect()) {

                String sql = "select *"
                        + " from userhistory uh"
                        + " inner join article a"
                        + " on a.articleid = uh.articleid"
                        + " where userid=? "
                        + " order by a.popularityscore desc";

                pstmt = con.getConnection().prepareStatement(sql);
                pstmt.setInt(1, userid);

                rs = con.customQuery(pstmt);

                while (rs.next()) {

                    UserHistory history = new UserHistory();

                    history.setUserid(rs.getInt("userid"));
                    history.setArticleid(rs.getInt("articleid"));
                    history.setUpvote(rs.getBoolean("upvote"));
                    history.setDownvote(rs.getBoolean("downvote"));
                    history.setTimestamp(rs.getTimestamp("timestamp"));

                    retval.add(history);
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
    public List<UserHistory> getAllHistory(Integer userid, Date from, Date to) throws SQLException {
        List<UserHistory> retval = null;
        ResultSet rs = null;
        PreparedStatement pstmt = null;

        try {

            con = new DBConnection();

            if (con.connect()) {

                String sql = "select * from userhistory where userid=? and timestamp > ? and timestamp < ?";
                pstmt = con.getConnection().prepareStatement(sql);
                pstmt.setInt(1, userid);
                pstmt.setTimestamp(2, new Timestamp(from.getTime()));
                pstmt.setTimestamp(3, new Timestamp(to.getTime()));

                rs = con.customQuery(pstmt);

                while (rs.next()) {

                    UserHistory history = new UserHistory();

                    history.setUserid(rs.getInt("userid"));
                    history.setArticleid(rs.getInt("articleid"));
                    history.setUpvote(rs.getBoolean("upvote"));
                    history.setDownvote(rs.getBoolean("downvote"));
                    history.setTimestamp(rs.getTimestamp("timestamp"));

                    retval.add(history);
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
    public List<UserHistory> getAllHistory(Integer userid, Duration duration) throws SQLException {
        List<UserHistory> retval = null;
        ResultSet rs = null;
        PreparedStatement pstmt = null;

        try {

            con = new DBConnection();

            if (con.connect()) {

                String sql = "select * from userhistory where userid=? and datediff(sysdate(),timestamp) <= ?";
                pstmt = con.getConnection().prepareStatement(sql);
                pstmt.setInt(1, userid);
                pstmt.setInt(2, duration.getDuration());

                rs = con.customQuery(pstmt);

                while (rs.next()) {

                    UserHistory history = new UserHistory();

                    history.setUserid(rs.getInt("userid"));
                    history.setArticleid(rs.getInt("articleid"));
                    history.setUpvote(rs.getBoolean("upvote"));
                    history.setDownvote(rs.getBoolean("downvote"));
                    history.setTimestamp(rs.getTimestamp("timestamp"));

                    retval.add(history);
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
    public Integer saveOrUpdate(UserHistory entity) throws SQLException {
        ResultSet rs = null;
        Integer retval = null;

        try {

            con = new DBConnection();
            if (con.connect()) {

                cstmt = (CallableStatement) con.getConnection().prepareCall("{call sp_ins_userhistory(?,?,?,?,?)}");


                cstmt.setInt("p_userid", entity.getUserid());
                cstmt.setInt("p_articleid", entity.getArticleid());
                cstmt.setTimestamp("p_timestamp", entity.getTimestamp());
                cstmt.setBoolean("p_upvote", entity.getUpvote());
                cstmt.setBoolean("p_downvote", entity.getDownvote());

                rs = con.saveOrUpdate(cstmt);

                String sql = "select last_insert_id()";

                rs = con.customQuery(sql);

                while (rs.next()) {
                    retval = rs.getInt(1);
                }
            }


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
    public void saveOrUpdateAll(List<UserHistory> entities) throws SQLException {
        ResultSet rs = null;

        try {

            con = new DBConnection();
            if (con.connect()) {

                Iterator<UserHistory> iterator = entities.iterator();

                while (iterator.hasNext()) {

                    UserHistory entity = iterator.next();

                    cstmt = (CallableStatement) con.getConnection().prepareCall("{call sp_ins_userhistory(?,?,?,?,?)}");


                    cstmt.setInt("p_userid", entity.getUserid());
                    cstmt.setInt("p_articleid", entity.getArticleid());
                    cstmt.setTimestamp("p_timestamp", entity.getTimestamp());
                    cstmt.setBoolean("p_upvote", entity.getUpvote());
                    cstmt.setBoolean("p_downvote", entity.getDownvote());

                    rs = con.saveOrUpdate(cstmt);

                }

            }
        } catch (ClassNotFoundException ex) {
            logger.log(Priority.ERROR, ex.toString());
        } catch (SQLException e) {
            throw e;
        } finally {
            cstmt.close();
            con.disconnect();
        }


    }

    @Override
    public UserHistory findById(UserHistoryPK key) throws SQLException {
        UserHistory retval = null;
        ResultSet rs = null;
        PreparedStatement pstmt = null;

        try {

            con = new DBConnection();

            if (con.connect()) {

                String sql = "select * from userhistory where userid=? and articleid=?";
                pstmt = con.getConnection().prepareStatement(sql);
                pstmt.setInt(1, key.getUserid());
                pstmt.setInt(2, key.getArticleid());

                rs = con.customQuery(pstmt);

                while (rs.next()) {

                    retval.setUserid(rs.getInt("userid"));
                    retval.setArticleid(rs.getInt("articleid"));
                    retval.setTimestamp(rs.getTimestamp("timestamp"));
                    retval.setUpvote(rs.getBoolean("upvote"));
                    retval.setDownvote(rs.getBoolean("downvote"));

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
    public List<UserHistory> getAll() throws SQLException {
        List<UserHistory> retval = new ArrayList<UserHistory>();
        ResultSet rs = null;

        try {

            con = new DBConnection();

            if (con.connect()) {

                cstmt = (CallableStatement) con.getConnection().prepareCall("{call sp_sel_userhistory()}");

                rs = con.customQuery(cstmt);

                while (rs.next()) {

                    UserHistory uh = new UserHistory();

                    uh.setUserid(rs.getInt("userid"));
                    uh.setArticleid(rs.getInt("articleid"));
                    uh.setTimestamp(rs.getTimestamp("timestamp"));
                    uh.setUpvote(rs.getBoolean("upvote"));
                    uh.setDownvote(rs.getBoolean("downvote"));

                    retval.add(uh);
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
    public boolean delete(UserHistoryPK key) throws SQLException {
        boolean retval = false;
        ResultSet rs = null;


        try {

            con = new DBConnection();

            if (con.connect()) {

                cstmt = (CallableStatement) con.getConnection().prepareCall("{call sp_del_userhistory(?,?)}");
                cstmt.setInt(1, key.getUserid());
                cstmt.setInt(1, key.getArticleid());

                con.customQuery(cstmt);

            }

            retval = true;

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
    public boolean deleteAll(List<UserHistory> entities) throws SQLException {
        boolean retval = false;

        try {

            con = new DBConnection();

            if (con.connect()) {


                Iterator<UserHistory> iterator = entities.iterator();

                while (iterator.hasNext()) {

                    UserHistory entity = iterator.next();

                    cstmt = (CallableStatement) con.getConnection().prepareCall("{call sp_del_userhistory(?,?,?)}");
                    cstmt.setInt(1, entity.getUserid());
                    cstmt.setInt(1, entity.getArticleid());
                    cstmt.setTimestamp(1, entity.getTimestamp());

                    con.customQuery(cstmt);

                }

            }

            retval = true;

        } catch (ClassNotFoundException ex) {
            logger.log(Priority.ERROR, ex.toString());
        } catch (SQLException ex) {
            throw ex;
        } finally {
            con.disconnect();
        }
        return retval;
    }
}
