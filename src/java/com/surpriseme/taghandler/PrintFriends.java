/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.surpriseme.taghandler;

import com.surpriseme.DAO.UserGraphDAO;
import com.surpriseme.DAOImpl.UserGraphDAOImpl;
import com.surpriseme.controllers.client.ArticleController;
import com.surpriseme.entities.User;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author Harmish
 */
public class PrintFriends extends SimpleTagSupport {

    /**
     * Called by the container to invoke this tag. The implementation of this
     * method is provided by the tag library developer, and handles all tag
     * processing, body iteration, etc.
     */
    @Override
    public void doTag() throws JspException, IOException {
        JspWriter out = getJspContext().getOut();

        PageContext ctx = (PageContext) getJspContext();

        User u = (User) ctx.getSession().getAttribute("user");


        JSONObject jObj = null;
        JSONArray jArr = new JSONArray();

        UserGraphDAO userGraphDao = new UserGraphDAOImpl();
        List<User> friends = null;
        try {
            //friends = userGraphDao.getAllFriends(u.getUserid());
            friends = userGraphDao.getAllFriends(1);
        } catch (SQLException ex) {
        }

        if (friends != null) {
            Iterator<User> iterator = friends.iterator();

            while (iterator.hasNext()) {
                User user = iterator.next();
                jObj = new JSONObject();

                jObj.put("id", user.getUserid());
                jObj.put("name", user.getFirstname() + " " + user.getLastname());

                jArr.add(jObj);

            }

            System.out.println(jArr);
            out.println(jArr.toJSONString());
        }

        /*JspWriter out = getJspContext().getOut();

         List<User> friendslist = null;
         User user;
         UserGraphDAOImpl userdao = new UserGraphDAOImpl();
         try {
         friendslist = userdao.getAllFriends(1);
         int size = friendslist.size();
         if (size > 0) {

         out.print("[");
         for (int i = 0; i < size; i++) {
         user = (User) friendslist.get(i);
         out.print("{id: " + user.getUserid() + ", name: \"" + user.getFirstname() + " " + user.getLastname() + "\"},");
         //                    out.print("{\"name\":\"" + user.getFirstname() + " " + user.getLastname() + "\",\"id\":\"" + user.getUserid().toString() + "\"};");
         //                    out.print( "{\"userid\":\"" + user.getUserid() + "\",\"name\":\"" + user.getFirstname() + " " + user.getLastname() +"\"},");
         }
         //              out.print( "{\"userid\":\"7\",\"name\":\"harshini shah\"}");
         out.print("]");
         }
         } catch (IOException e) {
         } catch (SQLException e) {
         }*/
    }
}
