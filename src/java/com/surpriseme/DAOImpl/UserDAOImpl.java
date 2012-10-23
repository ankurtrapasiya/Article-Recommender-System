/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.surpriseme.DAOImpl;

import com.surpriseme.DAO.UserDAO;
import com.surpriseme.entities.User;
import com.surpriseme.utils.DBConnection;
import com.surpriseme.utils.UtilityMethods;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
    public boolean blockUser(Integer userid) throws SQLException {
        boolean retval = false;


        try {

            con = new DBConnection();

            if (con.connect()) {

                con.executeQuery("update ");
                
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
    public boolean unblockUser(Integer userid) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<User> searchUser(String firstName, String lastName, String email) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean addToCircle(Integer userId) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean removeFromCircle(Integer userId) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ResultSet saveOrUpdate(User entity) throws SQLException {

        ResultSet rs = null;

        try {

            con = new DBConnection();
            if (con.connect()) {

                cstmt = (CallableStatement) con.getConnection().prepareCall("{call sp_ins_user(?,?,?,?,?,?,?,?,?,?,?)}");


                cstmt.setString("p_username", entity.getUsername());
                cstmt.setString("p_password", entity.getPassword());
                cstmt.setString("p_email", entity.getEmail());
                cstmt.setString("p_firstname", entity.getFirstname());
                cstmt.setString("p_lastname", entity.getLastname());
                cstmt.setDate("p_dob", UtilityMethods.getSqlDate(entity.getDob()));
                cstmt.setString("p_state", entity.getState());
                cstmt.setString("p_city", entity.getCity());
                cstmt.setString("p_country", entity.getCountry());
                cstmt.setBoolean("p_isactive", entity.getIsactive());
                cstmt.setDate("p_timeofregistration", UtilityMethods.getSqlDate(entity.getTimeofregistration()));
                rs = con.saveOrUpdate(cstmt);

            }

        } catch (ClassNotFoundException ex) {
            logger.log(Priority.ERROR, ex.toString());
        } catch (SQLException e) {
            throw e;
        } finally {
            cstmt.close();
            con.disconnect();
        }

        return rs;

    }

    @Override
    public ResultSet saveOrUpdateAll(List<User> entities) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public User findById(Integer key) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<User> getAll() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean delete(Integer key) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean deleteAll(List<User> entities) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
