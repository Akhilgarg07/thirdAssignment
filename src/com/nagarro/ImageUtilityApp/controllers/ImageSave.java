package com.nagarro.ImageUtilityApp.controllers;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Paths;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.nagarro.ImageUtilityApp.entity.Images;
import com.nagarro.ImageUtilityApp.entity.Users;
import com.nagarro.ImageUtilityApp.util.HibernateUtil;

@WebServlet("/ImageSave")
@MultipartConfig
public class ImageSave extends HttpServlet {
	private static final long serialVerisonUID = 1;
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		
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
		
		Part part = null;
		try {
			part = request.getPart("file");
		} catch(Exception e) {
			response.getWriter().println("could not process");
		}
		String fileName = Paths.get(part.getSubmittedFileName()).getFileName().toString();
		String SAVE_DIR = "Images";
		String appPath = request.getServletContext().getRealPath("");
		String savePath = appPath + File.separator + SAVE_DIR;
		String filePath = savePath + File.separator + fileName ;
		InputStream fileContent = part.getInputStream();
		
		
		Images image = new Images();
//		image.setImg(new File(filePath));
		//image.setImage(imgFile);
		image.setName(fileName);
		image.setSize((int) (part.getSize()/1000));
		image.setImagPath(filePath);
		System.out.println(fileName);
		System.out.println(filePath);
		System.out.println((int)part.getSize());
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		System.out.println(username);
		Query query = session.createQuery("from Users where username=:username");
		query.setParameter("username", username);
		
		Users user = (Users)query.uniqueResult();
		user.getImageList().add(image);
		image.setUserId(user);
		try {
			session.save(image);
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			session.close();
			PrintWriter out = response.getWriter();
			out.println("could not process");
			return  ;
		}
		session.close();
		response.getWriter().print("");
		response.sendRedirect("ImageUtility.jsp");
		
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("ImageUtility.jsp");
	}

	
	

}
