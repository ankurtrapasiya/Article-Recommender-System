 
package com.surpriseme.controllers.client;

import com.surpriseme.DAOImpl.UserDAOImpl;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Harmish
 */
public class EmailCheckerController extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    //    doPost(request, response);
        PrintWriter out = response.getWriter();
        out.println("Houston this is alpha do you copy??");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            boolean available = false;
        PrintWriter out = response.getWriter();
        try {
            System.out.println("The email id = " + request.getParameter("email"));
            available = new UserDAOImpl().isEmailAvailable(request.getParameter("email"));
        } catch (SQLException ex) {
            Logger.getLogger(EmailCheckerController.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(available) {
            out.println(1);
        } else {
            out.println(0);
        }
    }
    
    

}
