package com.surpriseme.controllers.client;

import com.surpriseme.DAOImpl.UserDAOImpl;
import com.surpriseme.entities.User;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

// import javax.servlet.http.HttpSession;
public class UserProfileController extends javax.servlet.http.HttpServlet {

    static final long serialVersionUID = 1L;

    public UserProfileController() {
        super();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Update User profile with Requested Value   

        boolean retval = false;
        int UserId, ret = 0;


        HttpSession sid = request.getSession(false);
        User user = (User) sid.getAttribute("user");
        String Edit = request.getParameter("id");

        UserId = user.getUserid();
        UserDAOImpl userDao = new UserDAOImpl();
        if (Edit != null) {
            try {

                user = new User();
                user = userDao.findById(UserId);
                // Set Edited Value 
                user.setFirstname(request.getParameter("Name"));
                System.out.println("Name " + request.getParameter("Name"));
                user.setFirstname(request.getParameter("Fname"));
                user.setLastname(request.getParameter("Lname"));

                user.setEmail(request.getParameter("Email"));

                // set Date of Birth
                Date d = null;
                SimpleDateFormat fm = null;
                DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                String dob = request.getParameter("Dob");

                try {
                    d = formatter.parse(dob);
                } catch (ParseException ex) {
                    ex.printStackTrace();
                }
                user.setDob(new Date(d.getTime()));
                user.setCity(Integer.parseInt(request.getParameter("City")));
                user.setState(Integer.parseInt(request.getParameter("State")));
                user.setCountry(Integer.parseInt(request.getParameter("Country")));

                /* Collection<Part> parts = request.getParts();
      
                 System.out.println(request.getParameter("upload"));
                
                 Part filePart = request.getPart("upload");
                 InputStream imageInputStream = filePart.getInputStream();
                 //read imageInputStream
                 //System.out.println("part name"+filePart.getName());
                 filePart.write("D:\\" + filePart.getName()); 
                 user.setImage("../images/profile/"+request.getParameter("upload"));       */

                // Update setted information 
                ret = userDao.saveOrUpdate(user);

            } catch (SQLException ex) {
                Logger.getLogger(SignUpController.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (ret == 0) {
                // if update successfully, send redirect to again profile.jsp page with successfull msg

                retval = true;
                request.setAttribute("status", retval);
                request.setAttribute("user", user);

                RequestDispatcher rd = request.getRequestDispatcher("profile.jsp");
                rd.forward(request, response);
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        boolean retval = false;
        int UserId, ret = 0;

        HttpSession sid = request.getSession(false);
        User user = (User) sid.getAttribute("user");

        UserId = user.getUserid();
        // Display User Profile username,firstname,lastname,date of birth,city,state,country
        UserDAOImpl userDao = new UserDAOImpl();
        try {
            user = new User();
            user = userDao.findById(UserId); // search user with given ID
            retval = true;

        } catch (SQLException ex) {
            Logger.getLogger(SignUpController.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.setAttribute("status", retval);
        request.setAttribute("user", user);
        RequestDispatcher rd = request.getRequestDispatcher("profile.jsp");
        // redirect to profile.jsp page
        rd.forward(request, response);


    }
}
