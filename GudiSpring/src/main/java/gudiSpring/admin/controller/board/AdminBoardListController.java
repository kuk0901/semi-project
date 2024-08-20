package gudiSpring.admin.controller.board;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import gudiSpring.board.dao.freeboard.BoardDao;
import gudiSpring.board.dto.reviewboard.ReviewBoardDto;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/admin/board/list")
public class AdminBoardListController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		int recordsPerPage = 10;
		int page = req.getParameter("page") != null ? Integer.parseInt(req.getParameter("page")) : 1;
		int start = (page - 1) * recordsPerPage + 1;
		int end = page * recordsPerPage;

		Connection conn = null;
		try {
			ServletContext sc = this.getServletContext();
			conn = (Connection) sc.getAttribute("conn");

			// AdminDao 초기화 및 데이터 가져오기
			BoardDao boardDao = new BoardDao();
			boardDao.setConnection(conn);
			List<ReviewBoardDto> boardList = boardDao.selectBoardList(start, end);

			// 총 게시글 수 및 페이지 수 계산
			int totalRecords = boardDao.getTotalCount();
			int totalPages = (int) Math.ceil(totalRecords * 1.0 / recordsPerPage);

			// JSP에 데이터 전달
			req.setAttribute("totalBoardList", boardList); // 변수 이름 변경
			req.setAttribute("totalPages", totalPages);
			req.setAttribute("currentPage", page);

			res.setContentType("text/html");
			res.setCharacterEncoding("UTF-8");
			RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/admin/board/adminBoardListView.jsp");
			dispatcher.forward(req, res);

		} catch (Exception e) {
			e.printStackTrace(); // 로그 출력
			throw new ServletException("An error occurred while processing the request.", e);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, res);
	}

}
