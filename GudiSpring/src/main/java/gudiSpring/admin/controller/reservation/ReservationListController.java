package gudiSpring.admin.controller.reservation;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;

import gudiSpring.reservation.dao.ReservationDao;
import gudiSpring.reservation.dto.ReservationDto;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(value = "/admin/reservation/list")
public class ReservationListController extends HttpServlet {

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

			ReservationDao reservationDao = new ReservationDao();
			reservationDao.setConnection(conn);

			// 전체 사용자 수 조회
			int totalReservation = reservationDao.reservationTotalCount();
			
			// 전체 페이지 수 계산
			int totalPages = (int) Math.ceil((double) totalReservation / pageSize);

			// 시작 인덱스 계산
			int start = (currentPage - 1) * pageSize + 1;

			ArrayList<ReservationDto> reservationList = (ArrayList<ReservationDto>) reservationDao
			    .selectReservationList(start, pageSize);
			
			int pageGroupSize = 5; // 페이지 그룹의 크기(1~5, 6~10, 11~15)
			int currentGroup = (int) Math.ceil((double) currentPage / pageGroupSize);
			int startPage = (currentGroup - 1) * pageGroupSize + 1;
			int endPage = Math.min(currentGroup * pageGroupSize, totalPages);

			req.setAttribute("reservationList", reservationList);
			req.setAttribute("currentPage", currentPage);
			req.setAttribute("totalPages", totalPages);
			req.setAttribute("startPage", startPage);
			req.setAttribute("endPage", endPage);
			req.setAttribute("pageGroupSize", pageGroupSize);

			RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/admin/reservation/reservationListView.jsp");

			dispatcher.forward(req, res);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		Connection conn = null;

		// 페이지 관련 파라미터 처리
		int currentPage = 1;
		int pageSize = 10; // 한 페이지에 표시할 항목 수
		
		try {
			String placeName = req.getParameter("search");
			
			ServletContext sc = this.getServletContext();
			conn = (Connection) sc.getAttribute("conn");

			String pageStr = req.getParameter("page");

			// 페이지 값이 있는 경우 해당 값으로 사용
			if (pageStr != null && !pageStr.isEmpty()) {
				currentPage = Integer.parseInt(pageStr);
			}

			ReservationDao reservationDao = new ReservationDao();
			reservationDao.setConnection(conn);

			// 검색 예약 수 조회
			int totalReservation = reservationDao.searchTotalCount(placeName);

			// 전체 페이지 수 계산
			int totalPages = (int) Math.ceil((double) totalReservation / pageSize);

			// 시작 인덱스 계산
			int start = (currentPage - 1) * pageSize + 1;

			ArrayList<ReservationDto> reservationList = (ArrayList<ReservationDto>) reservationDao
			    .searchSelectReservationList(placeName, start, pageSize);

			int pageGroupSize = 5; // 페이지 그룹의 크기(1~5, 6~10, 11~15)
			int currentGroup = (int) Math.ceil((double) currentPage / pageGroupSize);
			int startPage = (currentGroup - 1) * pageGroupSize + 1;
			int endPage = Math.min(currentGroup * pageGroupSize, totalPages);

			req.setAttribute("reservationList", reservationList);
			req.setAttribute("currentPage", currentPage);
			req.setAttribute("totalPages", totalPages);
			req.setAttribute("startPage", startPage);
			req.setAttribute("endPage", endPage);
			req.setAttribute("pageGroupSize", pageGroupSize);
			req.setAttribute("searchKeyword", placeName);
			
			RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/admin/reservation/reservationListView.jsp");

			dispatcher.forward(req, res);
			
		} catch (Exception e) {
			e.printStackTrace();

			RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/error/e500.jsp");

			dispatcher.forward(req, res);
		}

	}

}
