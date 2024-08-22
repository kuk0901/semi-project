package gudiSpring.admin.controller.notice;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import gudiSpring.board.dao.notice.NoticeDao;
import gudiSpring.board.dto.notice.NoticeDto;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/admin/notice/list")
@SuppressWarnings("serial")
public class NoticeListController extends HttpServlet {
		
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		int recordsPerPage = 10;
        int page = req.getParameter("page") != null ? Integer.parseInt(req.getParameter("page")) : 1;
        int start = (page - 1) * recordsPerPage + 1;
        int end = page * recordsPerPage;

        Connection conn = null;

        try {
            ServletContext sc = this.getServletContext();
            conn = (Connection) sc.getAttribute("conn");

            NoticeDao noticeDao = new NoticeDao();
            noticeDao.setConnection(conn);

            // 게시글 목록 조회
            List<NoticeDto> noticeList = noticeDao.selectList(start, end);

            for (NoticeDto notice : noticeList) {
                // 게시판 이름 설정
                String boardInfoName = noticeDao.getBoardInfoName(notice.getContentBoardInfoNo());
                notice.setBoardInfoName(boardInfoName);

                // 이미지 태그를 <사진>으로 대체
                String contentText = notice.getContentText();
                if (contentText != null) {
                    contentText = contentText.replaceAll("<img[^>]*>", "<사진>");
                    contentText = contentText.replaceAll("<p>|</p>|<br\\s*/?>", " ");
                    notice.setContentText(contentText);
                }

                // 작성자의 닉네임을 가져와 설정
                String nickname = noticeDao.getNicknameByUserNo(notice.getUserNo());
                notice.setNickname(nickname);
            }

            int totalRecords = noticeDao.getTotalCount();
            int totalPages = (int) Math.ceil(totalRecords * 1.0 / recordsPerPage);

            // 게시글 목록 조회
            req.setAttribute("noticeList", noticeList);
            req.setAttribute("totalPages", totalPages);
            req.setAttribute("currentPage", page);

            // JSP 페이지로 포워딩
            res.setContentType("text/html");
            res.setCharacterEncoding("UTF-8");
            RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/admin/notice/noticeBoardListView.jsp");
            dispatcher.forward(req, res);
        }
        catch (Exception e) {
        	
            throw new ServletException("공지사항 목록 조회 중 오류 발생", e);
        }
	}//doget
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, res);
	}
}
