package gudiSpring.admin.controller.event;

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

@WebServlet(value = "/admin/event/detail")
public class EventDetailController extends HttpServlet {

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
			
			RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/admin/event/eventDetailView.jsp");

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
			ServletContext sc = this.getServletContext();
			
			int eventNo = Integer.parseInt(req.getParameter("eventNo"));
			String eventName = req.getParameter("eventName");
			String openDate = req.getParameter("openDate");
			String closeDate = req.getParameter("closeDate");
			String userNickname = req.getParameter("nickname");
			
			conn = (Connection) sc.getAttribute("conn");
			
			EventDto eventDto = new EventDto(eventNo, eventName, openDate, closeDate, userNickname);
			
			EventDao eventDao = new EventDao();
			eventDao.setConnection(conn);
			
			int result = eventDao.eventUpdate(eventDto);
			
			if (result > 0) {
				// toast 관련 setAttribute 성공
				req.setAttribute("msg", "이벤트 내용을 변경했습니다.");
			} else if (result == 0) {
				// toast 관련 setAttribute 실패
				req.setAttribute("msg", "이벤트 내용을 변경하지 못했습니다.");
			}
			
			doGet(req, res);
		} catch (Exception e) {
			e.printStackTrace();
			
			RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/error/e500.jsp");

			dispatcher.forward(req, res);
		}
	}

	

}
