/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.surpriseme.DAOImpl;

import com.surpriseme.DAO.ArticleDAO;
import com.surpriseme.entities.Article;
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
public class ArticleDAOImpl implements ArticleDAO {

    CallableStatement cstmt;
    DBConnection con;
    private static final Logger logger = Logger.getLogger(UserDAOImpl.class);

    @Override
    public boolean updateViews(Integer articleId) throws SQLException {
        boolean retval = false;
        try {
            con = new DBConnection();
            if (con.connect()) {

                Article article = this.findById(articleId);

                if (article != null) {

                    int count = 0;
                    String sql = "update article set ";

                    count = article.getViewed();

                    sql += "viewed=" + (count + 1);

                    sql += " where articleid=" + articleId;

                    retval = con.executeQuery(sql);
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
    public boolean vote(Integer articleId, boolean up) throws SQLException {

        boolean retval = false;
        try {
            con = new DBConnection();
            if (con.connect()) {

                Article article = this.findById(articleId);

                if (article != null) {

                    int count = 0;
                    String sql = "update article set ";
                    if (up) {
                        count = article.getUpvote();
                        sql += "upvote=" + (count + 1);
                    } else {
                        count = article.getDownvote();
                        sql += "downvote=" + (count + 1);
                    }

                    sql += " where articleid=" + articleId;

                    retval = con.executeQuery(sql);
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
    public boolean saveOrUpdate(Article entity) throws SQLException {
        ResultSet rs = null;
        boolean retval = false;

        try {

            con = new DBConnection();
            if (con.connect()) {

                if (entity.getArticleid() != null) {

                    cstmt = (CallableStatement) con.getConnection().prepareCall("{call sp_upd_article(?,?,?,?,?,?,?)}");
                    cstmt.setInt("p_articleid", entity.getArticleid());

                } else {
                    cstmt = (CallableStatement) con.getConnection().prepareCall("{call sp_ins_article(?,?,?,?,?,?)}");
                }

                cstmt.setString("p_title", entity.getTitle());
                cstmt.setString("p_body", entity.getBody());
                cstmt.setInt("p_upvote", entity.getUpvote());
                cstmt.setInt("p_downvote", entity.getDownvote());
                cstmt.setInt("p_viewed", entity.getViewed());
                cstmt.setTimestamp("p_timestamp", entity.getTimestamp());

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
    public void saveOrUpdateAll(List<Article> entities) throws SQLException {
        ResultSet rs = null;

        try {

            con = new DBConnection();
            if (con.connect()) {

                Iterator<Article> iterator = entities.iterator();

                while (iterator.hasNext()) {

                    Article entity = iterator.next();

                    if (entity.getArticleid() != null) {

                        cstmt = (CallableStatement) con.getConnection().prepareCall("{call sp_upd_article(?,?,?,?,?,?,?)}");
                        cstmt.setInt("p_articleid", entity.getArticleid());

                    } else {
                        cstmt = (CallableStatement) con.getConnection().prepareCall("{call sp_ins_article(?,?,?,?,?,?)}");
                    }

                    cstmt.setString("p_title", entity.getTitle());
                    cstmt.setString("p_body", entity.getBody());
                    cstmt.setInt("p_upvote", entity.getUpvote());
                    cstmt.setInt("p_downvote", entity.getDownvote());
                    cstmt.setInt("p_viewed", entity.getViewed());
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
    public Article findById(Integer key) throws SQLException {
        Article retval = null;
        ResultSet rs = null;
        PreparedStatement pstmt = null;

        try {

            con = new DBConnection();

            if (con.connect()) {

                String sql = "select * from article where articleid=?";
                pstmt = con.getConnection().prepareStatement(sql);
                pstmt.setInt(1, key);

                rs = con.customQuery(pstmt);

                while (rs.next()) {

                    Article article = new Article();

                    article.setArticleid(rs.getInt("articleid"));
                    article.setTitle(rs.getString("title"));
                    article.setBody(rs.getString("body"));
                    article.setUpvote(rs.getInt("upvote"));
                    article.setDownvote(rs.getInt("downvote"));
                    article.setViewed(rs.getInt("viewed"));
                    article.setTimestamp(rs.getTimestamp("timestamp"));

                    retval = article;
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
    public List<Article> getAll() throws SQLException {
        List<Article> retval = new ArrayList<Article>();
        ResultSet rs = null;

        try {

            con = new DBConnection();

            if (con.connect()) {

                cstmt = (CallableStatement) con.getConnection().prepareCall("{call sp_sel_article()}");

                rs = con.customQuery(cstmt);

                while (rs.next()) {

                    Article article = new Article();

                    article.setArticleid(rs.getInt("articleid"));
                    article.setTitle(rs.getString("title"));
                    article.setBody(rs.getString("body"));
                    article.setUpvote(rs.getInt("upvote"));
                    article.setDownvote(rs.getInt("downvote"));
                    article.setViewed(rs.getInt("viewed"));
                    article.setTimestamp(rs.getTimestamp("timestamp"));

                    retval.add(article);
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

                cstmt = (CallableStatement) con.getConnection().prepareCall("{call sp_del_article(?)}");

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
    public boolean deleteAll(List<Article> entities) throws SQLException {
        boolean retval = false;

        try {

            con = new DBConnection();

            if (con.connect()) {


                Iterator<Article> iterator = entities.iterator();

                while (iterator.hasNext()) {

                    Article entity = iterator.next();

                    cstmt = (CallableStatement) con.getConnection().prepareCall("{call sp_del_article(?)}");
                    cstmt.setInt(1, entity.getArticleid());

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
