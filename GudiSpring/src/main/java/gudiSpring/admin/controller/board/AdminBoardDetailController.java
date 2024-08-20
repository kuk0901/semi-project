package gudiSpring.admin.controller.board;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import gudiSpring.board.dao.freeboard.BoardDao;
import gudiSpring.board.dto.reviewboard.ReviewBoardDto;
import gudiSpring.comment.dao.CommentDao;
import gudiSpring.comment.dto.CommentDto;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/admin/board/detail")
public class AdminBoardDetailController extends HttpServlet  {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		  int contentNo = Integer.parseInt(req.getParameter("contentNo"));
	        Connection conn = null;
		
	        try {
	        	 // ServletContext에서 Connection 객체 가져오기
	            ServletContext sc = this.getServletContext();
	            conn = (Connection) sc.getAttribute("conn");

	        	
	            BoardDao BoardDao = new BoardDao();
	            BoardDao.setConnection(conn);

	            ReviewBoardDto boardDetail = BoardDao.selectDetail(contentNo);
	            
	            if (boardDetail != null) {
	            	
	            	 // 댓글 목록 가져오기
	                CommentDao commentDao = new CommentDao();
	                commentDao.setConnection(conn);
	                List<CommentDto> commentList = commentDao.getCommentsByContentNo(contentNo);

	                // 게시글 정보와 댓글 목록을 request에 설정
	                req.setAttribute("boardDetail", boardDetail);
	                req.setAttribute("commentList", commentList);
	                
	                RequestDispatcher dispatcher
	                = req.getRequestDispatcher("/jsp/admin/board/adminDetailView.jsp");
	                dispatcher.forward(req, res);
	            } else {
	                res.sendRedirect(req.getContextPath() + "/admin/board/list");
	            }
	        
	        
	        } 
	        
	        
	        catch (Exception e) {
	        	  e.printStackTrace(); // 예외를 출력하여 문제를 확인할 수 있게 함
	              throw new ServletException(e); // 예외를 다시 던져서 호출자가 처리하도록 함
			}
	        
	        
	}//doget
	
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}
	
}	
