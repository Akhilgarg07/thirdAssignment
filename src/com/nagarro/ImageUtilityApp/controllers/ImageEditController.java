package com.nagarro.ImageUtilityApp.controllers;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.nagarro.ImageUtilityApp.constants.Constants;
import com.nagarro.ImageUtilityApp.dao.impl.LoginDAOImpl;
import com.nagarro.ImageUtilityApp.entity.Images;
import com.nagarro.ImageUtilityApp.entity.Users;
import com.nagarro.ImageUtilityApp.service.CookieService;
import com.nagarro.ImageUtilityApp.util.HibernateUtil;

@WebServlet("/ImageEdit")
public class ImageEditController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		request.setAttribute("imageId", id);
		request.getRequestDispatcher(Constants.imageEdit).forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int imageId = Integer.parseInt(request.getParameter("imageId"));
		String imageName = request.getParameter("imageName");
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Images img = (Images) session.get(Images.class, imageId);
		img.setName(imageName);
		try {
			session.update(img);
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			PrintWriter out = response.getWriter();
			out.print("error");
		}finally {
			session.close();
		}
		String username = CookieService.getCookieUsername(request);
		
		Users user = new LoginDAOImpl().getUser(username);
		
		request.setAttribute(Constants.imageList, user.getImageList());
		request.setAttribute(Constants.username, username);
		request.getRequestDispatcher(Constants.imageUtility).forward(request, response);
		
	}
}
