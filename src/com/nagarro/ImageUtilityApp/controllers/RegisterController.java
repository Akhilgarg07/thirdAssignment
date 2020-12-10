package com.nagarro.ImageUtilityApp.controllers;
import javax.servlet.annotation.WebServlet;
import org.hibernate.Transaction;

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
//        System.out.println("check1");
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
//            session.getTransaction().commit();
        }catch (Exception e){
            response.getWriter().println("User could not be saved");
            response.getWriter().println(e);
            session.getTransaction().rollback();
            session.close();
            return;
        }
//        session.flush();
        session.close();
        response.getWriter().print("Registered Successfully");
        response.addCookie(new Cookie("username",username));
        response.addCookie(new Cookie("password",password));
        response.sendRedirect("login.jsp");

    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        response.sendRedirect("register.jsp");
    }
}
