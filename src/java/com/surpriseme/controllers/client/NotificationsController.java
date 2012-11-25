/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.surpriseme.controllers.client;

import com.surpriseme.DAO.UserGraphDAO;
import com.surpriseme.DAOImpl.UserGraphDAOImpl;
import com.surpriseme.entities.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author spruic
 */

public class NotificationsController extends HttpServlet {

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
            out.println("<title>Servlet NotificationsController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet NotificationsController at " + request.getContextPath() + "</h1>");
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

        boolean retval = false;
        System.out.println("Servlet");
        List<User> userlist = null;
        UserGraphDAO userDao = new UserGraphDAOImpl();
        //  userGraph = new UserGraph();
        try {
            userlist= new ArrayList<User>();
            userlist = userDao.updateUserGraph();//to call method
            // to include in method once a metod is called//   while (rs.next()) {

            //              UserGraph usergraph = new UserGraph();

            //            usergraph.setIsnotified(1);

            retval = true;
        } catch (SQLException ex) {

            Logger.getLogger(SignUpController.class.getName()).log(Level.SEVERE, null, ex);

        }
        request.setAttribute("status", retval);

        request.setAttribute("user", userlist);
        RequestDispatcher rd = request.getRequestDispatcher("notifications.jsp");

        rd.forward(request, response);

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
        /**
         * Returns a short description of the servlet.
         *
         * @return a String containing servlet description
         */
    }
}
