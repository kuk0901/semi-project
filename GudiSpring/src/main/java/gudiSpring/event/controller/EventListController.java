package gudiSpring.event.controller;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;

import gudiSpring.event.dao.EventDao;
import gudiSpring.event.dto.EventDto;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(value = "/event/list")
public class EventListController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		Connection conn = null;
		
		try {
			ServletContext sc = this.getServletContext();

			conn = (Connection) sc.getAttribute("conn");
			
			EventDao eventDao = new EventDao();
			eventDao.setConnection(conn);
			
			ArrayList<EventDto> eventList = (ArrayList<EventDto>)eventDao.selectEventList();
			
			req.setAttribute("eventList", eventList);
			
			RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/event/eventListView.jsp");
			dispatcher.forward(req, res);
			
		} catch (Exception e) {
			e.printStackTrace();

			RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/error/e500.jsp");
			dispatcher.forward(req, res);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		Connection conn = null;
		
		try {
			String eventName = req.getParameter("search");
			
			ServletContext sc = this.getServletContext();

			conn = (Connection) sc.getAttribute("conn");

			EventDao eventdao = new EventDao();
			eventdao.setConnection(conn);

			ArrayList<EventDto> eventList = (ArrayList<EventDto>) eventdao.searchEventList(eventName);

			req.setAttribute("eventList", eventList);
			
			RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/event/eventListView.jsp");
			dispatcher.forward(req, res);
			
		} catch (Exception e) {
			e.printStackTrace();

			RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/error/e500.jsp");
			dispatcher.forward(req, res);
		}
	}
}
