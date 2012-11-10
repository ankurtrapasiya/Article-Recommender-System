/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.surpriseme.DAOImpl;

import com.surpriseme.DAO.UserActivationDAO;
import com.surpriseme.entities.UserActivation;
import com.surpriseme.utils.DBConnection;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.apache.log4j.Logger;
import org.apache.log4j.Priority;

/**
 *
 * @author ankur
 */
public class UserActivationDAOImpl implements UserActivationDAO {

    CallableStatement cstmt;
    DBConnection con;
    private static final Logger logger = Logger.getLogger(UserDAOImpl.class);

    @Override
    public Integer saveOrUpdate(UserActivation entity) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void saveOrUpdateAll(List<UserActivation> entities) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public UserActivation findById(Integer key) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<UserActivation> getAll() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean delete(Integer key) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean deleteAll(List<UserActivation> entities) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean verifyToken(String token) throws SQLException {

        UserActivation ua;
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        boolean retval = false;
        try {
            con = new DBConnection();

            if (con.connect()) {

                String sql = "{ call sp_sel_useractivationbytoken (?) }";
                pstmt = con.getConnection().prepareStatement(sql);
                pstmt.setString(1, token);

                rs = con.customQuery(pstmt);
                if (rs.next()) {
                    retval = true;

                } else {
                    retval = false;
                }
            }


        } catch (ClassNotFoundException e) {
            logger.log(Priority.ERROR, e.toString());
        } catch (SQLException ex) {
            throw ex;
        } finally {
            con.disconnect();
            return retval;
        }

    }

    public boolean activateUser(String token) throws SQLException {
        boolean retval = false;
        try {
            con = new DBConnection();
            if (con.connect()) {
                String updatequery = "update surpriseme.useractivation set isactive = 1 where token = '" + token + "'";
                retval = con.executeQuery(updatequery);

            }

        } catch (ClassNotFoundException e) {
        }

        return retval;

    }
}
