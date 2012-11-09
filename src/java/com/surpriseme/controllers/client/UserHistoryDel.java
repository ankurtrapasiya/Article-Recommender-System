/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.surpriseme.controllers.client;

import com.surpriseme.DAO.UserHistoryDAO;
import com.surpriseme.DAOImpl.HistoryDAOImpl;
import com.surpriseme.helper.UserHistoryPK;
import com.surpriseme.entities.User;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Vikr@m
 */
public class UserHistoryDel extends HttpServlet {

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
            out.println("<title>Servlet UserHistoryDel</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UserHistoryDel at " + request.getContextPath() + "</h1>");
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
        //  processRequest(request, response);
        
        
        Integer id = null;
        Integer artId = null;
        User user=null;
        id=0;
        artId=0;
        boolean b = false;
        HttpSession session = request.getSession(false);
        artId = Integer.parseInt(request.getParameter("articleid"));
        
        
        System.out.println("Article ID is = " + artId);
        System.out.println(session.getAttribute("user"));
        if (session != null) {
            if (session.getAttribute("user") != null) {
                // id = Integer.parseInt((String) session.getAttribute("user"));
                
                user = (User) session.getAttribute("user");
                System.out.println("User Logged in is = " + user.getUserid());
                
                id=user.getUserid();
                
                if (id != 0 && artId != 0) {
                    
                    UserHistoryDAO historyDao = new HistoryDAOImpl();
                    UserHistoryPK userHist = new UserHistoryPK(id, artId);
                    
                    try {
                        
                        historyDao.delete(userHist);
                        // request.getRequestDispatcher("history.jsp").forward(request, response);
                    } catch (SQLException ex) {
                        Logger.getLogger(UserHistoryDel.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
            
            
        }
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
        //  processRequest(request, response);

        
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
