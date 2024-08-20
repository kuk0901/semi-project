package gudiSpring.board.controller.notice;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import gudiSpring.board.dao.notice.NoticeDao;
import gudiSpring.board.dto.notice.NoticeDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/board/notice/detail")
public class NoticeDetailController extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		  String contentNoParam = req.getParameter("	");
		   
		    // 나머지 코드  
		
		int contentNo = Integer.parseInt(req.getParameter("contentNo"));
		  
	        Connection conn = (Connection) getServletContext().getAttribute("conn");
	        NoticeDao noticeDao = new NoticeDao();
	        noticeDao.setConnection(conn);

	        NoticeDto notice = noticeDao.getNoticeById(contentNo);

	        req.setAttribute("notice", notice);

	    req.getRequestDispatcher("/jsp/board/notice/noticeDetail.jsp").forward(req, res);
	    }
	

	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}



}



