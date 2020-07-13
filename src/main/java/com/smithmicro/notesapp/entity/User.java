package com.smithmicro.notesapp.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="users")
public class User implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
    @Column(name="username")
    private String userName;
   
	@Column(name="password")
	private String passcode;
	
	@Column(name="enabled")
	private int isUserLogged;

	
	
	
	public User() {
		
	}

	

	public String getUserName() {
		return userName;
	}



	public void setUserName(String userName) {
		this.userName = userName;
	}



	public String getPasscode() {
		return passcode;
	}

	public void setPasscode(String passcode) {
		this.passcode = passcode;
	}

	public int getIsUserLogged() {
		return isUserLogged;
	}

	public void setIsUserLogged(int isUserLogged) {
		this.isUserLogged = isUserLogged;
	}
	
	

	@Override
	public String toString() {
		return "User [user=" + userName + ", passcode=" + passcode + ", isUserLogged="+isUserLogged + "]";
	}

}
