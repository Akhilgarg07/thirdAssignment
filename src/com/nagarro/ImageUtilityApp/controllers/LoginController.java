package com.nagarro.ImageUtilityApp.controllers;

import javax.servlet.annotation.WebServlet;
import org.hibernate.Session;
import org.hibernate.query.Query;

import com.nagarro.ImageUtilityApp.entity.Users;
import com.nagarro.ImageUtilityApp.service.impl.LoginServiceImpl;
import com.nagarro.ImageUtilityApp.util.HibernateUtil;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/Login")
public class LoginController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("check2");
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		Users user=new LoginServiceImpl().checkUserCredentials(username,password);
		
		if (user == null) {
			response.getWriter().print("No users found");
		} else if (user.getPassword().equals(password)) {
			request.setAttribute("li", user.getImageList());
			request.setAttribute("username", username);
			request.getRequestDispatcher("ImageUtility.jsp").forward(request, response);
			response.addCookie(new Cookie("username", username));
			response.addCookie(new Cookie("password", password));
		} else {
			response.getWriter().print("Invalid password");
		}
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect("./login.jsp");
	}

}

//imagesList
//jstl