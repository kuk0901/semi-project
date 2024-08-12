package gudiSpring.comment.controller;

import java.io.IOException;
import java.sql.Connection;

import gudiSpring.comment.dao.CommentDao;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/deleteComment")
public class DeleteCommentController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		/*
		 * int commentNo = Integer.parseInt(req.getParameter("commentNo"));
		 * 
		 */
//		int commentNo  = Integer.parseInt(req.getParameter("commentNo"));
//	        System.out.println("commentNo딜리트잘되나?"+commentNo);
//	        
//	       int contentNo =Integer.parseInt(req.getParameter("contentNo"));
//		 

		int commentNo = Integer.parseInt(req.getParameter("commentNo"));
		int contentNo = Integer.parseInt(req.getParameter("contentNo"));

		Connection conn = null;
		try {

			// ServletContext에서 Connection 가져오기
			ServletContext sc = this.getServletContext();
			conn = (Connection) sc.getAttribute("conn");

			CommentDao commentDao = new CommentDao();
			commentDao.setConnection(conn);
			commentDao.deleteComment(commentNo);

			res.sendRedirect(req.getContextPath() + "/freeboard/Detail?contentNo=" + contentNo);
		} catch (Exception e) {
			throw new ServletException("댓글 삭제 오류", e);

		}
	}
	// doget

}
