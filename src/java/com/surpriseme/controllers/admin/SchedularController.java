/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.surpriseme.controllers.admin;

import com.surpriseme.DAOImpl.SourceDAOImpl;
import com.surpriseme.DAOImpl.UserDAOImpl;
import com.surpriseme.entities.Source;
import com.surpriseme.utils.FeedSchedular;
import com.surpriseme.utils.ScheduleSourceCrawling;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.apache.log4j.Priority;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author udit
 */
public class SchedularController extends HttpServlet implements Runnable {

    private static final Logger logger = Logger.getLogger(UserDAOImpl.class);
    Hashtable<FeedSchedular, String> map = null;

    @Override
    public void init(ServletConfig config) throws ServletException {
        map = new Hashtable<FeedSchedular, String>();
        new Thread(this).start();
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
        PrintWriter out = response.getWriter();
        SourceDAOImpl sourcedaoimpl = new SourceDAOImpl();
        Source source = null;
        List<Source> sources = null;
        JSONObject job = null;
        JSONObject send = null;
        JSONArray jarray = new JSONArray();
        String action = request.getParameter("action");
        String sourcename = "";
        Set set = null;
        try {
            if (action.equals("getall")) {//get all sources

                sources = sourcedaoimpl.getAll();

                if (sources != null) {
                    for (int i = 0; i < sources.size(); i++) {
                        job = new JSONObject();
                        job.put("name", sources.get(i).getName());
                        jarray.add(job);
                    }
                } else {
                    logger.log(Priority.ERROR, "Sources not found in database.");
                }

                response.setContentType("application/json");
                out.println(jarray);
            }//getall
            else if (action.equals("getfeedurls")) {//

                Boolean feedurlexists = false;
                set = this.map.keySet();



                jarray = new JSONArray();
                sourcename = request.getParameter("sourcename");
                sources = sourcedaoimpl.findFeedUrlBySource(sourcename);
                if (sources != null) {

                    for (int i = 0; i < sources.size(); i++) {
                        feedurlexists = false;
                        if (!set.isEmpty()) {
                            Iterator iter = set.iterator();
                            while (iter.hasNext()) {
                                FeedSchedular fs = (FeedSchedular) iter.next();

                                if (sources.get(i).getFeedurl().equals(fs.getFeedUrl())) {
                                    feedurlexists = true;
                                    break;
                                }
                            }
                        }
                        if ((feedurlexists == false) || (set.isEmpty())) {
                            job = new JSONObject();
                            job.put("feedurl", sources.get(i).getFeedurl());
                            jarray.add(job);
                            feedurlexists = false;
                        }
                    }

                } else {
                    logger.log(Priority.ERROR, "Sources not found in database.");
                }
                response.setContentType("application/json");
                out.println(jarray);

            } else if (action.equals("startcrawling")) {

                String urlfeed = request.getParameter("feedurl");
                String crawltime = request.getParameter("crawltime");
                String pattern = request.getParameter("pattern");
                FeedSchedular fs = new FeedSchedular(urlfeed, pattern, Long.parseLong(crawltime));

                ScheduleSourceCrawling ssc = new ScheduleSourceCrawling(fs);

                if (!this.map.containsKey(fs)) {
                    this.map.put(fs, urlfeed);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.log(Priority.ERROR, ex.toString());

        }


        out.close();

    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
    
    @Override
    public void run() {
        while (true) {

            try {
                System.out.println(" IN RUN()");
                Set set1 = this.map.keySet();
                if (!set1.isEmpty()) {
                    Iterator iter = set1.iterator();
                    while (iter.hasNext()) {
                       
                        FeedSchedular fs = (FeedSchedular) iter.next();
                        
                        
                        if (!fs.isIsScheduled() && !fs.isIsDone()) {
                            System.out.println("Start HashCode():"+fs.hashCode());
                            System.out.println("Scheduled Feed url is :" + fs.getFeedUrl());
                            new Thread(new ScheduleSourceCrawling(fs)).start();
                        } else if (fs.isIsDone()) {
                            String remove = map.remove(fs);
                            System.out.println("Removed Feed url is:"+remove);
                            set1=this.map.keySet();
                            iter=set1.iterator();
                            break;
                        }
                        
                    }//end while
                }

                Thread.sleep(10000L);
            } catch (InterruptedException ex) {
                System.out.println(ex.toString());
            }

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
