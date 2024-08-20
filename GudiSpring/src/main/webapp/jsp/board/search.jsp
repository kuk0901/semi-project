<!-- 이거아직안만든거임 무시 -->
<%-- <%@page import="java.util.ArrayList"%> --%>
<%-- <%@page import="java.sql.Connection"%> --%>
<%-- <%@ page import="java.io.PrintWriter"%> --%>
<%-- <%@ page import="java.text.SimpleDateFormat"%> --%>
<%-- <%@page import="gudiSpring.board.dao.freeboard.SearchDao"%> --%>
<%-- <%@ page language="java" contentType="text/html; charset=EUC-KR" --%>
<%-- 	pageEncoding="EUC-KR"%> --%>
<!-- <!DOCTYPE html> -->
<!-- <html> -->
<!-- <head> -->
<!-- <meta charset="EUC-KR"> -->
<!-- <title>Insert title here</title> -->
<!-- </head> -->
<!-- <body> -->
<!-- 	<table class="table table-bordered"> -->
<!-- 		<thead> -->
<!-- 			<tr> -->
<!-- 				<th>ID</th> -->
<!-- 				<th>제목</th> -->
<!-- 				<th>작성자</th> -->
<!-- 				<th>작성일</th> -->
<!-- 			</tr> -->
<!-- 		</thead> -->
<!-- 		<tbody> -->
<%-- 			<% --%>
//              // SearchDao를 사용하여 데이터를 가져옵니다.
//        Connection conn = (Connection) getServletContext().getAttribute("conn");
//        SearchDao searchDao = new SearchDao(conn);
//        String searchField = request.getParameter("searchField");
//        String searchText = request.getParameter("searchText");
//        ArrayList<FreeBoardDto> list = searchDao.getSearch(searchField, searchText);

//                     // 검색 결과가 없는 경우
//       if (list == null || list.size() == 0) {
//                         out.println("<tr><td colspan='4'>검색결과가 없습니다.</td></tr>");
//         } else {
//                         // 검색 결과가 있는 경우
//                         for (FreeBoardDto dto : list) {
<%--                 %> --%>
<!-- 			<tr> -->
<!-- 				게시글 ID -->
<%-- 				<td><%= dto.getFreeBoardId() %></td> --%>

<!-- 				게시글 제목 -->
<%-- 				<td><a href="view.jsp?freeBoardId=<%=dto.getFreeBoardId()%>"> --%>
<%-- 						<%=dto.getFreeBoardTitle().replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;") --%>
<%-- 		.replaceAll("\n", "<br>")%> --%>
<!-- 				</a></td> -->

<!-- 				작성자 -->
<%-- 				<td><%=dto.getFreeBoardWriter()%></td> --%>

<!-- 				생성일 -->
<%-- 				<td><%=dto.getCreateDate()%></td> --%>
<!-- 			</tr> -->
<%-- 			<% --%>
//                         }
//                     }
<%--                 %> --%>
<!-- 		</tbody> -->
<!-- 	</table> -->
<!-- </body> -->
<!-- </html> -->