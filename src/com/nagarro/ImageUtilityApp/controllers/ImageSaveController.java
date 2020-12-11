package com.nagarro.ImageUtilityApp.controllers;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.nagarro.ImageUtilityApp.constants.Constants;
import com.nagarro.ImageUtilityApp.dao.impl.LoginDAOImpl;
import com.nagarro.ImageUtilityApp.entity.Images;
import com.nagarro.ImageUtilityApp.entity.Users;
import com.nagarro.ImageUtilityApp.service.CookieService;
import com.nagarro.ImageUtilityApp.util.HibernateUtil;

@WebServlet("/ImageSave")
@MultipartConfig
public class ImageSaveController extends HttpServlet {
	private static final long serialVerisonUID = 1;

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		String username = CookieService.getCookieUsername(request);

		Part part = null;
		try {
			part = request.getPart("file");
		} catch (Exception e) {
			response.getWriter().println("could not process");
		}
		if (part != null) {
			String fileName = Paths.get(part.getSubmittedFileName()).getFileName().toString();
			String SAVE_DIR = "Images";
			String appPath = request.getServletContext().getRealPath("");
			String savePath = appPath + File.separator + SAVE_DIR;
			String filePath = savePath + File.separator + fileName;

			Images image = new Images();
			image.setName(fileName);
			image.setSize((int) (part.getSize() / 1000));
			image.setImagPath(filePath);

			Session session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();

			Users user = new LoginDAOImpl().getUser(username);
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
				return;
			}finally {
			session.close();
			}
			response.getWriter().print("");
			request.setAttribute(Constants.imageList, user.getImageList());
			request.setAttribute(Constants.username, username);
			request.getRequestDispatcher(Constants.imageUtility).forward(request, response);

		}
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect(Constants.imageUtility);
	}

}
