/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.surpriseme.controllers.client;

import com.surpriseme.DAO.BlockedUsersDAO;
import com.surpriseme.DAO.UserGraphDAO;
import com.surpriseme.DAOImpl.BlockedUserDAOImpl;
import com.surpriseme.DAOImpl.UserDAOImpl;
import com.surpriseme.DAOImpl.UserGraphDAOImpl;
import com.surpriseme.entities.BlockedUsers;
import com.surpriseme.entities.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.apache.log4j.Priority;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author udit
 */
public class UserGraphController extends HttpServlet {

    private static final Logger logger = Logger.getLogger(UserDAOImpl.class);

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
        PrintWriter out = response.getWriter();

        User u = new User();
        JSONArray jArr = new JSONArray();
        JSONObject jObj = new JSONObject();
        HttpSession session = request.getSession(false);
        Integer userid = null;

        //int userid = 101;
        //session se ID
        if (session != null) {
            userid = ((User) session.getAttribute("user")).getUserid();

            UserGraphDAO userDao = new UserGraphDAOImpl();
            BlockedUsersDAO blockDao = new BlockedUserDAOImpl();

            try {
                List<User> list = userDao.getAllFriends(userid);
                List<BlockedUsers> blockedlist = blockDao.getBlockedUsers();
                Boolean isblocked = false;
                if ((list != null) || (!list.isEmpty())) {
                    for (int i = 0; i < list.size(); i++) {
                        u = list.get(i);
                        if (((blockedlist != null) || !blockedlist.isEmpty()) ) {
                            for (int j = 0; j < blockedlist.size(); j++) {
                                if (list.get(i).getUserid() == blockedlist.get(j).getUserid()) {
                                    isblocked = true;
                                    break;
                                }
                            }
                        }
                        jObj = new JSONObject();
                        jObj.put("userid", u.getUserid());
                        jObj.put("firstname", u.getFirstname());
                        jObj.put("lastname", u.getLastname());
                        jObj.put("image", u.getImage());
                        if (isblocked == true) {
                            jObj.put("blocked", 1);
                            isblocked = false;
                        }
                        jArr.add(jObj);
                    }

                } else {
                    logger.log(Priority.ERROR, "Sources not found in database.");
                }

            } catch (SQLException ex) {
                logger.log(Priority.ERROR, ex.toString());
                ex.printStackTrace();
            }
        }

        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        jObj = new JSONObject();
        jObj.put("content", jArr);
        out.println(jObj);

    }
}