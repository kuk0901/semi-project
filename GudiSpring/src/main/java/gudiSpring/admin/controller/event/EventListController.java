package gudiSpring.admin.controller.event;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import gudiSpring.event.dao.EventDao;
import gudiSpring.event.dto.EventDto;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(value = "/admin/event/list")
public class EventListController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		Connection conn = null;

		try {
			ServletContext sc = this.getServletContext();

			conn = (Connection) sc.getAttribute("conn");

			EventDao eventDao = new EventDao();
			eventDao.setConnection(conn);

			ArrayList<EventDto> eventList = (ArrayList<EventDto>) eventDao.selectEventList();

			req.setAttribute("eventList", eventList);

			RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/admin/event/eventListView.jsp");

			dispatcher.forward(req, res);

		} catch (Exception e) {
			e.printStackTrace();
			
			RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/error/e500.jsp");
			
			dispatcher.forward(req, res);
		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		try {

			String formName = req.getParameter("formName");

			switch (formName) {
				case "searchEventsForm": {
					ArrayList<EventDto> eventList = (ArrayList<EventDto>) handleEventSearchForm(req, res);
	
					req.setAttribute("eventList", eventList);
	
					RequestDispatcher rd = req.getRequestDispatcher("/jsp/admin/event/eventListView.jsp");
					rd.forward(req, res);
	
					return;
				}

				case "removeEventsForm": {
					int result = handleEventRemoveForm(req, res);
	
					if (result > 0) {
						req.setAttribute("msg", "이벤트를 삭제했습니다.");
					} else if (result == 0) {
						req.setAttribute("msg", "이벤트를 삭제하지 못했습니다.");
					}
					
					doGet(req, res);
				}

				default:
					System.out.println();
			}

		} catch (Exception e) {
			e.printStackTrace();
		
			RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/error/e500.jsp");
			
			dispatcher.forward(req, res);
		}

	}

	private List<EventDto> handleEventSearchForm(HttpServletRequest req, HttpServletResponse res) {

		ArrayList<EventDto> eventList = null;

		String eventName = req.getParameter("search");

		ServletContext sc = this.getServletContext();

		Connection conn = (Connection) sc.getAttribute("conn");

		EventDao eventdao = new EventDao();
		eventdao.setConnection(conn);

		eventList = (ArrayList<EventDto>) eventdao.searchEventList(eventName);

		return eventList;

	}

	private int handleEventRemoveForm(HttpServletRequest req, HttpServletResponse res) {
		int result = 0;

		List<Integer> removeEventNo = Arrays.stream(req.getParameterValues("remove")).map(Integer::parseInt) // 각 요소 형 변환
		    .collect(Collectors.toCollection(ArrayList::new)); // 변환된 요소들을 새로운 ArrayList 만들어 수집

		ServletContext sc = this.getServletContext();

		Connection conn = (Connection) sc.getAttribute("conn");

		EventDao eventdao = new EventDao();
		eventdao.setConnection(conn);

		result = eventdao.deleteEvents((ArrayList<Integer>) removeEventNo);

		return result;
	}

}
