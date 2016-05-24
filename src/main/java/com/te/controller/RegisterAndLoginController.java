package com.te.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class RegisterAndLoginController {

	Logger logger = Logger.getLogger(RegisterAndLoginController.class);
	
	/**
	 * 登录页面
	 * @return
	 */
	@RequestMapping("/login")
	public String vLogin(){
		logger.info("/login");
		return "login";
	}
	
	/**
	 * 注册页面
	 * @return
	 */
	@RequestMapping("/register")
	public String vRegister(){
		logger.info("/register");
		return "register";
	}
	
}
