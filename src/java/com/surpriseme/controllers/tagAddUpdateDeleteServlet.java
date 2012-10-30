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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author peace
 */
public class tagAddUpdateDeleteServlet extends HttpServlet {

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
            out.println("<title>Servlet tagAddUpdateDeleteServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet tagAddUpdateDeleteServlet at " + request.getContextPath() + "</h1>");
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
      TagDAOImpl tsg=new TagDAOImpl();
      List<Tag> data=new ArrayList<Tag>();
        try {
            data=tsg.getAll();
      
        } catch (SQLException ex) {
            Logger.getLogger(tagAddUpdateDeleteServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
      
        request.setAttribute("data", data);
        request.getRequestDispatcher("admin/tagAddUpdateDelete.jsp").forward(request, response);
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
        String ck=request.getParameter("clicked");
         Tag entity=new Tag();
            TagDAOImpl tsg=new TagDAOImpl();
            int tem=Integer.parseInt(request.getParameter("tagid"));
        if(ck.contentEquals("up")){
            
           
           
                    try {
                        entity=tsg.findById(tem);
                         request.setAttribute("entity", entity);
                    } catch (SQLException ex) {
                        Logger.getLogger(tagAddUpdateDeleteServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                   
                    request.getRequestDispatcher("admin/InsertUpdateTag.jsp").forward(request, response);
            
            
        }
        else if(ck.contentEquals("del")){
                ServletContext context=this.getServletContext();
                String filePath = context.getInitParameter("file-upload");
                    try {
                        entity=tsg.findById(tem);
                        String str=entity.getIcon();
                        File f= new File(filePath+str);
                        if(f.exists()){
                            if(str.contentEquals("icn_tags.png")){
                                
                            }
                            else{
                                f.delete();
                            }
                        }
                        
                            
                  
                        tsg.delete(tem);
                    } catch (SQLException ex) {
                        Logger.getLogger(tagAddUpdateDeleteServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    doGet(request,response);
        }
        else if(ck.contentEquals("add")){
           
            request.getRequestDispatcher("admin/InsertUpdateTag.jsp").forward(request, response);
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
