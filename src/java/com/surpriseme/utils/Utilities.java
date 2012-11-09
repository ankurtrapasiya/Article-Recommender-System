/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.surpriseme.utils;

import com.surpriseme.entities.Article;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author ankur
 */
public class Utilities {

    public static int articleDisplayThreshold = 10;

    public static java.sql.Date getSqlDate(java.util.Date date) {
        java.sql.Date sdate = new java.sql.Date(date.getTime());
        return sdate;
    }

    public static boolean sendActivationMail(String recepient, String activationmessage) {
        try {
            final String username = "surpriseme.sen@gmail.com";
            final String password = "livefreeordiehard";
            Properties props = new Properties();
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.socketFactory.port", "465");
            props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.port", "465");

            Authenticator authenticator = new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {

                    return new PasswordAuthentication(username, password);
                }
            };
            Session session = Session.getDefaultInstance(props, authenticator);
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.addRecipients(Message.RecipientType.TO, InternetAddress.parse(recepient));
            message.setSubject("SurpriseMe activation link");
            message.setText(activationmessage);

            Transport.send(message);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static String toMD5(String chain) {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("MD5");
            md.update(chain.getBytes("UTF-8"));
            byte[] mb = md.digest();
            String out = "";
            for (int i = 0; i < mb.length; i++) {
                byte temp = mb[i];
                String s = Integer.toHexString(new Byte(temp));
                while (s.length() < 2) {
                    s = "0" + s;
                }
                s = s.substring(s.length() - 2);
                out += s;
            }
            return out;

        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
        return null;
    }

    public static List<Article> processArticles(Map<Category, List<Article>> map) {

        List<Article> retval = new ArrayList<Article>();

        List<Article> browsingList = map.get(Category.BROWSING); //3
        int b = 3;

        List<Article> randomizedList = map.get(Category.RANDOMIZATION); //2
        int r = 2;

        List<Article> popularList = map.get(Category.POPULARITY); //3
        int p = 3;

        List<Article> relevancyList = map.get(Category.RELEVANCY); //2
        int rl = 2;

        int factor = articleDisplayThreshold / 10;
        b += factor;
        r *= factor;
        p *= factor;
        rl *= factor;


        //list indexes
        int bi = 0;
        int ri = 0;
        int pi = 0;
        int rli = 0;

        boolean bf = false;
        boolean rf = false;
        boolean pf = false;
        boolean rlf = false;

        while (true) {

            int bb = b;
            int rr = r;
            int pp = p;
            int rrll = rl;

            List<Article> temp = new ArrayList<Article>();

            while (pp > 0) {
                if (pi < popularList.size()) {
                    temp.add(popularList.get(pi));
                    pi++;
                } else {
                    pf = true;
                    break;
                }
                pp--;
            }
            while (bb > 0) {
                if (bi < browsingList.size()) {
                    temp.add(browsingList.get(bi));
                    bi++;
                } else {
                    bf = true;
                    break;
                }
                bb--;
            }
            while (rrll > 0) {
                if (rli < relevancyList.size()) {
                    temp.add(relevancyList.get(rli));
                    rli++;
                } else {
                    rlf = true;
                    break;
                }
                rrll--;
            }
            while (rr > 0) {
                if (ri < randomizedList.size()) {
                    temp.add(randomizedList.get(ri));
                    ri++;
                } else {
                    rf = true;
                    break;
                }
                rr--;
            }
            retval.addAll(temp);

            if (bf && rf && pf && rlf) {
                break;
            }
        }

        return retval;
    }

    public static boolean uploadImage(String path, String id, javax.servlet.http.Part filePart, String filename) throws IOException {
        File file = new File(path + "//images" + id);
        if (!(file.isDirectory())) {
            file.mkdir();
            System.out.println(path);
        }
        path = path + "//images" + id;
        //System.out.println(path);
        InputStream filecontent = filePart.getInputStream();
        OutputStream out = new FileOutputStream(new File(path + "//" + filename));
        int read = 0;
        byte[] bytes = new byte[1024];
        while ((read = filecontent.read(bytes)) != -1) {
            out.write(bytes, 0, read);
        }
        filecontent.close();
        out.flush();
        out.close();
        return true;
    }

    public static String getValue(javax.servlet.http.Part part) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(part.getInputStream(), "UTF-8"));
        StringBuilder value = new StringBuilder();
        char[] buffer = new char[1024];
        for (int length = 0; (length = reader.read(buffer)) > 0;) {
            value.append(buffer, 0, length);
        }
        return value.toString();
    }

    public static String getFilename(javax.servlet.http.Part part, String path) {

        for (String cd : part.getHeader("content-disposition").split(";")) {
            if (cd.trim().startsWith("filename")) {
                String filename = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
                return filename.substring(filename.lastIndexOf('/') + 1).substring(filename.lastIndexOf('\\') + 1); // MSIE fix.
            }
        }
        return null;
    }

    public static ArrayList<String> getPath(int pid, String path) {
        //System.out.println(path);
        File file = new File(path);
        //System.out.println(file.getPath());
        File[] files = file.listFiles();
        ArrayList<String> imageList = new ArrayList<String>();
        for (int fileInList = 0; fileInList < files.length; fileInList++) {
            imageList.add(files[fileInList].getName());
            //System.out.println(path);
        }
        return imageList;
    }

    public static String deleteImages(ArrayList<String> imagename, String path) {
        Iterator ir = imagename.iterator();
        String pkg = null;
        while (ir.hasNext()) {
            String str = (String) ir.next();
            if (str.equals("submit")) {
            } else {
                String[] part = str.split("_");
                //System.out.println(part[0]);
                //System.out.println(part[1]);
                //System.out.println(path);
                pkg = part[1];
                path = path + "//images//package" + part[1] + "//" + part[0];
                File f = new File(path);
                f.delete();
            }

        }
        return pkg;
    }
}
