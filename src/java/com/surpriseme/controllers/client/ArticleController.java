/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.surpriseme.controllers.client;

import com.surpriseme.DAO.ArticleDAO;
import com.surpriseme.DAO.UserDAO;
import com.surpriseme.DAOImpl.ArticleDAOImpl;
import com.surpriseme.DAOImpl.UserDAOImpl;
import com.surpriseme.entities.Article;
import com.surpriseme.entities.Interest;
import com.surpriseme.utils.Category;
import com.surpriseme.utils.Utilities;
import java.io.IOException;
import java.sql.SQLException;
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
                    while (!articles.isEmpty()) {

                        Article a = articles.get(i);

                        jObj = new JSONObject();
                        jObj.put("articleid", a.getArticleid());
                        jObj.put("title", a.getTitle());
                        jObj.put("body", a.getBody());
                        jObj.put("upvote", a.getUpvote());
                        jObj.put("downvote", a.getDownvote());
                        jObj.put("viewed", a.getViewed());
                        jObj.put("timestamp", a.getTimestamp());
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
                    interestList = userDao.getAllUserInterests(1);
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
        super.doPost(req, resp);
    }
}
