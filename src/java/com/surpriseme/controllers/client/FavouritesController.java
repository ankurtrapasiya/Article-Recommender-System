/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.surpriseme.controllers.client;

import com.surpriseme.DAO.ArticleDAO;
import com.surpriseme.DAO.FavouritesDAO;
import com.surpriseme.DAOImpl.ArticleDAOImpl;
import com.surpriseme.DAOImpl.FavouritesDAOImpl;
import com.surpriseme.entities.Article;
import com.surpriseme.entities.Favourites;
import com.surpriseme.entities.User;
import com.surpriseme.helper.FavouritesPK;
import java.io.IOException;
import java.sql.SQLException;
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

/**
 *
 * @author ankur
 */
public class FavouritesController extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        FavouritesDAO favouriteDao = null;
        ArticleDAO articleDao = null;
        User user = null;
        List<Favourites> list = null;
        JSONArray jArr = new JSONArray();
        JSONObject jObj = null;
        
        if (session != null) {
            user = (User) session.getAttribute("user");
            favouriteDao = new FavouritesDAOImpl();
            articleDao = new ArticleDAOImpl();
            
            if (user != null) {
                try {
                    list = favouriteDao.getAllFavourites(user.getUserid());
                } catch (SQLException ex) {
                    Logger.getLogger(FavouritesController.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                Iterator<Favourites> iterator = list.iterator();
                
                while (iterator.hasNext()) {
                    jObj = new JSONObject();
                    Article article = null;
                    Favourites fav = iterator.next();
                    try {
                        
                        article = articleDao.findById(fav.getArticleid());
                        
                        jObj.put("title", article.getTitle());
                        jObj.put("articleid", article.getArticleid());
                        
                    } catch (SQLException ex) {
                        Logger.getLogger(FavouritesController.class.getName()).log(Level.SEVERE, null, ex);
                    }
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
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String articleid = req.getParameter("articleid");
        FavouritesDAO favouriteDao = null;
        HttpSession session = req.getSession(false);
        User user = null;
        
        if (session != null) {
            user = (User) session.getAttribute("user");
            if (user != null) {
                if (articleid != null) {
                    
                    favouriteDao = new FavouritesDAOImpl();
                    Favourites fav = null;
                    
                    FavouritesPK favPk = new FavouritesPK(user.getUserid(), Integer.parseInt(articleid));
                    try {
                        fav = favouriteDao.findById(favPk);
                    } catch (SQLException ex) {
                        Logger.getLogger(FavouritesController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    if (fav != null) {
                        fav.setIsfav(false);
                        try {
                            favouriteDao.saveOrUpdate(fav);
                        } catch (SQLException ex) {
                            Logger.getLogger(FavouritesController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
                
            }
        }
        doGet(req, resp);
    }
}
