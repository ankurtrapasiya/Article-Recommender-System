/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.surpriseme.controllers.client;

import com.surpriseme.DAO.UserGraphDAO;
import com.surpriseme.DAOImpl.UserGraphDAOImpl;
import com.surpriseme.entities.User;
import java.io.IOException;
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
 * @author udit
 */
public class RemoveFromGraphController extends HttpServlet {

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
        User u = new User();
        UserGraphDAO userDao = new UserGraphDAOImpl();
        Integer userid = null;

        HttpSession session = request.getSession(false);
        if (session != null) {
            if (session.getAttribute("user") != null) {

                try {
                    int friendid = Integer.parseInt(request.getParameter("userid"));
                    
                    userid = ((User) session.getAttribute("user")).getUserid();

                    userDao.removeFromCircle(friendid, userid);
                    
                } catch (SQLException ex) {
                    Logger.getLogger(RemoveFromGraphController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
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