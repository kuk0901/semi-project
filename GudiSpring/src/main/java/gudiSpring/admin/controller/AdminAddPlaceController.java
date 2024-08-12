package gudiSpring.admin.controller;

import java.io.IOException;
import java.sql.Connection;

import gudiSpring.place.dao.PlaceDao;
import gudiSpring.place.dto.PlaceDto;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(value = "/admin/place/add")
public class AdminAddPlaceController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/admin/addPlaceView.jsp");
		
		dispatcher.forward(req, res);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		Connection conn = null;
		
		try {
			
			String category = req.getParameter("category");
			int areaNo = Integer.parseInt(req.getParameter("areaNo"));
			String placeName = req.getParameter("placeName");
			String plAddress = req.getParameter("plAddress");
			String plPhone = req.getParameter("plPhone");
			String plWebsite = req.getParameter("plWebsite");
			
			PlaceDto placeDto = new PlaceDto(category, areaNo, placeName, plAddress, plPhone, plWebsite);
					
			ServletContext sc = this.getServletContext();
			conn = (Connection) sc.getAttribute("conn");
			
			PlaceDao placeDao = new PlaceDao();
			placeDao.setConnection(conn);
			
			int result;
			result = placeDao.placeInsert(placeDto);
			
			// validation
			if (result == 0) {
				
				RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/error/adminPlaceInsertError.jsp");
				
				dispatcher.forward(req, res);
				
				return;
			}
			
			RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/admin/addPlaceView.jsp");
			
			dispatcher.forward(req, res);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
