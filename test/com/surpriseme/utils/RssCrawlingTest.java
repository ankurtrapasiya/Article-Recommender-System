/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.surpriseme.utils;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author udit
 */
public class RssCrawlingTest {
    
    public RssCrawlingTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testSomeMethod() {
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of crawlArticles method, of class RssCrawling.
     */
    @Test
    public void testCrawlArticles() {
        System.out.println("crawlArticles");
        String rssurl = "http://timesofindia.indiatimes.com/rssfeeds/-2128936835.cms";
        RssCrawling instance = new RssCrawling();
        instance.crawlArticles(rssurl);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
}
