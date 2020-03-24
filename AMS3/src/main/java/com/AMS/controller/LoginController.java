package com.AMS.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.AMS.services.LoginService;

@Controller
public class LoginController {

	@Autowired
	private LoginService loginService;


	@RequestMapping(value = "/")
	public String showLogin(HttpServletResponse response) {
		return "login";
	}

	@RequestMapping("loginSession")
	public ModelAndView redirectpage(String email, String pass, HttpServletRequest request, HttpSession session,
			Model model1) {
		return loginService.loginRedirect(email, pass, request, session, model1);
	}

	@PostMapping("mail")
	public String mailsend(String to, Model model) {
		boolean sendmail = loginService.mailSend(to);
		if (sendmail) {
			model.addAttribute("emailforgot", "1");
		} else {
			model.addAttribute("emailforgot", "0");
		}
		return "login";
	}

	@RequestMapping("changePassword")
	public ModelAndView changePassword(String code, Model model) {
		return loginService.checkLink(code);
	}

	@RequestMapping("changePasswordForm")
	public ModelAndView changePasswordform(String email, String code, String pass, String cpass) {
		return loginService.changePasswordForm(email, code, pass, cpass);
	}

	// logout
	@RequestMapping("logout")
	public String destroySession(HttpServletRequest request,HttpServletResponse response) {
		response.setHeader("Cache-Control","no-cache");
		response.setHeader("Cache-Control","no-store");
		response.setHeader("Pragma","no-cache");
		response.setDateHeader ("Expires", 0);
		request.getSession().invalidate();
		return "redirect:/";
	}

}
