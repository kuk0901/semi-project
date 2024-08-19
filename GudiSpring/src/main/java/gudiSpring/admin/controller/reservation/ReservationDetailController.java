package gudiSpring.admin.controller.reservation;

import java.io.IOException;
import java.sql.Connection;
import java.util.HashMap;

import gudiSpring.event.dao.EventDao;
import gudiSpring.event.dto.EventDto;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(value = "/admin/reservation/detail")
public class ReservationDetailController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		Connection conn = null;
		
		try {
			ServletContext sc = this.getServletContext();
			
			int eventNo = Integer.parseInt(req.getParameter("eventNo"));
			
			conn = (Connection) sc.getAttribute("conn");

			EventDao eventDao = new EventDao();
			eventDao.setConnection(conn);
			
			HashMap<String, String> eventMap = eventDao.adminPlaceDetailSelect(eventNo);
			
			req.setAttribute("eventMap", eventMap);
			
			RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/admin/reservation/reservationDetailView.jsp");

			dispatcher.forward(req, res);
		} catch (Exception e) {
			e.printStackTrace();
			
			RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/error/e500.jsp");

			dispatcher.forward(req, res);
		}
		
	}

}
