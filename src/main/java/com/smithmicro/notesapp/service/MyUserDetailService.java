package com.smithmicro.notesapp.service;

import java.util.logging.Logger;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.smithmicro.notesapp.dao.LoginDAO;
import com.smithmicro.notesapp.entity.MyUserPrincipal;
import com.smithmicro.notesapp.entity.User;


@Service
public class MyUserDetailService implements UserDetailsService {

	private Logger logger = Logger.getLogger(getClass().getName());
	
	@Autowired
	private LoginDAO loginDao;
	
	@Transactional
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		logger.info("Entering User"+username);
		
		try {
	        	if(username == null ||  username.isEmpty()){
	        		throw new UsernameNotFoundException("User not found");
	        	}
	            User user = loginDao.findUserByUserName(username);
	            logger.info("user from userdetailsservice"+user.getUserName());
	            logger.info("user from userdetailsservice"+user.getPasscode());
	            if (user == null || user.getUserName() == null) {
	            	throw new UsernameNotFoundException("User not found");
	            }
	           return new MyUserPrincipal(user);
	        } catch (Exception e) {
	            throw new UsernameNotFoundException("User not found");
	        }
	}
	
	

}
