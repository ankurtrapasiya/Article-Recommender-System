/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.surpriseme.controllers.client;

import com.surpriseme.DAOImpl.UserDAOImpl;
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
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author ankur
 */
public class LoginController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (request.getParameter("signout") != null) {
            session.invalidate();
            response.sendRedirect("/surpriseme");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(true);

        if (request.getParameter("btnRegister") != null) {

            response.sendRedirect("SignUp");

        } else {
            JSONArray jArr = new JSONArray();
            JSONObject jObj = null;

            UserDAOImpl userDao = new UserDAOImpl();
            User user = null;

            String username = request.getParameter("username");
            String password = request.getParameter("password");
            try {
                user = userDao.isValidUser(username, password);
            } catch (SQLException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }

            if (user != null) {
                session.setAttribute("user", user);
                session.setAttribute("islogged", "true");
                response.sendRedirect("index.jsp");

            } else {
                jObj = new JSONObject();
                jObj.put("status", "fail");
                jArr.add(jObj);
            }

            jObj = new JSONObject();
            jObj.put("content", jArr);

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().println(jObj);

        }
    }
}
