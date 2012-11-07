/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.surpriseme.DAOImpl;

import com.surpriseme.entities.ArticleTag;
import com.surpriseme.helper.ArticleTagPK;
import java.sql.Timestamp;
import java.util.Date;
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
public class ArticleTagDAOImplTest {
    
    public ArticleTagDAOImplTest() {
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
     * Test of saveOrUpdate method, of class ArticleTagDAOImpl.
     */
    @Test
    public void testSaveOrUpdate() throws Exception {
        System.out.println("saveOrUpdate");
        ArticleTag entity = new ArticleTag(3, 102, new Timestamp(new Date().getTime()));
        ArticleTagDAOImpl instance = new ArticleTagDAOImpl();
        ArticleTagPK expResult = new ArticleTagPK(3,102);
        ArticleTagPK result = instance.saveOrUpdate(entity);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
       // fail("The test case is a prototype.");
    }

    /**
     * Test of saveOrUpdateAll method, of class ArticleTagDAOImpl.
     */
    //@Test
    public void testSaveOrUpdateAll() throws Exception {
        System.out.println("saveOrUpdateAll");
        List<ArticleTag> entities = null;
        ArticleTagDAOImpl instance = new ArticleTagDAOImpl();
        instance.saveOrUpdateAll(entities);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findById method, of class ArticleTagDAOImpl.
     */
   // @Test
    public void testFindById() throws Exception {
        System.out.println("findById");
        ArticleTagPK key = null;
        ArticleTagDAOImpl instance = new ArticleTagDAOImpl();
        ArticleTag expResult = null;
        ArticleTag result = instance.findById(key);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAll method, of class ArticleTagDAOImpl.
     */
   // @Test
    public void testGetAll() throws Exception {
        System.out.println("getAll");
        ArticleTagDAOImpl instance = new ArticleTagDAOImpl();
        List expResult = null;
        List result = instance.getAll();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of delete method, of class ArticleTagDAOImpl.
     */
   // @Test
    public void testDelete() throws Exception {
        System.out.println("delete");
        ArticleTagPK key = null;
        ArticleTagDAOImpl instance = new ArticleTagDAOImpl();
        boolean expResult = false;
        boolean result = instance.delete(key);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteAll method, of class ArticleTagDAOImpl.
     */
  //  @Test
    public void testDeleteAll() throws Exception {
        System.out.println("deleteAll");
        List<ArticleTag> entities = null;
        ArticleTagDAOImpl instance = new ArticleTagDAOImpl();
        boolean expResult = false;
        boolean result = instance.deleteAll(entities);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAllTagsOfArticle method, of class ArticleTagDAOImpl.
     */
  //  @Test
    public void testGetAllTagsOfArticle() throws Exception {
        System.out.println("getAllTagsOfArticle");
        Integer articleId = null;
        ArticleTagDAOImpl instance = new ArticleTagDAOImpl();
        List expResult = null;
        List result = instance.getAllTagsOfArticle(articleId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
