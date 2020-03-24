package com.AMS.services;


import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import com.AMS.model.Appuser;
import com.AMS.repository.AppuserRepository;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private AppuserRepository repo;

	public String getSaltString() {
		String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		StringBuilder salt = new StringBuilder();
		Random rnd = new Random();
		while (salt.length() < 10) { // length of the random string.
			int index = (int) (rnd.nextFloat() * SALTCHARS.length());
			salt.append(SALTCHARS.charAt(index));
		}
		String saltStr = salt.toString();
		return saltStr;
	}

	@Override
	public ModelAndView loginRedirect(String email1, String pass1, HttpServletRequest request, HttpSession session,
			Model model1) {
		String email=email1.trim();
		String pass=SHA1_Encrypt.encryptThisString(pass1);
		List<Appuser> user1 = repo.authentication(email, pass);
		ModelAndView model = new ModelAndView();
		if (user1.isEmpty()) {
			model.addObject("error", "Invalid Email And Password");
			model.addObject("email", email);
			model.addObject("pass", pass1);
			model.setViewName("login");
		} else {
			int role = 0;
			int id = 0;
			for (Appuser newuser : user1) {
				role = newuser.getRole().getObjId();
				id = newuser.getObjId();
				String name=newuser.getFirstName() + " "+newuser.getLastName();
				request.getSession().setAttribute("MY_SESSION_EMAIL", email);
				request.getSession().setAttribute("MY_SESSION_ID", id);
				request.getSession().setAttribute("MY_SESSION_ROLE", role);
				request.getSession().setAttribute("MY_SESSION_NAME", name);
				if(role==1)
				{
					request.getSession().setAttribute("MY_SESSION_COMPANY_NAME", "AMS");
				}
				else
				{
					request.getSession().setAttribute("MY_SESSION_COMPANY_ID", newuser.getCompany().getObjId());
					request.getSession().setAttribute("MY_SESSION_COMPANY_NAME", newuser.getCompany().getName());
				}	
			}
			if (role == 1) {
				model.setViewName("redirect:superAdminDashboard");
			} else if (role == 2) {
				model.setViewName("redirect:adminDashboard");
			} else {
				model.setViewName("redirect:hrDashboard");
			}

		}
		return model;
	}

	@Override
	public boolean mailSend(String to) {
		String code = getSaltString();
		String link = "http://localhost:8080/changePassword?code=" + code;
		String subject = "Forgot Password ";
		String user = "shah.rumit99@gmail.com";
		String pass = "8866291338";

		List<Appuser> user1 = repo.checkEmail(to);
		if (user1.isEmpty()) {
			return false;
		} else {
			String email1 = "";
			for (Appuser newuser : user1) {
				int id = newuser.getObjId();
				email1 = newuser.getEmail();
				Appuser newuser2 = repo.findById(id).orElse(null);
				newuser2.setCode(code);
				newuser2.setCodeStatus("Active");
				repo.save(newuser2);
			}

			boolean bool = SendMail.send1(email1, subject, code, link, user, pass);
			return bool;
		}
	}

	@Override
	public ModelAndView checkLink(String code) {
		ModelAndView model = new ModelAndView();
		List<Appuser> appuser = repo.dataGet(code);
		if (appuser.isEmpty()) {
			model.setViewName("pageNotFound");
		} else {
			for (Appuser user : appuser) {
				if (user.getCodeStatus().equals("Active")) {
					String email = user.getEmail();
					model.addObject("email", email);
					model.addObject("code", code);
					model.setViewName("changePassword");
				} else {
					model.setViewName("pageNotFound");
				}
			}
		}
		return model;
	}

	@Override
	public ModelAndView changePasswordForm(String email, String code, String pass, String cpass) {
		ModelAndView model = new ModelAndView();
		List<Appuser> appuser = repo.dataGet(code);
		if (appuser.isEmpty()) {
			model.setViewName("pageNotFound");
		} else {
			for (Appuser user : appuser) {
				if (user.getEmail().equals(email) && user.getCodeStatus().equals("Active")) {
					if (pass.equals(cpass)) {
						// save
						int id = user.getObjId();
						Appuser newuser2 = repo.findById(id).orElse(null);
						newuser2.setCodeStatus("Inactive");
						newuser2.setPassword(SHA1_Encrypt.encryptThisString(pass));
						repo.save(newuser2);
						model.addObject("changeSuccessfully", "1");
						model.setViewName("login");
					} else {
						model.addObject("error", "Password and confirm password no match.");
						model.addObject("email", email);
						model.addObject("code", code);
						model.addObject("pass", pass);
						model.addObject("cpass", cpass);
						model.setViewName("changePassword");
					}
				} else {
					model.setViewName("pageNotFound");
				}
			}
		}
		return model;

	}

	

}
