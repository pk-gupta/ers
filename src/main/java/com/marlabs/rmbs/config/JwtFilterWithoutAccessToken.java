package com.marlabs.rmbs.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.GenericFilterBean;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.impl.DefaultClaims;

public class JwtFilterWithoutAccessToken extends GenericFilterBean {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
    @Override
    public void doFilter(final ServletRequest req,
                         final ServletResponse res,
                         final FilterChain chain) throws IOException, ServletException {
                
    
                HttpServletRequest request1 = (HttpServletRequest) req;
                HttpServletResponse response = (HttpServletResponse) res;

                

                if ("OPTIONS".equalsIgnoreCase(request1.getMethod())) {
                    response.setHeader("Access-Control-Allow-Methods",
                                    "POST,GET,DELETE");
                    response.setHeader("Access-Control-Max-Age", "3600");
                    response.setHeader(
                                    "Access-Control-Allow-Headers",
                                    "content-type,access-control-request-headers,access-control-request-method,accept,origin,authorization,x-requested-with");
                    response.setStatus(HttpServletResponse.SC_OK);
                } else {
                    
    
 
        final HttpServletRequest request = (HttpServletRequest) req;
        
        log.info("##########Entered into JwtFilter wihtout access token .doFilter()##########");

        String tokenParam=request.getParameter("access_token");
      
        
          String authHeader = request.getHeader("Authorization");
          
      
          if(authHeader==null){
                  authHeader="Bearer "+tokenParam;
          }
          
         if (!authHeader.startsWith("Bearer")) {
            throw new ServletException("Missing or invalid Authorization header.");
        }

       final String token =authHeader.substring(7); // The part after "Bearer "

        try {
                 final Claims claims1=new DefaultClaims();
                 claims1.put("contactId", token);
                 request.setAttribute("claims", claims1);
     
        
        }
        catch (final SignatureException e) {
            throw new ServletException("Invalid token.",e);
        }
        chain.doFilter(req, res);
        log.info("##########Exit from JwtFilter  wihtout access token .doFilter()##########");
    }
    }
}
