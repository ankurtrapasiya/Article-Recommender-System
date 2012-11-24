/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.surpriseme.DAOImpl;

import com.surpriseme.DAO.UserDAO;
import com.surpriseme.entities.User;
import com.surpriseme.entities.UserActivation;
import com.surpriseme.helper.SuggesionsHelper;
import com.surpriseme.utils.DBConnection;
import com.surpriseme.utils.Utilities;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
    public List<User> searchUser(String name, Integer userid) throws SQLException {
        List<User> retval = new ArrayList<User>();
        ResultSet rs = null;
        PreparedStatement pstmt = null;

        try {

            con = new DBConnection();

            if (con.connect()) {

                String sql = "select * from user where username like ? or firstname like ? or lastname like ? and userid != ?";
                pstmt = con.getConnection().prepareStatement(sql);
                pstmt.setString(1, "%" + name + "%");
                pstmt.setString(2, "%" + name + "%");
                pstmt.setString(3, "%" + name + "%");
                pstmt.setInt(4, userid);

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
                    user.setState(rs.getInt("state"));
                    user.setCity(rs.getInt("city"));
                    user.setCountry(rs.getInt("country"));
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
                cstmt.setInt("p_state", entity.getState());
                cstmt.setInt("p_city", entity.getCity());
                cstmt.setInt("p_country", entity.getCountry());
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
                    cstmt.setInt("p_state", entity.getState());
                    cstmt.setInt("p_city", entity.getCity());
                    cstmt.setInt("p_country", entity.getCountry());
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
                    user.setState(rs.getInt("state"));
                    user.setCity(rs.getInt("city"));
                    user.setCountry(rs.getInt("country"));
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
                    user.setState(rs.getInt("state"));
                    user.setCity(rs.getInt("city"));
                    user.setCountry(rs.getInt("country"));
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
                    user.setState(rs.getInt("state"));
                    user.setCity(rs.getInt("city"));
                    user.setCountry(rs.getInt("country"));
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
    public boolean insertIntoUserActivation(UserActivation ua) throws SQLException {

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
                user.setState(rs.getInt("state"));
                user.setCity(rs.getInt("city"));
                user.setCountry(rs.getInt("country"));
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

    @Override
    public boolean isEmailAvailable(String email) throws SQLException {
        boolean available = false;
        ResultSet rs;
        try {
            con = new DBConnection();
            if (con.connect()) {

                rs = con.customQuery("select * from user where email = '" + email + "'");
                if (rs.next()) {
                    available = false;
                    System.out.println("Result set has record for this email id.");
                } else {
                    available = true;
                }
            }
        } catch (ClassNotFoundException e) {
        } catch (SQLException ex) {
            throw ex;
        } finally {
            return available;
        }
    }

    @Override
    public boolean isUsernameAvailable(String username) throws SQLException {
        boolean available = false;
        ResultSet rs;
        try {
            con = new DBConnection();
            if (con.connect()) {
                rs = con.customQuery("select * from user where username = '" + username + "'");
                if (rs.next()) {
                    available = false;
                } else {
                    available = true;
                }
            }
        } catch (ClassNotFoundException e) {
        } catch (SQLException ex) {
            throw ex;
        } finally {
            return available;
        }
    }

    @Override
    public List<SuggesionsHelper> getUserSuggestions() throws SQLException {

        ResultSet rs = null;
        PreparedStatement pstmt = null;
        String query = null;
        String query_update = null;
        List<User> retval = null;
        Boolean updated = false;
        SuggesionsHelper suggest = null;
        List<SuggesionsHelper> sugg = null;
        try {

            con = new DBConnection();

            if (con.connect()) {
                retval = new ArrayList<User>();
                sugg = new ArrayList<SuggesionsHelper>();

                query = "SELECT article.articleid, user.username, article.body, article.title, articlelinks.articleurl as url from user inner join usersuggestions on user.userid  = usersuggestions.friendid inner join article on article.articleid  = usersuggestions.articleid inner join articlelinks on articlelinks.articleid  = article.articleid where usersuggestions.isviewed =   0 and usersuggestions.friendid !=1";
                pstmt = con.getConnection().prepareStatement(query);
                rs = con.customQuery(pstmt);

                //   query_update = "UPDATE usersuggestions SET isviewed=1 WHERE isviewed=0";

                //     updated= con.executeQuery(query_update);
                while (rs.next()) {

                    suggest = new SuggesionsHelper();
                    suggest.setBody(rs.getString("body"));
                    suggest.setTitle(rs.getString("title"));
                    suggest.setUrl(rs.getString("url"));
                    suggest.setArticleid(rs.getInt("articleid"));
                    suggest.setUser(rs.getString("username"));
                    sugg.add(suggest);
                }

            }

        } catch (ClassNotFoundException ex) {
            logger.log(Priority.ERROR, ex.toString());
        } catch (SQLException ex) {
            throw ex;
        } finally {
            con.disconnect();
        }
        return sugg;
    }
}
