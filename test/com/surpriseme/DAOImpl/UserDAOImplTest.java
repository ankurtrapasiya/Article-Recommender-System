/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.surpriseme.DAOImpl;

import com.surpriseme.entities.User;
import java.sql.ResultSet;
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
public class UserDAOImplTest {
    
    public UserDAOImplTest() {
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
     * Test of blockUser method, of class UserDAOImpl.
     */
    //@Test
    public void testBlockUser() throws Exception {
        System.out.println("blockUser");
        Integer userid = null;
        UserDAOImpl instance = new UserDAOImpl();
        boolean expResult = false;
        boolean result = instance.blockUser(userid);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of unblockUser method, of class UserDAOImpl.
     */
   // @Test
    public void testUnblockUser() throws Exception {
        System.out.println("unblockUser");
        Integer userid = null;
        UserDAOImpl instance = new UserDAOImpl();
        boolean expResult = false;
        boolean result = instance.unblockUser(userid);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of searchUser method, of class UserDAOImpl.
     */
    //@Test
    public void testSearchUser() throws Exception {
        System.out.println("searchUser");
        String firstName = "";
        String lastName = "";
        String email = "";
        UserDAOImpl instance = new UserDAOImpl();
        List expResult = null;
        List result = instance.searchUser(firstName, lastName, email);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addToCircle method, of class UserDAOImpl.
     */
   // @Test
    public void testAddToCircle() throws Exception {
        System.out.println("addToCircle");
        Integer userId = null;
        UserDAOImpl instance = new UserDAOImpl();
        boolean expResult = false;
        boolean result = instance.addToCircle(userId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeFromCircle method, of class UserDAOImpl.
     */
    //@Test
    public void testRemoveFromCircle() throws Exception {
        System.out.println("removeFromCircle");
        Integer userId = null;
        UserDAOImpl instance = new UserDAOImpl();
        boolean expResult = false;
        boolean result = instance.removeFromCircle(userId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of saveOrUpdate method, of class UserDAOImpl.
     */
    @Test
    public void testSaveOrUpdate() throws Exception {
        System.out.println("saveOrUpdate");      
        
        User u=new User();        
        
        u.setUsername("abc");
        u.setPassword("abc");
        u.setEmail("abc@abc.com");
        u.setFirstname("abc");
        u.setLastname("abc");
        u.setDob(new Date());
        u.setState("guj");
        u.setCity("gnagar");
        u.setCountry("india");
        u.setIsactive(false);
        u.setTimeofregistration(new Date());
        
        UserDAOImpl instance = new UserDAOImpl();
        ResultSet expResult = null;
        ResultSet result = instance.saveOrUpdate(u);
        //assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of saveOrUpdateAll method, of class UserDAOImpl.
     */
   // @Test
    public void testSaveOrUpdateAll() throws Exception {
        System.out.println("saveOrUpdateAll");
        List<User> entities = null;
        UserDAOImpl instance = new UserDAOImpl();
        ResultSet expResult = null;
        ResultSet result = instance.saveOrUpdateAll(entities);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findById method, of class UserDAOImpl.
     */
    //@Test
    public void testFindById() throws Exception {
        System.out.println("findById");
        Integer key = null;
        UserDAOImpl instance = new UserDAOImpl();
        User expResult = null;
        User result = instance.findById(key);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAll method, of class UserDAOImpl.
     */
    //@Test
    public void testGetAll() throws Exception {
        System.out.println("getAll");
        UserDAOImpl instance = new UserDAOImpl();
        List expResult = null;
        List result = instance.getAll();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of delete method, of class UserDAOImpl.
     */
   // @Test
    public void testDelete() throws Exception {
        System.out.println("delete");
        Integer key = null;
        UserDAOImpl instance = new UserDAOImpl();
        boolean expResult = false;
        boolean result = instance.delete(key);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteAll method, of class UserDAOImpl.
     */
   // @Test
    public void testDeleteAll() throws Exception {
        System.out.println("deleteAll");
        List<User> entities = null;
        UserDAOImpl instance = new UserDAOImpl();
        boolean expResult = false;
        boolean result = instance.deleteAll(entities);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
