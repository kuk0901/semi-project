package gudiSpring.board.controller.freeboard;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import gudiSpring.board.dao.freeboard.BoardDao;
import gudiSpring.board.dto.freeboard.BoardDto;
import gudiSpring.comment.dao.CommentDao;
import gudiSpring.comment.dto.CommentDto;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/board/freeboard/detail")
public class BoardDetailController extends HttpServlet {

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
            BoardDao boardDao = new BoardDao();
            boardDao.setConnection(conn);
        
            BoardDto boardDto = boardDao.selectOne(contentNo);
          
            	
            if (boardDto.getContentFile() != null && !boardDto.getContentFile().isEmpty()) {
                String imagePath = "/freeboard/" + new File(boardDto.getContentFile()).getName();
                boardDto.setContentFile(imagePath);
            }

                
            	
            // 댓글 조회
            CommentDao commentDao = new CommentDao();
            commentDao.setConnection(conn);
            
            List<CommentDto> commentList = commentDao.getCommentsByContentNo(contentNo);
            
            req.setAttribute("boardDto", boardDto);
            req.setAttribute("commentList", commentList);
            

            res.setContentType("text/html");
            res.setCharacterEncoding("UTF-8");
            
            RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/board/freeboard/boardDetailView.jsp");
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
		 doGet(req,res);
	}//dopost종료
	
	
	

}//전체종료
