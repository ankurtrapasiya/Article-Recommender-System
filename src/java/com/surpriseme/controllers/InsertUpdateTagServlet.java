/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.surpriseme.controllers;

import com.surpriseme.DAOImpl.TagDAOImpl;
import com.surpriseme.entities.Tag;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author peace
 */
public class InsertUpdateTagServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet InsertUpdateTagServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet InsertUpdateTagServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        } finally {            
            out.close();
        }
    }

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
        //processRequest(request, response);
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
        //processRequest(request, response);
        Map p=request.getParameterMap();
        TagDAOImpl tg=new TagDAOImpl();
        Tag entity = new Tag();
        if(p.containsKey("tagid")){
            entity.setTagid(Integer.parseInt(request.getParameter("tagid")));
            entity.setDescription(request.getParameter("txtDes"));
            entity.setIcon(request.getParameter("icn"));
            entity.setName(request.getParameter("txtName"));
                    try {
                        tg.saveOrUpdate(entity);
                    } catch (SQLException ex) {
                        Logger.getLogger(InsertUpdateTagServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
        }
        else{
           
              entity.setDescription(request.getParameter("txtDes2"));
            entity.setIcon(request.getParameter("icn2"));
            entity.setName(request.getParameter("txtName2"));
            System.out.println(entity.getName());
            System.out.println(entity.getDescription());
             System.out.println(entity.getIcon());
                    try {
                        tg.saveOrUpdate(entity);
                    } catch (SQLException ex) {
                        Logger.getLogger(InsertUpdateTagServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
        }
       // request.getRequestDispatcher("tagAddUpdateDeleteServlet").forward(request, response);
        response.sendRedirect("tagAddUpdateDeleteServlet");
    }

    /**
     * Returns a short description of the servlet.
     *
     * @retturn a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
