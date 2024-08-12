package gudiSpring.comment.controller;

import java.io.IOException;
import java.sql.Connection;

import gudiSpring.comment.dao.CommentDao;
import gudiSpring.comment.dto.CommentDto;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/editComment")
public class EditCommentController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doGet(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		int commentNo = Integer.parseInt(req.getParameter("commentNo"));
		int contentNo = Integer.parseInt(req.getParameter("contentNo"));
		String commentContent = req.getParameter("commentContent");

		Connection conn = null;
		try {
			ServletContext sc = this.getServletContext();
			conn = (Connection) sc.getAttribute("conn");

			CommentDto commentDto = new CommentDto();
			commentDto.setCommentNo(commentNo);
			commentDto.setContentComment(commentContent);

			CommentDao commentDao = new CommentDao();
			commentDao.setConnection(conn);
			commentDao.updateComment(commentDto);

			res.sendRedirect(req.getContextPath() + "/freeboard/Detail?contentNo=" + contentNo);
		} catch (Exception e) {
			throw new ServletException("댓글 수정 오류", e);
		}
	}// doget
}// 전체
