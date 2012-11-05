/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.surpriseme.controllers.admin;

import com.surpriseme.DAOImpl.ArticleDAOImpl;
import com.surpriseme.DAOImpl.TagDAOImpl;
import com.surpriseme.entities.Article;
import com.surpriseme.entities.Tag;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author udit
 */
public class ArticleTag extends HttpServlet {

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
            //out.println("hello");
            TagDAOImpl tag = new TagDAOImpl();
            List<Tag> taglist = tag.getTags(Integer.parseInt(request.getParameter("id")));
            //List<Tag> taglist = tag.getTags(1);
            JSONArray jr = new JSONArray();
            JSONObject task = null;
            JSONObject send = new JSONObject();

            for (Tag t : taglist) {
                task = new JSONObject();
                task.put("id", t.getTagid());
                task.put("title", t.getName());
                task.put("desc", t.getDescription());

                jr.add(task);
            }
            send.put("tags", jr);
            System.out.println(task);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            out.print(send);



        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }



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
