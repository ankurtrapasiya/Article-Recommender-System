/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.surpriseme.DAOImpl;

import com.surpriseme.DAO.SourceDAO;
import com.surpriseme.entities.Source;
import com.surpriseme.utils.DBConnection;
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
public class SourceDAOImpl implements SourceDAO {

    CallableStatement cstmt;
    DBConnection con;
    private static final Logger logger = Logger.getLogger(SourceDAOImpl.class);

    @Override
    public Integer saveOrUpdate(Source entity) throws SQLException {

        ResultSet rs = null;
        Integer retval = null;

        try {

            con = new DBConnection();
            if (con.connect()) {

                if (entity.getSourceid() != null) {

                    cstmt = (CallableStatement) con.getConnection().prepareCall("{call sp_upd_source(?,?,?,?,?,?)}");
                    cstmt.setInt("p_sourceid", entity.getSourceid());

                } else {
                    cstmt = (CallableStatement) con.getConnection().prepareCall("{call sp_ins_source(?,?,?,?,?)}");
                }

                cstmt.setString("p_name", entity.getName());
                cstmt.setString("p_url", entity.getUrl());
                cstmt.setString("p_feedurl", entity.getFeedurl());
                cstmt.setString("p_icon", entity.getIcon());
                cstmt.setBoolean("p_isactive", entity.getIsactive());

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
    public void saveOrUpdateAll(List<Source> entities) throws SQLException {
        ResultSet rs = null;

        try {

            con = new DBConnection();
            if (con.connect()) {

                Iterator<Source> iterator = entities.iterator();

                while (iterator.hasNext()) {

                    Source entity = iterator.next();

                    if (entity.getSourceid() != null) {

                        cstmt = (CallableStatement) con.getConnection().prepareCall("{call sp_upd_source(?,?,?,?,?,?)}");
                        cstmt.setInt("p_sourceid", entity.getSourceid());

                    } else {
                        cstmt = (CallableStatement) con.getConnection().prepareCall("{call sp_ins_source(?,?,?,?,?)}");
                    }

                    cstmt.setString("p_name", entity.getName());
                    cstmt.setString("p_url", entity.getUrl());
                    cstmt.setString("p_feedurl", entity.getFeedurl());
                    cstmt.setString("p_icon", entity.getIcon());
                    cstmt.setBoolean("p_isactive", entity.getIsactive());

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
    public Source findById(Integer key) throws SQLException {
        Source retval = null;
        ResultSet rs = null;
        PreparedStatement pstmt = null;

        try {

            con = new DBConnection();

            if (con.connect()) {

                String sql = "select * from source where sourceid=?";
                pstmt = con.getConnection().prepareStatement(sql);
                pstmt.setInt(1, key);

                rs = con.customQuery(pstmt);

                while (rs.next()) {

                    Source source = new Source();
                    source.setSourceid(rs.getInt("sourceid"));
                    source.setName(rs.getString("name"));
                    source.setUrl(rs.getString("url"));
                    source.setFeedurl(rs.getString("feedurl"));
                    source.setIcon(rs.getString("icon"));
                    source.setIsactive(rs.getBoolean("isactive"));

                    retval = source;
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
    public List<Source> getAll() throws SQLException {
        List<Source> retval = new ArrayList<Source>();
        ResultSet rs = null;

        try {

            con = new DBConnection();

            if (con.connect()) {

                cstmt = (CallableStatement) con.getConnection().prepareCall("{call sp_sel_source()}");

                rs = con.customQuery(cstmt);

                while (rs.next()) {

                    Source source = new Source();

                    source.setSourceid(rs.getInt("sourceid"));
                    source.setName(rs.getString("name"));
                    source.setUrl(rs.getString("url"));
                    source.setFeedurl(rs.getString("feedurl"));
                    source.setIcon(rs.getString("icon"));
                    source.setIsactive(rs.getBoolean("isactive"));

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

    @Override
    public boolean delete(Integer key) throws SQLException {
        boolean retval = false;
        ResultSet rs = null;


        try {

            con = new DBConnection();

            if (con.connect()) {

                cstmt = (CallableStatement) con.getConnection().prepareCall("{call sp_del_source(?)}");

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
    public boolean deleteAll(List<Source> entities) throws SQLException {

        boolean retval = false;

        try {

            con = new DBConnection();

            if (con.connect()) {


                Iterator<Source> iterator = entities.iterator();

                while (iterator.hasNext()) {

                    Source entity = iterator.next();

                    cstmt = (CallableStatement) con.getConnection().prepareCall("{call sp_del_source(?)}");
                    cstmt.setInt(1, entity.getSourceid());

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
