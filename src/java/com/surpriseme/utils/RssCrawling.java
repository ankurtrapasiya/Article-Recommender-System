/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.surpriseme.utils;

import com.mysql.jdbc.CallableStatement;
import com.sun.syndication.feed.synd.SyndCategoryImpl;
import com.sun.syndication.feed.synd.SyndContentImpl;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.feed.synd.SyndLinkImpl;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;
import com.surpriseme.DAOImpl.UserDAOImpl;
import com.surpriseme.entities.Article;
import com.surpriseme.entities.ArticleLinks;
import com.surpriseme.entities.Tag;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import org.apache.log4j.Logger;
import org.apache.log4j.Priority;
import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;

/**
 *
 * @author udit
 */
public class RssCrawling {

    CallableStatement cstmt;
    DBConnection con;
    ArrayList<Tag> tags;
    Tag tag;
    Article article;
    ArticleLinks articleLinks;
    private static final Logger logger = Logger.getLogger(UserDAOImpl.class);

    private void fetchArticles(String rssurl) {
        try {
            URL feedUrl = new URL(rssurl);
            SyndFeedInput input = new SyndFeedInput();
            SyndFeed feed = input.build(new XmlReader(feedUrl));
            tags=new ArrayList<Tag>();
            article=new Article();
            for (SyndEntry entry : (List<SyndEntry>) feed.getEntries()) {
            
                article.setTitle(entry.getTitle());
                articleLinks.setArticleurl(entry.getUri());
                article.setTimestamp(new Timestamp(parseDate(entry.getPublishedDate().toString())));
                article.setBody(Jsoup.clean(entry.getDescription().getValue(), Whitelist.simpleText()));
                
                for (SyndCategoryImpl category : (List<SyndCategoryImpl>) entry.getCategories()) {
                    tag=new Tag();
                    tag.setName(category.getName());
                    tags.add(tag);
                }
            }
            
        } catch (MalformedURLException ex) {
            logger.log(Priority.ERROR, ex.toString());
        } catch (IOException ex) {
            logger.log(Priority.ERROR, ex.toString());
        } catch (FeedException ex) {
            logger.log(Priority.ERROR, ex.toString());
        } 
    }
    
    private long parseDate(String date){
        Date currentArticleDate;
        long articleDate=0;
        String currentArticleDateString;
        DateFormat formatter = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
            //Wed, 10 Oct 2012 01:16:48 +0000 
            //Thu, 25 Oct 2012 04:58:02 GMT
            //Thu Oct 25 10:46:27 IST 2012
        try{
            currentArticleDate = formatter.parse(date);//parsing string of Rcs 822 date format to date object.
            formatter = new SimpleDateFormat("yyy-MM-dd HH:mm:ss");//Add new format which is compitible with mysql datetime
            currentArticleDateString = formatter.format(currentArticleDate);//format that PubDate to the required format.
            currentArticleDate = formatter.parse(currentArticleDateString);
            articleDate=currentArticleDate.getTime();
            
        }catch(ParseException ex){
            logger.log(Priority.ERROR, ex.toString());
        }
        
        return articleDate;
    }
}
