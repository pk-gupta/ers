package com.marlabs.rmbs.config;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.jfree.util.Log;

public class AccessCtrlFilter implements Filter {
	public void init(FilterConfig arg0) throws ServletException {
		Log.info(" *** Supportin cors origin class -- In init method: ***");
	}
	

	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpServletResponse response = (HttpServletResponse) res;
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
		response.setHeader("Access-Control-Max-Age", "3600");
		response.setHeader("Access-Control-Allow-Headers", "Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With");

		chain.doFilter(req, response);
	}

	public void destroy() {
		Log.info(" *** Supportin cors origin class -- In destroy method: ***");
	}

}
