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
import javax.servlet.http.HttpSession;

/**
 *
 * @author Harmish
 */
public class Search extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String keyword = request.getParameter("txtKeyword");
        
        //String keyword="naxals";
        if (keyword != null) {
            
            keyword = keyword.trim();
            
            if (keyword != "") {
                
                try {
                    ArticleDAOImpl articledoa = new ArticleDAOImpl();
                    List<Article> articlelist = articledoa.getRelevantArticles(keyword);
                    int size = articlelist.size();
                    System.out.println(size);
                    request.setAttribute("articles", articlelist);
                    HttpSession session = request.getSession();
                    session.setAttribute("articles", articlelist);
                    //RequestDispatcher rd;
                    //rd = request.getRequestDispatcher("client/demo.jsp");
                    //rd.forward(request, response);
                    response.sendRedirect("searchresults.jsp");
                } catch (SQLException ex) {
                    Logger.getLogger(Search.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else {
            RequestDispatcher rd = request.getRequestDispatcher("searchresults.jsp");
            rd.forward(request, response);
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
