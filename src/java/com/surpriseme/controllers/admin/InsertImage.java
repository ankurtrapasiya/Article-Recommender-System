/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.surpriseme.controllers.admin;

import com.surpriseme.entities.Image;
import com.surpriseme.utils.DBConnection;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author udit
 */
public class InsertImage extends HttpServlet {

    Image img;
    DBConnection con;

    @Override
    public void init() throws ServletException {
        img = new Image();
        con = new DBConnection();
        try {
            con.connect();
        } catch (SQLException ex) {
            Logger.getLogger(InsertImage.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(InsertImage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

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


        img.setName(request.getParameter("name"));
        img.setTable(request.getParameter("table"));
        img.setColumn(request.getParameter("column"));
        img.setPath_url(request.getParameter("path"));
        img.setFile_url(request.getParameter("file"));
        img.setP_key(request.getParameter("key"));
        img.setP_value(request.getParameter("value"));
        img.setDefaultImage(request.getParameter("defaultimg"));
        request.setAttribute("fileurl", img.getFile_url());
        ResourceBundle rb = ResourceBundle.getBundle("com.surpriseme.config.iconpath");
        String filePath = rb.getString(img.getPath_url());
        request.setAttribute("img", filePath + img.getName());
        request.getRequestDispatcher("InsertImage.jsp").forward(request, response);

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
        ResultSet rs = null;
        String message = null;
        String name = null;
        File file;
        int maxFileSize = 5000 * 1024;
        int maxMemSize = 5000 * 1024;
        // ServletContext context = this.getServletContext();
        //String filePath = context.getInitParameter(img.getPath_url());
        ResourceBundle rb = ResourceBundle.getBundle("com.surpriseme.config.iconpath");
        String filePath = rb.getString(img.getPath_url());
        String[] allowedExtension = {".jpg", ".png", ".gif", ".jpeg"};

        // Verify the content type
        String contentType = request.getContentType();
        if ((contentType.indexOf("multipart/form-data") >= 0)) {

            DiskFileItemFactory factory = new DiskFileItemFactory();
            // maximum size that will be stored in memory
            factory.setSizeThreshold(maxMemSize);
            // Location to save data that is larger than maxMemSize.
            factory.setRepository(new File("/home/udit/temp"));

            // Create a new file upload handler
            ServletFileUpload upload = new ServletFileUpload(factory);
            // maximum file size to be uploaded.
            upload.setSizeMax(maxFileSize);
            try {
                // Parse the request to get file items.
                List fileItems = upload.parseRequest(request);

                // Process the uploaded file items
                Iterator i = fileItems.iterator();

                while (i.hasNext()) {
                    FileItem fi = (FileItem) i.next();
                    if (!fi.isFormField()) {

                        // Get the uploaded file parameters
                        String fieldName = fi.getFieldName();
                        String fileName = fi.getName();
                        boolean isInMemory = fi.isInMemory();
                        long sizeInBytes = fi.getSize();
                        //restrict File type
                        name = fileName;
                        String query = "Select * from " + img.getTable() + " where " + img.getColumn() + "='" + name + "'";
                        System.out.println(query);
                        rs = con.customQuery(query);
                        if (rs.next()) {
                            message = "Icon with Similar Name  Exist";
                            request.setAttribute("name", name);
                            request.setAttribute("message", message);
                            request.getRequestDispatcher("InsertImage.jsp").forward(request, response);
                        } else {
                            if (fileName != null) {
                                if (isExtension(allowedExtension, fileName)) {
                                    // Write the file

                                    if (fileName.lastIndexOf("\\") >= 0) {
                                        file = new File(filePath
                                                + fileName.substring(fileName.lastIndexOf("\\")));
                                    } else {
                                        file = new File(filePath
                                                + fileName.substring(fileName.lastIndexOf("\\") + 1));
                                    }
                                    fi.write(file);
                                    message = "Uploaded Image";
                                    if (name.contentEquals(img.getName())) {
                                    } else {
                                        String s = img.getName();
                                        File f = new File(filePath + s);
                                        if (f.exists()) {
                                            if (s.contentEquals(img.getDefaultImage())) {
                                            } else {
                                                f.delete();
                                            }
                                        }
                                        String sql = "Update " + img.getTable() + " set " + img.getColumn() + "=" + "'" + name + "'" + " where " + img.getP_key() + "=" + "'" + img.getP_value() + "'";
                                        System.out.println(sql);
                                        con.executeQuery(sql);
                                    }
                                } else {
                                    message = "File type not Supported";
                                    request.setAttribute("fileurl", img.getFile_url());
                                    request.setAttribute("name", name);
                                    request.setAttribute("message", message);
                                    request.getRequestDispatcher("InsertImage.jsp").forward(request, response);
                                }
                            } else {
                                message = "Please select a file.";
                                request.setAttribute("fileurl", img.getFile_url());
                                request.setAttribute("name", name);
                                request.setAttribute("message", message);
                                request.getRequestDispatcher("InsertImage.jsp").forward(request, response);
                            }
                        }

                    }

                }



            } catch (Exception ex) {
                System.out.println(ex);
            }
        } else {
            message = "File not Uploaded";
        }
        request.setAttribute("name", name);
        request.setAttribute("message", message);
        System.out.println(img.getFile_url());
        response.sendRedirect(img.getFile_url());
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

    public boolean isExtension(String[] ext, String str) {
        boolean rtval = false;
        for (int i = 0; i < ext.length; i++) {
            if (str.endsWith(ext[i])) {
                rtval = true;
            }
        }

        return rtval;

    }
}
