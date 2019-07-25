package com.marlabs.rmbs.config;

import java.util.EnumSet;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.AbstractRequestLoggingFilter;

@Configuration
public class WebConfig {
	
	private static final Logger log = LoggerFactory.getLogger(WebConfig.class);

	@Bean
    public FilterRegistrationBean accessCtrlFilter() {
	    FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new AccessCtrlFilter());
        registration.setDispatcherTypes(EnumSet.allOf(DispatcherType.class));
        registration.addUrlPatterns("/*");
        return registration;
    }
	@Bean
	public Filter loggingFilter(){
	    AbstractRequestLoggingFilter f = new AbstractRequestLoggingFilter() {

	        @Override
	        protected void beforeRequest(HttpServletRequest request, String message) {
	        	if (log.isDebugEnabled()) {
	    			log.debug(message);
	    		}
	        }

	        @Override
	        protected void afterRequest(HttpServletRequest request, String message) {
	        	if (log.isDebugEnabled()) {
	    			log.debug(message);
	    		}
	        }
	    };
	    f.setIncludeClientInfo(true);
	    f.setIncludePayload(true);
	    f.setIncludeQueryString(true);

	    f.setBeforeMessagePrefix("BEFORE REQUEST  [");
	    f.setAfterMessagePrefix("AFTER REQUEST    [");
	    f.setAfterMessageSuffix("]\n");
	    return f;
	}

}