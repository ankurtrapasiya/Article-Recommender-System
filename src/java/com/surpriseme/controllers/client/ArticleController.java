/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.surpriseme.controllers.client;

import com.surpriseme.DAO.ArticleDAO;
import com.surpriseme.DAO.UserDAO;
import com.surpriseme.DAOImpl.ArticleDAOImpl;
import com.surpriseme.DAOImpl.HistoryDAOImpl;
import com.surpriseme.DAOImpl.UserDAOImpl;
import com.surpriseme.entities.Article;
import com.surpriseme.entities.Interest;
import com.surpriseme.entities.UserHistory;
import com.surpriseme.helper.UserHistoryPK;
import com.surpriseme.utils.Category;
import com.surpriseme.utils.Utilities;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author ankur
 */
public class ArticleController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession(false);
        List<Article> articles = null;
        HashMap<Category, List<Article>> articlesSuggestions;

        JSONArray jArr = new JSONArray();
        JSONObject jObj = new JSONObject();

        ArticleDAO articleDao = null;
        Integer userId = null;
        Integer intereseId = null;

        if (session != null) {


            if (req.getParameter("interestid") != null) {
                intereseId = Integer.parseInt(req.getParameter("interestid"));
            }
            userId = 1;
            if (req.getParameter("userid") != null) {
                //userId = Integer.parseInt(session.getAttribute("userid").toString());
            }

            if (intereseId != null && userId != null) {

                articleDao = new ArticleDAOImpl();
                try {

                    articlesSuggestions = articleDao.suggestArticle(userId, intereseId);

                    articles = Utilities.processArticles(articlesSuggestions);

                    int i = 0;
                    while (i < articles.size()) {

                        Article a = articles.get(i);

                        jObj = new JSONObject();
                        jObj.put("articleid", a.getArticleid());
                        jObj.put("title", a.getTitle());
                        jObj.put("body", a.getBody());
                        jObj.put("upvote", a.getUpvote());
                        jObj.put("downvote", a.getDownvote());
                        jObj.put("viewed", a.getViewed());
                        jObj.put("timestamp", a.getTimestamp().toString());
                        articles.remove(i);

                        jArr.add(jObj);

                        i++;
                    }

                } catch (SQLException ex) {
                    Logger.getLogger(ArticleController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {

                UserDAO userDao = new UserDAOImpl();
                List<Interest> interestList = null;
                try {
                    interestList = userDao.getUserInterests(1, true);
                } catch (SQLException ex) {
                    Logger.getLogger(ArticleController.class.getName()).log(Level.SEVERE, null, ex);
                }

                if (interestList != null) {
                    jArr = new JSONArray();

                    for (Interest i : interestList) {
                        jObj = new JSONObject();
                        jObj.put("interestid", i.getInterestid());
                        jObj.put("name", i.getName());
                        jArr.add(jObj);
                    }
                }

            }
            jObj = new JSONObject();
            jObj.put("content", jArr);

            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().println(jObj);
        }


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        JSONArray jArr = new JSONArray();
        JSONObject jObj = null;

        Integer articleId = Integer.parseInt(req.getParameter("articleid"));
        //Integer userId = Integer.parseInt(req.getParameter("userId"));
        Integer userId = 1;
        // Integer friendId = Integer.parseInt(req.getParameter("friendId"));
        boolean flag = true;


        String action = req.getParameter("action");
        ArticleDAOImpl articleDao = new ArticleDAOImpl();
        HistoryDAOImpl historyDao = new HistoryDAOImpl();
        UserHistory history = null;


        try {
            history = historyDao.findById(new UserHistoryPK(userId, articleId));
        } catch (SQLException ex) {
            Logger.getLogger(ArticleController.class.getName()).log(Level.SEVERE, null, ex);
        }

        Article article = null;

        if (articleId != null && action != null) {
            if (action.equals("upvote")) {
                try {

                    Integer upvote = null;

                    if (history == null) {
                        history = new UserHistory(userId, articleId, new Timestamp(new java.util.Date().getTime()), Boolean.TRUE, Boolean.FALSE);
                    } else if (history.getDownvote()) {
                        history.setTimestamp(new Timestamp(new java.util.Date().getTime()));
                        history.setUpvote(Boolean.TRUE);
                        history.setDownvote(Boolean.FALSE);
                    } else {
                        flag = false;
                    }

                    if (flag) {
                        historyDao.saveOrUpdate(history);
                        upvote = articleDao.vote(articleId, true);
                    }

                    if (upvote != null) {

                        jObj = new JSONObject();
                        jObj.put("upvote", upvote);
                        jArr.add(jObj);
                        
                        Article a=articleDao.findById(articleId);
                        jObj=new JSONObject();
                        jObj.put("downvote",a.getDownvote());
                        jArr.add(jObj);
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(ArticleController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (action.equals("downvote")) {
                try {
                    Integer downvote = null;
                    if (history == null) {
                        history = new UserHistory(userId, articleId, new Timestamp(new java.util.Date().getTime()), Boolean.FALSE, Boolean.TRUE);
                    } else if (history.getUpvote()) {
                        history.setTimestamp(new Timestamp(new java.util.Date().getTime()));
                        history.setUpvote(Boolean.FALSE);
                        history.setDownvote(Boolean.TRUE);
                    } else {
                        flag = false;
                    }

                    if (flag) {
                        historyDao.saveOrUpdate(history);
                        downvote = articleDao.vote(articleId, false);
                    }

                    if (downvote != null) {

                        jObj = new JSONObject();
                        jObj.put("downvote", downvote);
                                                
                        jArr.add(jObj);
                        
                        Article a=articleDao.findById(articleId);
                        jObj=new JSONObject();
                        jObj.put("upvote",a.getUpvote());
                        
                        jArr.add(jObj);
                    }

                } catch (SQLException ex) {
                    Logger.getLogger(ArticleController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (action.equals("suggest")) {
                /*try {
                 articleDao.suggestArticle(userId, friendId, articleId);
                 } catch (SQLException ex) {
                 Logger.getLogger(ArticleController.class.getName()).log(Level.SEVERE, null, ex);
                 }*/
            } else if (action.equals("addtofav")) {
                try {
                    articleDao.addArticleToFavourites(userId, articleId);


                } catch (SQLException ex) {
                    Logger.getLogger(ArticleController.class
                            .getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        jObj = new JSONObject();
        jObj.put("content", jArr);

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().println(jObj);
    }
}
