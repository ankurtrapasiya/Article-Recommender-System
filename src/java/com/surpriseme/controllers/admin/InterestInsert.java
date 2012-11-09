/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.surpriseme.controllers.admin;

import com.surpriseme.DAOImpl.InterestDAOImpl;
import com.surpriseme.DAOImpl.InterestDAOImpl;
import com.surpriseme.entities.Interest;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author udit
 */
public class InterestInsert extends HttpServlet {

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
        request.getRequestDispatcher("insertinterest.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        InterestDAOImpl interestDao = null;
        Interest interest = null;
        int retval;
        interestDao = new InterestDAOImpl();
       
        String name = request.getParameter("txtname");
        String description = request.getParameter("txtdescription");
        String iconpath = request.getParameter("icon");
        Timestamp timestamp = new Timestamp(new java.util.Date().getTime());
        interest = new Interest();

        
        interest.setName(name);
        interest.setDescription(description);
        interest.setIconpath(iconpath);
        
        interest.setTimestamp(new java.sql.Timestamp(timestamp.getTime()));
        try {
            retval = interestDao.saveOrUpdate(interest);
        } catch (Exception ex) {
            Logger.getLogger(InterestInsert.class.getName()).log(Level.SEVERE, null, ex);
        }
        response.sendRedirect("InsertImage?name="+interest.getIconpath()+"&table=interest&column=iconpath&path=file-upload-interest&file=InterestController&key=name&value="+interest.getName()+"&defaultimg=icn_interest.png");

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
