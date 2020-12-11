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

import com.nagarro.ImageUtilityApp.entity.Images;
import com.nagarro.ImageUtilityApp.entity.Users;
import com.nagarro.ImageUtilityApp.util.HibernateUtil;

@WebServlet("/ImageEdit")
public class ImageEdit extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id1 = request.getParameter("id");
		int id = Integer.parseInt(id1);
		System.out.println(id);
		// request.setAttribute(name, o);
		request.setAttribute("imageId", id);
		request.getRequestDispatcher("ImageEdit.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("imageId");
		int imageId = Integer.parseInt(id);
		String str = request.getParameter("name");
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Images img = (Images) session.get(Images.class, imageId);
		img.setName(str);
		try {
			session.update(img);
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			session.close();
			PrintWriter out = response.getWriter();
			out.print("error");
			return;
		}
		Cookie[] cookies = request.getCookies();
		String username = null;
		if (cookies != null) {
			for (Cookie c : cookies) {
				if (c.getName().equals("username")) {
					username = c.getValue();
				}
			}
		}
		Query query = session.createQuery("from Users where username=:username");
		query.setParameter("username", username);
		Users user = (Users) query.uniqueResult();
		request.setAttribute("li", user.getImageList());
		request.setAttribute("username", username);
		request.getRequestDispatcher("ImageUtility.jsp").forward(request, response);
		session.close();
	}
}
