/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.surpriseme.controllers;

import com.surpriseme.DAO.ArticleDAO;
import com.surpriseme.DAOImpl.ArticleDAOImpl;
import com.surpriseme.entities.Article;
import com.surpriseme.utils.Category;
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

/**
 *
 * @author ankur
 */
public class ArticleController_Client extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession(false);
        List<Article> articles=null;
        HashMap<Category,List<Article>> articlesSuggestions;
        
        ArticleDAO articleDao=null;
        Integer userId=null;
        Integer intereseId=null;

        if (session != null) {
            
            intereseId = Integer.parseInt(req.getParameter("interestid"));
            userId = Integer.parseInt(session.getAttribute("userid").toString());

            articleDao = new ArticleDAOImpl();
            try {
                
                articlesSuggestions = articleDao.suggestArticle(userId, intereseId);
                
                
                
            } catch (SQLException ex) {
                Logger.getLogger(ArticleController_Client.class.getName()).log(Level.SEVERE, null, ex);
            }

            articleDao =
        }


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
