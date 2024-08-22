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

			// 전체 사용자 수 조회
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

		// 페이지 관련 파라미터 처리
		int currentPage = 1;
		int pageSize = 10; // 한 페이지에 표시할 항목 수

		try {
			String pageStr = req.getParameter("page");
			String eventName = req.getParameter("search");

			// 페이지 값이 있는 경우 해당 값으로 사용
			if (pageStr != null && !pageStr.isEmpty()) {
				currentPage = Integer.parseInt(pageStr);
			}

			ServletContext sc = this.getServletContext();

			conn = (Connection) sc.getAttribute("conn");

			EventDao eventDao = new EventDao();
			eventDao.setConnection(conn);

			// 전체 이벤트 수 조회
			int totalEvents = eventDao.searchEventTotalCount(eventName);

			// 전체 페이지 수 계산
			int totalPages = (int) Math.ceil((double) totalEvents / pageSize);

			// 시작 인덱스 계산
			int start = (currentPage - 1) * pageSize + 1;

			ArrayList<EventDto> eventList = (ArrayList<EventDto>) eventDao.searchEventList(eventName, start, pageSize);

			int pageGroupSize = 5; // 페이지 그룹의 크기(1~5, 6~10, 11~15)
			int currentGroup = (int) Math.ceil((double) currentPage / pageGroupSize);
			int startPage = (currentGroup - 1) * pageGroupSize + 1;
			int endPage = Math.min(currentGroup * pageGroupSize, totalPages);

			req.setAttribute("eventList", eventList);
			req.setAttribute("currentPage", currentPage);
			req.setAttribute("totalPages", totalPages);
			req.setAttribute("startPage", startPage);
			req.setAttribute("endPage", endPage);
			req.setAttribute("pageGroupSize", pageGroupSize);

			RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/event/eventListView.jsp");
			dispatcher.forward(req, res);

		} catch (Exception e) {
			e.printStackTrace();

			RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/error/e500.jsp");
			dispatcher.forward(req, res);
		}
	}
}
