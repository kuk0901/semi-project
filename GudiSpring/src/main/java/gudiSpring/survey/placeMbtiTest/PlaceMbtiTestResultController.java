package gudiSpring.survey.placeMbtiTest;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

import gudiSpring.place.dao.PlaceDao;
import gudiSpring.place.dto.PlaceDto;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(value = "/survey/placeMbtiTest/result")
public class PlaceMbtiTestResultController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		Connection conn = null;
		
		try {
			ServletContext sc = this.getServletContext();
			conn = (Connection) sc.getAttribute("conn");
			
			String placeMbtiTestResutStr = req.getParameter("place");
			
			ArrayList<Integer> listNo = specialCharacterSplit(placeMbtiTestResutStr);
			
			PlaceDao placeDao = new PlaceDao();
			placeDao.setConnection(conn);
			
			ArrayList<PlaceDto> placeList = (ArrayList<PlaceDto>) placeDao.placeMbtiTestResultList(listNo);
			
			req.setAttribute("placeList", placeList);

			RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/survey/placeMbti/placeMbtiTestResult.jsp");
			dispatcher.forward(req, res);
			
		} catch (Exception e) {
			e.printStackTrace();
			
			RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/error/e500.jsp");
			dispatcher.forward(req, res);
		}
		
	}
	
	private ArrayList<Integer> specialCharacterSplit(String str) {
		
		return Arrays.stream(str.split("_!"))
						.filter(s -> !s.isEmpty())
			      .map(Integer::parseInt)
			      .collect(Collectors.toCollection(ArrayList::new));
	
	}
}
