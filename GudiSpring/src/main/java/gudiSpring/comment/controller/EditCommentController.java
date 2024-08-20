package gudiSpring.comment.controller;

import java.io.IOException;
import java.sql.Connection;

import gudiSpring.comment.dao.CommentDao;
import gudiSpring.comment.dto.CommentDto;
import gudiSpring.user.dto.UserDto;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

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
	protected void doPost(HttpServletRequest req,
			HttpServletResponse res)
					throws ServletException, IOException {
		 int commentNo = Integer.parseInt(req.getParameter("commentNo"));
	        int contentNo = Integer.parseInt(req.getParameter("contentNo"));
	        String commentContent = req.getParameter("commentContent");
	        String boardType = req.getParameter("boardType"); // 게시판 유형 파라미터 추가
	       
	        HttpSession session = req.getSession();
	        UserDto userDto = (UserDto) session.getAttribute("userDto");

	        // 권한 확인: 로그인되어 있지 않거나, 댓글 작성자도 아니고, 관리자가 아닌 경우
	        if (userDto == null) {
	            res.sendRedirect(req.getContextPath() + "/auth/signin");
	            return;
	        }

	        Connection conn = null;
	        try {
	            ServletContext sc = this.getServletContext();
	            conn = (Connection) sc.getAttribute("conn");


	            CommentDao commentDao = new CommentDao();
	            commentDao.setConnection(conn);

	            // 댓글을 가져와서 작성자 확인 작성자이거나oradmin이거나
	            CommentDto existingComment = commentDao.getCommentById(commentNo);
	            if (existingComment == null || 
	               (existingComment.getUserNo() != (userDto.getUserNo()) && !"ADMIN".equals(userDto.getAuthority()))) {
	                res.sendRedirect(req.getContextPath() + "/board/" + boardType + "/detail?contentNo=" + contentNo);
	                return;
	            }

	            // 수정 작업 수행
	            CommentDto commentDto = new CommentDto();
	            commentDto.setCommentNo(commentNo);
	            commentDto.setContentComment(commentContent);

	            commentDao.updateComment(commentDto);
	           

	            res.sendRedirect(req.getContextPath() + "/board/" + boardType + "/detail?contentNo=" + contentNo);	
	        } catch (Exception e) {
	            throw new ServletException("댓글 수정 오류", e);
	        }
	    }//doget
	}//전체
	


