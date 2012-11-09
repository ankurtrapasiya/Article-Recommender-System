/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.surpriseme.controllers.client;

import com.surpriseme.DAO.UserGraphDAO;
import com.surpriseme.DAOImpl.UserDAOImpl;
import com.surpriseme.DAOImpl.UserGraphDAOImpl;
import com.surpriseme.entities.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
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
 * @author udit
 */
public class SearchUserController extends HttpServlet {

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

        PrintWriter out = response.getWriter();
        JSONArray jArr = new JSONArray();
        JSONObject jObj = new JSONObject();

        User u = new User();
        UserDAOImpl userDao = new UserDAOImpl();
        UserGraphDAO graphDao = new UserGraphDAOImpl();

        Integer userid = null;

        HttpSession session = request.getSession(false);
        if (session != null) {
            if (session.getAttribute("user") != null) {

                userid = ((User) session.getAttribute("user")).getUserid();

                String username = request.getParameter("username");
                Boolean isFriend = false;
                try {
                    List<User> alluserlist = userDao.searchUser(username, userid);
                    List<User> friendslist = graphDao.getAllFriends(userid);
                    if (!alluserlist.isEmpty()) {
                        for (int i = 0; i < alluserlist.size(); i++) {
                            u = alluserlist.get(i);
                            if (!friendslist.isEmpty()) {
                                for (int j = 0; j < friendslist.size(); j++) {
                                    if (alluserlist.get(i).getUserid() == friendslist.get(j).getUserid()) {
                                        isFriend = true;
                                        break;
                                    }
                                }
                            }
                            jObj = new JSONObject();
                            jObj.put("userid", u.getUserid());
                            jObj.put("firstname", u.getFirstname());
                            jObj.put("lastname", u.getLastname());
                            jObj.put("image", u.getImage());
                            if (isFriend == true) {
                                jObj.put("isfriend", 1);
                                isFriend = false;
                            }
                            jArr.add(jObj);
                        }
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(AddToGraphController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }



        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        jObj = new JSONObject();
        jObj.put("content", jArr);
        out.println(jObj);

    }
}