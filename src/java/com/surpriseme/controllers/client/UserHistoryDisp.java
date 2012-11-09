/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.surpriseme.controllers.client;

import com.surpriseme.DAO.ArticleDAO;
import com.surpriseme.DAO.UserHistoryDAO;
import com.surpriseme.DAOImpl.ArticleDAOImpl;
import com.surpriseme.DAOImpl.HistoryDAOImpl;
import com.surpriseme.entities.UserHistory;
import com.surpriseme.helper.HistoryHelper;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.Timestamp;

/**
 *
 * @author Vikr@m
 */
public class UserHistoryDisp extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    /*
     protected void processRequest(HttpServletRequest request, HttpServletResponse response)
     throws ServletException, IOException {
     response.setContentType("text/html;charset=UTF-8");
     PrintWriter out = response.getWriter();
     try {
     /* TODO output your page here. You may use following sample code. */
    //      out.println("<html>");
    //      out.println("<head>");
    //      out.println("<title>Servlet UserHistoryDisp</title>");            
    //      out.println("</head>");
    //      out.println("<body>");
    //      out.println("<h1>Servlet UserHistoryDisp at " + request.getContextPath() + "</h1>");
    //      out.println("</body>");
    //       out.println("</html>");
    //    } finally {            
    //        out.close();
    //   }
    //   */
    //   }
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
        // processRequest(request, response);
        doPost(request, response);
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
    /**
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // processRequest(request, response);
        PrintWriter out = response.getWriter();

        Integer usid=null, artid=null;
        Timestamp timest=null;
        Boolean upvote=false, downvote=false;
        
        int len = 0, i = 0;
        ArrayList<UserHistory> uh = new ArrayList();
        int size = uh.size();
        // JSONArray jArr = new JSONArray();
        // JSONObject jObj = new JSONObject();  

        UserHistoryDAO up1 = new HistoryDAOImpl();
        System.out.println("Java Vikram");
        try {
            uh = (ArrayList<UserHistory>) up1.getAllHistory(1);
            System.out.println("Java Vikram TRY " + uh.get(0).getArticleid());


            Iterator<UserHistory> iterator = uh.iterator();
            
            // List<UserHistoryPK> list=new ArrayList<UserHistoryPK>();
            List<HistoryHelper> list=new ArrayList<HistoryHelper>();
            ArticleDAO articleDAO=new ArticleDAOImpl();
 
            while (iterator.hasNext()) {
                
                /*
                UserHistory userHistory=iterator.next();
                usid=userHistory.getUserid();
                artid=userHistory.getArticleid();
                timest=userHistory.getTimestamp();
                upvote=userHistory.getUpvote();
                downvote=userHistory.getDownvote();
                
                Article a=new Article();        
                UserHistoryPK hh=new UserHistoryPK(usid, artid);
                
                hh.setArticleid(artid);
                hh.setUserid(usid);
                hh.setTimestamp(timest);
                hh.setUpvote(upvote);
                hh.setDownvote(downvote);
                */
                
                
                   
                UserHistory userHistory=iterator.next();
                
                HistoryHelper hh=new HistoryHelper();
                
                hh.setTitle(articleDAO.findById(userHistory.getArticleid()).getTitle());
                hh.setTimestamp(userHistory.getTimestamp());
                hh.setDownvote(userHistory.getDownvote());
                hh.setUpvote(userHistory.getUpvote());
                hh.setArticleid(userHistory.getArticleid());
                
                list.add(hh);
               
                
            }


            /* len=uh.size();
             while(i<len)
             {
             System.out.println(uh.get(i));
             System.out.println("Java Vikram while");
             uh.add();
             }
             */
            request.setAttribute("data", list);
            RequestDispatcher rd = request.getRequestDispatcher("history.jsp");
            rd.forward(request, response);

            /*
             while(i<len)
             {
             jArr.add(uh.get(i));
                
                
             UserHistory a = uh.get(i);

             jObj = new JSONObject();
             jObj.put("articleid", a.getUserid());
             jObj.put("title", a.getArticleid());
             jObj.put("body", a.getTimestamp().toString());
             jObj.put("upvote", a.getUpvote());
             jObj.put("downvote", a.getDownvote());
                        
             uh.remove(i);

             jArr.add(jObj);

             i++;
             }
             // obj.put("val","");
             */




        } catch (Exception ex) {
            ex.printStackTrace();
            Logger.getLogger(UserHistoryDisp.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            System.out.println("Vikram Finally");
        }
        /*
         jObj = new JSONObject();
         jObj.put("content", jArr);

         response.setContentType("application/json");
         response.setCharacterEncoding("UTF-8");
         response.getWriter().println(jObj); 
         */

        /*
         len=uh.size();
         while(i<len)
         {
         System.out.println(uh.get(i));
         }
         */




    }
    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    /*
     @Override
     public String getServletInfo() {
     return "Short description";
     }// </editor-fold>  */
}