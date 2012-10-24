/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.surpriseme.DAOImpl;

import com.surpriseme.DAO.TagDAO;
import com.surpriseme.entities.Tag;
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
import org.apache.log4j.Logger;
import org.apache.log4j.Priority;

/**
 *
 * @author ankur
 */
public class TagDAOImpl implements TagDAO {

    CallableStatement cstmt;
    DBConnection con;
    private static final Logger logger = Logger.getLogger(UserDAOImpl.class);

    @Override
    public boolean addTagToArticle(Integer tagId, Integer ArticleId) throws SQLException {

        boolean retval = false;

        ResultSet rs = null;

        try {

            con = new DBConnection();

            if (con.connect()) {

                cstmt = (CallableStatement) con.getConnection().prepareCall("{call sp_ins_articletag(?,?,?)}");

                cstmt.setInt("p_articleid", ArticleId);
                cstmt.setInt("p_tagid", tagId);
                cstmt.setTimestamp("p_timestamp", new Timestamp(new Date().getTime()));

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
    public boolean saveOrUpdate(Tag entity) throws SQLException {
        ResultSet rs = null;
        boolean retval = false;

        try {

            con = new DBConnection();
            if (con.connect()) {

                if (entity.getTagid() != null) {

                    cstmt = (CallableStatement) con.getConnection().prepareCall("{call sp_upd_tag(?,?,?,?)}");
                    cstmt.setInt("p_tagid", entity.getTagid());

                } else {
                    cstmt = (CallableStatement) con.getConnection().prepareCall("{call sp_ins_tag(?,?,?)}");
                }


                cstmt.setString("p_name", entity.getName());
                cstmt.setString("p_icon", entity.getIcon());
                cstmt.setString("p_description", entity.getDescription());

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
    public void saveOrUpdateAll(List<Tag> entities) throws SQLException {
        ResultSet rs = null;

        try {

            con = new DBConnection();
            if (con.connect()) {

                Iterator<Tag> iterator = entities.iterator();

                while (iterator.hasNext()) {

                    Tag entity = iterator.next();

                    if (entity.getTagid() != null) {

                        cstmt = (CallableStatement) con.getConnection().prepareCall("{call sp_upd_tag(?,?,?,?)}");
                        cstmt.setInt("p_tagid", entity.getTagid());

                    } else {
                        cstmt = (CallableStatement) con.getConnection().prepareCall("{call sp_ins_tag(?,?,?)}");
                    }

                    cstmt.setString("p_name", entity.getName());
                    cstmt.setString("p_icon", entity.getIcon());
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
    public Tag findById(Integer key) throws SQLException {
        Tag retval = null;
        ResultSet rs = null;
        PreparedStatement pstmt = null;

        try {

            con = new DBConnection();

            if (con.connect()) {

                String sql = "select * from tag where tagid=?";
                pstmt = con.getConnection().prepareStatement(sql);
                pstmt.setInt(1, key);

                rs = con.customQuery(pstmt);

                while (rs.next()) {

                    Tag tag = new Tag();

                    tag.setTagid(rs.getInt("tagid"));
                    tag.setName(rs.getString("name"));
                    tag.setIcon(rs.getString("icon"));
                    tag.setDescription(rs.getString("description"));

                    retval = tag;
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
    public List<Tag> getAll() throws SQLException {
        List<Tag> retval = new ArrayList<Tag>();
        ResultSet rs = null;

        try {

            con = new DBConnection();

            if (con.connect()) {

                cstmt = (CallableStatement) con.getConnection().prepareCall("{call sp_sel_tag()}");

                rs = con.customQuery(cstmt);

                while (rs.next()) {

                    Tag tag = new Tag();

                    tag.setTagid(rs.getInt("tagid"));
                    tag.setName(rs.getString("name"));
                    tag.setIcon(rs.getString("icon"));
                    tag.setDescription(rs.getString("description"));

                    retval.add(tag);
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

                cstmt = (CallableStatement) con.getConnection().prepareCall("{call sp_del_tag(?)}");

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
    public boolean deleteAll(List<Tag> entities) throws SQLException {
        boolean retval = false;

        try {

            con = new DBConnection();

            if (con.connect()) {


                Iterator<Tag> iterator = entities.iterator();

                while (iterator.hasNext()) {

                    Tag entity = iterator.next();

                    cstmt = (CallableStatement) con.getConnection().prepareCall("{call sp_del_tag(?)}");
                    cstmt.setInt(1, entity.getTagid());

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
    public boolean removeTagFromArticle(Integer tagId, Integer ArticleId) throws SQLException {
        boolean retval = false;

        ResultSet rs = null;

        try {

            con = new DBConnection();

            if (con.connect()) {

                cstmt = (CallableStatement) con.getConnection().prepareCall("{call sp_del_articletag(?,?)}");

                cstmt.setInt("p_articleid", ArticleId);
                cstmt.setInt("p_tagid", tagId);
              
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
}
