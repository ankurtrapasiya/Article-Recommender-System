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
    Boolean retval = false;
    int UserId;
    int InterestId;

    public UserInterestController() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String interestIds[] = req.getParameterValues("interest");

        UserInterestDAO userInterestDao = new UserInterestDAOImpl();


        HttpSession session = req.getSession();
        User u = (User) session.getAttribute("user");
        int id = u.getUserid();
        
        try {
            for (int i = 0; i < interestIds.length; i++) {

                //Ankur after you fix the problem of signupcontroller mentioned in email uncomment all the line in this 
                //servlet and remove the statement at line 42

                //UNCOMMENT THIS LINE
                userInterestDao.addInterestToUser(Integer.parseInt(interestIds[i]), id);

                //remove the belove line
                //userInterestDao.addInterestToUser(Integer.parseInt(interestIds[i]), 1);

            }
        } catch (SQLException ex) {
            Logger.getLogger(UserInterestController.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // HttpSession sid=request.getSession(true);
        //String Id=(String)sid.getAttribute("userid");

        UserId = 3;
        String View = request.getParameter("btnView");
        String Delete = request.getParameter("btnDelete");
        String Add = request.getParameter("btnAdd");


        UserInterestDAO userDao = null;
        List<Interest> interestList = new ArrayList<Interest>();
        List<Interest> notInterestList = null;

        if (View != null) {
            try {
                userDao = new UserInterestDAOImpl();
                interestList = userDao.getUserInterests(UserId, true);
                notInterestList = userDao.getUserInterests(UserId, false);
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

            try {
                userDao = new UserInterestDAOImpl();
                interestList = userDao.getUserInterests(UserId, true);
            } catch (SQLException ex) {
                Logger.getLogger(UserInterestController.class.getName()).log(Level.SEVERE, null, ex);
            }
            if ((interestList.size()) > (words.size())) {
                for (int i = 0; i < words.size(); i++) {
                    try {

                        int id = Integer.parseInt(words.get(i));
                        userDao = new UserInterestDAOImpl();
                        retval = userDao.removeInterestFromUser(id, UserId);
                    } catch (Exception ex) {
                        Logger.getLogger(SignUpController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            } else {
                retval = false;
            }
            try {
                userDao = new UserInterestDAOImpl();
                interestList = userDao.getUserInterests(UserId, true);
                notInterestList = userDao.getUserInterests(UserId, false);
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
                    userDao = new UserInterestDAOImpl();
                    retval = userDao.addInterestToUser(id, UserId);

                } catch (Exception ex) {
                    Logger.getLogger(SignUpController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            try {
                userDao = new UserInterestDAOImpl();
                interestList = userDao.getUserInterests(UserId, true);
                notInterestList = userDao.getUserInterests(UserId, false);

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
