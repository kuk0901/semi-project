package gudiSpring.admin.controller;

import java.io.IOException;
import java.sql.Connection;

import gudiSpring.admin.dao.AdminDao;
import gudiSpring.admin.dto.AdminDto;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(value = "/admin/signout")
public class AdminSignoutController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		HttpSession session = req.getSession();
	
		System.out.println(session);
		
		session.invalidate();
		
		res.sendRedirect(req.getContextPath() + "/admin");
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	}
}
