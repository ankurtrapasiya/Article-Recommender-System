/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.surpriseme.DAOImpl;

import com.surpriseme.DAO.ArticleTagDAO;
import com.surpriseme.entities.Article;
import com.surpriseme.entities.ArticleTag;
import com.surpriseme.entities.Tag;
import com.surpriseme.helper.ArticleTagPK;
import com.surpriseme.utils.DBConnection;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;
import org.apache.log4j.Priority;

/**
 *
 * @author ankur
 */
public class ArticleTagDAOImpl implements ArticleTagDAO {

    CallableStatement cstmt;
    DBConnection con;
    private static final Logger logger = Logger.getLogger(TagDAOImpl.class);

    @Override
    public ArticleTagPK saveOrUpdate(ArticleTag entity) throws SQLException {
        ArticleTagPK articleTagPK = null;

        ResultSet rs = null;

        try {

            con = new DBConnection();

            if (con.connect()) {

                cstmt = (CallableStatement) con.getConnection().prepareCall("{call sp_ins_articletag(?,?,?)}");

                cstmt.setInt("p_articleid", entity.getArticleid());
                cstmt.setInt("p_tagid", entity.getTagid());
                cstmt.setTimestamp("p_timestamp", entity.getTimestamp());

                rs = con.saveOrUpdate(cstmt);

            }
            articleTagPK = new ArticleTagPK(entity.getArticleid(), entity.getTagid());

        } catch (ClassNotFoundException ex) {
            logger.log(Priority.ERROR, ex.toString());
        } catch (SQLException ex) {
            throw ex;
        } finally {
            con.disconnect();
        }

        return articleTagPK;
    }

    @Override
    public void saveOrUpdateAll(List<ArticleTag> entities) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ArticleTag findById(ArticleTagPK key) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<ArticleTag> getAll() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean delete(ArticleTagPK key) throws SQLException {
        boolean retval = false;

        ResultSet rs = null;

        try {

            con = new DBConnection();

            if (con.connect()) {

                cstmt = (CallableStatement) con.getConnection().prepareCall("{call sp_del_articletag(?,?)}");

                cstmt.setInt("p_articleid", key.getArticleId());
                cstmt.setInt("p_tagid", key.getTagId());

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
    public boolean deleteAll(List<ArticleTag> entities) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Tag> getAllTagsOfArticle(Integer articleId) throws SQLException{
        List<Tag> retval = new ArrayList<Tag>();
        ResultSet rs = null;

        try {

            con = new DBConnection();

            if (con.connect()) {

                String sql = "select * from tag t,articletag at where at.tagid=t.tagid and at.articleid=" + articleId;

                rs = con.customQuery(sql);

                while (rs.next()) {

                    Tag t = new Tag();

                    t.setTagid(rs.getInt("tagid"));
                    t.setName(rs.getString("name"));
                    t.setIcon(rs.getString("icon"));
                    t.setDescription(rs.getString("description"));

                    retval.add(t);
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
