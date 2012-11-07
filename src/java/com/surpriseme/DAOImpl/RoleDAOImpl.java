/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.surpriseme.DAOImpl;

import com.surpriseme.DAO.RoleDAO;
import com.surpriseme.entities.Role;
import com.surpriseme.entities.Tag;
import com.surpriseme.utils.DBConnection;
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
public class RoleDAOImpl implements RoleDAO {

    CallableStatement cstmt;
    DBConnection con;
    private static final Logger logger = Logger.getLogger(TagDAOImpl.class);

    @Override
    public boolean assignRole(String rolename, Integer userid) throws SQLException {

        boolean retval = false;

        ResultSet rs = null;

        try {

            con = new DBConnection();

            if (con.connect()) {

                cstmt = (CallableStatement) con.getConnection().prepareCall("{call sp_ins_userrole(?,?,?)}");

                cstmt.setString("p_rolename", rolename);
                cstmt.setInt("p_userid", userid);

                UserDAOImpl userDao = new UserDAOImpl();
                cstmt.setString("p_username", userDao.findById(userid).getUsername());

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
    public String saveOrUpdate(Role entity) throws SQLException {

        ResultSet rs = null;
        String retval = null;

        try {

            con = new DBConnection();
            if (con.connect()) {

                if (entity.getRolename() != null) {

                    cstmt = (CallableStatement) con.getConnection().prepareCall("{call sp_upd_role(?)}");

                } else {
                    cstmt = (CallableStatement) con.getConnection().prepareCall("{call sp_ins_role(?,?)}");
                }

                cstmt.setString("p_rolename", entity.getRolename());
                cstmt.setString("p_description", entity.getDescription());

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

        return entity.getRolename();
    }

    @Override
    public void saveOrUpdateAll(List<Role> entities) throws SQLException {

        ResultSet rs = null;

        try {

            con = new DBConnection();
            if (con.connect()) {

                Iterator<Role> iterator = entities.iterator();

                while (iterator.hasNext()) {

                    Role entity = iterator.next();

                    if (entity.getRolename() != null) {

                        cstmt = (CallableStatement) con.getConnection().prepareCall("{call sp_upd_role(?)}");

                    } else {
                        cstmt = (CallableStatement) con.getConnection().prepareCall("{call sp_ins_role(?,?)}");
                    }

                    cstmt.setString("p_rolename", entity.getRolename());
                    cstmt.setString("p_description", entity.getDescription());

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
    public Role findById(String key) throws SQLException {

        Role retval = null;
        ResultSet rs = null;
        PreparedStatement pstmt = null;

        try {

            con = new DBConnection();

            if (con.connect()) {

                String sql = "select * from role where rolename=?";
                pstmt = con.getConnection().prepareStatement(sql);
                pstmt.setString(1, key);

                rs = con.customQuery(pstmt);

                while (rs.next()) {

                    Role role = new Role();


                    role.setRolename(rs.getString("rolename"));
                    role.setDescription(rs.getString("description"));

                    retval = role;
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
    public List<Role> getAll() throws SQLException {
        List<Role> retval = new ArrayList<Role>();
        ResultSet rs = null;

        try {

            con = new DBConnection();

            if (con.connect()) {

                cstmt = (CallableStatement) con.getConnection().prepareCall("{call sp_sel_role()}");

                rs = con.customQuery(cstmt);

                while (rs.next()) {

                    Role role = new Role();

                    role.setRolename(rs.getString("rolename"));
                    role.setDescription(rs.getString("description"));

                    retval.add(role);
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
    public boolean delete(String key) throws SQLException {
        boolean retval = false;
        ResultSet rs = null;


        try {

            con = new DBConnection();

            if (con.connect()) {

                cstmt = (CallableStatement) con.getConnection().prepareCall("{call sp_del_role(?)}");

                cstmt.setString(1, key);

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
    public boolean deleteAll(List<Role> entities) throws SQLException {
        boolean retval = false;

        try {

            con = new DBConnection();

            if (con.connect()) {


                Iterator<Role> iterator = entities.iterator();

                while (iterator.hasNext()) {

                    Role entity = iterator.next();

                    cstmt = (CallableStatement) con.getConnection().prepareCall("{call sp_del_role(?)}");
                    cstmt.setString(1, entity.getRolename());

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
