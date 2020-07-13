package com.smithmicro.notesapp.dao;

import java.util.logging.Logger;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.smithmicro.notesapp.entity.User;

@Repository
public class LoginDAOImpl implements LoginDAO{
    
	@Autowired
	private SessionFactory sessionFactory;
	
	private Logger logger = Logger.getLogger(getClass().getName());
	
	
	@Override
	public void saveUser(User theUser) {
		
		Session currentSession = sessionFactory.getCurrentSession();
		
		currentSession.saveOrUpdate(theUser);
		
	}
	
	
	@Override
	public User findUserByUserName(String username) {
		logger.info("Entering logindao");
		Session currentSession = sessionFactory.getCurrentSession();
		logger.info("sessionfactory"+currentSession);
		User theUser=currentSession.get(User.class,username);
		if(theUser==null) {
			logger.info("the user is null from database");
		}
		
		return theUser;
		
		
	}

}
