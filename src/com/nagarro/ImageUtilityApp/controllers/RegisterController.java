package com.nagarro.ImageUtilityApp.controllers;
import javax.servlet.annotation.WebServlet;
import org.hibernate.Transaction;

import com.nagarro.ImageUtilityApp.constants.Constants;
import com.nagarro.ImageUtilityApp.entity.Users;
import com.nagarro.ImageUtilityApp.util.HibernateUtil;

import org.hibernate.Session;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import java.io.IOException;

@Transactional
@WebServlet("/Register")
public class RegisterController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        Users user = new Users();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setUsername(username);
        user.setPassword(password);
        Transaction tx = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        try{
        	tx = session.beginTransaction();
            session.persist(user);
            session.flush();
            tx.commit();
        }catch (Exception e){
            response.getWriter().println("User could not be saved");
            response.getWriter().println(e);
            session.getTransaction().rollback();
            session.close();
            return;
        }finally {
        session.close();
        }
        response.getWriter().print("Registered Successfully");
        Cookie c = new Cookie("username",username);
        c.setMaxAge(Constants.cookieLifeTime);
        response.addCookie(c);
        response.addCookie(new Cookie("password",password));
        response.sendRedirect(Constants.login);

    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        response.sendRedirect(Constants.register);
    }
}
