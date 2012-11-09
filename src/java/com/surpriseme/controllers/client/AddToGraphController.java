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
public class AddToGraphController extends HttpServlet {

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
        HttpSession session = request.getSession();
        int userid = 1;

        try {
            int friendid = Integer.parseInt(request.getParameter("userid"));
            userDao.addToCircle(friendid, userid);
        } catch (SQLException ex) {
            Logger.getLogger(AddToGraphController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}