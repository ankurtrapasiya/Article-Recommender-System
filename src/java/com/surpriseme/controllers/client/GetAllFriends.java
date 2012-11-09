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
import java.util.Iterator;
import java.util.List;
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
public class GetAllFriends extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        JSONObject jObj = null;
        JSONArray jArr = new JSONArray();

        Integer userId = 1;

        HttpSession session = req.getSession(false);

        if (session != null) {
            userId = ((User) session.getAttribute("user")).getUserid();
        }

        if (req.getParameter("action") != null && req.getParameter("action").equals("givemefriends")) {

            UserGraphDAO userGraphDao = new UserGraphDAOImpl();
            List<User> friends = null;
            try {
                //friends = userGraphDao.getAllFriends(u.getUserid());
                friends = userGraphDao.getAllFriends(userId);
            } catch (SQLException ex) {
            }

            if (friends != null) {
                Iterator<User> iterator = friends.iterator();

                while (iterator.hasNext()) {
                    User user = iterator.next();
                    jObj = new JSONObject();

                    jObj.put("id", user.getUserid());
                    jObj.put("name", user.getFirstname() + " " + user.getLastname());

                    jArr.add(jObj);

                }

            }
        }
        jObj = new JSONObject();
        jObj.put("content", jArr);

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().println(jObj);
    }
}
