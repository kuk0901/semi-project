package gudiSpring.util.filter;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

public class CharacterEncodingFilter implements Filter {

	FilterConfig filterConfig = null;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		this.filterConfig = filterConfig;
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain fc) throws IOException, ServletException {
		// TODO Auto-generated method stub
		String encoding = this.filterConfig.getInitParameter("encoding");

		req.setCharacterEncoding(encoding);

		fc.doFilter(req, res);

	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		System.out.println("문자열 인코딩 destroy() 수행");
	}
}
