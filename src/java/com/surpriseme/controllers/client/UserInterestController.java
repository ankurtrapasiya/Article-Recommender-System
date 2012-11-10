package com.surpriseme.controllers.client;

import com.surpriseme.DAO.UserInterestDAO;
import com.surpriseme.DAOImpl.UserInterestDAOImpl;
import com.surpriseme.entities.Interest;
import com.surpriseme.entities.User;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UserInterestController extends javax.servlet.http.HttpServlet {

    static final long serialVersionUID = 1L;

    public UserInterestController() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // View all interest on interest page

        Boolean retval = false;
        int UserId;
        int InterestId;

        HttpSession sid = request.getSession(false);
        if (sid != null) {
            UserId = ((User) sid.getAttribute("user")).getUserid();


            UserInterestDAO userDao = null;
            List<Interest> interestList = new ArrayList<Interest>();
            List<Interest> notInterestList = null;

            try {
                userDao = new UserInterestDAOImpl();
                interestList = userDao.getUserInterests(UserId, true); // interest wghich are in user account selected
                notInterestList = userDao.getUserInterests(UserId, false); // interest which are not selected in user account
                retval = true;

            } catch (Exception ex) {
                Logger.getLogger(SignUpController.class.getName()).log(Level.SEVERE, null, ex);
            }
            request.setAttribute("status", retval);
            request.setAttribute("interest", interestList);
            request.setAttribute("Ninterests", notInterestList);
            RequestDispatcher rd = request.getRequestDispatcher("client/interests.jsp");
            // send redirect to interest.jsp page with interestlist
            rd.forward(request, response);

        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // HttpSession sid=request.getSession(true);
        //String Id=(String)sid.getAttribute("userid");

        Boolean retval = false;
        int UserId;
        int InterestId;

        HttpSession sid = request.getSession(false);
        UserId = ((User) sid.getAttribute("user")).getUserid();

        String Delete = request.getParameter("btnDelete");
        String Add = request.getParameter("btnAdd");

        UserInterestDAO userDao = null;
        List<Interest> interestList = new ArrayList<Interest>();
        List<Interest> notInterestList = null;

        if (Delete != null) {
            // delete all selected interest list from user account
            String hiddenId = request.getParameter("hdnInterestId");
            ArrayList<String> words = new ArrayList<String>();
            StringTokenizer st = new StringTokenizer(hiddenId, ",");

            while (st.hasMoreTokens()) {
                String token = st.nextToken();
                if (token != ",") {
                    words.add(token);
                }
            }

            try {
                userDao = new UserInterestDAOImpl();
                interestList = userDao.getUserInterests(UserId, true);
            } catch (SQLException ex) {
                Logger.getLogger(UserInterestController.class.getName()).log(Level.SEVERE, null, ex);
            }
            if ((interestList.size()) > (words.size())) { // condition for checking 'at least one interest should be there in account 
                for (int i = 0; i < words.size(); i++) {
                    try {

                        int id = Integer.parseInt(words.get(i));
                        userDao = new UserInterestDAOImpl();
                        retval = userDao.removeInterestFromUser(id, UserId); //delete interestList successfull
                        request.setAttribute("status", retval);
                    } catch (Exception ex) {
                        Logger.getLogger(SignUpController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            } else {

                retval = false; // deletion unsuccessfull
                request.setAttribute("status", retval);
            }
            System.out.println("retvale " + retval);
            try {
                userDao = new UserInterestDAOImpl();
                interestList = userDao.getUserInterests(UserId, true); // interestList which are in user account
                notInterestList = userDao.getUserInterests(UserId, false);// interestList which are not in account
            } catch (SQLException ex) {
                Logger.getLogger(UserInterestController.class.getName()).log(Level.SEVERE, null, ex);
            }


            // send redirect to interest.jsp page
            request.setAttribute("interest", interestList);
            request.setAttribute("Ninterests", notInterestList);
            RequestDispatcher rd = request.getRequestDispatcher("client/interests.jsp");
            rd.forward(request, response);
        }


        if (Add != null) {
            // add selected interestList in account
            String hiddenId = request.getParameter("hdnInterestId");
            ArrayList<String> words = new ArrayList<String>();
            StringTokenizer st = new StringTokenizer(hiddenId, ",");
            while (st.hasMoreTokens()) {
                String token = st.nextToken();
                if (token != ",") {
                    words.add(token);
                }
            }
            for (int i = 0; i < words.size(); i++) {
                try {
                    int id = Integer.parseInt(words.get(i));
                    userDao = new UserInterestDAOImpl();
                    retval = userDao.addInterestToUser(id, UserId); // add interest successfully

                } catch (Exception ex) {
                    Logger.getLogger(SignUpController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            try {
                userDao = new UserInterestDAOImpl();
                interestList = userDao.getUserInterests(UserId, true); // interestList which are in user account
                notInterestList = userDao.getUserInterests(UserId, false);// Interestlist which are not in user Account

            } catch (SQLException ex) {
                Logger.getLogger(UserInterestController.class.getName()).log(Level.SEVERE, null, ex);
            }
            // send redirect to interest.jsp page 
            request.setAttribute("status", retval);
            request.setAttribute("interest", interestList);
            request.setAttribute("Ninterests", notInterestList);
            RequestDispatcher rd = request.getRequestDispatcher("client/interests.jsp");
            rd.forward(request, response);
        }

    }
}