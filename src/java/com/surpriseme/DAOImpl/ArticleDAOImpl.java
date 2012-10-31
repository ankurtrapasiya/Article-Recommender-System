/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.surpriseme.DAOImpl;

import com.surpriseme.DAO.ArticleDAO;
import com.surpriseme.entities.Article;
import com.surpriseme.entities.UserHistory;
import com.surpriseme.helper.TagHelper;
import com.surpriseme.helper.TagSorter;
import com.surpriseme.utils.Category;
import com.surpriseme.utils.DBConnection;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import java.util.logging.Level;
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
    public Integer saveOrUpdate(Article entity) throws SQLException {
        ResultSet rs = null;
        Integer retval = null;

        try {

            con = new DBConnection();
            if (con.connect()) {

                if (entity.getArticleid() != null) {

                    cstmt = (CallableStatement) con.getConnection().prepareCall("{call sp_upd_article(?,?,?,?,?,?,?,?,?)}");
                    cstmt.setInt("p_articleid", entity.getArticleid());

                } else {
                    cstmt = (CallableStatement) con.getConnection().prepareCall("{call sp_ins_article(?,?,?,?,?,?,?,?)}");
                }

                cstmt.setString("p_title", entity.getTitle());
                cstmt.setString("p_body", entity.getBody());
                cstmt.setInt("p_upvote", entity.getUpvote());
                cstmt.setInt("p_downvote", entity.getDownvote());
                cstmt.setInt("p_viewed", entity.getViewed());
                cstmt.setTimestamp("p_timestamp", entity.getTimestamp());
                cstmt.setFloat("p_popularityscore", entity.getPopularityscore());
                cstmt.setTimestamp("p_publicationdate", entity.getPublicationdate());

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
    public void saveOrUpdateAll(List<Article> entities) throws SQLException {
        ResultSet rs = null;

        try {

            con = new DBConnection();
            if (con.connect()) {

                Iterator<Article> iterator = entities.iterator();

                while (iterator.hasNext()) {

                    Article entity = iterator.next();

                    if (entity.getArticleid() != null) {

                        cstmt = (CallableStatement) con.getConnection().prepareCall("{call sp_upd_article(?,?,?,?,?,?,?,?)}");
                        cstmt.setInt("p_articleid", entity.getArticleid());

                    } else {
                        cstmt = (CallableStatement) con.getConnection().prepareCall("{call sp_ins_article(?,?,?,?,?,?,?)}");
                    }

                    cstmt.setString("p_title", entity.getTitle());
                    cstmt.setString("p_body", entity.getBody());
                    cstmt.setInt("p_upvote", entity.getUpvote());
                    cstmt.setInt("p_downvote", entity.getDownvote());
                    cstmt.setInt("p_viewed", entity.getViewed());
                    cstmt.setTimestamp("p_timestamp", entity.getTimestamp());
                    cstmt.setFloat("p_popularityscore", entity.getPopularityscore());
                    cstmt.setTimestamp("p_publicationdate", entity.getPublicationdate());

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
                    article.setPopularityscore(rs.getFloat("popularityscore"));
                    article.setPublicationdate(rs.getTimestamp("publicationdate"));

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
                    article.setPopularityscore(rs.getFloat("popularityscore"));
                    article.setPublicationdate(rs.getTimestamp("publicationdate"));

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

    @Override
    public HashMap<Category, List<Article>> suggestArticle(int userid, int interestid) throws SQLException {

        HashMap<Category, List<Article>> retval = new HashMap<Category, List<Article>>();

        List<Article> articleList = null;

        ResultSet rs = null;

        StringBuilder sql = null;

        ArticleDAOImpl articleDAO = null;


        //Relevancy

        sql = new StringBuilder();
        sql.append("select articleid");
        sql.append(" from article a,userinterest ui,articleinterest ai");
        sql.append(" where articleid not in(");
        sql.append(" select a.articleid");
        sql.append(" from userhistory uh,article a,articleinterest ai");
        sql.append(" where uh.articleid=a.articleid");
        sql.append(" and a.articleid=ai.articleid");
        sql.append(" ai.interestid=").append(interestid);
        sql.append(" ui.userid=").append(userid).append(")");
        sql.append(" and a.articleid=ai.articleid");
        sql.append(" and ai.interestid=ui.interestid");
        sql.append(" and ui.interestid=").append(interestid);
        sql.append(" and ui.userid<>userid ");
        sql.append(" order by a.popularityscore desc");



        con = new DBConnection();
        try {

            if (con.connect()) {

                rs = con.customQuery(sql.toString());

                articleList = new ArrayList<Article>();

                while (rs.next()) {

                    Article a = articleDAO.findById(rs.getInt("articleid"));

                    articleList.add(a);

                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ArticleDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            con.disconnect();
        }

        retval.put(Category.RELEVANCY, articleList);


        //Popularity            

        sql = new StringBuilder();

        sql.append("select articleid");
        sql.append(" from article a,articleinterest ai");
        sql.append(" where a.articleid=ai.articleid");
        sql.append(" and ai.interestid=").append(interestid);
        sql.append(" and a.articleid not in(");
        sql.append(" select a.articleid");
        sql.append(" from article a,userhistory uh,articleinterest ui");
        sql.append(" where a.articleid=uh.articleid");
        sql.append(" and a.articleid=ai.articleid");
        sql.append(" and ai.interestid=").append(interestid);
        sql.append(" and uh.userid=").append(userid).append(")");
        sql.append(" order by a.popularityscore desc");

        con = new DBConnection();
        try {
            if (con.connect()) {

                rs = con.customQuery(sql.toString());

                articleList = new ArrayList<Article>();

                while (rs.next()) {

                    Article article = articleDAO.findById(rs.getInt("articleid"));

                    articleList.add(article);

                }

                retval.put(Category.POPULARITY, articleList);
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ArticleDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            con.disconnect();
        }

        //randomization                 

        sql = new StringBuilder();

        sql.append("select articleid");
        sql.append(" from article a,articleinterest ai");
        sql.append(" where a.articleid=ai.articleid");
        sql.append(" and ai.interestid=").append(interestid);
        sql.append(" and a.articleid not in(");
        sql.append(" select a.articleid");
        sql.append(" from article a,userhistory uh,articleinterest ui");
        sql.append(" where a.articleid=uh.articleid");
        sql.append(" and a.articleid=ai.articleid");
        sql.append(" and ai.interestid=").append(interestid);
        sql.append(" and uh.userid=").append(userid).append(")");
        sql.append(" and a.viewed<10");
        sql.append(" order by a.popularityscore desc ");


        con = new DBConnection();
        try {
            if (con.connect()) {

                rs = con.customQuery(sql.toString());

                articleList = new ArrayList<Article>();

                while (rs.next()) {

                    Article article = articleDAO.findById(rs.getInt("articleid"));

                    articleList.add(article);

                }

                retval.put(Category.RANDOMIZATION, articleList);
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ArticleDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            con.disconnect();
        }

        //browsing ( upvotes in the userhistory has not been considered )

        sql = new StringBuilder();

        sql.append("select a.articleid as articleid,t.tagid as tagid,t.name as name");
        sql.append(" from article a,userhistory uh,tag t,articletag at,articleinterest ai");
        sql.append(" where uh.articleid=at.articleid");
        sql.append(" and at.articleid=a.articleid");
        sql.append(" and at.tagid=t.tagid");
        sql.append(" and ai.articleid=a.articleid");
        sql.append(" and at.tagid in(");                
        sql.append(" select at.tagid");                
        sql.append(" from articletag at,userhistory uh,articleinterest ai,article a");
        sql.append(" where uh.articleid=at.articleid");
        sql.append(" and at.articleid=a.articleid");
        sql.append(" and ai.articleid=a.articleid");           
        sql.append(" and ai.interestid=?");           
        sql.append(" and uh.userid=?");           
        sql.append(" group by at.tagid");           
        sql.append(" order by count(*) desc)");           
        sql.append(" and a.articleid not in(");           
        sql.append(" select a.articleid");           
        sql.append(" from article a,userhistory uh,articleinterest ai");           
        sql.append(" where a.articleid=uh.articleid");           
        sql.append(" and ai.articleid=a.articleid");           
        sql.append(" and ai.interestid=?");           
        sql.append(" and uh.userid=?)");           
        sql.append(" and ai.interestid=?");           
        sql.append(" and uh.userid=?");
        sql.append(" group by t.tagid,t.name");
        sql.append(" order by count(*) desc , a.popularityscore desc");


        con = new DBConnection();
        articleList = new ArrayList<Article>();
        try {

            if (con.connect()) {

                rs = con.customQuery(sql.toString());

                ArticleDAOImpl articleDao = new ArticleDAOImpl();

                while (rs.next()) {

                    Integer key = rs.getInt("articleid");

                    Article article = articleDao.findById(key);

                    articleList.add(article);

                }

            }

            retval.put(Category.BROWSING, articleList);

        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ArticleDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            con.disconnect();
        }


        return retval;
    }

    private List<UserHistory> removeViewedArticles(List<UserHistory> usersHistory, List<UserHistory> otherUsersHistory) {
        List<UserHistory> retval = null;

        Map<Integer, UserHistory> map = new HashMap<Integer, UserHistory>();

        Iterator<UserHistory> users = usersHistory.iterator();

        while (users.hasNext()) {

            UserHistory uh = users.next();
            map.put(uh.getArticleid(), uh);

        }

        Iterator<UserHistory> others = otherUsersHistory.iterator();

        while (others.hasNext()) {

            UserHistory uh = others.next();

            if (map.containsKey(uh.getArticleid())) {
                others.remove();
            }

        }

        retval = otherUsersHistory;

        return retval;

    }

    @Override
    public boolean checkIfArticleExist(String guid) throws SQLException {
        boolean retval = false;
        ResultSet rs = null;
        try {
            con = new DBConnection();
            if (con.connect()) {

                String sql = "select * from articlelinks where articleurl='" + guid + "'";

                rs = con.customQuery(sql);

                while (rs.next()) {
                    retval = true;
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
    public boolean addSourceToArticle(Integer articleid, String articleurl, Integer sourceid) throws SQLException {
        boolean retval = false;
        ResultSet rs = null;

        try {

            con = new DBConnection();
            if (con.connect()) {

                cstmt = (CallableStatement) con.getConnection().prepareCall("{call sp_ins_articlelinks(?,?,?)}");


                cstmt.setInt("p_articleid", articleid);
                cstmt.setString("p_articleurl", articleurl);
                cstmt.setInt("p_sourceid", sourceid);

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
}
