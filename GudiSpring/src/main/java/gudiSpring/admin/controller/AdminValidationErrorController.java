package gudiSpring.admin.controller;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet(value = "/admin/error")
public class AdminValidationErrorController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/error/adminValidationError.jsp");
		
		dispatcher.forward(req, res);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		super.doPost(req, res);
	}
	
}
