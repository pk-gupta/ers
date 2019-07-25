
package com.marlabs.rmbs.config;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableAutoConfiguration
@EnableScheduling
@ComponentScan(basePackages = {"com.marlabs.rmbs.*","org.marlabs.consumer.*","org.marlabs.*"})
@EnableTransactionManagement
@EntityScan(basePackages = {"com.marlabs.rmbs.*","org.marlabs.consumer.*","org.marlabs.*"})
@EnableJpaRepositories(basePackages = {"org.marlabs.consumer.*","com.marlabs.rmbs.*","org.marlabs.*"})
@Configuration
@SpringBootApplication
public class ClaimSourceApplication {
	@Autowired
	private Environment env;
	private final Logger log = LoggerFactory.getLogger(this.getClass()); 
	@Bean
	public FilterRegistrationBean jwtFilter() {
		final FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		if(Arrays.asList(env.getActiveProfiles()).contains("prod") || Arrays.asList(env.getActiveProfiles()).contains("staging")){		
			registrationBean.setFilter(new JwtFilter());
		}else{
			registrationBean.setFilter(new JwtFilterWithoutAccessToken());
		}
		log.info("----------Active profiles----"+ env.getProperty("spring.profiles.active"));
		registrationBean.addUrlPatterns("/*");
		return registrationBean;
}
	

	
	public static void main(String[] args) {
		System.setProperty("spring.devtools.restart.enabled", "false");
        SpringApplication app = new SpringApplication(ClaimSourceApplication.class);
        app.run(args);
	}
	
	

}