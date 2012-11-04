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
import com.surpriseme.entities.UserSuggestions;
import com.surpriseme.utils.DBConnection;
import com.surpriseme.utils.Utilities;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.apache.log4j.Level;
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
    public boolean blockUserRequest(BlockedUsers entity) throws SQLException {

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
    public boolean blockUser(Integer userid, Integer blockerid) throws SQLException {
        boolean retval = false;
        ResultSet rs = null;

        try {

            con = new DBConnection();

            if (con.connect()) {

                cstmt = (CallableStatement) con.getConnection().prepareCall("{call sp_upd_blockedusers(?,?,?,?,?)}");

                Timestamp stamp = new Timestamp(new Date().getTime());

                cstmt.setInt("p_userid", userid);
                cstmt.setInt("p_blockerid", blockerid);
                cstmt.setTimestamp("p_timestamp", new java.sql.Timestamp(stamp.getTime()));
                cstmt.setString("p_reason", "");
                cstmt.setBoolean("p_isactive", false);

                rs = con.customQuery(cstmt);

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
    public boolean unblockUser(Integer userid, Integer blockerid) throws SQLException {

        boolean retval = false;
        ResultSet rs = null;

        try {

            con = new DBConnection();

            if (con.connect()) {

                cstmt = (CallableStatement) con.getConnection().prepareCall("{call sp_del_blockedusers(?,?)}");

                cstmt.setInt("p_userid", userid);
                cstmt.setInt("p_blockerid", blockerid);

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
                    user.setImage(rs.getString("image"));

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

                cstmt = (CallableStatement) con.getConnection().prepareCall("{call sp_ins_usergraph(?,?,?)}");


                cstmt.setInt("p_friendid", friendId);
                cstmt.setInt("p_userid", userId);
                cstmt.setBoolean("p_isnotifitied", false);

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

                    cstmt = (CallableStatement) con.getConnection().prepareCall("{call sp_upd_user(?,?,?,?,?,?,?,?,?,?,?,?,?)}");
                    cstmt.setInt("p_userid", entity.getUserid());

                } else {
                    cstmt = (CallableStatement) con.getConnection().prepareCall("{call sp_ins_user(?,?,?,?,?,?,?,?,?,?,?,?)}");
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
                cstmt.setString("p_image", entity.getImage());

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

                        cstmt = (CallableStatement) con.getConnection().prepareCall("{call sp_upd_user(?,?,?,?,?,?,?,?,?,?,?,?,?)}");
                        cstmt.setInt("p_userid", entity.getUserid());

                    } else {
                        cstmt = (CallableStatement) con.getConnection().prepareCall("{call sp_ins_user(?,?,?,?,?,?,?,?,?,?,?,?)}");
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
                    cstmt.setString("p_image", entity.getImage());

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
                    user.setImage(rs.getString("image"));

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
                    user.setImage(rs.getString("image"));

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
                    user.setImage(rs.getString("image"));

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
    public List<Interest> getUserInterests(Integer userid, boolean include) throws SQLException {
        List<Interest> retval = new ArrayList<Interest>();
        ResultSet rs = null;
        String query = "";

        try {

            con = new DBConnection();

            if (con.connect()) {

                if (include) {
                    query = "select * from userinterest where userid=" + userid;
                } else {
                    query = "select * from interest where interestid NOT IN(select interestid from userinterest where userid=" + userid + ")";
                }
                rs = con.customQuery(query);

                List<UserInterest> list = new ArrayList<UserInterest>();
                if (include) {
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
                } else {
                    InterestDAOImpl impl = new InterestDAOImpl();
                    while (rs.next()) {
                        Interest interest = new Interest();
                        int id = rs.getInt("interestid");
                        interest = impl.findById(id);
                        retval.add(interest);
                    }
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

                String sql = "select * from blockedusers where isactive=?";
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
    public List<BlockedUsers> getAllBlocked() throws SQLException {
        List<BlockedUsers> retval = new ArrayList<BlockedUsers>();
        ResultSet rs = null;

        try {

            con = new DBConnection();

            if (con.connect()) {

                cstmt = (CallableStatement) con.getConnection().prepareCall("{call sp_sel_blockedusers()}");

                rs = con.customQuery(cstmt);

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

    @Override
    public User isValidUser(String username, String password) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        User ret = null;
        try {

            con = new DBConnection();
            con.connect();

            String sql = "select * from user where username=? and password=? and isactive=?";

            pstmt = con.getConnection().prepareStatement(sql);
            pstmt.setString(1, username);
            pstmt.setString(2, Utilities.toMD5(password));
            pstmt.setBoolean(3, true);


            rs = pstmt.executeQuery();

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
                user.setImage(rs.getString("image"));

                ret = user;
            }


        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UserDAOImpl.class.getName()).log(Level.ERROR, null, ex);
        } catch (SQLException e) {
            throw e;
        } finally {
            con.disconnect();
        }
        return ret;
    }
}
