/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.surpriseme.DAOImpl;

import com.surpriseme.DAO.InterestDAO;
import com.surpriseme.entities.Interest;
import com.surpriseme.entities.Source;
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
public class InterestDAOImpl implements InterestDAO {

    CallableStatement cstmt;
    DBConnection con;
    private static final Logger logger = Logger.getLogger(UserDAOImpl.class);

    @Override
    public Integer saveOrUpdate(Interest entity) throws SQLException {

        ResultSet rs = null;
        Integer retval = null;

        try {

            con = new DBConnection();
            if (con.connect()) {

                if (entity.getInterestid() != null) {

                    cstmt = (CallableStatement) con.getConnection().prepareCall("{call sp_upd_interest(?,?,?,?)}");
                    cstmt.setInt("p_interestid", entity.getInterestid());

                } else {
                    cstmt = (CallableStatement) con.getConnection().prepareCall("{call sp_ins_interest(?,?,?,?,?)}");
                }


                cstmt.setString("p_name", entity.getName());
                cstmt.setString("p_description", entity.getDescription());
                cstmt.setString("p_iconpath", entity.getIconpath());
                cstmt.setTimestamp("p_timestamp", entity.getTimestamp());

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
    public void saveOrUpdateAll(List<Interest> entities) throws SQLException {

        ResultSet rs = null;

        try {

            con = new DBConnection();
            if (con.connect()) {

                Iterator<Interest> iterator = entities.iterator();

                while (iterator.hasNext()) {

                    Interest entity = iterator.next();

                    if (entity.getInterestid() != null) {

                        cstmt = (CallableStatement) con.getConnection().prepareCall("{call sp_upd_interest(?,?,?,?,?)}");
                        cstmt.setInt("p_interestid", entity.getInterestid());

                    } else {
                        cstmt = (CallableStatement) con.getConnection().prepareCall("{call sp_ins_interest(?,?,?,?)}");
                    }

                    cstmt.setString("p_name", entity.getName());
                    cstmt.setString("p_description", entity.getDescription());
                    cstmt.setString("p_iconpath", entity.getIconpath());
                    cstmt.setTimestamp("p_timestamp", entity.getTimestamp());

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
    public Interest findById(Integer key) throws SQLException {
        Interest retval = null;
        ResultSet rs = null;
        PreparedStatement pstmt = null;

        try {

            con = new DBConnection();

            if (con.connect()) {

                String sql = "select * from interest where interestid=?";
                pstmt = con.getConnection().prepareStatement(sql);
                pstmt.setInt(1, key);

                rs = con.customQuery(pstmt);

                while (rs.next()) {

                    Interest interest = new Interest();

                    interest.setInterestid(rs.getInt("interestid"));
                    interest.setName(rs.getString("name"));
                    interest.setDescription(rs.getString("description"));
                    interest.setIconpath(rs.getString("iconpath"));
                    interest.setTimestamp(rs.getTimestamp("timestamp"));

                    retval = interest;
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
    public List<Interest> getAll() throws SQLException {

        List<Interest> retval = new ArrayList<Interest>();
        ResultSet rs = null;

        try {

            con = new DBConnection();

            if (con.connect()) {

                cstmt = (CallableStatement) con.getConnection().prepareCall("{call sp_sel_interest()}");

                rs = con.customQuery(cstmt);

                while (rs.next()) {

                    Interest interest = new Interest();

                    interest.setInterestid(rs.getInt("interestid"));
                    interest.setName(rs.getString("name"));
                    interest.setDescription(rs.getString("description"));
                    interest.setIconpath(rs.getString("iconpath"));
                    interest.setTimestamp(rs.getTimestamp("timestamp"));


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
    public boolean delete(Integer key) throws SQLException {
        boolean retval = false;
        ResultSet rs = null;


        try {

            con = new DBConnection();

            if (con.connect()) {

                cstmt = (CallableStatement) con.getConnection().prepareCall("{call sp_del_interest(?)}");

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
    public boolean deleteAll(List<Interest> entities) throws SQLException {
        boolean retval = false;

        try {

            con = new DBConnection();

            if (con.connect()) {


                Iterator<Interest> iterator = entities.iterator();

                while (iterator.hasNext()) {

                    Interest entity = iterator.next();

                    cstmt = (CallableStatement) con.getConnection().prepareCall("{call sp_del_interest(?)}");
                    cstmt.setInt(1, entity.getInterestid());

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
    public List<Integer> getUsersOfInterest(Integer interestid) throws SQLException {
        List<Integer> retval = null;
        ResultSet rs = null;
        PreparedStatement pstmt = null;

        try {

            con = new DBConnection();

            if (con.connect()) {

                String sql = "select userid from userinterest where interestid=?";
                pstmt = con.getConnection().prepareStatement(sql);
                pstmt.setInt(1, interestid);

                rs = con.customQuery(pstmt);

                while (rs.next()) {

                    Integer userid = rs.getInt("userid");

                    retval.add(userid);
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
    public List<Source> getSourcesOfIntereset(Integer interestid) throws SQLException {
        List<Source> retval = null;
        ResultSet rs = null;
        PreparedStatement pstmt = null;

        try {

            con = new DBConnection();

            if (con.connect()) {

                String sql = "select sourceid from interestsources where interestid=?";
                pstmt = con.getConnection().prepareStatement(sql);
                pstmt.setInt(1, interestid);

                rs = con.customQuery(pstmt);

                SourceDAOImpl sourceDao = new SourceDAOImpl();

                while (rs.next()) {

                    Source source = null;

                    Integer sourceid = rs.getInt("sourceid");

                    source = sourceDao.findById(sourceid);

                    retval.add(source);
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
