/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.surpriseme.controllers.client;

import com.surpriseme.DAOImpl.InterestDAOImpl;
import com.surpriseme.entities.Interest;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
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
 * @author Harmish
 */
public class FetchInterestController extends HttpServlet {

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
            out.println("<title>Servlet FetchInterestController</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet FetchInterestController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        } finally {            
            out.close();
        }
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        InterestDAOImpl idi = new InterestDAOImpl();
        try {
            List<Interest> interestlist =  idi.getAll();
            System.out.println("The size of interst list " + interestlist.size());
            request.setAttribute("interests", interestlist);
            
            RequestDispatcher rd = request.getRequestDispatcher("client/userinterest.jsp");
            rd.forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(FetchInterestController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }


}
