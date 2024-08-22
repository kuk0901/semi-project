package gudiSpring.admin.controller.board;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import gudiSpring.admin.dto.AdminDto;
import gudiSpring.board.dao.freeboard.BoardDao;
import gudiSpring.comment.dao.CommentDao;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/admin/board/delete")
public class AdminBoardDeleteController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		int contentNo = Integer.parseInt(req.getParameter("contentNo"));
		   HttpSession session = req.getSession();
	        AdminDto adminDto = (AdminDto) session.getAttribute("adminDto");
	     
	        if (adminDto == null ) {
	            res.sendRedirect(req.getContextPath() + "/admin");
	            return;
	        }
		Connection conn = null;
		try {
			ServletContext sc = this.getServletContext();
			conn = (Connection) sc.getAttribute("conn");

			// 댓글 먼저 삭제
			CommentDao commentDao = new CommentDao();
			commentDao.setConnection(conn);
			commentDao.deleteCommentsByContentNo(contentNo);

			BoardDao boardDao = new BoardDao();
			boardDao.setConnection(conn);
			List<String> filePaths = boardDao.getFilePathsByContentNo(contentNo);
			
			// 파일 삭제
			for (String filePath : filePaths) {
				File file = new File("D:/GudiSpring/img/" + filePath);
				if (file.exists() && file.isFile()) {
					file.delete();
				}
			}

			// 게시글 및 이미지 삭제
			boardDao.deletePost(contentNo);

			// 게시글 목록 페이지로 리다이렉트
			res.sendRedirect(req.getContextPath() + "/admin/board/list");
		} catch (Exception e) {
			// TODO: handle exception
		}

	}// doget

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, res);
	}
}
