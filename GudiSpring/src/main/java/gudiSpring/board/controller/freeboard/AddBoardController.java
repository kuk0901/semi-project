package gudiSpring.board.controller.freeboard;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.io.File;
import java.io.IOException;
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
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/board/freeboard/add")
public class AddBoardController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static final String UPLOAD_DIRECTORY = "C:/GudiSpring/img/freeboard";
    private static final String DEFAULT_FILE = "default-file.txt"; // 기본 파일 이름 설정
    private static final String CHARSET = StandardCharsets.UTF_8.name(); // 인코딩 설정

   
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) 
            throws ServletException, IOException {
    	
    	HttpSession session = req.getSession();
        UserDto userDto = (UserDto) session.getAttribute("userDto");
       
        // 권한 확인
        if (userDto == null) {
            res.sendRedirect(req.getContextPath() + "/auth/signin");
            return;
        }
        // 작성 폼으로 이동
        RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/board/freeboard/newFreeBoardForm.jsp");
        dispatcher.forward(req, res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
    	res.setContentType("text/html; charset=UTF-8");
    	req.setCharacterEncoding(CHARSET);
    	
  	// DiskFileItemFactory 설정-업로드기능
        DiskFileItemFactory factory = DiskFileItemFactory.builder()
            .setPath(Paths.get(UPLOAD_DIRECTORY)) // 임시 저장소 디렉토리 설정
            .get();
        // JakartaServletFileUpload 설정-파일크기기능
        JakartaServletFileUpload upload = new JakartaServletFileUpload(factory);
        upload.setFileSizeMax(1024 * 1024 * 10); // 10MB
        upload.setSizeMax(1024 * 1024 * 50); // 50MB
        // 업로드 디렉토리 생성
        File uploadDir = new File(UPLOAD_DIRECTORY);
        if (!uploadDir.exists()) {
            if (!uploadDir.mkdirs()) {
                throw new ServletException("Failed to create upload directory.");
            }
        }

        String subject = null;
        String text = null;
        String filePath = DEFAULT_FILE; // 기본 파일 경로 설정
        HttpSession session = req.getSession();
        UserDto userDto = (UserDto) session.getAttribute("userDto");
      

        int userNo = userDto.getUserNo();  

        Connection conn = null;
        try {
            // ServletContext에서 Connection 가져오기
            ServletContext sc = this.getServletContext();
            conn = (Connection) sc.getAttribute("conn");

            if (JakartaServletFileUpload.isMultipartContent(req)) {
                // 요청에서 폼 아이템 파싱
                List<FileItem> formItems = upload.parseRequest(req);

                for (FileItem item : formItems) {
                    if (item.isFormField()) {
                        // 일반 폼 필드 처리->
                        String fieldName = item.getFieldName();
                        
                        String fieldValue = new String(item.get(), StandardCharsets.UTF_8); // 인코딩 설정

                        if ("contentSubject".equals(fieldName)) {
                            subject = fieldValue;
                        } 
                        else if ("contentText".equals(fieldName)) {
                            text = fieldValue;
                        }
                    	} 
                    	else {
                        // 파일 필드 처리
                        String fileName = new File(item.getName()).getName();

                        // 파일 이름이 비어있지 않으면 파일 저장 처리
                        if (!fileName.isEmpty()) {
                            // 이미지 파일만 업로드 가능하도록 확장자 검사
                            if (!(fileName.endsWith(".png") || fileName.endsWith(".jpg") || fileName.endsWith(".webp"))
                            		|| fileName.endsWith(".jpeg") || fileName.endsWith(".gif")) {
                                throw new ServletException("이미지 파일만 업로드 가능합니다.");
                            } else {
                                // 타임스탬프를 추가하여 파일 이름을 유니크하게 만듦
                                String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
                                String uniqueFileName = timeStamp + "_" + fileName;

                                filePath = UPLOAD_DIRECTORY + File.separator + uniqueFileName;
                                File storeFile = new File(filePath);

                                // 파일 저장
                                item.write(storeFile.toPath());
                                filePath = "freeboard/" + uniqueFileName; // 상대 경로로 변경
                             // 본문에 이미지 태그 추가
                             
                                text += "<br/><img src='/images/" + filePath + "' alt='" + uniqueFileName + "'/><br/>";
                            }
                        }
                    }
                }
            }


          

            if (subject == null || subject.isEmpty()) {
                throw new ServletException("Subject is missing or empty.");
            }
            if (text == null || text.isEmpty()) {
                throw new ServletException("Text is missing or empty.");
            }
            // BoardDto 객체 생성 및 데이터 설정
            BoardDto boardDto = new BoardDto();
            boardDto.setContentSubject(subject);
            boardDto.setContentText(text);
            boardDto.setContentFile(filePath); // 파일 경로 설정
            boardDto.setUserNo(userNo);

            BoardDao boardDao = new BoardDao();
            boardDao.setConnection(conn);
            boardDao.addBoard(boardDto);

            // 게시글 목록 페이지로 리다이렉트
            res.sendRedirect(req.getContextPath() + "/board/freeboard/list");
        } catch (FileUploadException | SQLException e) {
            e.printStackTrace();
            // 예외 처리
            throw new ServletException("파일 업로드 중 오류 발생", e);
        } catch (Exception e) {
            e.printStackTrace();
            // 일반적인 예외 처리
            throw new ServletException("게시글 추가 중 오류 발생", e);
        }
    }
}
