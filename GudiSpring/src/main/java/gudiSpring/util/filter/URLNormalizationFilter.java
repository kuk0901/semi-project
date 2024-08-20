package gudiSpring.util.filter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

public class URLNormalizationFilter implements Filter {

	 	// 정적 파일 확장자 목록 정의
    private static final List<String> STATIC_EXTENSIONS = Arrays.asList(".jpg", ".jpeg", ".png", ".gif", ".css", ".js");
    // 정적 리소스 경로 정의
    private static final String STATIC_PATH = "/static/";

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain fc)
            throws IOException, ServletException {
        HttpServletRequest httpReq = (HttpServletRequest) req;
        HttpServletResponse httpRes = (HttpServletResponse) res;

        String contextPath = httpReq.getContextPath();
        String uri = httpReq.getRequestURI();

        // 정적 리소스 경로나 특정 확장자를 가진 파일은 정규화 과정에서 제외
        if (uri.startsWith(contextPath + STATIC_PATH) || hasStaticExtension(uri)) {
            fc.doFilter(req, res);
            return;
        }

        /* 
          중복된 슬래시를 단일 슬래시로 변환
        	정규화된 URL로 리다이렉트
        	쿼리 스트링이 있다면 포함
        */
        String norUri = uri.replaceAll("/{2,}", "/");

        // 실제 변경이 있고, 리다이렉트가 필요한 경우에만 리다이렉트
        if (!norUri.equals(uri) && !isAlreadyRedirected(httpReq)) {
            String redirectUrl = httpReq.getRequestURL().toString().replace(uri, norUri);
            String queryString = httpReq.getQueryString();
            if (queryString != null && !queryString.isEmpty()) {
                redirectUrl += "?" + queryString;
            }
            
            httpReq.setAttribute("redirected", true);
            httpRes.sendRedirect(redirectUrl);
            return;
        }

        // 정규화된 URI를 사용하여 요청 처리
        HttpServletRequestWrapper wrapperReq = new HttpServletRequestWrapper(httpReq) {
            @Override
            public String getRequestURI() {
                return norUri;
            }
        };

        fc.doFilter(wrapperReq, res);
    }

    // URI가 정적 파일 확장자로 끝나는지 확인
    private boolean hasStaticExtension(String uri) {
        return STATIC_EXTENSIONS.stream().anyMatch(uri.toLowerCase()::endsWith);
    }

    // 무한 리다이렉트 루프를 방지 => 리다이렉트 여부를 확인
    private boolean isAlreadyRedirected(HttpServletRequest request) {
        return request.getAttribute("redirected") != null;
    }
}