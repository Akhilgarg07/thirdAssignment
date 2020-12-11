package com.nagarro.ImageUtilityApp.controllers;

import java.io.IOException;
import javax.servlet.annotation.WebServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;

import com.nagarro.ImageUtilityApp.entity.Images;
import com.nagarro.ImageUtilityApp.entity.Users;
import com.nagarro.ImageUtilityApp.util.HibernateUtil;

@WebServlet("/ImageDelete")
public class ImageDelete extends HttpServlet{
	private static final long serialVersionUID = 1L;
    
	   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("Welcome");
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String str = request.getParameter("imageId");
		int imageid  =  Integer.parseInt(str) ;
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Images img = (Images) session.get(Images.class, imageid);
		Users user = img.getUserId();
		user.getImageList().remove(img);
		session.delete(img);
		session.getTransaction().commit();
		session.close();
		response.getWriter().print("Deletion sucessful");
		response.sendRedirect("ImageUtility.jsp");
	}
}
