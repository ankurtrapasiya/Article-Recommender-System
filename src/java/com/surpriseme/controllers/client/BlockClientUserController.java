/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.surpriseme.controllers.client;

import com.surpriseme.DAO.BlockedUsersDAO;
import com.surpriseme.DAOImpl.BlockedUserDAOImpl;
import com.surpriseme.entities.BlockedUsers;
import com.surpriseme.entities.User;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author udit
 */
public class BlockClientUserController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        User u = new User();
        BlockedUsersDAO userDao = new BlockedUserDAOImpl();
        Integer blockerid = null;

        HttpSession session = request.getSession(false);
        if (session != null) {
            if (session.getAttribute("user") != null) {
                blockerid = ((User) session.getAttribute("user")).getUserid();

                try {
                    int userid = Integer.parseInt(request.getParameter("userid"));

                    BlockedUsers bu = new BlockedUsers(userid, blockerid, new Timestamp(new java.util.Date().getTime()), "xyz", false);

                    userDao.blockUserRequest(bu);

                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}
