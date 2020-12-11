package com.nagarro.ImageUtilityApp.dao.impl;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.nagarro.ImageUtilityApp.dao.LoginDAO;
import com.nagarro.ImageUtilityApp.entity.Users;
import com.nagarro.ImageUtilityApp.service.LoginService;
import com.nagarro.ImageUtilityApp.util.HibernateUtil;

public class LoginDAOImpl implements LoginDAO {
	
	public Users getUser(String username, String password) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Query query = session.createQuery("from Users U where U.username=:username");
		query.setParameter("username", username);
		Users user = (Users) query.uniqueResult();
		session.getTransaction();
		session.close();
		return user;
	}
	
	public Users getUser(String username) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Query query = session.createQuery("from Users U where U.username=:username");
		query.setParameter("username", username);
		Users user = (Users) query.uniqueResult();
		session.getTransaction();
		session.close();
		return user;
	}

}
