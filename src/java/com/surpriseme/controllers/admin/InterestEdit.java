/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.surpriseme.controllers.admin;

import com.surpriseme.DAOImpl.InterestDAOImpl;
import com.surpriseme.entities.Interest;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.Timestamp;
import java.sql.SQLException;
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
public class InterestEdit extends HttpServlet {

    

    

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
        Interest it=new Interest();
        InterestDAOImpl  InIDao=new InterestDAOImpl();
        it.setDescription(request.getParameter("txtdescription"));
        it.setName(request.getParameter("txtname"));
        String str=(request.getParameter("icon"));
        if(str!=null){
            it.setIconpath(str);
        }
        else{
            it.setIconpath("icn_interest.png");
        }
        it.setInterestid(Integer.parseInt(request.getParameter("interestid")));
        java.sql.Timestamp stamp=new java.sql.Timestamp(new Date().getTime());
        it.setTimestamp(new java.sql.Timestamp(stamp.getTime()));
        try {
            InIDao.saveOrUpdate(it);
        } catch (SQLException ex) {
            Logger.getLogger(InterestEdit.class.getName()).log(Level.SEVERE, null, ex);
        }
      response.sendRedirect("InterestController");  
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
