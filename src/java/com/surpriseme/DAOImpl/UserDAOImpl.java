/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.surpriseme.DAOImpl;

import com.surpriseme.DAO.UserDAO;
import com.surpriseme.entities.BlockedUsers;
import com.surpriseme.entities.Interest;
import com.surpriseme.entities.User;
import com.surpriseme.entities.UserActivation;
import com.surpriseme.entities.UserInterest;
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
    public Integer saveOrUpdate(User entity) throws SQLException {

        ResultSet rs = null;
        Integer retval = null;

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

    @Override
    public User findByUsername(String username) throws SQLException {
        User retval = null;
        ResultSet rs = null;
        PreparedStatement pstmt = null;

        try {

            con = new DBConnection();

            if (con.connect()) {

                String sql = "select * from user where username=?";
                pstmt = con.getConnection().prepareStatement(sql);
                pstmt.setString(1, username);

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
    public boolean sendActivationMail(UserActivation ua) throws SQLException {

        boolean retval = false;
        ResultSet rs = null;

        try {

            con = new DBConnection();
            if (con.connect()) {

                cstmt = (CallableStatement) con.getConnection().prepareCall("{call sp_ins_useractivation(?,?,?,?)}");

                cstmt.setInt("p_userid", ua.getUserid());
                cstmt.setString("p_token", ua.getToken());
                cstmt.setTimestamp("p_timestamp", ua.getTimestamp());
                cstmt.setBoolean("p_isactive", ua.getIsactive());

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
    public boolean addInterestToUser(Integer interestid, Integer userid) throws SQLException {
        boolean retval = false;
        ResultSet rs = null;

        try {

            con = new DBConnection();
            if (con.connect()) {

                cstmt = (CallableStatement) con.getConnection().prepareCall("{call sp_ins_userinterest(?,?)}");


                cstmt.setInt("p_userid", userid);
                cstmt.setInt("p_interestid", interestid);

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
    public boolean removeInterestFromUser(Integer interestid, Integer userid) throws SQLException {
        boolean retval = false;
        ResultSet rs = null;

        try {

            con = new DBConnection();
            if (con.connect()) {

                cstmt = (CallableStatement) con.getConnection().prepareCall("{call sp_del_userinterest(?,?)}");


                cstmt.setInt("p_userid", userid);
                cstmt.setInt("p_interestid", interestid);

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
    public List<Interest> getAllUserInterests(Integer userid) throws SQLException {
        List<Interest> retval = new ArrayList<Interest>();
        ResultSet rs = null;

        try {

            con = new DBConnection();

            if (con.connect()) {

                String query = "select * from userinterest where userid=" + userid;

                rs = con.customQuery(query);

                List<UserInterest> list = new ArrayList<UserInterest>();

                while (rs.next()) {

                    UserInterest userinterest = new UserInterest();

                    userinterest.setUserid(rs.getInt("userid"));
                    userinterest.setInterestid(rs.getInt("interestid"));

                    list.add(userinterest);
                }


                Iterator<UserInterest> i = list.iterator();
                InterestDAOImpl impl = new InterestDAOImpl();

                while (i.hasNext()) {

                    UserInterest ui = i.next();
                    Interest interest = impl.findById(ui.getInterestid());

                    retval.add(interest);
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

    @Override
    public List<BlockedUsers> getBlockedUsers() throws SQLException {
        List<BlockedUsers> retval = null;
        ResultSet rs = null;
        PreparedStatement pstmt = null;

        try {

            con = new DBConnection();

            if (con.connect()) {

                String sql = "select * from blockeduser where isactive=?";
                pstmt = con.getConnection().prepareStatement(sql);
                pstmt.setBoolean(1, true);

                rs = con.customQuery(pstmt);

                while (rs.next()) {

                    BlockedUsers user = new BlockedUsers();

                    user.setUserid(rs.getInt("userid"));
                    user.setBlockerid(rs.getInt("blockerid"));
                    user.setTimestamp(rs.getTimestamp("timestamp"));
                    user.setReason(rs.getString("reason"));
                    user.setIsActive(rs.getBoolean("isactive"));

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
