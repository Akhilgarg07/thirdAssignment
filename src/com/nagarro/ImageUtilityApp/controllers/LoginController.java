package com.nagarro.ImageUtilityApp.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nagarro.ImageUtilityApp.constants.Constants;
import com.nagarro.ImageUtilityApp.entity.Users;
import com.nagarro.ImageUtilityApp.service.impl.LoginServiceImpl;

@WebServlet("/Login")
public class LoginController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		Users user = new LoginServiceImpl().checkUserCredentials(username, password);

		if (null == user) {
			request.setAttribute("errorMessage", "No users found");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		} else if (user.getPassword().equals(password)) {
			request.setAttribute("imageList", user.getImageList());
			response.addCookie(new Cookie("username", username));
			response.addCookie(new Cookie("password", password));
			request.getRequestDispatcher(Constants.imageUtility).forward(request, response);
		} else {
			request.setAttribute("errorMessage", "Invalid password");
			request.getRequestDispatcher(Constants.login).forward(request, response);
		}
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect(Constants.login);
	}

}
