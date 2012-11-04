/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.surpriseme.utils;

import com.mysql.jdbc.CallableStatement;
import com.sun.syndication.feed.synd.SyndCategoryImpl;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;
import com.surpriseme.DAO.ArticleLinksDAO;
import com.surpriseme.DAO.ArticleTagDAO;
import com.surpriseme.DAOImpl.ArticleDAOImpl;
import com.surpriseme.DAOImpl.ArticleLinksDAOImpl;
import com.surpriseme.DAOImpl.ArticleTagDAOImpl;
import com.surpriseme.DAOImpl.SourceDAOImpl;
import com.surpriseme.DAOImpl.TagDAOImpl;
import com.surpriseme.DAOImpl.UserDAOImpl;
import com.surpriseme.entities.Article;
import com.surpriseme.entities.ArticleLinks;
import com.surpriseme.entities.Source;
import com.surpriseme.entities.Tag;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
    private static final Logger logger = Logger.getLogger(UserDAOImpl.class);

    public void crawlArticles(String rssurl) {
        Tag tag;
        Article article;
        ArticleLinks articleLinks = null;
        ArticleDAOImpl articleDAOImpl = null;
        TagDAOImpl tagDAOImpl = null;
        SourceDAOImpl sourceDAOImpl = null;
        Source source = null;
        Integer articleid = 0;
        Integer tagid = 0;
        Integer sourceid = 0;
        Boolean articleSaved = false;
        Boolean articleExists = false;
        Boolean tagAdded = false;
        Boolean tagAddedToArticle = false;
        Boolean articleurlAdded = false;
        try {
            URL feedUrl = new URL(rssurl);
            SyndFeedInput input = new SyndFeedInput();
            SyndFeed feed = input.build(new XmlReader(feedUrl));

            article = new Article();
            articleDAOImpl = new ArticleDAOImpl();
            articleLinks = new ArticleLinks();
            sourceDAOImpl = new SourceDAOImpl();
            source = new Source();
            tagDAOImpl = new TagDAOImpl();
            for (SyndEntry entry : (List<SyndEntry>) feed.getEntries()) {

                article.setTitle(entry.getTitle());
                System.out.println(entry.getTitle());
                articleLinks.setArticleurl(entry.getUri());
                //parseDate is the local method to format the PubDate into required format.
                article.setPublicationdate(new Timestamp(parseDate(entry.getPublishedDate().toString())));
                article.setBody(Jsoup.clean(entry.getDescription().getValue(), Whitelist.simpleText()));
                article.setTimestamp(new Timestamp(new Date().getTime()));
                //category is tag in our system.
                tags = new ArrayList<Tag>();
                for (SyndCategoryImpl category : (List<SyndCategoryImpl>) entry.getCategories()) {
                    tag = new Tag();
                    tag.setName(category.getName().toLowerCase());
                    tag.setIcon("");
                    tag.setDescription("");
                    tags.add(tag);

                }

                articleExists = articleDAOImpl.checkIfArticleExist(articleLinks.getArticleurl());
                if (articleExists == false) {
                    articleid = articleDAOImpl.saveOrUpdate(article);
                    if (articleid != null) {//if success
                        source = sourceDAOImpl.getSoureFromFeedUrl(rssurl);

                        ArticleLinksDAO articleSources = new ArticleLinksDAOImpl();

                        articleurlAdded = articleSources.addSourceToArticle(articleid, articleLinks.getArticleurl(), source.getSourceid());
                        if (articleurlAdded) {
                            if (tags.size() >= 1) {
                                for (int i = 0; i < tags.size(); i++) {
                                    tagid = tagDAOImpl.checkIfTagExist(tags.get(i).getName());
                                    if (tagid == null) {
                                        tagid = tagDAOImpl.saveOrUpdate(tags.get(i));
                                    }
                                    if (tagid != null) {

                                        ArticleTagDAO articleTagDao = new ArticleTagDAOImpl();

                                        tagAddedToArticle = articleTagDao.addTagToArticle(tagid, articleid);
                                    }//end if(tagAdded)
                                }//end of for
                            }
                        }
                    }//end if(articleupdated
                } else {
                    continue;
                }//end if(articleExists...
            }//end for

        } catch (MalformedURLException ex) {
            logger.log(Priority.ERROR, ex.toString());
        } catch (IOException ex) {
            logger.log(Priority.ERROR, ex.toString());
        } catch (FeedException ex) {
            logger.log(Priority.ERROR, ex.toString());
        } catch (SQLException ex) {
            logger.log(Priority.ERROR, ex.toString());
        }
    }

    private long parseDate(String date) {
        Date currentArticleDate;
        long articleDate = 0;
        String currentArticleDateString;
        DateFormat formatter = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
        //Wed, 10 Oct 2012 01:16:48 +0000 
        //Thu, 25 Oct 2012 04:58:02 GMT
        //Thu Oct 25 10:46:27 IST 2012
        try {
            currentArticleDate = formatter.parse(date);//parsing string of Rcs 822 date format to date object.
            formatter = new SimpleDateFormat("yyy-MM-dd HH:mm:ss");//Add new format which is compitible with mysql datetime
            currentArticleDateString = formatter.format(currentArticleDate);//format that PubDate to the required format.
            currentArticleDate = formatter.parse(currentArticleDateString);
            articleDate = currentArticleDate.getTime();

        } catch (ParseException ex) {
            logger.log(Priority.ERROR, ex.toString());
        }

        return articleDate;
    }
}
