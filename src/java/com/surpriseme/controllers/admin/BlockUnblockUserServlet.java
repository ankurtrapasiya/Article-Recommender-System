/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.surpriseme.controllers.admin;

import com.surpriseme.DAO.BlockedUsersDAO;
import com.surpriseme.DAOImpl.BlockedUserDAOImpl;
import com.surpriseme.DAOImpl.UserDAOImpl;
import com.surpriseme.entities.BlockedUsers;
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
import org.json.simple.JSONObject;

/**
 *
 * @author peace
 */
public class BlockUnblockUserServlet extends HttpServlet {

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
            out.println("<title>Servlet BlockUnblockUserServlet</title>");
            out.println("</head>");
            out.println("<body> List<T> getAll() throws SQLException;");
            out.println("<h1>Servlet BlockUnblockUserServlet at " + request.getContextPath() + "</h1>");
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
        // processRequest(request, response);
        UserDAOImpl user = new UserDAOImpl();
        BlockedUsersDAO blockedUser = new BlockedUserDAOImpl();
        User u = null;
        BlockedUsers bu = null;
        List<BlockedUsers> rtval = new ArrayList<BlockedUsers>();
        //List<String> username = new ArrayList<String>();
        //List<String> blockername = new ArrayList<String>();
        List<JSONObject> data = new ArrayList<JSONObject>();
        JSONObject obj = null;
        String username;
        String blockername;
        HttpSession session = null;
        try {

            rtval = blockedUser.getAllBlocked();
            for (int i = 0; i < rtval.size(); i++) {
                obj = new JSONObject();
                bu = new BlockedUsers();
                bu = rtval.get(i);
                u = user.findById(bu.getUserid());
                //username.add(u.getUsername());
                username = u.getUsername();
                u = user.findById(bu.getBlockerid());
                //blockername.add(u.getUsername());
                blockername = u.getUsername();
                obj.put("bu", bu);
                obj.put("user", username);
                obj.put("blocker", blockername);
                data.add(obj);
            }

        } catch (SQLException ex) {
            Logger.getLogger(BlockUnblockUserServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        session = request.getSession();
        session.setAttribute("data", data);
        //session.setAttribute("user",username);
        //session.setAttribute("blocker",blockername);
        //request.setAttribute("data", rtval);
        //RequestDispatcher rd = request.getReqat com.surpriseme.adminCotrollers.BlockUnblockUserServlet.doGet(BlockUnblockUserServlet.java:81)uestDispatcher("admin/BlockUnblockUser.jsp");
        //rd.forward(request, response);

        response.sendRedirect("BlockUnblockUser.jsp");


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
        String str = (String) request.getParameter("toggle");
        UserDAOImpl user = new UserDAOImpl();
        BlockedUsersDAO blockedUser = new BlockedUserDAOImpl();
        int userid = Integer.parseInt(request.getParameter("userid"));
        int blockerid = Integer.parseInt(request.getParameter("blockerid"));
        if (str.contentEquals("Block")) {
            try {
                blockedUser.blockUser(userid, blockerid);
            } catch (SQLException ex) {
                Logger.getLogger(BlockUnblockUserServlet.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            try {
                blockedUser.unblockUser(userid, blockerid);
            } catch (SQLException ex) {
                Logger.getLogger(BlockUnblockUserServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        doGet(request, response);

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
