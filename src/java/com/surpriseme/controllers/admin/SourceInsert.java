/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.surpriseme.controllers.admin;

import com.surpriseme.DAOImpl.SourceDAOImpl;
import com.surpriseme.entities.Source;
import java.io.IOException;
import java.io.PrintWriter;

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
public class SourceInsert extends HttpServlet {

    
     @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("insertsource.jsp").forward(request, response);
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         SourceDAOImpl sourceDao=null;
        Source source=null; 
        int retval;
      sourceDao=new SourceDAOImpl();
        // int interestid = Integer.parseInt((request.getParameter("txtinterestid"));
        String name = request.getParameter("txtname");
        String url = request.getParameter("txturl");
        String feedurl = request.getParameter("txtfeedurl");
        String icon = request.getParameter("Icon");
        boolean isactive = Boolean.parseBoolean(request.getParameter("rbactive"));
       
            source = new Source();
             
           // interest.setInterestid(interestid);
            source.setName(name);
            source.setUrl(url);
            source.setFeedurl(feedurl);
            source.setIcon(icon);
            source.setIsactive(isactive);
      System.out.println(icon);
            try {
                retval=sourceDao.saveOrUpdate(source);
            } catch (Exception ex) {
                Logger.getLogger(SourceInsert.class.getName()).log(Level.SEVERE, null, ex);
            }
            response.sendRedirect("InsertImage?name="+source.getIcon()+"&table=source&column=icon&path=file-upload-source&file=SourceController&key=feedurl&value="+source.getFeedurl()+"&defaultimg=icn_source.png");
        
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
