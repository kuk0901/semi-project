package gudiSpring.survey.placeMbtiTest;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(value = "/survey/placeMbtiTest")
public class PlaceMbtiTestController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		try {
			RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/survey/placeMbti/placeMbtiTest.jsp");
			
			dispatcher.forward(req, res);
		} catch (Exception e) {
			e.printStackTrace();
			
			RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/error/e500.jsp");
			dispatcher.forward(req, res);
		}
		
	}
}
