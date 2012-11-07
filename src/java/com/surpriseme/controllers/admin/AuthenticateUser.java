/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.surpriseme.controllers.admin;

import com.surpriseme.DAOImpl.RoleDAOImpl;
import com.surpriseme.DAOImpl.UserDAOImpl;
import com.surpriseme.entities.User;
import com.surpriseme.entities.UserRole;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author udit
 */
public class AuthenticateUser extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String msg = "Error Message";
        HttpSession session = null;
        try {
            session = request.getSession();
            String username = request.getParameter("username");
            String password = request.getParameter("password");

            UserDAOImpl userdaoimpl = new UserDAOImpl();
            User user = userdaoimpl.findByUsername(username);
            RoleDAOImpl roledaoimpl = new RoleDAOImpl();
            UserRole userrole = roledaoimpl.findUserRoleByUserId(user.getUserid());
            if (user == null) {
                msg = "Invalid Username or Password.";
            } else {
                if (password.equals(user.getPassword())) {
                    msg = "Valid User";
                    if (userrole.getRolename().equals("Admin")) {
                        msg = "ok";
                        session.setAttribute("user", user);
                    } else {
                        msg = "You dont have proper privileges to access this pages.";
                    }
                } else {
                    msg = "Invalid Username or Password.";
                }
            }

            out.print(msg);
            out.close();
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }

    }
}
