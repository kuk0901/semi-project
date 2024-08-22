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

		// 페이지 관련 파라미터 처리
		int currentPage = 1;
		int pageSize = 10; // 한 페이지에 표시할 항목 수

		try {
			ServletContext sc = this.getServletContext();

			conn = (Connection) sc.getAttribute("conn");

			String pageStr = req.getParameter("page");

			// 페이지 값이 있는 경우 해당 값으로 사용
			if (pageStr != null && !pageStr.isEmpty()) {
				currentPage = Integer.parseInt(pageStr);
			}

			EventDao eventDao = new EventDao();
			eventDao.setConnection(conn);

			// 전체 이벤트 수 조회
			int totalEvents = eventDao.eventTotalCount();

			// 전체 페이지 수 계산
			int totalPages = (int) Math.ceil((double) totalEvents / pageSize);

			// 시작 인덱스 계산
			int start = (currentPage - 1) * pageSize + 1;

			ArrayList<EventDto> eventList = (ArrayList<EventDto>) eventDao.selectEventList(start, pageSize);

			int pageGroupSize = 5; // 페이지 그룹의 크기(1~5, 6~10, 11~15)
			int currentGroup = (int) Math.ceil((double) currentPage / pageGroupSize);
			int startPage = (currentGroup - 1) * pageGroupSize + 1;
			int endPage = Math.min(currentGroup * pageGroupSize, totalPages);

			// 요청 속성 설정
			req.setAttribute("eventList", eventList);
			req.setAttribute("currentPage", currentPage);
			req.setAttribute("totalPages", totalPages);
			req.setAttribute("startPage", startPage);
			req.setAttribute("endPage", endPage);
			req.setAttribute("pageGroupSize", pageGroupSize);

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

		// 페이지 관련 파라미터 처리
		int currentPage = 1;
		int pageSize = 10; // 한 페이지에 표시할 항목 수

		String pageStr = req.getParameter("page");
		String eventName = req.getParameter("search");

		// 페이지 값이 있는 경우 해당 값으로 사용
		if (pageStr != null && !pageStr.isEmpty()) {
			currentPage = Integer.parseInt(pageStr);
		}

		ArrayList<EventDto> eventList = null;

		ServletContext sc = this.getServletContext();

		Connection conn = (Connection) sc.getAttribute("conn");

		EventDao eventDao = new EventDao();
		eventDao.setConnection(conn);

		// 전체 이벤트 수 조회
		int totalEvents = eventDao.searchEventTotalCount(eventName);

		// 전체 페이지 수 계산
		int totalPages = (int) Math.ceil((double) totalEvents / pageSize);

		// 시작 인덱스 계산
		int start = (currentPage - 1) * pageSize + 1;

		eventList = (ArrayList<EventDto>) eventDao.searchEventList(eventName, start, pageSize);

		int pageGroupSize = 5; // 페이지 그룹의 크기(1~5, 6~10, 11~15)
		int currentGroup = (int) Math.ceil((double) currentPage / pageGroupSize);
		int startPage = (currentGroup - 1) * pageGroupSize + 1;
		int endPage = Math.min(currentGroup * pageGroupSize, totalPages);
		
		req.setAttribute("currentPage", currentPage);
		req.setAttribute("totalPages", totalPages);
		req.setAttribute("startPage", startPage);
		req.setAttribute("endPage", endPage);
		req.setAttribute("pageGroupSize", pageGroupSize);
		
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
