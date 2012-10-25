/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.surpriseme.DAOImpl;

import com.surpriseme.entities.Article;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ankur
 */
public class ArticleDAOImplTest {
    
    public ArticleDAOImplTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of updateViews method, of class ArticleDAOImpl.
     */
    @Test
    public void testUpdateViews() throws Exception {
        System.out.println("updateViews");
        Integer articleId = null;
        ArticleDAOImpl instance = new ArticleDAOImpl();
        boolean expResult = false;
        boolean result = instance.updateViews(articleId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of vote method, of class ArticleDAOImpl.
     */
    @Test
    public void testVote() throws Exception {
        System.out.println("vote");
        Integer articleId = null;
        boolean up = false;
        ArticleDAOImpl instance = new ArticleDAOImpl();
        boolean expResult = false;
        boolean result = instance.vote(articleId, up);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of saveOrUpdate method, of class ArticleDAOImpl.
     */
    @Test
    public void testSaveOrUpdate() throws Exception {
        System.out.println("saveOrUpdate");
        Article entity = null;
        ArticleDAOImpl instance = new ArticleDAOImpl();
        boolean expResult = false;
        boolean result = instance.saveOrUpdate(entity);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of saveOrUpdateAll method, of class ArticleDAOImpl.
     */
    @Test
    public void testSaveOrUpdateAll() throws Exception {
        System.out.println("saveOrUpdateAll");
        List<Article> entities = null;
        ArticleDAOImpl instance = new ArticleDAOImpl();
        instance.saveOrUpdateAll(entities);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findById method, of class ArticleDAOImpl.
     */
    @Test
    public void testFindById() throws Exception {
        System.out.println("findById");
        Integer key = null;
        ArticleDAOImpl instance = new ArticleDAOImpl();
        Article expResult = null;
        Article result = instance.findById(key);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAll method, of class ArticleDAOImpl.
     */
    @Test
    public void testGetAll() throws Exception {
        System.out.println("getAll");
        ArticleDAOImpl instance = new ArticleDAOImpl();
        List expResult = null;
        List result = instance.getAll();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of delete method, of class ArticleDAOImpl.
     */
    @Test
    public void testDelete() throws Exception {
        System.out.println("delete");
        Integer key = null;
        ArticleDAOImpl instance = new ArticleDAOImpl();
        boolean expResult = false;
        boolean result = instance.delete(key);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteAll method, of class ArticleDAOImpl.
     */
    @Test
    public void testDeleteAll() throws Exception {
        System.out.println("deleteAll");
        List<Article> entities = null;
        ArticleDAOImpl instance = new ArticleDAOImpl();
        boolean expResult = false;
        boolean result = instance.deleteAll(entities);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
