/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.surpriseme.DAOImpl;

import com.surpriseme.DAO.ArticleLinksDAO;
import com.surpriseme.entities.ArticleLinks;
import com.surpriseme.helper.ArticleLinksPK;
import com.surpriseme.utils.DBConnection;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.apache.log4j.Priority;

/**
 *
 * @author ankur
 */
public class ArticleLinksDAOImpl implements ArticleLinksDAO {
    
    CallableStatement cstmt;
    DBConnection con;
    private static final Logger logger = Logger.getLogger(UserDAOImpl.class);
    
    @Override
    public ArticleLinksPK saveOrUpdate(ArticleLinks entity) throws SQLException {
        ArticleLinksPK retval = null;
        ResultSet rs = null;
        
        try {
            
            con = new DBConnection();
            if (con.connect()) {
                
                cstmt = (CallableStatement) con.getConnection().prepareCall("{call sp_ins_articlelinks(?,?,?)}");
                
                
                cstmt.setInt("p_articleid", entity.getArticleid());
                cstmt.setString("p_articleurl", entity.getArticleurl());
                cstmt.setInt("p_sourceid", entity.getSourceid());
                
                rs = con.saveOrUpdate(cstmt);
                
            }
            
            retval = new ArticleLinksPK(entity.getArticleid(), entity.getSourceid());
            
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
    public void saveOrUpdateAll(List<ArticleLinks> entities) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public ArticleLinks findById(ArticleLinksPK key) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public List<ArticleLinks> getAll() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public boolean delete(ArticleLinksPK key) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public boolean deleteAll(List<ArticleLinks> entities) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public List<ArticleLinks> getSourcesOfArticle(Integer articleId) throws SQLException {
        
        List<ArticleLinks> retval = new ArrayList<ArticleLinks>();
        ResultSet rs = null;
        
        try {
            con = new DBConnection();
            if (con.connect()) {
                
                PreparedStatement pstmt = con.getConnection().prepareStatement("select * from articlelinks where articleid=?");
                
                pstmt.setInt(1, articleId);
                
                rs = con.customQuery(pstmt);
                
                while (rs.next()) {
                    
                    ArticleLinks al = new ArticleLinks();
                    al.setArticleid(rs.getInt("articleid"));
                    al.setArticleurl(rs.getString("articleurl"));
                    al.setSourceid(rs.getInt("sourceid"));
                    
                    retval.add(al);
                    
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
