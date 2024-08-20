package gudiSpring.board.controller.reviewboard;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import gudiSpring.board.dao.reviewboard.ReviewBoardDao;
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

@WebServlet("/board/reviewboard/detail")
public class ReviewBoardDetailController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		// TODO Auto-generated method stub
			Connection conn = null;

        try {
            ServletContext sc = this.getServletContext();
            conn = (Connection) sc.getAttribute("conn");
            	
            
            int contentNo = Integer.parseInt(req.getParameter("contentNo"));
           
            // 현재 페이지 번호 추가예정~

          
            //게시글조회
            ReviewBoardDao boardDao = new ReviewBoardDao();
            boardDao.setConnection(conn);
        
            ReviewBoardDto boardDto = boardDao.selectOne(contentNo);
            
            // 닉네임 조회 및 설정
            
          
            String content = boardDto.getContentText();

           

            // 변환된 내용을 다시 boardDto에 설정
            boardDto.setContentText(content);
            // 이미지 경로 변환
            if (boardDto.getContentFiles() != null && !boardDto.getContentFiles().isEmpty()) {
                String contextPath = req.getContextPath();
                for (int i = 0; i < boardDto.getContentFiles().size(); i++) {
                    String fileName = new File(boardDto.getContentFiles().get(i)).getName();
                    boardDto.getContentFiles().set(i, contextPath + "/images/reviewboard/" + fileName);
                }
            }
                

                
            	
            // 댓글 조회
            CommentDao commentDao = new CommentDao();
            commentDao.setConnection(conn);
            
            List<CommentDto> commentList = commentDao.getCommentsByContentNo(contentNo);
            
            req.setAttribute("boardDto", boardDto);
            req.setAttribute("commentList", commentList);
            

            res.setContentType("text/html");
            res.setCharacterEncoding("UTF-8");
            
            RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/board/reviewboard/reivewBoardDetailView.jsp");
            dispatcher.forward(req, res);
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("error", e);
            RequestDispatcher dispatcher = req.getRequestDispatcher("/Error.jsp");
            dispatcher.forward(req, res);
        }
}//doget종료
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		  doGet(req, res);
	}//dopost종료
	
	
	

}//전체종료
