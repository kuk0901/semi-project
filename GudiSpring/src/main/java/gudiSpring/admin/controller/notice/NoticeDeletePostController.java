package gudiSpring.admin.controller.notice;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import gudiSpring.admin.dto.AdminDto;
import gudiSpring.board.dao.notice.NoticeDao;
import gudiSpring.board.dto.notice.NoticeDto;
import gudiSpring.comment.dao.CommentDao;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/admin/notice/delete")
public class NoticeDeletePostController extends HttpServlet{

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
	            AdminDto adminDto = (AdminDto) session.getAttribute("adminDto");

	         // 게시글 정보 가져오기
	            NoticeDao boardDao = new NoticeDao();
	            boardDao.setConnection(conn);
	            NoticeDto boardDto = boardDao.selectOne(contentNo);

	            // 권한 확인: 관리자이거나 작성자 본인인 경우만 삭제 허용
	            if (adminDto == null ) {
	                res.sendRedirect(req.getContextPath() + "/admin");
	                return;
	            }
	            // 댓글 먼저 삭제
				CommentDao commentDao = new CommentDao();
				commentDao.setConnection(conn);
				commentDao.deleteCommentsByContentNo(contentNo);

	            // 파일 경로 가져오기
	            
	            boardDao.setConnection(conn);
	            List<String> filePaths = boardDao.getFilePathsByContentNo(contentNo);
	          
	            
	            // 파일 삭제
	            for (String filePath : filePaths) {
	                File file = new File("D:/GudiSpring/img/" + filePath); // 경로 수정 필요
	                if (file.exists() && file.isFile()) {
	                    file.delete();
	                }
	            }
	           
	            //게시글삭제+이미지삭제

	            boardDao.deletePost(contentNo);

	            res.sendRedirect(req.getContextPath() + "/admin/board/list");
	}catch (Exception e) {
        throw new ServletException("게시글 삭제 중 오류 발생", e);
    }
	        
	

}//doget
    
@Override
protected void doPost(HttpServletRequest req, 
		HttpServletResponse res) throws ServletException, IOException {
	// TODO Auto-generated method stub
	doGet(req, res);
}
}//전체종료
