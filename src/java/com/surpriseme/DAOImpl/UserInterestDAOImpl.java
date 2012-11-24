/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.surpriseme.DAOImpl;

import com.surpriseme.DAO.UserInterestDAO;
import com.surpriseme.entities.Article;
import com.surpriseme.entities.Interest;
import com.surpriseme.entities.UserInterest;
import com.surpriseme.helper.UserInterestPK;
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
import java.util.logging.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.Priority;

/**
 *
 * @author ankur
 */
public class UserInterestDAOImpl implements UserInterestDAO {
    
    
    CallableStatement cstmt;
    DBConnection con;
    private static final Logger logger = Logger.getLogger(UserDAOImpl.class);

    @Override
    public UserInterestPK saveOrUpdate(UserInterest entity) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void saveOrUpdateAll(List<UserInterest> entities) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public UserInterest findById(UserInterestPK key) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<UserInterest> getAll() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean delete(UserInterestPK key) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean deleteAll(List<UserInterest> entities) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean addInterestToUser(Integer interestid, Integer userid) throws SQLException {
        boolean retval = false;
        ResultSet rs = null;

        try {

            con = new DBConnection();
            if (con.connect()) {

                cstmt = (CallableStatement) con.getConnection().prepareCall("{call sp_ins_userinterest(?,?)}");


                cstmt.setInt("p_userid", userid);
                cstmt.setInt("p_interestid", interestid);

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
    public boolean removeInterestFromUser(Integer interestid, Integer userid) throws SQLException {
        boolean retval = false;
        ResultSet rs = null;

        try {

            con = new DBConnection();
            if (con.connect()) {

                cstmt = (CallableStatement) con.getConnection().prepareCall("{call sp_del_userinterest(?,?)}");


                cstmt.setInt("p_userid", userid);
                cstmt.setInt("p_interestid", interestid);

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

    
    /**
     * 
     * @param userid
     * @param include - specified whether to include other interest which are not of user or not
     * true- only user interest
     * false -all except user's interest
     * @return
     * @throws SQLException 
     */
    @Override
    public List<Interest> getUserInterests(Integer userid, boolean include) throws SQLException {
        List<Interest> retval = new ArrayList<Interest>();
        ResultSet rs = null;
        String query = "";

        try {

            con = new DBConnection();

            if (con.connect()) {

                if (include) {
                    query = "select * from userinterest where userid=" + userid;
                } else {
                    query = "select * from interest where interestid NOT IN(select interestid from userinterest where userid=" + userid + ")";
                }
                rs = con.customQuery(query);

                List<UserInterest> list = new ArrayList<UserInterest>();
                if (include) {
                    while (rs.next()) {

                        UserInterest userinterest = new UserInterest();

                        userinterest.setUserid(rs.getInt("userid"));
                        userinterest.setInterestid(rs.getInt("interestid"));

                        list.add(userinterest);
                    }


                    Iterator<UserInterest> i = list.iterator();
                    InterestDAOImpl impl = new InterestDAOImpl();

                    while (i.hasNext()) {

                        UserInterest ui = i.next();
                        Interest interest = impl.findById(ui.getInterestid());

                        retval.add(interest);
                    }
                } else {
                    InterestDAOImpl impl = new InterestDAOImpl();
                    while (rs.next()) {
                        Interest interest = new Interest();
                        int id = rs.getInt("interestid");
                        interest = impl.findById(id);
                        retval.add(interest);
                    }
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
    public HashMap<Category, List<Article>> suggestArticle(int userid, int interestid) throws SQLException {

        HashMap<Category, List<Article>> retval = new HashMap<Category, List<Article>>();

        List<Article> articleList = null;

        ResultSet rs = null;

        StringBuilder sql = null;

        ArticleDAOImpl articleDAO = null;


        //Relevancy (consider view count )

        sql = new StringBuilder();
        sql.append("select a.articleid");
        sql.append(" from article a,userinterest ui,articleinterest ai");
        sql.append(" where a.articleid not in(");
        sql.append(" select a.articleid");
        sql.append(" from userhistory uh,article a,articleinterest ai");
        sql.append(" where uh.articleid=a.articleid");
        sql.append(" and a.articleid=ai.articleid");
        sql.append(" and ai.interestid=").append(interestid);
        sql.append(" and ui.userid=").append(userid).append(")");
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


        //Popularity            (consider view count )

        sql = new StringBuilder();

        sql.append("select a.articleid");
        sql.append(" from article a,articleinterest ai");
        sql.append(" where a.articleid=ai.articleid");
        sql.append(" and ai.interestid=").append(interestid);
        sql.append(" and a.articleid not in(");
        sql.append(" select a.articleid");
        sql.append(" from article a,userhistory uh,articleinterest ai");
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

                articleDAO = new ArticleDAOImpl();

                while (rs.next()) {

                    Integer articleId = rs.getInt("articleid");
                    Article article = articleDAO.findById(articleId);

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

        sql.append("select a.articleid");
        sql.append(" from article a,articleinterest ai");
        sql.append(" where a.articleid=ai.articleid");
        sql.append(" and ai.interestid=").append(interestid);
        sql.append(" and a.articleid not in(");
        sql.append(" select a.articleid");
        sql.append(" from article a,userhistory uh,articleinterest ai");
        sql.append(" where a.articleid=uh.articleid");
        sql.append(" and a.articleid=ai.articleid");
        sql.append(" and ai.interestid=").append(interestid);
        sql.append(" and uh.userid=").append(userid).append(")");
        sql.append(" and a.viewed<10");
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
        sql.append(" and ai.interestid=").append(interestid);
        sql.append(" and uh.userid=").append(userid);
        sql.append(" group by at.tagid");
        sql.append(" order by count(*) desc)");
        sql.append(" and a.articleid not in(");
        sql.append(" select a.articleid");
        sql.append(" from article a,userhistory uh,articleinterest ai");
        sql.append(" where a.articleid=uh.articleid");
        sql.append(" and ai.articleid=a.articleid");
        sql.append(" and ai.interestid=").append(interestid);
        sql.append(" and uh.userid=").append(userid).append(")");
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
    
     @Override
    public List<Integer> getUsersOfInterest(Integer interestid) throws SQLException {
        List<Integer> retval = new ArrayList<Integer>();
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



}
