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

@WebServlet("/addComment")
public class AddCommentController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int contentNo = Integer.parseInt(req.getParameter("contentNo"));

		String commentContent = req.getParameter("commentContent");

		CommentDto commentDto = new CommentDto();
		commentDto.setContentNo(contentNo);

		commentDto.setContentComment(commentContent);

		Connection conn = null;
		try {
			// ServletContext에서 Connection 가져오기
			ServletContext sc = this.getServletContext();
			conn = (Connection) sc.getAttribute("conn");

			CommentDao commentDao = new CommentDao();
			commentDao.setConnection(conn);
			commentDao.addComment(commentDto);

			res.sendRedirect(req.getContextPath() + "/freeboard/Detail?contentNo=" + contentNo);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException("오류 발생", e);
			// TODO: handle exception
		}
	}// do

}// controller
