package com.bookstore.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bookstore.entity.Category;

public class CategoryDAOTest{

	private static CategoryDAO categoryDAO;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		categoryDAO=new CategoryDAO();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		categoryDAO.close();
	}

	@Test
	public void testCreateCategory() {
		Category newcat=new Category("Java");
		categoryDAO.create(newcat);
		assertTrue(newcat.getCategoryId()>0);
	}

	@Test
	public void testUpdateCategory() {
		Integer catId=3;
		Category update=new Category(catId,"Marketing");
		Category updated=categoryDAO.update(update);
		assertEquals(update.getName(), updated.getName());
	}

	@Test
	public void testGet() {
		Category catId=categoryDAO.get(3);
		assertNotNull(catId);
	}

	@Test
	public void testDeleteObject() {
		Integer catId=4;
		categoryDAO.delete(catId);
		assertNull(categoryDAO.get(catId));
	}

	@Test
	public void testListAll() {
		List<Category> catlist=categoryDAO.listAll();
		for(Category cat:catlist){
			System.out.println(cat.getName());
		}
	}

	@Test
	public void testCount() {
		fail("Not yet implemented");
	}

}
