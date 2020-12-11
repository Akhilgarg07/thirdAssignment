package com.nagarro.ImageUtilityApp.dao;

import com.nagarro.ImageUtilityApp.entity.Users;

public interface LoginDAO {
	
	public Users getUser(String username, String password);
	public Users getUser(String username);
}
