package com.surpriseme.controllers.client;

import com.surpriseme.DAOImpl.UserDAOImpl;
import com.surpriseme.entities.Interest;
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

public class UserInterestController extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {

    static final long serialVersionUID = 1L;
    Boolean retval = false;
    int UserId;
    int InterestId;

    public UserInterestController() {
        super();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // HttpSession sid=request.getSession(true);
        //String Id=(String)sid.getAttribute("userid");

        UserId = 3;
        String View = request.getParameter("btnView");
        String Delete = request.getParameter("btnDelete");
        String Add = request.getParameter("btnAdd");


        UserDAOImpl UserDao = null;
        List<Interest> interestList = new ArrayList<Interest>();
        List<Interest> notInterestList = null;

        if (View != null) {
            try {
                UserDao = new UserDAOImpl();
                interestList = UserDao.getUserInterests(UserId, true);
                notInterestList = UserDao.getUserInterests(UserId, false);
                retval = true;



            } catch (Exception ex) {
                Logger.getLogger(SignUpController.class.getName()).log(Level.SEVERE, null, ex);
            }
            request.setAttribute("status", retval);
            request.setAttribute("interest", interestList);
            request.setAttribute("Ninterests", notInterestList);
            RequestDispatcher rd = request.getRequestDispatcher("client/interests.jsp");
            rd.forward(request, response);
        }


        if (Delete != null) {
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
                    UserDao = new UserDAOImpl();
                    retval = UserDao.removeInterestFromUser(id, UserId);
                } catch (Exception ex) {
                    Logger.getLogger(SignUpController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            try {
                UserDao = new UserDAOImpl();
                interestList = UserDao.getUserInterests(UserId, true);
                notInterestList = UserDao.getUserInterests(UserId, false);
            } catch (SQLException ex) {
                Logger.getLogger(UserInterestController.class.getName()).log(Level.SEVERE, null, ex);
            }
            request.setAttribute("status", retval);
            request.setAttribute("interest", interestList);
            request.setAttribute("Ninterests", notInterestList);
            RequestDispatcher rd = request.getRequestDispatcher("client/interests.jsp");
            rd.forward(request, response);
        }


        if (Add != null) {
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
                    UserDao = new UserDAOImpl();
                    retval = UserDao.addInterestToUser(id, UserId);

                } catch (Exception ex) {
                    Logger.getLogger(SignUpController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            try {
                UserDao = new UserDAOImpl();
                interestList = UserDao.getUserInterests(UserId, true);
                notInterestList = UserDao.getUserInterests(UserId, false);

            } catch (SQLException ex) {
                Logger.getLogger(UserInterestController.class.getName()).log(Level.SEVERE, null, ex);
            }
            request.setAttribute("status", retval);
            request.setAttribute("interest", interestList);
            request.setAttribute("Ninterests", notInterestList);
            RequestDispatcher rd = request.getRequestDispatcher("client/interests.jsp");
            rd.forward(request, response);
        }

    }
}
