package com.nagarro.ImageUtilityApp.service.impl;

import com.nagarro.ImageUtilityApp.dao.impl.LoginDAOImpl;
import com.nagarro.ImageUtilityApp.entity.Users;
import com.nagarro.ImageUtilityApp.service.LoginService;

public class LoginServiceImpl implements LoginService {

	public Users checkUserCredentials(String username, String password) {
		return new LoginDAOImpl().getUser(username,password);
	}

}
