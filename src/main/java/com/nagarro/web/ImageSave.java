package main.java.com.nagarro.web;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.Part;

import org.hibernate.Session;
import org.hibernate.query.Query;

import main.java.com.nagarro.entity.ImagesEntity;
import main.java.com.nagarro.entity.UsersEntity;
import main.java.com.nagarro.util.HibernateUtil;

import java.io.InputStream;
import java.io.PrintWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

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
		byte[] imgFile = new byte[(int) part.getSize()];
		
		ImagesEntity image = new ImagesEntity();
		image.setImg(new File(filePath));
		image.setImage(imgFile);
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Query query = session.createQuery("from users where username=:username");
		query.setParameter("username", username);
		
		UsersEntity user = (UsersEntity)query.uniqueResult();
		user.getImageList().add(image);
		image.setUser(user);
		try {
			session.save(user);
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
		response.sendRedirect("imgUpload.html");
		
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("imgUpload.html");
	}

	
	

}
