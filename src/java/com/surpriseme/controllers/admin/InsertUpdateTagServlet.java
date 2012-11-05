/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.surpriseme.controllers.admin;

import com.surpriseme.DAOImpl.TagDAOImpl;
import com.surpriseme.entities.Tag;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author udit
 */
public class InsertUpdateTagServlet extends HttpServlet {

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
        request.getRequestDispatcher("admin/InsertUpdateTag.jsp").forward(request, response);
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
        Map p = request.getParameterMap();
        TagDAOImpl tgDAO = new TagDAOImpl();
        Tag tg = new Tag();
        if (p.containsKey("tagid")) {
            

            tg.setTagid(Integer.parseInt(request.getParameter("tagid")));
            tg.setDescription(request.getParameter("txtDes"));
            tg.setIcon(request.getParameter("icn"));
            tg.setName(request.getParameter("txtName"));
            try {
                tgDAO.saveOrUpdate(tg);
            } catch (SQLException ex) {
                Logger.getLogger(InsertUpdateTagServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            
             response.sendRedirect("tagAddUpdateDeleteServlet");
        } else {

            tg.setDescription(request.getParameter("txtDes2"));
            tg.setIcon(request.getParameter("icn2"));
            tg.setName(request.getParameter("txtName2"));
            try {
                tgDAO.saveOrUpdate(tg);
            } catch (SQLException ex) {
                Logger.getLogger(InsertUpdateTagServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            response.sendRedirect("InsertImage?name=" + tg.getIcon() + "&table=tag&column=icon&path=file-upload&file=tagAddUpdateDeleteServlet&key=name&value=" + tg.getName());
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
