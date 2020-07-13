package com.smithmicro.notesapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.smithmicro.notesapp.dao.LoginDAO;
import com.smithmicro.notesapp.entity.User;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private LoginDAO loginDAO;
	
	
	@Override
	@Transactional
	public void saveUser(User theUser) {
		
		loginDAO.saveUser(theUser);
		
	}

}
