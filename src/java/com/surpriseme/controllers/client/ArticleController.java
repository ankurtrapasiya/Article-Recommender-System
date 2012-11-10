/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.surpriseme.controllers.client;

import com.surpriseme.DAO.ArticleDAO;
import com.surpriseme.DAO.ArticleLinksDAO;
import com.surpriseme.DAO.ArticleTagDAO;
import com.surpriseme.DAO.FavouritesDAO;
import com.surpriseme.DAO.UserGraphDAO;
import com.surpriseme.DAO.UserInterestDAO;
import com.surpriseme.DAO.UserSuggestionsDAO;
import com.surpriseme.DAOImpl.ArticleDAOImpl;
import com.surpriseme.DAOImpl.ArticleLinksDAOImpl;
import com.surpriseme.DAOImpl.ArticleTagDAOImpl;
import com.surpriseme.DAOImpl.FavouritesDAOImpl;
import com.surpriseme.DAOImpl.HistoryDAOImpl;
import com.surpriseme.DAOImpl.UserGraphDAOImpl;
import com.surpriseme.DAOImpl.UserInterestDAOImpl;
import com.surpriseme.DAOImpl.UserSuggestionsDAOImpl;
import com.surpriseme.entities.Article;
import com.surpriseme.entities.ArticleLinks;
import com.surpriseme.entities.Favourites;
import com.surpriseme.entities.Interest;
import com.surpriseme.entities.Tag;
import com.surpriseme.entities.User;
import com.surpriseme.entities.UserHistory;
import com.surpriseme.helper.FavouritesPK;
import com.surpriseme.helper.UserHistoryPK;
import com.surpriseme.utils.Category;
import com.surpriseme.utils.Utilities;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
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
import sun.text.normalizer.CharTrie;

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
        JSONArray tArr = new JSONArray();

        ArticleDAO articleDao = null;
        ArticleTagDAO articleTagDao = new ArticleTagDAOImpl();
        UserInterestDAO suggestions = new UserInterestDAOImpl();
        ArticleLinksDAO articleLinksDao = new ArticleLinksDAOImpl();

        Integer userId = null;
        Integer intereseId = null;

        if (session != null) {


            if (req.getParameter("interestid") != null) {
                intereseId = Integer.parseInt(req.getParameter("interestid"));
            }
            if (req.getParameter("userid") != null) {
                //userId = Integer.parseInt(session.getAttribute("userid").toString());
            }
            userId = 1;
            if (req.getParameter("freq") != null) {
                try {
                    intereseId = new UserInterestDAOImpl().getUserInterests(userId, true).get(0).getInterestid();
                } catch (SQLException ex) {
                    Logger.getLogger(ArticleController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            if (intereseId != null && userId != null) {

                articleDao = new ArticleDAOImpl();
                List<Tag> tags = null;
                List<ArticleLinks> sources = null;

                try {

                    articlesSuggestions = suggestions.suggestArticle(userId, intereseId);

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

                        SimpleDateFormat sdf = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss");
                        String date = sdf.format(new Date(a.getTimestamp().getTime()));

                        jObj.put("popularityscore", a.getPopularityscore());
                        jObj.put("publicationdate", date);
                        articles.remove(i);

                        tags = articleTagDao.getAllTagsOfArticle(a.getArticleid());



                        Iterator<Tag> iterator = tags.iterator();
                        tArr = new JSONArray();
                        while (iterator.hasNext()) {
                            Tag tag = iterator.next();

                            JSONObject jObj1 = new JSONObject();

                            jObj1.put("tagid", tag.getTagid());
                            jObj1.put("name", tag.getName());
                            jObj1.put("icon", tag.getIcon());
                            jObj1.put("description", tag.getDescription());

                            tArr.add(jObj1);
                        }
                        jObj.put("tags", tArr);

                        sources = articleLinksDao.getSourcesOfArticle(a.getArticleid());


                        Iterator<ArticleLinks> iterator1 = sources.iterator();

                        tArr = new JSONArray();

                        while (iterator1.hasNext()) {
                            ArticleLinks al = iterator1.next();

                            JSONObject jObj1 = new JSONObject();

                            jObj1.put("source", al.getArticleurl());

                            tArr.add(jObj1);
                        }

                        jObj.put("sources", tArr);


                        jArr.add(jObj);


                        i++;
                    }

                } catch (SQLException ex) {
                    Logger.getLogger(ArticleController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {

                UserInterestDAO userDao = new UserInterestDAOImpl();
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
        String action = req.getParameter("action");
        ArticleDAOImpl articleDao = new ArticleDAOImpl();
        HistoryDAOImpl historyDao = new HistoryDAOImpl();
        UserHistory history = null;
        Integer articleId = null;
        Integer userId = 1;
        HttpSession session = req.getSession(false);

        if (session != null) {
            userId = ((User) session.getAttribute("user")).getUserid();
        }

        /*if (req.getParameter("userId") != null) {
         userId = Integer.parseInt(req.getParameter("userId"));
         }*/
        // Integer friendId = Integer.parseInt(req.getParameter("friendId"));

        if (req.getParameter("articleid") != null) {
            articleId = Integer.parseInt(req.getParameter("articleid"));

            try {
                history = historyDao.findById(new UserHistoryPK(userId, articleId));
            } catch (SQLException ex) {
                Logger.getLogger(ArticleController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        //Integer userId = Integer.parseInt(req.getParameter("userId"));
        //Integer userId = 1;
        // Integer friendId = Integer.parseInt(req.getParameter("friendId"));
        boolean flag = true;

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

                        Article a = articleDao.findById(articleId);
                        jObj = new JSONObject();
                        jObj.put("downvote", a.getDownvote());
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

                        Article a = articleDao.findById(articleId);
                        jObj = new JSONObject();
                        jObj.put("upvote", a.getUpvote());

                        jArr.add(jObj);
                    }

                } catch (SQLException ex) {
                    Logger.getLogger(ArticleController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (action.equals("suggest")) {

                String friendId = req.getParameter("friendid");

                String[] ids = friendId.split(",");

                UserSuggestionsDAO suggestionsDao = new UserSuggestionsDAOImpl();
                try {
                    for (int i = 0; i < ids.length; i++) {
                        suggestionsDao.suggestArticle(userId, Integer.parseInt(ids[i]), articleId);
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(ArticleController.class.getName()).log(Level.SEVERE, null, ex);
                }
                jObj = new JSONObject();
                jObj.put("data", "true");
                jArr.add(jObj);
            } else if (action.equals("addtofavourites")) {
                try {

                    FavouritesDAO favDao = new FavouritesDAOImpl();
                    Favourites fav = favDao.findById(new FavouritesPK(userId, articleId));

                    if (fav != null && !fav.isIsfav()) {

                        fav = new Favourites(userId, articleId, fav.isReadlater(), true);

                    } else {
                        fav = new Favourites(userId, articleId, false, true);
                    }
                    favDao.saveOrUpdate(fav);


                    if (history == null) {
                        history = new UserHistory(userId, articleId, new Timestamp(new java.util.Date().getTime()), Boolean.FALSE, Boolean.FALSE);
                        historyDao.saveOrUpdate(history);
                    }
                    jObj = new JSONObject();
                    jObj.put("status", "true");
                    jArr.add(jObj);

                    jObj = new JSONObject();
                    jObj.put("status", "false");
                    jArr.add(jObj);
                } catch (SQLException ex) {
                    Logger.getLogger(ArticleController.class
                            .getName()).log(Level.SEVERE, null, ex);
                }
            } else if (action.equals("readitlater")) {
                try {

                    FavouritesDAO favDao = new FavouritesDAOImpl();
                    Favourites fav = favDao.findById(new FavouritesPK(userId, articleId));

                    if (fav != null && !fav.isReadlater()) {

                        fav = new Favourites(userId, articleId, true, fav.isIsfav());

                    } else {
                        fav = new Favourites(userId, articleId, true, false);
                    }


                    favDao.saveOrUpdate(fav);


                    if (history == null) {
                        history = new UserHistory(userId, articleId, new Timestamp(new java.util.Date().getTime()), Boolean.FALSE, Boolean.FALSE);
                        historyDao.saveOrUpdate(history);
                    }

                    jObj = new JSONObject();
                    jObj.put("status", "true");
                    jArr.add(jObj);


                    jObj = new JSONObject();
                    jObj.put("status", "false");
                    jArr.add(jObj);


                } catch (SQLException ex) {
                    Logger.getLogger(ArticleController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (action.equals("givemefriends")) {


                UserGraphDAO userGraphDao = new UserGraphDAOImpl();
                List<User> friends = null;
                try {
                    //friends = userGraphDao.getAllFriends(u.getUserid());
                    friends = userGraphDao.getAllFriends(1);
                } catch (SQLException ex) {
                }

                if (friends != null) {
                    Iterator<User> iterator = friends.iterator();

                    while (iterator.hasNext()) {
                        User user = iterator.next();
                        jObj = new JSONObject();

                        jObj.put("id", user.getUserid());
                        jObj.put("name", user.getFirstname() + " " + user.getLastname());

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
}
