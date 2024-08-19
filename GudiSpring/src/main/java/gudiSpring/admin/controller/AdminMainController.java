package gudiSpring.admin.controller;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import gudiSpring.admin.dao.AdminDao;
import gudiSpring.user.dao.UserDao;
import gudiSpring.user.dto.UserDto;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(value = "/admin/main")
public class AdminMainController extends HttpServlet {

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

			UserDao userDao = new UserDao();

			userDao.setConnection(conn);

			// 전체 사용자 수 조회
			int totalUsers = userDao.userTotalCount();

			// 전체 페이지 수 계산
			int totalPages = (int) Math.ceil((double) totalUsers / pageSize);

			// 시작 인덱스 계산
			int start = (currentPage - 1) * pageSize + 1;

			// 사용자 목록 조회
			ArrayList<UserDto> userList = (ArrayList<UserDto>) userDao.selectUserList(start, pageSize);

			int pageGroupSize = 5; // 페이지 그룹의 크기(1~5, 6~10, 11~15)
			int currentGroup = (int) Math.ceil((double) currentPage / pageGroupSize);
			int startPage = (currentGroup - 1) * pageGroupSize + 1;
			int endPage = Math.min(currentGroup * pageGroupSize, totalPages);

			// 요청 속성 설정
			req.setAttribute("userList", userList);
			req.setAttribute("currentPage", currentPage);
			req.setAttribute("totalPages", totalPages);
			req.setAttribute("startPage", startPage);
			req.setAttribute("endPage", endPage);
			req.setAttribute("pageGroupSize", pageGroupSize);

			RequestDispatcher rd = req.getRequestDispatcher("/jsp/admin/mainView.jsp");
			rd.forward(req, res);

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

			// 페이징 관련 파라미터 처리
			// FIXME: 눈에 익숙해지면 getParameter 부분 상단으로 올림?
			int currentPage = 1;
			int pageSize = 10; // 한 페이지에 표시할 항목 수

			String pageStr = req.getParameter("page");
			if (pageStr != null && !pageStr.isEmpty()) {
				currentPage = Integer.parseInt(pageStr);
			}

			switch (formName) {
			case "searchUsersForm": {
				handleUserSearchForm(req, res, currentPage, pageSize);

				return;
			}

			case "removeUsersForm": {
				int result = handleUserRemoveForm(req, res);

				if (result > 0) {
					req.setAttribute("msg", "회원이 삭제되었습니다.");
				} else if (result == 0) {
					req.setAttribute("msg", "회원을 삭제하지 못했습니다.");
				}
				doGet(req, res);
			}

			default:
				System.out.println();

			}

		} catch (Exception e) {
			e.printStackTrace();

			RequestDispatcher rd = req.getRequestDispatcher("/jsp/error/e500.jsp");

			rd.forward(req, res);
		}
	}

	private void handleUserSearchForm(HttpServletRequest req, HttpServletResponse res, int currentPage, int pageSize)
	    throws ServletException, IOException {
		String userName = req.getParameter("search");

		ServletContext sc = this.getServletContext();
		Connection conn = (Connection) sc.getAttribute("conn");
		UserDao userDao = new UserDao();
		userDao.setConnection(conn);

		// 검색 결과의 총 개수를 가져옴
		int totalUsers = userDao.userSearchTotalCount(userName);

		// 전체 페이지 수 계산
		int totalPages = (int) Math.ceil((double) totalUsers / pageSize);

		// 시작 인덱스 계산
		int start = (currentPage - 1) * pageSize + 1;

		// 페이징된 검색 결과를 가져옴
		ArrayList<UserDto> userList = (ArrayList<UserDto>) userDao.searchUserList(userName);

		// 페이지 네비게이션을 위한 정보 계산
		int pageGroupSize = 5; // 페이지 그룹의 크기
		int currentGroup = (int) Math.ceil((double) currentPage / pageGroupSize);
		int startPage = (currentGroup - 1) * pageGroupSize + 1;
		int endPage = Math.min(currentGroup * pageGroupSize, totalPages);

		// 요청 속성 설정
		req.setAttribute("userList", userList);
		req.setAttribute("currentPage", currentPage);
		req.setAttribute("totalPages", totalPages);
		req.setAttribute("startPage", startPage);
		req.setAttribute("endPage", endPage);
		req.setAttribute("pageGroupSize", pageGroupSize);
		req.setAttribute("searchKeyword", userName);

		RequestDispatcher rd = req.getRequestDispatcher("/jsp/admin/mainView.jsp");
		rd.forward(req, res);
	}

	private int handleUserRemoveForm(HttpServletRequest req, HttpServletResponse res) {
		int result = 0;

		List<Integer> removeUsersNo = Arrays.stream(req.getParameterValues("remove")).map(Integer::parseInt) // 각 요소 형 변환
		    .collect(Collectors.toCollection(ArrayList::new)); // 변환된 요소들을 새로운 ArrayList 만들어 수집

		ServletContext sc = this.getServletContext();

		Connection conn = (Connection) sc.getAttribute("conn");

		UserDao userDao = new UserDao();
		userDao.setConnection(conn);

		result = userDao.deleteUsers((ArrayList<Integer>) removeUsersNo);

		return result;
	}
}
