/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.surpriseme.controllers.client;

import com.surpriseme.DAOImpl.ArticleDAOImpl;
import com.surpriseme.DAOImpl.UserDAOImpl;
import com.surpriseme.entities.Article;
import com.surpriseme.helper.SuggesionsHelper;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author spruic
 */
@WebServlet(name = "suggestionsController", urlPatterns = {"/suggestionsController"})
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

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Integer articleId = Integer.parseInt(req.getParameter("articleid"));
        ArticleDAOImpl articleDao = new ArticleDAOImpl();
        Article article = null;

        JSONArray jArr = null;
        JSONObject jObj = null;

        try {
            article = articleDao.findById(articleId);
        } catch (SQLException ex) {
            Logger.getLogger(SuggestionsController.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (article != null) {

            jArr = new JSONArray();
            jObj = new JSONObject();


            jObj.put("articleid", article.getArticleid());
            jObj.put("title", article.getTitle());
            jObj.put("body", article.getBody());
            jObj.put("upvote", article.getUpvote());
            jObj.put("downvote", article.getDownvote());
            jObj.put("viewed", article.getViewed());
            jObj.put("timestamp", article.getTimestamp().toString());
            jObj.put("popularityscore", article.getPopularityscore());
            jObj.put("publicationdate", article.getPublicationdate().toString());

            jArr.add(jObj);

        }

        jObj=new JSONObject();
        jObj.put("content", jArr);
        
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().println(jObj);

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
        List<SuggesionsHelper> suglist = null;

        UserDAOImpl userDao = new UserDAOImpl();
        //  userGraph = new UserGraph();
        try {
            suglist = new ArrayList<SuggesionsHelper>();
            // get links of suggested articles
            suglist = userDao.getUserSuggestions();//get name user who suggested articles and update isviewed
            // to include in method once a metod is called//   while (rs.next()) {

            //              UserGraph usergraph = new UserGraph();

            //            usergraph.setIsnotified(1);

            retval = true;
        } catch (SQLException ex) {

            Logger.getLogger(SignUpController.class.getName()).log(Level.SEVERE, null, ex);

        }
        request.setAttribute("status", retval);

        request.setAttribute("user", suglist);

        RequestDispatcher rd = request.getRequestDispatcher("client/suggestions.jsp");

        rd.forward(request, response);

    }
}
