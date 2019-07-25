package com.marlabs.rmbs.config;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.beans.factory.config.ServiceLocatorFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.marlabs.rmbs.request.service.ClaimServiceFactory;
import com.marlabs.rmbs.request.service.DomesticTravelService;
import com.marlabs.rmbs.request.service.GiftService;
import com.marlabs.rmbs.request.service.InternationalTravelService;
import com.marlabs.rmbs.request.service.LocalConveyanceService;
import com.marlabs.rmbs.request.service.MiscellaneousService;


@Configuration
public class ClaimTypeConfig {
	
	@Bean
	 public FactoryBean<Object> serviceLocatorFactoryBean() {
	    ServiceLocatorFactoryBean factoryBean = new ServiceLocatorFactoryBean();
	    factoryBean.setServiceLocatorInterface(ClaimServiceFactory.class);
	    return factoryBean;
	 }


	 @Bean(name = "Gift")
	 @Scope(scopeName = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	 public GiftService gift() {
	    return new GiftService();
	 }
	 
	 
	 @Bean(name = "Local Conveyance")
	 @Scope(scopeName = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	 public LocalConveyanceService localConveyance() {
	    return new LocalConveyanceService();
	 }
	
	 @Bean(name = "Domestic Travel")
	 @Scope(scopeName = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	 public DomesticTravelService domesticTravel() {
	    return new DomesticTravelService();
	 }
	 
	 @Bean(name = "International Travel")
	 @Scope(scopeName = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	 public InternationalTravelService internationalTravel() {
	    return new InternationalTravelService();
	 }
	 
	 @Bean(name = "Miscellaneous")
	 @Scope(scopeName = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	 public MiscellaneousService miscellaneous() {
	    return new MiscellaneousService();
	 }
	 
	 
	 
	 
}
