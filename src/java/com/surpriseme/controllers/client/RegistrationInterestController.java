/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.surpriseme.controllers.client;

import com.surpriseme.DAO.UserInterestDAO;
import com.surpriseme.DAOImpl.UserInterestDAOImpl;
import com.surpriseme.entities.UserInterest;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ankur
 */
public class RegistrationInterestController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        HttpSession session = req.getSession(false);
        UserInterestDAO interestDao = new UserInterestDAOImpl();


        if (session != null) {

            String[] interestIds = req.getParameterValues("interest");

            String userId = req.getParameter("hdnUserId");

            UserInterest userInterest = null;


            for (int i = 0; i < interestIds.length; i++) {
                try {
                    interestDao.addInterestToUser(Integer.parseInt(interestIds[i]), Integer.parseInt(userId));
                } catch (SQLException ex) {
                    Logger.getLogger(RegistrationInterestController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            resp.sendRedirect("/surpriseme");
        }


    }
}
