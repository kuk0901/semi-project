package gudiSpring.board.controller.reviewboard;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import gudiSpring.board.dao.reviewboard.ReviewBoardDao;
import gudiSpring.board.dto.reviewboard.ReviewBoardDto;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet("/board/reviewboard/list")
public class ReviewBoardListController extends HttpServlet {
   
 
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) 
    		throws ServletException, IOException {
    	// TODO Auto-generated method stub
    	  
    	int recordsPerPage = 10;
    	int page = req.getParameter("page") != null ? Integer.parseInt(req.getParameter("page")) : 1;
    	int start = (page - 1) * recordsPerPage + 1;
    	int end = page * recordsPerPage;

           
           Connection conn = null;
           
    	   try {
    		   ServletContext sc = this.getServletContext();
			// 미리 준비된 DB 객체 불러오기
			conn = (Connection)sc.getAttribute("conn");
			
			ReviewBoardDao boardDao = new ReviewBoardDao();
			boardDao.setConnection(conn);
			 // 게시글 목록 조회
			List<ReviewBoardDto> boardList = boardDao.selectList(start, end);
			
		
			
			for (ReviewBoardDto board : boardList) {
				// 게시판 이름 설정
				String boardInfoName = boardDao.getBoardInfoName(board.getContentBoardInfoNo());
			    board.setBoardInfoName(boardInfoName);
			 // 이미지 태그를 <사진>으로 대체이거없으면 <img~~~로 도배됨>
			    String contentText = board.getContentText();
			    if (contentText != null) {
			        // <img> 태그를 <사진>으로 대체
			    	// 모든 HTML 태그 제거
			        contentText = contentText.replaceAll("<[^>]*>", " ");

			        // 변환된 텍스트를 다시 설정
			        board.setContentText(contentText);
			    }
			    
			    // 작성자의 닉네임을 가져와 설정
	            String nickname = boardDao.getNicknameByUserNo(board.getUserNo());
	            board.setNickname(nickname);
			    
	            
			    
			}

			int totalRecords = boardDao.getTotalCount();
            int totalPages = (int) Math.ceil(totalRecords * 1.0 / recordsPerPage);
			  // 게시글 목록 조회
            req.setAttribute("boardList", boardList);
            req.setAttribute("totalPages", totalPages);
            req.setAttribute("currentPage", page);

			
            // JSP 페이지로 포워딩
            res.setContentType("text/html");
            res.setCharacterEncoding("UTF-8");
            RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/board/reviewboard/reviewBoardListView.jsp");
            dispatcher.forward(req, res);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
    	   
    	   
    }
    

  
    
    
    
    
    
    
    
    
  }
