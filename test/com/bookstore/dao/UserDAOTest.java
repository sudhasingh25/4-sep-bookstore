package com.bookstore.dao;

import static org.junit.Assert.*;

import java.util.List;


import javax.persistence.PersistenceException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bookstore.entity.Users;

public class UserDAOTest{

	
	private static UserDAO userDAO;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
		userDAO=new UserDAO();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		userDAO.close();
	}

	@Test
	public void testDelete() {
		
	}

	@Test
	public void testCount() {
		long total=userDAO.count();
		assertEquals(21, total);
	}

	@Test
	public void testListAll() {
		List<Users> listuser=userDAO.listAll();
		for(Users u:listuser){
			System.out.println(u.getFullName());
		}
		assertNotNull(listuser);
	}


	@Test
	public void testCreateUsers() {
		Users user=new Users();
		user.setEmail("Naina123@gmail.com");
		user.setFullName("Naina Singh");
		user.setPassword("Naina123");
		//System.out.print("username"+user.getFullName());
		user=userDAO.create(user);
		//System.out.print("userid"+user.getUserId());
		//System.out.print("username"+user.getFullName());
		//System.out.print("****************");
		assertNull(user);
	}
	
	@Test(expected=PersistenceException.class)
	public void testCreateUsersFieldNotSet() {
		Users user=new Users();
		user=userDAO.create(user);
	}

	@Test
	public void testUpdateUsers() {
		int userId=2;
		Users user=new Users(userId,"uma123@gmail.com","uma123","uma");
		user=userDAO.update(user);
		
		assertEquals("uma123", user.getPassword());
	}

	@Test
	public void testGetObject() {
		int userid=3;
		Users user=userDAO.get(userid);
		System.out.print("User in get method : "+user.getFullName());
		assertNotNull(user);
	}
	
	

}
