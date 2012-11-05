/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.surpriseme.controllers.admin;

import com.surpriseme.DAOImpl.SourceDAOImpl;
import com.surpriseme.entities.Source;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author udit
 */
public class SourceController extends HttpServlet {

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
        List<Source> list = null;
        SourceDAOImpl ABC = new SourceDAOImpl();
        try {

            list = ABC.getAll();


        } catch (SQLException ex) {

            Logger.getLogger(SourceController.class.getName()).log(Level.SEVERE, null, ex);

        }

        request.setAttribute("list", list);
        request.getRequestDispatcher("source.jsp").forward(request, response);
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
        SourceDAOImpl sourceDao = null;
        Source source = null;
        int retval;
        System.out.println(tem);
        if (tem != null && tem.contentEquals("Edit")) {

            sourceDao = new SourceDAOImpl();
            System.out.println("in edit");
            int sourceid = Integer.parseInt(request.getParameter("Sourceid"));
            System.out.println("source id");
            try {
                source = sourceDao.findById(sourceid);

            } catch (SQLException ex) {
                Logger.getLogger(SourceController.class.getName()).log(Level.SEVERE, null, ex);
            }
            request.setAttribute("XYZ", source);
            request.getRequestDispatcher("editsource.jsp").forward(request, response);
        } else if (tem != null && tem.contentEquals("Delete")) {

            sourceDao = new SourceDAOImpl();
            boolean returnval = false;
            int sourceid = Integer.parseInt(request.getParameter("Sourceid"));

            try {
                 ResourceBundle bundle = ResourceBundle.getBundle("com.surpriseme.config.iconpath");
                String filePath = bundle.getString("file-upload-source");

                source = sourceDao.findById(sourceid);
                String str = source.getIcon();
                File f = new File(filePath + str);
                if (f.exists()) {
                    if (str.contentEquals("icn_source.png")) {
                    } else {
                        f.delete();
                    }
                }
                returnval = sourceDao.delete(sourceid);

            } catch (SQLException ex) {
                Logger.getLogger(SourceController.class.getName()).log(Level.SEVERE, null, ex);
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
