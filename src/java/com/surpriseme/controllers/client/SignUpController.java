package com.surpriseme.controllers.client;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.surpriseme.DAOImpl.UserDAOImpl;
import com.surpriseme.entities.User;
import com.surpriseme.entities.UserActivation;
import com.surpriseme.utils.Utilities;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;
import net.tanesha.recaptcha.ReCaptchaImpl;
import net.tanesha.recaptcha.ReCaptchaResponse;

/**
 * Servlet implementation class SignUpController
 */
public class SignUpController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private boolean verifyCaptcha(HttpServletRequest request) {
        String remoteAddr = request.getRemoteAddr();
        ReCaptchaImpl reCaptcha = new ReCaptchaImpl();
        reCaptcha.setPrivateKey("6LdpJNgSAAAAACVXRbfQR1kb-LMx0tiuZbu337jG");
        String challenge = request.getParameter("recaptcha_challenge_field");
        String uresponse = request.getParameter("recaptcha_response_field");
        ReCaptchaResponse reCaptchaResponse = reCaptcha.checkAnswer(remoteAddr, challenge, uresponse);
        return reCaptchaResponse.isValid();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String firstName = req.getParameter("txtFirstName");
        String lastName = req.getParameter("txtLastName");
        String email = req.getParameter("txtEmail");
        String username = req.getParameter("username");
        String password = req.getParameter("txtPassword");

        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        Date dob = null;
        try {
            dob = df.parse(req.getParameter("txtDob"));
            String dt = dob.toString();
        } catch (java.text.ParseException ex) {
            Logger.getLogger(SignUpController.class.getName()).log(Level.SEVERE, null, ex);
        }

        String country = req.getParameter("txtCountry");

        User u = new User(username, password, email, firstName, lastName, dob, country, Boolean.TRUE, new Date());
        if (verifyCaptcha(req)) {


            UserDAOImpl userDao = new UserDAOImpl();
            Integer retval = null;
            try {

                //Error in the following method. For detailed comment check userdoaimpl
                retval=userDao.saveOrUpdate(u);
            } catch (SQLException ex) {
                Logger.getLogger(SignUpController.class.getName()).log(Level.SEVERE, null, ex);
            }

            String hashedtext = Utilities.toMD5(firstName + lastName + email);
            try {
                u = userDao.findByUsername(u.getUsername());
            } catch (SQLException ex) {
                Logger.getLogger(SignUpController.class.getName()).log(Level.SEVERE, null, ex);
            }

            String mailmessage = "Dear " + firstName + " " + lastName + "\n\nKindly click on the following link to verify your email id.\n\nhttp://localhost:8080/UserRegistration/VerifyEmail?tocken=" + hashedtext;

            if (retval!=null) {
                boolean sent;
                sent = Utilities.sendActivationMail(email, mailmessage);

                if (true) {
                    UserActivation ua;
                    ua = new UserActivation(u.getUserid(), hashedtext, new Timestamp(new Date().getTime()), false);
                    try {
                        userDao.insertIntoUserActivation(ua);
                    } catch (SQLException ex) {
                        Logger.getLogger(SignUpController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
            HttpSession session = req.getSession();
            session.setAttribute("user", u);
            RequestDispatcher rd = req.getRequestDispatcher("../FetchInterestController");
            rd.forward(req, resp);
        } else {
            RequestDispatcher rd = req.getRequestDispatcher("../client/registration.jsp");
            rd.forward(req, resp);
        }
    }
}
