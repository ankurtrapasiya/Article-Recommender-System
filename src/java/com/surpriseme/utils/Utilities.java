/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.surpriseme.utils;

import java.security.MessageDigest;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author ankur
 */
public class Utilities {

    public static java.sql.Date getSqlDate(java.util.Date date) {
        java.sql.Date sdate = new java.sql.Date(date.getTime());
        return sdate;
    }

    public static boolean sendActivationMail(String recepient, String activationmessage) {
        try {
            final String username = "username";
            final String password = "password";
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
    
}
