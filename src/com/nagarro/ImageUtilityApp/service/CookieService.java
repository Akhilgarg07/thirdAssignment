package com.nagarro.ImageUtilityApp.service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class CookieService {
	
	public static String getCookieUsername(HttpServletRequest request) {
		String username = null ;
		
		Cookie[] cookies = request.getCookies();
		if (cookies!=null)
		{
			for (Cookie c : cookies) {
				if(c.getName().equals("username"))
				{
					username = c.getValue();
				}
			}
		}
		return username;
	}

}
