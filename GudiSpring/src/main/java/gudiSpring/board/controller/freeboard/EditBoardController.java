package gudiSpring.board.controller.freeboard;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.fileupload2.core.DiskFileItemFactory;
import org.apache.commons.fileupload2.core.FileItem;
import org.apache.commons.fileupload2.core.FileUploadException;
import org.apache.commons.fileupload2.jakarta.JakartaServletFileUpload;

import gudiSpring.board.dao.freeboard.BoardDao;
import gudiSpring.board.dto.freeboard.BoardDto;
import gudiSpring.user.dto.UserDto;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/board/freeboard/edit")
public class EditBoardController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static final String UPLOAD_DIRECTORY = "D:/GudiSpring/img/freeboard";
    private static final String DEFAULT_FILE = "default-file.txt"; // 기본 파일 이름 설정
    private static final String CHARSET = StandardCharsets.UTF_8.name(); // 인코딩 설정
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        int contentNo = Integer.parseInt(req.getParameter("contentNo"));
        HttpSession session = req.getSession();
        UserDto userDto = (UserDto) session.getAttribute("userDto");

        Connection conn = null;
        try {
            ServletContext sc = this.getServletContext();
            conn = (Connection) sc.getAttribute("conn");

            BoardDao boardDao = new BoardDao();
            boardDao.setConnection(conn);
            BoardDto boardDto = boardDao.selectOne(contentNo);
            // 권한 확인: 관리자이거나 작성자 본인인 경우만 접근 허용
            if (userDto == null ) {
                res.sendRedirect(req.getContextPath() + "/auth/signin");
                return;
            }
            req.setAttribute("boardDto", boardDto);
            req.getRequestDispatcher("/jsp/board/freeboard/editFreeBoardForm.jsp").forward(req, res);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException("게시글 수정 중 오류 발생", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        
    	   req.setCharacterEncoding(CHARSET);
           res.setContentType("text/html; charset=UTF-8");

           DiskFileItemFactory factory = DiskFileItemFactory.builder()
               .setPath(Paths.get(UPLOAD_DIRECTORY)) // 임시 저장소 디렉토리 설정
               .get();
           // JakartaServletFileUpload 설정
           JakartaServletFileUpload upload = new JakartaServletFileUpload(factory);
           upload.setFileSizeMax(1024 * 1024 * 10); // 10MB
           upload.setSizeMax(1024 * 1024 * 50); // 50MB
           File uploadDir = new File(UPLOAD_DIRECTORY);
           if (!uploadDir.exists()) {
               if (!uploadDir.mkdirs()) {
                   throw new ServletException("Failed to create upload directory.");
               }
           }
           
           
           
           
           
           int contentNo = 0;
           String contentSubject = null;
           String contentText = null;
           String filePath = DEFAULT_FILE; // 기본 파일 경로 설정
           boolean deleteFile = false;
           Connection conn = null;
           
           HttpSession session = req.getSession();
           UserDto userDto = (UserDto) session.getAttribute("userDto");

           int userNo = userDto.getUserNo();  
        try {
            ServletContext sc = this.getServletContext();
            conn = (Connection) sc.getAttribute("conn");
            if (JakartaServletFileUpload.isMultipartContent(req)) {
                // 요청에서 폼 아이템 파싱
                List<FileItem> formItems = upload.parseRequest(req);

                for (FileItem item : formItems) {
                    if (item.isFormField()) {
                        // 일반 폼 필드 처리
                        String fieldName = item.getFieldName();
                        String fieldValue = new String(item.get(), CHARSET); // 인코딩 설정

                        if ("contentNo".equals(fieldName)) {
                            contentNo = Integer.parseInt(fieldValue);
                        } else if ("contentSubject".equals(fieldName)) {
                            contentSubject = fieldValue;
                        } else if ("contentText".equals(fieldName)) {
                            contentText = fieldValue;
                        }
                    } else {
                        // 파일 필드 처리
                        String fileName = new File(item.getName()).getName();

                        // 파일 이름이 비어있지 않으면 파일 저장 처리
                        if (!fileName.isEmpty()) {
                            // 이미지 파일만 업로드 가능하도록 확장자 검사
                            if (!(fileName.endsWith(".png") || fileName.endsWith(".jpg") || fileName.endsWith(".webp")
                                    || fileName.endsWith(".jpeg") || fileName.endsWith(".gif"))) {
                            	 
                            	req.setAttribute("errorMessage", "이미지 파일만 업로드 가능합니다.");
                            	throw new ServletException("이미지 파일만 업로드 가능합니다.");
                                
                            } else {
                            	 // 타임스탬프 추가-유니크파일네임
                            	String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
                            	String uniqueFileName = timeStamp + "_" + fileName;
                            	filePath = UPLOAD_DIRECTORY + File.separator + uniqueFileName;
                            	File storeFile = new File(filePath);


                                // 파일 저장
                                item.write(storeFile.toPath());	
                                filePath = "freeboard/" + uniqueFileName; // 상대 경로로 변경
                            }
                        }
                    }
                }
            }
            

            
            if (contentSubject == null || contentSubject.isEmpty()) {
                throw new ServletException("Subject is missing or empty.");
            }
            if (contentText == null || contentText.isEmpty()) {
                throw new ServletException("Text is missing or empty.");
            }
            
            // BoardDto 객체 생성 및 데이터 설정
            BoardDto boardDto = new BoardDto();
            boardDto.setContentNo(contentNo);
            boardDto.setContentSubject(contentSubject);
            boardDto.setContentText(contentText);
            
            	
//            파일 삭제 체크박스가 선택된 경우 파일 경로를 기본 파일로 설정
            if (deleteFile) {
                boardDto.setContentFile(DEFAULT_FILE);
            } else {
                boardDto.setContentFile(filePath); // 파일 경로 설정
            }

            BoardDao boardDao = new BoardDao();
            boardDao.setConnection(conn);
            boardDao.updateBoard(boardDto);

            // 게시글 목록 페이지로 리다이렉트
            res.sendRedirect(req.getContextPath() + "/board/freeboard/list");
        } catch (FileUploadException | SQLException e) {
            e.printStackTrace();
            // 예외 처리
            throw new ServletException("파일 업로드 중 오류 발생", e);
        } catch (Exception e) {
            e.printStackTrace();
            // 일반적인 예외 처리
            throw new ServletException("게시글 수정 중 오류 발생", e);
        }
    }
    
}

