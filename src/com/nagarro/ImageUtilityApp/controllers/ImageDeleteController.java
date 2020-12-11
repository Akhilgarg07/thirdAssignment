package com.nagarro.ImageUtilityApp.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import com.nagarro.ImageUtilityApp.constants.Constants;
import com.nagarro.ImageUtilityApp.entity.Images;
import com.nagarro.ImageUtilityApp.entity.Users;
import com.nagarro.ImageUtilityApp.util.HibernateUtil;

@WebServlet("/ImageDelete")
public class ImageDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect("Welcome");
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int imageid = Integer.parseInt(request.getParameter("imageId"));
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Images img = (Images) session.get(Images.class, imageid);
		Users user = img.getUserId();
		try {
			session.delete(img);
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
		} finally {
			session.close();
		}
		response.getWriter().print("Deletion sucessful");
		request.setAttribute(Constants.imageList, user.getImageList());
		request.setAttribute(Constants.username, user.getUsername());
		request.getRequestDispatcher(Constants.imageUtility).forward(request, response);

	}
}
