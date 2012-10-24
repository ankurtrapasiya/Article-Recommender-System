/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.surpriseme.DAOImpl;

import com.surpriseme.entities.Tag;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author ankur
 */
public class TagDAOImplTest {
    
    public TagDAOImplTest() {
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
     * Test of addTagToArticle method, of class TagDAOImpl.
     */
    @Test
    public void testAddTagToArticle() throws Exception {
        System.out.println("addTagToArticle");
        Integer tagId = null;
        Integer ArticleId = null;
        TagDAOImpl instance = new TagDAOImpl();
        boolean expResult = false;
        boolean result = instance.addTagToArticle(tagId, ArticleId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of saveOrUpdate method, of class TagDAOImpl.
     */
    @Test
    public void testSaveOrUpdate() throws Exception {
        System.out.println("saveOrUpdate");
        Tag entity = null;
        TagDAOImpl instance = new TagDAOImpl();
        boolean expResult = false;
        boolean result = instance.saveOrUpdate(entity);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of saveOrUpdateAll method, of class TagDAOImpl.
     */
    @Test
    public void testSaveOrUpdateAll() throws Exception {
        System.out.println("saveOrUpdateAll");
        List<Tag> entities = null;
        TagDAOImpl instance = new TagDAOImpl();
        instance.saveOrUpdateAll(entities);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findById method, of class TagDAOImpl.
     */
    @Test
    public void testFindById() throws Exception {
        System.out.println("findById");
        Integer key = null;
        TagDAOImpl instance = new TagDAOImpl();
        Tag expResult = null;
        Tag result = instance.findById(key);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAll method, of class TagDAOImpl.
     */
    @Test
    public void testGetAll() throws Exception {
        System.out.println("getAll");
        TagDAOImpl instance = new TagDAOImpl();
        List expResult = null;
        List result = instance.getAll();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of delete method, of class TagDAOImpl.
     */
    @Test
    public void testDelete() throws Exception {
        System.out.println("delete");
        Integer key = null;
        TagDAOImpl instance = new TagDAOImpl();
        boolean expResult = false;
        boolean result = instance.delete(key);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteAll method, of class TagDAOImpl.
     */
    @Test
    public void testDeleteAll() throws Exception {
        System.out.println("deleteAll");
        List<Tag> entities = null;
        TagDAOImpl instance = new TagDAOImpl();
        boolean expResult = false;
        boolean result = instance.deleteAll(entities);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeTagFromArticle method, of class TagDAOImpl.
     */
    @Test
    public void testRemoveTagFromArticle() throws Exception {
        System.out.println("removeTagFromArticle");
        Integer tagId = null;
        Integer ArticleId = null;
        TagDAOImpl instance = new TagDAOImpl();
        boolean expResult = false;
        boolean result = instance.removeTagFromArticle(tagId, ArticleId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
