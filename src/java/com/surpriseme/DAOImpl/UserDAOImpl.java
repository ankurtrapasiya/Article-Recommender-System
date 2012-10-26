/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.surpriseme.DAOImpl;

import com.surpriseme.DAO.UserDAO;
import com.surpriseme.entities.BlockedUsers;
import com.surpriseme.entities.User;
import com.surpriseme.utils.DBConnection;
import com.surpriseme.utils.Utilities;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.log4j.Logger;
import org.apache.log4j.Priority;

/**
 *
 * @author ankur
 */
public class UserDAOImpl implements UserDAO {

    CallableStatement cstmt;
    DBConnection con;
    private static final Logger logger = Logger.getLogger(UserDAOImpl.class);

    @Override
    public boolean blockUser(BlockedUsers entity) throws SQLException {

        boolean retval = false;

        ResultSet rs = null;

        try {

            con = new DBConnection();

            if (con.connect()) {

                cstmt = (CallableStatement) con.getConnection().prepareCall("{call sp_ins_blockedusers(?,?,?,?,?)}");

                cstmt.setInt("p_userid", entity.getUserid());
                cstmt.setInt("p_blockerid", entity.getBlockerid());
                cstmt.setTimestamp("p_timestamp", entity.getTimestamp());
                cstmt.setString("p_reason", entity.getReason());

                // has to be true
                cstmt.setBoolean("p_isactive", entity.isIsActive());

                rs = con.saveOrUpdate(cstmt);

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
    public boolean unblockUser(BlockedUsers entity) throws SQLException {

        boolean retval = false;
        ResultSet rs = null;

        try {

            con = new DBConnection();

            if (con.connect()) {

                cstmt = (CallableStatement) con.getConnection().prepareCall("{call sp_del_blockedusers(?,?)}");

                cstmt.setInt("p_userid", entity.getUserid());
                cstmt.setInt("p_blockerid", entity.getBlockerid());

                rs = con.saveOrUpdate(cstmt);

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
    public List<BlockedUsers> getAllBlocked() throws SQLException {
        List<BlockedUsers> retval = new ArrayList<BlockedUsers>();
        ResultSet rs = null;
        

        try {

            con = new DBConnection();

            if (con.connect()) {

             

                rs = con.selectRecords("blockedusers");

                while (rs.next()) {

                    BlockedUsers user = new BlockedUsers();
                    User temp = new User();
                    temp=findById(rs.getInt("userid"));
                    user.setUsername(temp.getUsername());
                    temp=findById(rs.getInt("blockerid"));
                    user.setBlockername(temp.getUsername());
                    user.setTimestamp(rs.getTimestamp("timestamp"));
                    user.setReason(rs.getString("reason"));
                    user.setIsActive(rs.getBoolean("isactive"));
                    user.setBlockerid(rs.getInt("blockerid"));
                    user.setUserid(rs.getInt("userid"));
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
    public List<User> searchUser(String firstName, String lastName, String email) throws SQLException {
        List<User> retval = new ArrayList<User>();
        ResultSet rs = null;
        PreparedStatement pstmt = null;

        try {

            con = new DBConnection();

            if (con.connect()) {

                String sql = "select * from user where firstname like ? and lastname like ?";
                pstmt = con.getConnection().prepareStatement(sql);
                pstmt.setString(1, "%" + firstName + "%");
                pstmt.setString(2, "%" + lastName + "%");
                rs = con.customQuery(pstmt);

                while (rs.next()) {

                    User user = new User();

                    user.setUsername(rs.getString("userid"));
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
    public boolean addToCircle(Integer friendId, Integer userId) throws SQLException {

        boolean retval = false;
        ResultSet rs = null;

        try {

            con = new DBConnection();
            if (con.connect()) {

                cstmt = (CallableStatement) con.getConnection().prepareCall("{call sp_ins_usergraph(?,?)}");


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
    public boolean saveOrUpdate(User entity) throws SQLException {

        ResultSet rs = null;
        boolean retval = false;

        try {

            con = new DBConnection();
            if (con.connect()) {

                if (entity.getUserid() != null) {

                    cstmt = (CallableStatement) con.getConnection().prepareCall("{call sp_upd_user(?,?,?,?,?,?,?,?,?,?,?,?)}");
                    cstmt.setInt("p_userid", entity.getUserid());

                } else {
                    cstmt = (CallableStatement) con.getConnection().prepareCall("{call sp_ins_user(?,?,?,?,?,?,?,?,?,?,?)}");
                }


                cstmt.setString("p_username", entity.getUsername());
                cstmt.setString("p_password", entity.getPassword());
                cstmt.setString("p_email", entity.getEmail());
                cstmt.setString("p_firstname", entity.getFirstname());
                cstmt.setString("p_lastname", entity.getLastname());
                cstmt.setDate("p_dob", Utilities.getSqlDate(entity.getDob()));
                cstmt.setString("p_state", entity.getState());
                cstmt.setString("p_city", entity.getCity());
                cstmt.setString("p_country", entity.getCountry());
                cstmt.setBoolean("p_isactive", entity.getIsactive());
                cstmt.setDate("p_timeofregistration", Utilities.getSqlDate(entity.getTimeofregistration()));

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
    public void saveOrUpdateAll(List<User> entities) throws SQLException {
        ResultSet rs = null;

        try {

            con = new DBConnection();
            if (con.connect()) {

                Iterator<User> iterator = entities.iterator();

                while (iterator.hasNext()) {

                    User entity = iterator.next();

                    if (entity.getUserid() != null) {

                        cstmt = (CallableStatement) con.getConnection().prepareCall("{call sp_upd_user(?,?,?,?,?,?,?,?,?,?,?,?)}");
                        cstmt.setInt("p_userid", entity.getUserid());

                    } else {
                        cstmt = (CallableStatement) con.getConnection().prepareCall("{call sp_ins_user(?,?,?,?,?,?,?,?,?,?,?)}");
                    }

                    cstmt.setString("p_username", entity.getUsername());
                    cstmt.setString("p_password", entity.getPassword());
                    cstmt.setString("p_email", entity.getEmail());
                    cstmt.setString("p_firstname", entity.getFirstname());
                    cstmt.setString("p_lastname", entity.getLastname());
                    cstmt.setDate("p_dob", Utilities.getSqlDate(entity.getDob()));
                    cstmt.setString("p_state", entity.getState());
                    cstmt.setString("p_city", entity.getCity());
                    cstmt.setString("p_country", entity.getCountry());
                    cstmt.setBoolean("p_isactive", entity.getIsactive());
                    cstmt.setDate("p_timeofregistration", Utilities.getSqlDate(entity.getTimeofregistration()));
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
    public User findById(Integer key) throws SQLException {
        User retval = null;
        ResultSet rs = null;
        PreparedStatement pstmt = null;

        try {

            con = new DBConnection();

            if (con.connect()) {

                String sql = "select * from user where userid=?";
                pstmt = con.getConnection().prepareStatement(sql);
                pstmt.setInt(1, key);

                rs = con.customQuery(pstmt);

                while (rs.next()) {

                    User user = new User();

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

                    retval = user;
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
    public List<User> getAll() throws SQLException {
        List<User> retval = new ArrayList<User>();
        ResultSet rs = null;

        try {

            con = new DBConnection();

            if (con.connect()) {

                cstmt = (CallableStatement) con.getConnection().prepareCall("{call sp_sel_user()}");

                rs = con.customQuery(cstmt);

                while (rs.next()) {

                    User user = new User();

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
    public boolean delete(Integer key) throws SQLException {
        boolean retval = false;
        ResultSet rs = null;


        try {

            con = new DBConnection();

            if (con.connect()) {

                cstmt = (CallableStatement) con.getConnection().prepareCall("{call sp_del_user(?)}");

                cstmt.setInt(1, key);

                rs = con.customQuery(cstmt);

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
    public boolean deleteAll(List<User> entities) throws SQLException {

        boolean retval = false;

        try {

            con = new DBConnection();

            if (con.connect()) {


                Iterator<User> iterator = entities.iterator();

                while (iterator.hasNext()) {

                    User entity = iterator.next();

                    cstmt = (CallableStatement) con.getConnection().prepareCall("{call sp_del_user(?)}");
                    cstmt.setInt(1, entity.getUserid());

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
