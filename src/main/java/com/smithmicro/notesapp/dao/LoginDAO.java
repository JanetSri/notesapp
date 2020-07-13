package com.smithmicro.notesapp.dao;

import com.smithmicro.notesapp.entity.User;

public interface LoginDAO {
   
	public void saveUser(User theUser);
	
	public User findUserByUserName(String username);
}
