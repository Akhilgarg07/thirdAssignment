package main.java.com.nagarro.web;

import java.io.IOException;
import javax.servlet.annotation.WebServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;

import main.java.com.nagarro.util.HibernateUtil;
import main.java.com.nagarro.entity.ImagesEntity;
import main.java.com.nagarro.entity.UsersEntity;

@WebServlet("/ImageDelete")
public class ImageDelete extends HttpServlet{
	private static final long serialVersionUID = 1L;
    
	   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("Welcome");
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String str = request.getParameter("id");
		int id  =  Integer.parseInt(str) ;
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		ImagesEntity img = (ImagesEntity) session.get(ImagesEntity.class, id);
		UsersEntity user = img.getUser();
		user.getImageList().remove(img);
		session.delete(img);
		session.getTransaction().commit();
		session.close();
	}
}
