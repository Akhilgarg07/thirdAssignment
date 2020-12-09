package com.nagarro.ImageUtilityApp.controllers;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;

import com.nagarro.ImageUtilityApp.entity.Images;
import com.nagarro.ImageUtilityApp.util.HibernateUtil;

@WebServlet("/ImageEdit")
public class ImageEdit extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("Welcome");
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String str = request.getParameter("id");
		int id  =  Integer.parseInt(str) ;
		str = request.getParameter("name");
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Images img = (Images) session.get(Images.class, id);
		img.setName(str);
		try {
			session.update(img);
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			session.close();
			PrintWriter out = response.getWriter();
			out.print("error");
			return  ;
		}
		session.close();
	}

}
