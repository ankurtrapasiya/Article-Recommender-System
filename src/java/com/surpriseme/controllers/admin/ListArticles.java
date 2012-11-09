/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.surpriseme.controllers.admin;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;
import com.surpriseme.DAOImpl.*;
import com.surpriseme.utils.*;
import java.sql.SQLException;
import java.util.List;
import com.surpriseme.entities.*;
import java.util.ArrayList;
import org.apache.log4j.Logger;
import org.json.simple.JSONArray;

/**
 *
 * @author udit
 */
public class ListArticles extends HttpServlet {

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            PrintWriter out = response.getWriter();
            ArticleDAOImpl article = new ArticleDAOImpl();
            List<Article> articlelist = article.getAll();
            JSONArray jr = new JSONArray();
            JSONObject task = null;
            JSONObject send = new JSONObject();

            for (Article a : articlelist) {
                task = new JSONObject();
                task.put("id", a.getArticleid());
                task.put("title", a.getTitle());
                task.put("body", a.getBody());
                jr.add(task);
            }
            send.put("article", jr);
            System.out.println(task);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            out.print(send);



        } catch (SQLException e) {
            System.out.println(e);
        }
        //   processRequest(request, response);
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
