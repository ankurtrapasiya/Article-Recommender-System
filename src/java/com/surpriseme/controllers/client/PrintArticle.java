/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.surpriseme.controllers.client;

import com.surpriseme.DAOImpl.ArticleDAOImpl;
import com.surpriseme.DAOImpl.ArticleLinksDAOImpl;
import com.surpriseme.entities.Article;
import com.surpriseme.entities.ArticleLinks;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Harmish
 */
public class PrintArticle extends HttpServlet {

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            response.setContentType("text/html;charset=UTF-8");
            int articleid = Integer.parseInt(request.getParameter("articleid"));
            PrintWriter out = response.getWriter();
            ArticleDAOImpl articledao = new ArticleDAOImpl();
            Article article = articledao.findById(articleid);
                
            
            
            out.println("<b>" + article.getTitle() + "</b><br/><p>" + article.getBody() + "</p>");
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(PrintArticle.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
