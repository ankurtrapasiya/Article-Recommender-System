/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.surpriseme.controllers.admin;

import com.surpriseme.DAO.InterestDAO;
import com.surpriseme.DAOImpl.InterestDAOImpl;
import com.surpriseme.DAOImpl.UserDAOImpl;
import com.surpriseme.entities.Interest;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author udit
 */
public class InterestController extends HttpServlet {

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Interest> list = null;
        System.out.println("In");
        InterestDAOImpl ABC = new InterestDAOImpl();
        try {

            list = ABC.getAll();
        } catch (SQLException ex) {
            Logger.getLogger(InterestController.class.getName()).log(Level.SEVERE, null, ex);
        }

        request.setAttribute("list", list);
        request.getRequestDispatcher("interest.jsp").forward(request, response);

    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String tem = request.getParameter("sub");
        InterestDAOImpl interestDao = null;
        Interest interest = null;
        int retval;

        if (tem.contentEquals("Edit")) {

            interestDao = new InterestDAOImpl();

            int interestid = Integer.parseInt(request.getParameter("Interestid"));

            try {
                interest = interestDao.findById(interestid);

            } catch (SQLException ex) {
                Logger.getLogger(InterestController.class.getName()).log(Level.SEVERE, null, ex);
            }
            request.setAttribute("XYZ", interest);
            request.getRequestDispatcher("editinterest.jsp").forward(request, response);
        } else {

            interestDao = new InterestDAOImpl();
            boolean returnval = false;
            int interestid = Integer.parseInt(request.getParameter("Interestid"));

            try {
                ResourceBundle bundle = ResourceBundle.getBundle("com.surpriseme.config.iconpath");
                String filePath = bundle.getString("file-upload-interest");

                interest = interestDao.findById(interestid);
                String str = interest.getIconpath();
                File f = new File(filePath + str);
                if (f.exists()) {
                    if (str.contentEquals("icn_interest.png")) {
                    } else {
                        f.delete();
                    }
                }
                returnval = interestDao.delete(interestid);

            } catch (SQLException ex) {
                Logger.getLogger(InterestController.class.getName()).log(Level.SEVERE, null, ex);
            }
            doGet(request, response);
        }

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
