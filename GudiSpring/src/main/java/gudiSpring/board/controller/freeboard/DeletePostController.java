package gudiSpring.board.controller.freeboard;

import java.io.IOException;
import java.sql.Connection;

import gudiSpring.board.dao.freeboard.BoardDao;
import gudiSpring.board.dao.reviewboard.ReviewBoardDao;
import gudiSpring.board.dto.freeboard.BoardDto;
import gudiSpring.board.dto.reviewboard.ReviewBoardDto;
import gudiSpring.comment.dao.CommentDao;
import gudiSpring.user.dto.UserDto;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/board/freeboard/delete")
public class DeletePostController extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest req, 
			HttpServletResponse res) 
					throws ServletException, IOException {
		  int contentNo = Integer.parseInt(req.getParameter("contentNo"));

	        Connection conn = null;
	        try {
	            ServletContext sc = this.getServletContext();
	            conn = (Connection) sc.getAttribute("conn");
	            // 세션에서 사용자 정보 확인
	            HttpSession session = req.getSession();
	            UserDto userDto = (UserDto) session.getAttribute("userDto");

	         // 게시글 정보 가져오기
	           BoardDao boardDao = new BoardDao();
	            boardDao.setConnection(conn);
	           BoardDto boardDto = boardDao.selectOne(contentNo);

	            // 권한 확인: 관리자이거나 작성자 본인인 경우만 삭제 허용
	            if (userDto == null || 
	                (!userDto.hasAdminPermission() && userDto.getUserNo() != boardDto.getUserNo())) {
	                res.sendRedirect(req.getContextPath() + "/auth/signin");
	                return;
	            }
	         // 댓글 먼저 삭제
				CommentDao commentDao = new CommentDao();
				commentDao.setConnection(conn);
				commentDao.deleteCommentsByContentNo(contentNo);
				
			// 게시글삭제	
	          
	            boardDao.setConnection(conn);
	            boardDao.deletePost(contentNo);

	            res.sendRedirect(req.getContextPath() + "/board/freeboard/list");
	}catch (Exception e) {
        throw new ServletException("게시글 삭제 중 오류 발생", e);
    }
	        
	

}//doget
    
@Override
protected void doPost(HttpServletRequest req, 
		HttpServletResponse res) throws ServletException, IOException {
	// TODO Auto-generated method stub
	super.doPost(req, res);
}
}//전체종료
