package gudiSpring.admin.controller.admin;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;

import gudiSpring.admin.dao.AdminDao;
import gudiSpring.admin.dto.AdminDto;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(value = "/admin/list")
public class AdminListController extends HttpServlet {
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

			AdminDao adminDao = new AdminDao();
			adminDao.setConnection(conn);

			// 전체 사용자 수 조회
			int totalUsers = adminDao.adminTotalCount();

			// 전체 페이지 수 계산
			int totalPages = (int) Math.ceil((double) totalUsers / pageSize);

			// 시작 인덱스 계산
			int start = (currentPage - 1) * pageSize + 1;

			ArrayList<AdminDto> adminList = (ArrayList<AdminDto>) adminDao.selectAdminList(start, pageSize);

			int pageGroupSize = 5; // 페이지 그룹의 크기(1~5, 6~10, 11~15)
			int currentGroup = (int) Math.ceil((double) currentPage / pageGroupSize);
			int startPage = (currentGroup - 1) * pageGroupSize + 1;
			int endPage = Math.min(currentGroup * pageGroupSize, totalPages);

			// 요청 속성 설정
			req.setAttribute("adminList", adminList);
			req.setAttribute("currentPage", currentPage);
			req.setAttribute("totalPages", totalPages);
			req.setAttribute("startPage", startPage);
			req.setAttribute("endPage", endPage);
			req.setAttribute("pageGroupSize", pageGroupSize);

			RequestDispatcher rd = req.getRequestDispatcher("/jsp/admin/admin/adminListView.jsp");
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

			switch (formName) {
			case "searchAdminForm": {

				int currentPage = 1;
				int pageSize = 10; // 한 페이지에 표시할 항목 수

				handleSearchAdminForm(req, res, currentPage, pageSize);

				break;
			}

			// admin detail 페이지로 넘어가기 위한 계정 비밀번호 확인
			case "adminCheckForm": {
				boolean result = handleAdminCheckForm(req, res);

				// 조회 결과
				if (result) {
					int userNo = Integer.parseInt(req.getParameter("adminNo"));

					res.sendRedirect(req.getContextPath() + "/admin/list/detail?adminNo=" + userNo);
					return;
				} else {
					req.setAttribute("errorMsg", "비밀번호를 틀렸습니다.");
				}
				break;
			}
			default:
				System.out.println();
				break;
			}

			doGet(req, res);
		} catch (Exception e) {
			e.printStackTrace();

			RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/error/e500.jsp");
			dispatcher.forward(req, res);
		}

	}

	private void handleSearchAdminForm(HttpServletRequest req, HttpServletResponse res, int page, int size) {
		String adminName = req.getParameter("search");

		ServletContext sc = this.getServletContext();
		Connection conn = (Connection) sc.getAttribute("conn");

		AdminDao adminDao = new AdminDao();
		adminDao.setConnection(conn);

		// 검색 결과의 총 개수를 가져옴
		int totalUsers = adminDao.adminSearchTotalCount(adminName);

		// 전체 페이지 수 계산
		int totalPages = (int) Math.ceil((double) totalUsers / size);

		// 시작 인덱스 계산
		int start = (page - 1) * size + 1;

		// 페이징된 검색 결과를 가져옴
		ArrayList<AdminDto> adminList = (ArrayList<AdminDto>) adminDao.searchAdminList(adminName);

		// 페이지 네비게이션을 위한 정보 계산
		int pageGroupSize = 5; // 페이지 그룹의 크기
		int currentGroup = (int) Math.ceil((double) page / pageGroupSize);
		int startPage = (currentGroup - 1) * pageGroupSize + 1;
		int endPage = Math.min(currentGroup * pageGroupSize, totalPages);

		// 요청 속성 설정
		req.setAttribute("adminList", adminList);
		req.setAttribute("currentPage", page);
		req.setAttribute("totalPages", totalPages);
		req.setAttribute("startPage", startPage);
		req.setAttribute("endPage", endPage);
		req.setAttribute("pageGroupSize", pageGroupSize);
		req.setAttribute("searchKeyword", adminName);
	}

	private boolean handleAdminCheckForm(HttpServletRequest req, HttpServletResponse res) {
		boolean result = false;

		int userNo = Integer.parseInt(req.getParameter("adminNo"));
		String pwd = req.getParameter("password");

		try {
			ServletContext sc = this.getServletContext();
			Connection conn = (Connection) sc.getAttribute("conn");

			AdminDao adminDao = new AdminDao();
			adminDao.setConnection(conn);

			result = adminDao.adminExistPwd(userNo, pwd);

			req.setAttribute("adminNo", userNo);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}
}
