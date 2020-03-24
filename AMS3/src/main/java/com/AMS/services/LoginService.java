package com.AMS.services;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

public interface LoginService {
	//check authentication 
	public ModelAndView loginRedirect(String email,String pass,HttpServletRequest request,HttpSession session,Model model1);
	
	
	//send mail
	public boolean mailSend(String to);
	
	//check link is valid or not.
	public ModelAndView checkLink(String code);
	
	//change password form 
	public ModelAndView changePasswordForm(String email,String code,String pass,String cpass);
	
	
}
