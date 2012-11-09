/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.surpriseme.controllers.client;

import com.surpriseme.DAOImpl.ArticleDAOImpl;
import com.surpriseme.entities.Article;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Harmish
 */
public class Search extends HttpServlet {

   

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String keyword = "des";
               try {
                        ArticleDAOImpl articledoa = new ArticleDAOImpl();
                        List<Article> articlelist = articledoa.getRelevantArticles(keyword);
                        int size = articlelist.size();
                        
                       // System.out.println(size);
                        request.setAttribute("articles", articlelist);
                        
                        RequestDispatcher rd = request.getRequestDispatcher("client/searchresults.jsp");//request.getRequestDispatcher("../client/searchresults.jsp");
                        rd.forward(request, response);
                    } catch (SQLException ex) {
                        Logger.getLogger(Search.class.getName()).log(Level.SEVERE, null, ex);
                    }
        
    }

  
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
