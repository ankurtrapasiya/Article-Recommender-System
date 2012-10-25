/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.surpriseme.DAOImpl;

import com.surpriseme.entities.BlockedUsers;
import com.surpriseme.entities.User;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;

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
   // @Test
    public void testBlockUser() throws Exception {
        System.out.println("blockUser");
        BlockedUsers entity = new BlockedUsers();
        
        entity.setUserid(2);
        entity.setBlockerid(1);
        entity.setReason("hey this is the reason");
        entity.setIsActive(true);
        entity.setTimestamp(new Timestamp(new Date().getTime()));
        
        UserDAOImpl instance = new UserDAOImpl();
        boolean expResult = true;
        boolean result = instance.blockUser(entity);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of unblockUser method, of class UserDAOImpl.
     */
    //@Test
    public void testUnblockUser() throws Exception {
        System.out.println("unblockUser");
        BlockedUsers entity = new BlockedUsers();
        
        entity.setBlockerid(1);
        entity.setUserid(2);
        
        
        UserDAOImpl instance = new UserDAOImpl();
        boolean expResult = true;
        boolean result = instance.unblockUser(entity);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of searchUser method, of class UserDAOImpl.
     */
    //@Test
    public void testSearchUser() throws Exception {
        System.out.println("searchUser");
        String firstName = "ab";
        String lastName = "a";
        String email = "";
        UserDAOImpl instance = new UserDAOImpl();
        //List expResult = null;
        List result = instance.searchUser(firstName, lastName, email);
        //assertEquals(expResult, result);
        System.out.println("list" + result.size());
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of addToCircle method, of class UserDAOImpl.
     */
    //@Test
    public void testAddToCircle() throws Exception {
        System.out.println("addToCircle");
        Integer friendId = 3;
        Integer userId = 1;
        UserDAOImpl instance = new UserDAOImpl();
        boolean expResult = true;
        boolean result = instance.addToCircle(friendId, userId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of removeFromCircle method, of class UserDAOImpl.
     */
    //@Test
    public void testRemoveFromCircle() throws Exception {
        System.out.println("removeFromCircle");
        Integer friendId = 3;
        Integer userId = 1;
        UserDAOImpl instance = new UserDAOImpl();
        boolean expResult = true;
        boolean result = instance.removeFromCircle(friendId, userId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of saveOrUpdate method, of class UserDAOImpl.
     */
    //@Test
    public void testSaveOrUpdate() throws Exception {
        System.out.println("saveOrUpdate");

        User u = new User();

        //u.setUserid(3);
        u.setUsername("ankur");
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
        boolean expResult = true;
        boolean result = instance.saveOrUpdate(u);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of saveOrUpdateAll method, of class UserDAOImpl.
     */
    //@Test
    public void testSaveOrUpdateAll() throws Exception {
        System.out.println("saveOrUpdateAll");
        List<User> entities = new ArrayList<User>();
        
        User u = new User();

        u.setUserid(4);
        u.setUsername("raj");
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
        
        entities.add(u);
        
        u=new User();
        u.setUserid(7);
        u.setUsername("anil");
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
        entities.add(u);
        
        UserDAOImpl instance = new UserDAOImpl();
        instance.saveOrUpdateAll(entities);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of findById method, of class UserDAOImpl.
     */
   // @Test
    public void testFindById() throws Exception {
        System.out.println("findById");
        Integer key = 1;
        UserDAOImpl instance = new UserDAOImpl();
        //User expResult = null;
        User result = instance.findById(key);
        System.out.println(result);
        //assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
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
        //assertEquals(expResult, result);
        System.out.println(result.size());
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of delete method, of class UserDAOImpl.
     */
    //@Test
    public void testDelete() throws Exception {
        System.out.println("delete");
        Integer key = 1;
        UserDAOImpl instance = new UserDAOImpl();
        boolean expResult = false;
        boolean result = instance.delete(key);
        //assertEquals(expResult, result);
        System.out.println(result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of deleteAll method, of class UserDAOImpl.
     */
    //@Test
    public void testDeleteAll() throws Exception {
        System.out.println("deleteAll");
        List<User> entities = new ArrayList<User>();
        
        User u=new User();
        u.setUserid(5);
        
        entities.add(u);
        
        u=new User();
        u.setUserid(6);
        
        entities.add(u);
        
        
        UserDAOImpl instance = new UserDAOImpl();
        boolean expResult = false;
        boolean result = instance.deleteAll(entities);
        System.out.println("result"  + result);
        
                
        //assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
}
