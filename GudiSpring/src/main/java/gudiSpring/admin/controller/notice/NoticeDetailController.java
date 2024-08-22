package gudiSpring.admin.controller.notice;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import gudiSpring.board.dao.notice.NoticeDao;
import gudiSpring.board.dto.notice.NoticeDto;
import gudiSpring.comment.dao.CommentDao;
import gudiSpring.comment.dto.CommentDto;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/admin/notice/detail")
public class NoticeDetailController extends HttpServlet {
	
		@Override
	    protected void doGet(HttpServletRequest req, HttpServletResponse res) 
	            throws ServletException, IOException {
	        
	        Connection conn = null;

	        try {
	            ServletContext sc = this.getServletContext();
	            conn = (Connection) sc.getAttribute("conn");
	            
	            int contentNo = Integer.parseInt(req.getParameter("contentNo"));
	           
	            // 게시글 조회
	            NoticeDao noticeDao = new NoticeDao();
	            noticeDao.setConnection(conn);
	        
	            NoticeDto noticeDto = noticeDao.selectOne(contentNo);

	            // 본문 내용 처리
	            String content = noticeDto.getContentText();
	            noticeDto.setContentText(content);

	            // 이미지 경로 변환
	            if (noticeDto.getContentFiles() != null && !noticeDto.getContentFiles().isEmpty()) {
	                String contextPath = req.getContextPath();
	                for (int i = 0; i < noticeDto.getContentFiles().size(); i++) {
	                    String fileName = new File(noticeDto.getContentFiles().get(i)).getName();
	                    noticeDto.getContentFiles().set(i, contextPath + "/img/notice/" + fileName);
	                }
	            }
	                
	            // 댓글 조회
	            CommentDao commentDao = new CommentDao();
	            commentDao.setConnection(conn);
	            
	            List<CommentDto> commentList = commentDao.getCommentsByContentNo(contentNo);
	            
	            req.setAttribute("noticeDto", noticeDto);
	            req.setAttribute("commentList", commentList);
	            

	            res.setContentType("text/html");
	            res.setCharacterEncoding("UTF-8");
	            
	            RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/admin/notice/noticeDetailView.jsp");
	            dispatcher.forward(req, res);
	        } catch (Exception e) {
	            e.printStackTrace();
	            req.setAttribute("error", e);
	            RequestDispatcher dispatcher = req.getRequestDispatcher("/Error.jsp");
	            dispatcher.forward(req, res);
	        }
	    }//doget

	    @Override
	    protected void doPost(HttpServletRequest req, HttpServletResponse res) 
	            throws ServletException, IOException {
	        doGet(req, res);
	    }
	

}





