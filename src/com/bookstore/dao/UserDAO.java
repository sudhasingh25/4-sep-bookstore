package com.bookstore.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

import com.bookstore.entity.Users;

public class UserDAO extends JpaDAO<Users> implements GenericDAO<Users> {
		
	public UserDAO() {
	}

	@Override
	public void delete(Object obj) {
		super.delete(Users.class,obj);
	}

	@Override
	public long count() {
		long total= super.countWithNamedQuery("Users.count");
		System.out.print("total ------------> "+total);
		return total;
	}

	@Override
	public List<Users> listAll() {
		return super.findWithNamedQuery("Users.findAll");
	}

	@Override
	public Users create(Users entity) {
		return super.create(entity);
	}

	@Override
	public Users update(Users entity) {
		return super.update(entity);
	}

	@Override
	public Users get(Object id) {
		return super.find(Users.class,id);
	}

	public Users findByEmail(String email) {
		List<Users> users= super.findWithNamedQuery("Users.findByEmail","email",email);
		if(users!=null && users.size()>0){
			return users.get(0);
		}
		return null;
	}
	
	public boolean checkLogin(String email,String password){
		Map<String, Object> parameters=new HashMap<>();
		parameters.put("email", email);
		parameters.put("password", password);
		
		List<Users> listUsers=super.findWithNamedQuery("Users.checkLogin", parameters);
		if(listUsers.size()==1){
			return true;
		}
		return false;
	}
}
