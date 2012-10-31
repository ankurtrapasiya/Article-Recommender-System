package com.surpriseme.controllers.client;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UserInterestsControllers
 */

public class UserInterestsControllers extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserInterestsControllers() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     * response)
     */    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*String interestIds[] = request.getParameterValues("interests");
                
         int i = 0;
         DatabaseOperations dop = new DatabaseOperations();
         while(i < interestids.length) {
         dop.executeQuery("insert into userinterest values (" + Integer.parseInt(request.getParameter("userid")) + "," + Integer.parseInt(interestids[i]) + ")");
         }
         PrintWriter out = response.getWriter();
         out.println("Insertion Successfull");*/
    }
}
