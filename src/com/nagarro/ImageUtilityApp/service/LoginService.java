package com.nagarro.ImageUtilityApp.service;

import com.nagarro.ImageUtilityApp.entity.Users;

public interface LoginService {
	
	public Users checkUserCredentials(String username, String password);

}
