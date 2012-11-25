/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.surpriseme.DAOImpl;

import com.surpriseme.DAO.ArticleTagDAO;
import com.surpriseme.DAO.TagDAO;
import com.surpriseme.entities.ArticleTag;
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
public class TagDAOImpl implements TagDAO {
    
    CallableStatement cstmt;
    DBConnection con;
    private static final Logger logger = Logger.getLogger(TagDAOImpl.class);
    
    @Override
    public Integer saveOrUpdate(Tag entity) throws SQLException {
        ResultSet rs = null;
        Integer retval = null;
        
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

                //cstmt = (CallableStatement) con.getConnection().prepareCall("{call sp_del_tag(?)}");

                //cstmt.setInt(1, key);
                String sql = "Delete from articletag where tagid=" + key;
                con.executeQuery(sql);
                
                sql = "Delete from tag where tagid=" + key;
                con.executeQuery(sql);
                
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
    public Integer checkIfTagExist(String tagname) throws SQLException {
        Integer retval = null;
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        
        try {
            
            con = new DBConnection();
            
            if (con.connect()) {
                
                String sql = "select tagid from tag where lower(name)=lower(?)";
                pstmt = con.getConnection().prepareStatement(sql);
                pstmt.setString(1, tagname);
                
                rs = con.customQuery(pstmt);
                
                while (rs.next()) {
                    
                    retval = rs.getInt("tagid");
                    
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
    
    public int getArticleID(int tagid) throws SQLException {
        int artid = 0;
        ResultSet rs = null;
        
        try {
            
            con = new DBConnection();
            
            if (con.connect()) {
                
                cstmt = (CallableStatement) con.getConnection().prepareCall("{call sp_sel_getarticleid(?)}");
                
                cstmt.setInt("id", tagid);
                
                
                rs = con.saveOrUpdate(cstmt);
                while (rs.next()) {
                    artid = Integer.parseInt(rs.getString("0"));
                }
            }
            
            
        } catch (ClassNotFoundException ex) {
            logger.log(Priority.ERROR, ex.toString());
        } catch (SQLException ex) {
            throw ex;
        } finally {
            con.disconnect();
        }
        
        return artid;
    }
    
    public List<Tag> getRemaining(int articleid) throws SQLException {
        List<Tag> retval = new ArrayList<Tag>();
        ResultSet rs = null;
        
        try {
            
            con = new DBConnection();
            
            if (con.connect()) {
                
                cstmt = (CallableStatement) con.getConnection().prepareCall("{call sp_sel_remaining(" + articleid + ")}");
                
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
        
        System.out.println("retval" + retval);
        return retval;
        
    }
    
    public void savechanges(List<ArticleTag> entities) throws SQLException {
        ResultSet rs = null;
        
        try {
            
            int articleid = entities.get(0).getArticleid();
            deleteAllArticleTags(articleid);
            for (int i = 0; i < entities.size(); i++) {
                
                ArticleTagDAO articleTagDao = new ArticleTagDAOImpl();
                
                ArticleTag at = new ArticleTag(entities.get(i).getArticleid(), entities.get(i).getTagid(), new Timestamp(new Date().getTime()));
                
                articleTagDao.saveOrUpdate(at);
                
            }
            
            
            
        } catch (Exception e) {
        } finally {
            cstmt.close();
            
            con.disconnect();
        }
        
        
        
    }
    
    public List<Tag> getTags(int articleid) throws SQLException {
        List<Tag> retval = new ArrayList<Tag>();
        ResultSet rs = null;
        
        try {
            
            con = new DBConnection();
            
            if (con.connect()) {
                
                cstmt = (CallableStatement) con.getConnection().prepareCall("{call sp_sel_article_tag(" + articleid + ")}");
                
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
    
    public boolean deleteAllArticleTags(int articleid) throws SQLException {
        boolean retval = false;
        
        try {
            
            con = new DBConnection();
            
            if (con.connect()) {
                String sql = "Delete from articletag where articleid=" + articleid;
                
                con.executeQuery(sql);
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
