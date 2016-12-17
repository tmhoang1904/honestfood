package vn.com.arillance.honestfood.config.app;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
@Component
public class CORSFilter implements Filter {
	
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
	
	    HttpServletResponse response = (HttpServletResponse) res;
	
	    response.setHeader("Access-Control-Allow-Origin", "*");
	    response.setHeader("Access-Control-Allow-Credentials", "true");
	    response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, PUT, DELETE, PATCH");
	    response.setHeader("Access-Control-Max-Age", "3600");
	    response.setHeader("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With, If-Modified-Since, remember-me");
	
	    chain.doFilter(req, res);
	}	
	public void init(FilterConfig filterConfig) {
	}
	
	public void destroy() {
	}
}