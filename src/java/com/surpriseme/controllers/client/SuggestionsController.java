/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.surpriseme.controllers.client;

import com.surpriseme.DAO.UserSuggestionsDAO;
import com.surpriseme.DAOImpl.UserSuggestionsDAOImpl;
import com.surpriseme.entities.ArticleLinks;
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
import javax.servlet.http.HttpSession;

/**
 *
 * @author spruic
 */
public class SuggestionsController extends HttpServlet {

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
            out.println("<title>Servlet suggestionsController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet suggestionsController at " + request.getContextPath() + "</h1>");
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
        boolean returl = false;
        System.out.println("Servlet");
        List<User> userlist = null;
        List<ArticleLinks> articlelist = null;

        UserSuggestionsDAO userSuggestionDao = new UserSuggestionsDAOImpl();

        Integer userId = null;

        HttpSession session = request.getSession(false);

        if (session != null) {


            if (session.getAttribute("user") != null) {
                User user = (User) session.getAttribute("user");
                userId = user.getUserid();
            }
        }

        //  userGraph = new UserGraph();
        try {
            userlist = new ArrayList<User>();
            articlelist = new ArrayList<ArticleLinks>();

            articlelist = userSuggestionDao.getSuggestedLinks(userId);// get links of suggested articles
            userlist = userSuggestionDao.getUserSuggestions(userId);//get name user who suggested articles and update isviewed
            // to include in method once a metod is called//   while (rs.next()) {

            //              UserGraph usergraph = new UserGraph();

            //            usergraph.setIsnotified(1);
            returl = true;
            retval = true;
        } catch (SQLException ex) {

            Logger.getLogger(SignUpController.class.getName()).log(Level.SEVERE, null, ex);

        }
        request.setAttribute("status", retval);

        request.setAttribute("user", userlist);
        request.setAttribute("links", articlelist);
        RequestDispatcher rd = request.getRequestDispatcher("client/suggestions.jsp");

        rd.forward(request, response);

    }
}
