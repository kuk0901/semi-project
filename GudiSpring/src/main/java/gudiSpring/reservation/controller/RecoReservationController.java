package gudiSpring.reservation.controller;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;

import gudiSpring.area.dao.AreaDao;
import gudiSpring.area.dto.AreaDto;
import gudiSpring.place.dao.PlaceDao;
import gudiSpring.place.dto.PlaceDto;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(value = "/reservation/recommend")
public class RecoReservationController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		Connection conn = null;
		
		try {
			ServletContext sc = this.getServletContext();
			
			conn = (Connection) sc.getAttribute("conn");
			
			String placeNoParam = req.getParameter("place");
			int placeNo = -1;			
			String areaNoParam = req.getParameter("area");
			int areaNo = 1;
			String userNoParam = req.getParameter("user");
		
			System.out.println(placeNoParam);
			System.out.println(areaNoParam);
			System.out.println(userNoParam);
			
			if (placeNoParam != null && !placeNoParam.isEmpty()) {
				try {
					placeNo = Integer.parseInt(placeNoParam);
				} catch (NumberFormatException e) {
					e.printStackTrace();
				}
			}
			
			if (areaNoParam != null && !areaNoParam.isEmpty()) {
				try {
					areaNo = Integer.parseInt(areaNoParam);
				} catch (NumberFormatException e) {
					e.printStackTrace();
				}
			}			
			
			
			AreaDao areaDao = new AreaDao();
			areaDao.setConnection(conn);
			
			PlaceDao placeDao = new PlaceDao();
			placeDao.setConnection(conn);
			
			PlaceDto placeDto = placeDao.selectPlace(placeNo);
			req.setAttribute("placeDto", placeDto);
			
			ArrayList<AreaDto> reservationArea = (ArrayList<AreaDto>) areaDao.reservationArea();
			req.setAttribute("reservationArea", reservationArea);
			
			ArrayList<PlaceDto> reservationPlace = (ArrayList<PlaceDto>) placeDao.reservationPlace();
			req.setAttribute("reservationPlace", reservationPlace);
			
			RequestDispatcher rd = req.getRequestDispatcher("/jsp/reservation/recommendedReservationView.jsp");
			rd.forward(req, res);
		} catch (Exception e) {
			e.printStackTrace();
			
			// error page 넘어가는 부분 e500.jsp
		}
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		try {
			
			String visitDate = req.getParameter("visitDate");
			String visitorNum = req.getParameter("visitorNum");			
						
			RequestDispatcher dispathcer = req.getRequestDispatcher("/jsp/reservation/reservationFinishView.jsp");
			
			dispathcer.forward(req, res);
			
			
		} catch (Exception e) {
			e.printStackTrace();
			
			// error page 넘어가는 부분 e500.jsp
		}
	}
}
