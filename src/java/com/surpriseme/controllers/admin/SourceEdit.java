/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.surpriseme.controllers.admin;

import com.surpriseme.DAOImpl.SourceDAOImpl;
import com.surpriseme.entities.Source;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
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
public class SourceEdit extends HttpServlet {

    

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
       Source so=new Source();
        SourceDAOImpl  soDao=new SourceDAOImpl();
        
        so.setName(request.getParameter("txtname"));
        so.setUrl(request.getParameter("txturl"));
        so.setFeedurl(request.getParameter("txtfeedurl"));
        
        String str=(request.getParameter("Icon"));
        if(str!=null){
            so.setIcon(str);
        }
        else{
            so.setIcon("icn");
        }
        so.setSourceid(Integer.parseInt(request.getParameter("sourceid")));
        so.setIsactive(Boolean.parseBoolean(request.getParameter("rbactive")));
    
        try {
            soDao.saveOrUpdate(so);
        } 
        catch (SQLException ex)
        {
            Logger.getLogger(SourceEdit.class.getName()).log(Level.SEVERE, null, ex);
        }
      response.sendRedirect("SourceController");  
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
