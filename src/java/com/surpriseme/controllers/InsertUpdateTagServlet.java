/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.surpriseme.controllers;

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
        //processRequest(request, response);
        Map p=request.getParameterMap();
        TagDAOImpl tgDAO=new TagDAOImpl();
        Tag tg = new Tag();
        HttpSession se=request.getSession(false);
            se.removeAttribute("entity");     
        if(p.containsKey("tagid")){
            if(p.containsKey("prev")){
                   
                    ServletContext context=this.getServletContext();
                String filePath = context.getInitParameter("file-upload");
                        String str=request.getParameter("prev");
                        File f= new File(filePath+str);
                        if(f.exists()){
                            if(str.contentEquals("icn_tags.png")){
                                
                            }
                            else{
                                f.delete();
                            }
                        }
            }
           
            tg.setTagid(Integer.parseInt(request.getParameter("tagid")));
            tg.setDescription(request.getParameter("txtDes"));
            tg.setIcon(request.getParameter("icn"));
            tg.setName(request.getParameter("txtName"));
                    try {
                        tgDAO.saveOrUpdate(tg);
                    } catch (SQLException ex) {
                        Logger.getLogger(InsertUpdateTagServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
        }
        else{
          
             tg.setDescription(request.getParameter("txtDes2"));
            tg.setIcon(request.getParameter("icn2"));
            tg.setName(request.getParameter("txtName2"));
                    try {
                        tgDAO.saveOrUpdate(tg);
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
