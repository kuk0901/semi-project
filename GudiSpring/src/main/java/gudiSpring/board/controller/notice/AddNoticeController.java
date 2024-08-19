package gudiSpring.board.controller.notice;

import java.io.IOException;
import java.sql.Connection;

import gudiSpring.board.dao.notice.NoticeDao;
import gudiSpring.board.dto.notice.NoticeDto;
import gudiSpring.user.dto.UserDto;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/board/notice/add")
public class AddNoticeController extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		// Notice 작성 폼으로 이동
        RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/board/notice/newNoticeBoardForm.jsp");
        dispatcher.forward(req, res);
		// TODO Auto-generated method stub
		  
   
	}//doget종료
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// 요청 파라미터에서 공지사항 정보 추출
        String subject = req.getParameter("subject");
        String text = req.getParameter("contentText");
        String file = "default.txt";
//       int userNo = Integer.parseInt(req.getParameter("userNo"));
       
        //로그인/로그아웃전임시용
        HttpSession session = req.getSession();
        UserDto userDto = (UserDto) session.getAttribute("userDto");

        int userNo = userDto.getUserNo();  
   
      if (subject == null || subject.trim().isEmpty()) {
          subject = "Untitled";  // 기본값 설정
      }
      if (text == null || text.trim().isEmpty()) {
    	    text = "No content provided.";  // 기본값 설정
    	    throw new ServletException("Content text cannot be empty.");
    	}
        NoticeDto notice = new NoticeDto();
        notice.setContentSubject(subject);
        notice.setContentText(text);
        notice.setContentFile(file);
        notice.setUserNo(userNo);
        
        Connection conn = null;
        
        try {
            ServletContext sc = this.getServletContext();
            conn = (Connection) sc.getAttribute("conn");
            
            NoticeDao noticeDao = new NoticeDao();
            noticeDao.setConnection(conn);
            noticeDao.addNoticeBoard(notice);
            
            res.sendRedirect(req.getContextPath() + "/board/notice/list");
            
        } catch (Exception e) {
            throw new ServletException("공지사항 추가 중 오류 발생", e);
        }
		
	}
}//controller종료
