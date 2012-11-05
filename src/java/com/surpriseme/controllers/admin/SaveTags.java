/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.surpriseme.controllers.admin;

import com.surpriseme.DAOImpl.TagDAOImpl;
import com.surpriseme.entities.ArticleTag;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sun.util.calendar.Gregorian;

/**
 *
 * @author udit
 */
public class SaveTags extends HttpServlet {

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
            //processRequest(request, response);
            String id = request.getParameter("id");
            String[] tag = request.getParameter("tags").split(",");
            List<ArticleTag> arr = new ArrayList<ArticleTag>();
            for (int i = 0; i < tag.length; i++) {
                ArticleTag t = new ArticleTag();
                t.setArticleid(Integer.parseInt(id));
                t.setTagid(Integer.parseInt(tag[i]));
                t.setTimestamp(new Timestamp(new Date().getTime()));
                arr.add(t);
            }
            System.out.println(id);

            TagDAOImpl up = new TagDAOImpl();
            up.savechanges(arr);
            System.out.println("Success");
        } catch (Exception ex) {
            Logger.getLogger(SaveTags.class.getName()).log(Level.SEVERE, null, ex);
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
