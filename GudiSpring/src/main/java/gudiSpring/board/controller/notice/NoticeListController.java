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

@WebServlet("/board/notice/list")
@SuppressWarnings("serial")
public class NoticeListController extends HttpServlet {
		
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 // Connection 객체를 ServletContext에서 가져옵니다.
        Connection conn = (Connection) getServletContext().getAttribute("conn");

        // NoticeDao 객체 생성 및 Connection 설정
        NoticeDao noticeDao = new NoticeDao();
        noticeDao.setConnection(conn);

        // 공지사항 리스트를 가져옵니다.
        List<NoticeDto> notices = noticeDao.getAllNotices();

        // 공지사항 리스트를 request에 설정
        req.setAttribute("notices", notices);

        // JSP 페이지로 포워딩하여 결과를 표시합니다.
        req.getRequestDispatcher("/jsp/board/notice/notice.jsp").forward(req, res);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, res);
	}
}
