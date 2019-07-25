package com.marlabs.rmbs.login.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.marlabs.rmbs.login.vo.LoginVo;

/**
 * LoginService.java
 * @author Pappu.kumar
 * Dec 29, 2015
 */ 

@Service
public interface LoginService {
    public LoginVo loginAuthentication(HttpServletRequest http) throws Exception; 
}
