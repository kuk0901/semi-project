package gudiSpring.admin.controller.event;

import java.io.IOException;
import java.sql.Connection;

import gudiSpring.event.dao.EventDao;
import gudiSpring.event.dto.EventDto;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(value = "/admin/event/add")
public class EventAddController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/admin/event/eventAddView.jsp");

		dispatcher.forward(req, res);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		Connection conn = null;
		
		try {
			ServletContext sc = this.getServletContext();

			conn = (Connection) sc.getAttribute("conn");
			
			String eventName = req.getParameter("eventName");
			String openDate = req.getParameter("openDate");
			String closeDate = req.getParameter("closeDate");
			
			EventDto eventDto = new EventDto(eventName, openDate, closeDate);
			
			EventDao eventDao = new EventDao();
			eventDao.setConnection(conn);
			
			int result = eventDao.eventInsert(eventDto);
			
			if (result > 0) {
				req.setAttribute("msg", "이벤트가 추가 되었습니다.");
			} else if (result == 0) {
				req.setAttribute("msg", "이벤트를 추가하지 못했습니다.");
			}
			
			doGet(req, res);
		} catch (Exception e) {
			e.printStackTrace();
			
			RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/error/e500.jsp");
			
			dispatcher.forward(req, res);
		}
	}

}
