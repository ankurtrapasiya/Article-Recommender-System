/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.surpriseme.controllers.admin;

import com.surpriseme.DAOImpl.TagDAOImpl;
import com.surpriseme.entities.Tag;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.surpriseme.DAOImpl.*;
import java.util.List;
import javax.servlet.RequestDispatcher;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author udit
 */
public class ManageTags extends HttpServlet {

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
        try {
            PrintWriter out = response.getWriter();
            TagDAOImpl tag = new TagDAOImpl();
            //System.out.println(request.getParameter("articleid"));
            int articleid = Integer.parseInt(request.getParameter("articleid"));
            List<Tag> alltags = tag.getRemaining(articleid);
            List<Tag> articletags = tag.getTags(articleid);
            JSONArray jr1 = new JSONArray();
            JSONArray jr2 = new JSONArray();
            JSONArray jrmain = new JSONArray();

            JSONObject task = null;
            JSONObject send1 = new JSONObject();
            JSONObject send2 = new JSONObject();
            JSONObject sendmain = new JSONObject();


            for (Tag t : alltags) {
                task = new JSONObject();
                task.put("id", t.getTagid());
                task.put("title", t.getName());
                task.put("desc", t.getDescription());

                jr1.add(task);
            }
            for (Tag t : articletags) {
                task = new JSONObject();
                task.put("id", t.getTagid());
                task.put("title", t.getName());
                task.put("desc", t.getDescription());

                jr2.add(task);
            }
            send1.put("alltags", jr1);
            send2.put("articletags", jr2);
            jrmain.add(send1);
            jrmain.add(send2);
            sendmain.put("tags", jrmain);
            // System.out.println(task);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            out.print(sendmain);


        } catch (SQLException ex) {
            Logger.getLogger(ManageTags.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            PrintWriter out=response.getWriter();
            TagDAOImpl tag=new TagDAOImpl();
            int articleid=Integer.parseInt(request.getParameter("articleid"));
            List<Tag> alltags=tag.getAll();
            List<Tag> articletags=tag.getTags(articleid);
            JSONArray jr1=new JSONArray();
            JSONArray jr2=new JSONArray();
            JSONArray jrmain=new JSONArray();
            
            JSONObject task = null;
            JSONObject send1=new JSONObject();
            JSONObject send2=new JSONObject();
            JSONObject sendmain=new JSONObject();
            

            for (Tag t : alltags) {
                task = new JSONObject();
                task.put("id",t.getTagid());
                task.put("title", t.getName());
                task.put("desc",t.getDescription());
                
                jr1.add(task);
            }
            for (Tag t : articletags) {
                task = new JSONObject();
                task.put("id",t.getTagid());
                task.put("title", t.getName());
                task.put("desc",t.getDescription());
                
                jr2.add(task);
            }
            send1.put("alltags", jr1);
            send2.put("articletags", jr2);
            jrmain.add(send1);
            jrmain.add(send2);
            sendmain.put("tags",jrmain);
            System.out.println(task);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            out.print(sendmain);
              
           
        } catch (SQLException ex) {
            Logger.getLogger(ManageTags.class.getName()).log(Level.SEVERE, null, ex);
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
