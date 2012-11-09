/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.surpriseme.DAOImpl;

import com.surpriseme.DAO.UserGraphDAO;
import com.surpriseme.entities.User;
import com.surpriseme.entities.UserGraph;
import com.surpriseme.helper.UserGraphPK;
import com.surpriseme.utils.DBConnection;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.apache.log4j.Priority;

/**
 *
 * @author ankur
 */
public class UserGraphDAOImpl implements UserGraphDAO {

    CallableStatement cstmt;
    DBConnection con;
    private static final Logger logger = Logger.getLogger(UserDAOImpl.class);

    @Override
    public UserGraphPK saveOrUpdate(UserGraph entity) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void saveOrUpdateAll(List<UserGraph> entities) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public UserGraph findById(UserGraphPK key) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<UserGraph> getAll() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean delete(UserGraphPK key) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean deleteAll(List<UserGraph> entities) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean addToCircle(Integer friendId, Integer userId) throws SQLException {

        boolean retval = false;
        ResultSet rs = null;

        try {

            con = new DBConnection();
            if (con.connect()) {

                cstmt = (CallableStatement) con.getConnection().prepareCall("{call sp_ins_usergraph(?,?,?)}");


                cstmt.setInt("p_friendid", friendId);
                cstmt.setInt("p_userid", userId);
                cstmt.setBoolean("p_isnotified", false);

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
    public boolean removeFromCircle(Integer friendId, Integer userId) throws SQLException {
        ResultSet rs = null;
        boolean retval = false;

        try {

            con = new DBConnection();
            if (con.connect()) {

                cstmt = (CallableStatement) con.getConnection().prepareCall("{call sp_del_usergraph(?,?)}");


                cstmt.setInt("p_friendid", friendId);
                cstmt.setInt("p_userid", userId);

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
    public List<User> updateUserGraph() throws SQLException {
        ResultSet rs = null;
        ResultSet rs1 = null;
        String query = null;
        String query_update = null;
        PreparedStatement pstmt = null;
        List<User> retval = null;
        Boolean updated = false;
        try {

            con = new DBConnection();

            if (con.connect()) {
                retval = new ArrayList<User>();
                // cstmt = (CallableStatement) con.getConnection().prepareCall("{call sp_upd_user(?,?,?)}");

                // cstmt.setInt("p_friendid", friendid);
                //cstmt.setInt("p_userid", userid);
                //cstmt.setInt("p_isnotifitied",value);
                query = "SELECT * from user inner join usergraph on user.userid  = usergraph.friendid where usergraph.isnotified =   0 and usergraph.friendid !=1";
                pstmt = con.getConnection().prepareStatement(query);
                rs = con.customQuery(pstmt);

                query_update = "UPDATE usergraph SET isnotified=1 WHERE isnotified=0";

                updated = con.executeQuery(query_update);
                //---------------------
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
                    user.setImage(rs.getString("image"));

                    retval.add(user);
                }

            }
        } catch (ClassNotFoundException ex) {
            logger.log(Priority.ERROR, ex.toString());
        } catch (SQLException e) {
            throw e;
        } finally {
            // cstmt.close();
            con.disconnect();
        }

        return retval;
    }

    @Override
    public List<User> getAllFriends(Integer userid) throws SQLException {

        List<User> retval = new ArrayList<User>();
        ResultSet rs = null;
        String sql;

        try {

            con = new DBConnection();

            if (con.connect()) {

                sql = "select * from usergraph where userid=" + userid;

                rs = con.customQuery(sql);

                UserDAOImpl userDao = new UserDAOImpl();

                while (rs.next()) {

                    User user = new User();

                    Integer friendId = rs.getInt("friendid");

                    user = userDao.findById(friendId);

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
}
