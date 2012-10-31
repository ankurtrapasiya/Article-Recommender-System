/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.surpriseme.controllers;

import atg.taglib.json.util.JSONException;
import atg.taglib.json.util.JSONObject;
import com.google.gson.Gson;
import com.surpriseme.utils.FieldValidator;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author peace
 */
public class valiDationServlet extends HttpServlet {

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
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet valiDationServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet valiDationServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        } finally {            
            out.close();
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
        processRequest(request, response);
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
       
            //processRequest(request, response);
            System.out.println("Inside");
            HttpSession ses=request.getSession(false);
            JSONObject obj=null;
            String pagePath=null;
            
            try {
                obj=new JSONObject(ses.getAttribute("validate").toString());
                System.out.println(obj);
                Iterator<String> i=obj.keys();
                pagePath=obj.getString(i.next());
                while(i.hasNext()){
                    JSONObject tem=new JSONObject();
                    String Form=i.next();
                    System.out.println(Form);
                    tem=obj.getJSONObject(Form);
                    System.out.println(tem);
                    Iterator<String> j=tem.keys();
                    boolean isValid=true;
                    while(j.hasNext()){
                        String ele=j.next();
                        System.out.println(ele);
                        System.out.println((String)request.getParameter(ele));
                        System.out.println(tem.getString(ele));
                        String mess=validate((String)request.getParameter(ele),tem.getString(ele));
                        System.out.println(mess);
                        if(mess!=null){
                            request.setAttribute(ele, mess);
                            isValid=false;
                        }
                        else{
                            request.setAttribute(ele, "");
                        }
                    }
                    if(isValid){
                        
                        request.setAttribute(Form+"Valid", true);
                    }
                    
                }
                
                
            } catch (JSONException ex) {
                Logger.getLogger(valiDationServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            
          request.getRequestDispatcher("admin/"+pagePath+".jsp").forward(request, response);
           
        
       
       
       
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

    private String validate(String txt, String choice) {
        String mess=null;
        FieldValidator fv=new FieldValidator();
        System.out.println("function");
        if(choice.contentEquals("name")){

            if(txt.isEmpty()){
                mess="Field Can't be Empty";
                
            }
            else if(Character.isDigit(txt.charAt(0))){
             mess="Name can't begin with Number";  
            }
            else{
                if(fv.isName(txt)){
                     
                }
                else{
                   mess="Not a Valid Name Valid Name contains alphabets, numbers and Underscore";
                }
            }
        }
        else if(choice.contentEquals("sentance")){
            
                if(fv.isSentence(txt)){
                      
                }
                else{
                     mess="Not a valid Sentence. Valid sentence contains alphabets, numbers, a space, a comma and full-stop only."; 
                }
            
            
        }
        return mess;
    }
}
