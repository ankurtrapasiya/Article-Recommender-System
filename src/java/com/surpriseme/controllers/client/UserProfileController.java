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

// import javax.servlet.http.HttpSession;
public class UserProfileController extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {

    static final long serialVersionUID = 1L;
    boolean retval = false;
    public User user = null;
    int UserId, ret = 0;

    public UserProfileController() {
        super();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        //HttpSession sid=request.getSession(true);
        //String Id=(String)sid.getAttribute("userid");
        String View = ""; //request.getParameter("btnView");
        String Edit = request.getParameter("btnEdit");

        UserId = 1;
        UserDAOImpl userDao = new UserDAOImpl();

        if (View != null) {
            try {
                user = new User();
                user = userDao.findById(UserId);
                retval = true;

            } catch (SQLException ex) {
                Logger.getLogger(SignUpController.class.getName()).log(Level.SEVERE, null, ex);
            }
            request.setAttribute("status", retval);
            request.setAttribute("user", user);
            RequestDispatcher rd = request.getRequestDispatcher("profile.jsp");
            rd.forward(request, response);
        }


        if (Edit != null) {
            try {
                user = new User();
                user = userDao.findById(UserId);
                user.setFirstname(request.getParameter("Fname"));
                user.setLastname(request.getParameter("Lname"));
                user.setEmail(request.getParameter("Email"));

                user.setImage(request.getParameter("imgpath_1"));

                Date d = null;
                SimpleDateFormat fm = null;
                DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                String dob = request.getParameter("Dob");

                System.out.println(dob);
                try {
                    d = formatter.parse(dob);
                } catch (ParseException ex) {
                    Logger.getLogger(UserProfileController.class.getName()).log(Level.SEVERE, null, ex);
                }
                user.setDob(new Date(d.getTime()));
                user.setCity(request.getParameter("City"));
                user.setState(request.getParameter("State"));
                user.setCountry(request.getParameter("Country"));
                ret = userDao.saveOrUpdate(user);

            } catch (SQLException ex) {
                Logger.getLogger(SignUpController.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (ret == 0) {
                retval = true;
                request.setAttribute("status", retval);
                request.setAttribute("user", user);

                RequestDispatcher rd = request.getRequestDispatcher("client/profile.jsp");
                rd.forward(request, response);
            }
        }


    }
}
