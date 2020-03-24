package com.AMS.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import com.AMS.model.Appuser;
import com.AMS.services.AdminService;

@Controller
public class AdminController {

	@Autowired
	public AdminService adminService;

	// header Admin Profile
	@RequestMapping("adminProfile")
	public ModelAndView adminprofile(HttpSession session, Model model1) {
		ModelAndView model = new ModelAndView();
		if (!adminService.checkSession(session)) {
			model.setViewName("login");
		} else {
			int session1 = (Integer) session.getAttribute("MY_SESSION_ID");
			model = adminService.dataGet(session1, model1);
		}
		return model;
	}

	// Header Update Profile
	@RequestMapping("adminProfileUpdate")
	public String adminProfileUpdate(int objId, String fname, String lname, String email, String phone, String add1,
			String add2, String city, String state, String country, HttpSession session) {
		if (!adminService.checkSession(session)) {
			return "pageNotFound";
		} else {
			adminService.adminProfileUpdate(objId, fname, lname, email, phone, add1, add2, city, state, country);
			return "redirect:adminProfile";
		}

	}

	// Header Change Password
	@RequestMapping("changePasswordAdmin")
	public String changePasswordAdmin(HttpSession session) {
		if (!adminService.checkSession(session)) {
			return "login";
		} else {
			return "changePasswordAdmin";
		}
	}

	// Header Change Password
	@RequestMapping("ChangePasswordAdmin")
	public ModelAndView ChangePasswordA(String oldPass, String newPass, String newCPass, HttpSession session) {
		ModelAndView model = new ModelAndView();
		if (!adminService.checkSession(session)) {
			model.setViewName("login");
		} else {
			int id = (Integer) session.getAttribute("MY_SESSION_ID");
			model = adminService.changePasswordAdmin(id, oldPass, newPass, newCPass);
		}
		return model;
	}

	// Header Company Profile
	@RequestMapping("adminCompanyDetails")
	public ModelAndView adminCompanyDetails(HttpSession session, Model model1) {
		ModelAndView model = new ModelAndView();
		if (!adminService.checkSession(session)) {
			model.setViewName("login");
		} else {
			int session1 = (Integer) session.getAttribute("MY_SESSION_ID");
			model = adminService.companyDataGet(session1, model1);
		}
		return model;
	}

	// Header Company Profile Update
	@RequestMapping("adminCompanyDetailsUpdate")
	public String adminCompanyDetailsUpdate(HttpSession session, int id, String name, String gstin, String mobile,
			String email, String add1, String add2, String city, String state, String pincode, String country) {
		if (!adminService.checkSession(session)) {
			return "login";
		} else {
			adminService.updateCompanyData(id, name, gstin, mobile, email, add1, add2, city, state, pincode, country);
			return "redirect:adminCompanyDetails";
		}

	}

	// Deashboard index
	@RequestMapping("adminDashboard")
	public ModelAndView adminDashboard(HttpSession session, Model model1) {
		ModelAndView model = new ModelAndView();
		if (!adminService.checkSession(session)) {
			model.setViewName("login");
		} else {
			String session1 = (String) session.getAttribute("MY_SESSION_NAME");
			model.addObject("session", session1);
			adminService.findNumberOfRecord(model1, session);
			model.setViewName("adminDashboard");
		}
		return model;
	}

	// Show Employee
	@RequestMapping("adminEmployee")
	public String adminEmployee(Model model, HttpSession session) {
		if (!adminService.checkSession(session)) {
			return "login";
		} else {
			model.addAttribute("employees", adminService.findAllEmployee(session));
			return "adminEmployee";
		}
	}

	// Manage HR
	@RequestMapping("manageHR")
	public String manageHR(Model model, HttpSession session) {
		if (!adminService.checkSession(session)) {
			return "login";
		} else {
			model.addAttribute("hrs", adminService.findAllHR(session));
			return "manageHR";
		}
	}

	// ADD HR
	@RequestMapping("addHR")
	public ModelAndView addHR(Appuser appuser, HttpSession session, String cpass, Model model1) {
		ModelAndView model = new ModelAndView();
		if (!adminService.checkSession(session)) {
			model.setViewName("login");
		} else {
			model1.addAttribute("hrs", adminService.findAllHR(session));
			model = adminService.addHR(appuser, cpass, session);
		}
		return model;
	}

	// EDIT HR
	@RequestMapping("editHR")
	public String editHR(Model model, HttpSession session, int id) {
		if (!adminService.checkSession(session)) {
			return "login";
		} else {
			Appuser appuser = adminService.findByIdHR(id);
			char gender = appuser.getGender();
			if (gender == 'M') {
				model.addAttribute("gender", 1);
			} else {
				model.addAttribute("gender", 2);
			}
			model.addAttribute("edithr", appuser);
			model.addAttribute("hrs", adminService.findAllHR(session));
			model.addAttribute("editHR", 1);
			model.addAttribute("statusData", adminService.findStatusData());
			return "manageHR";
		}

	}

	// UPDATE HR
	@RequestMapping("updateHR")
	public String updateHR(int id, String fname, String lname, String email, int sid, char optradio, String phone,
			String add1, String add2, String pincode, String city, String state, String country, Model model,
			HttpSession session) {
		if (!adminService.checkSession(session)) {
			return "login";
		} else {
			adminService.updateHR(id, fname, lname, email, sid, optradio, phone, add1, add2, pincode, city, state,
					country);
			model.addAttribute("hrs", adminService.findAllHR(session));
			return "manageHR";
		}
	}

	// DELETE HR
	@RequestMapping("deleteHR")
	public String deletHR(int delete, HttpSession session, Model model) {
		if (!adminService.checkSession(session)) {
			return "login";
		} else {
			adminService.deleteHR(delete);
			model.addAttribute("hrs", adminService.findAllHR(session));
			return "manageHR";
		}
	}

	// upload image admin
	@RequestMapping("updatePhotoAdmin")
	public String uploadImage(MultipartFile imageFile, HttpSession session, HttpServletRequest request) {
		try {
			adminService.saveImage(imageFile, session, request);
		} catch (Exception e) {
			System.out.println(e);
		}
		return "redirect:adminProfile";
	}

	// upload Image Company
	@RequestMapping("updatePhotoCompanyAdmin")
	public String updatePhotoCompanyAdmin(int objId, MultipartFile imageFile, HttpSession session,
			HttpServletRequest request) {
		try {
			adminService.saveImageCompany(objId, imageFile, session, request);
		} catch (Exception e) {
			System.out.println(e);
		}
		return "redirect:adminCompanyDetails";
	}
}
