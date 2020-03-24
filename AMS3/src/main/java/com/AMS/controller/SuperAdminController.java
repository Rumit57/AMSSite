package com.AMS.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.AMS.configs.ApplicationConfig;
import com.AMS.model.Appuser;
import com.AMS.model.Company;
import com.AMS.services.SuperAdminService;

@Controller
public class SuperAdminController {

	@Autowired
	private SuperAdminService superAdminService;
	
	@Autowired
	private ApplicationConfig config;
	
	// header
	@RequestMapping("superAdminProfile")
	public ModelAndView superAdminProfile(HttpSession session) {
		ModelAndView model = new ModelAndView();
		if (!superAdminService.checkSession(session)) {
			model.setViewName("login");
		} else {
			String email = (String) session.getAttribute("MY_SESSION_EMAIL");
			model = superAdminService.dataGet(email);
			model.addObject("dir", System.getProperty("user.dir")+"/uploads");
		}
		return model;
	}

	// update profile
	@RequestMapping("superAdminProfileUpdate")
	public String superAdminProfileUpdate(int user_id, String fname, String lname, String email, String phone,
			String add1, String add2, String city, String state, String country, HttpSession session) {
		if (!superAdminService.checkSession(session)) {
			return "pageNotFound";
		}else {
			// save
			superAdminService.superAdminProfileUpdate(user_id, fname, lname, email, phone, add1, add2, city, state,
					country);
			return "redirect:superAdminProfile";
		}
	}

	// change password
	@RequestMapping("changePasswordSuperAdmin")
	public String changePasswordSuperAdmin(HttpSession session) {
		if (!superAdminService.checkSession(session)) {
			return "login";
		} else {
			return "changePasswordSuperAdmin";
		}
	}

	@RequestMapping("changePasswordSA")
	public ModelAndView changePasswordSA(String oldPass, String newPass, String newCPass, HttpSession session) {
		ModelAndView model = new ModelAndView();
		if (!superAdminService.checkSession(session)) {
			model.setViewName("pageNotFound");
		}else {
			int id = (Integer) session.getAttribute("MY_SESSION_ID");
			model = superAdminService.changePassword(id, oldPass, newPass, newCPass);
		}
		return model;
	}


	// dashboard action
	@RequestMapping("superAdminDashboard")
	public ModelAndView superAdminDashboard(HttpSession session, Model model1) {
		ModelAndView model = new ModelAndView();
		if (!superAdminService.checkSession(session)) {
			model.setViewName("login");
		}else {
			String session1 = (String) session.getAttribute("MY_SESSION_NAME");
			model.addObject("session", session1);
			superAdminService.findNumberOfRecord(model1);
			model.setViewName("superAdminDashboard");
		}
		return model;
	}

	// SA company
	@RequestMapping("saCompany")
	public String saCompany(HttpSession session, Model model) {
		if (!superAdminService.checkSession(session)) {
			return "login";
		} else {
			model.addAttribute("companys", superAdminService.findAll1());
			return "saCompany";
		}
	}

	// Add Company
	@RequestMapping("addCompany")
	public ModelAndView addCompany(Company company, Model model,HttpSession session) {
		if (!superAdminService.checkSession(session)) {
			ModelAndView model1 = new ModelAndView();
			model1.setViewName("pageNotFound");
			return model1;
		} else {
		model.addAttribute("companys", superAdminService.findAll1());
		return superAdminService.addCompany(company);
		}
	}

	// delete company
	@RequestMapping("companyDelete")
	public String SA_companyDelete(int delete, HttpSession session) {
		if (!superAdminService.checkSession(session)) {
			return "login";
		} else {
			superAdminService.deleteCompanyData(delete);
			return "redirect:saCompany";
		}
	}

	// edit form company
	@RequestMapping("editCompany")
	public String saCompanyEdit(int id, Model model, HttpSession session) {
		if (!superAdminService.checkSession(session)) {
			return "login";
		} else {
			Company company = superAdminService.findByIdCompany(id);
			model.addAttribute("editcompanys", company);
			model.addAttribute("editclick", 1);
			model.addAttribute("companys", superAdminService.findAll1());
			model.addAttribute("statusData", superAdminService.findStatusData());
			return "saCompany";
		}
	}

	// update company
	@RequestMapping("updateCompany")
	public String saCompanyUpdate(int objId, String name, String taxId, String companyEmail, String address1,
			String address2, String phoneNumber, String city, String state, String pincode, String country,
			int status) {
		// update
		superAdminService.SAcompanyUpdate(objId, name, taxId, companyEmail, address1, address2, phoneNumber, city,
				state, pincode, country, status);
		return "redirect:saCompany";
	}

	// details of company
	@RequestMapping("companyDetails")
	public String saCompanyDetails(int id, Model model, HttpSession session) {
		if (!superAdminService.checkSession(session)) {
			return "pageNotfound";
		} else {
			Company company = superAdminService.findByIdCompany(id);
			model.addAttribute("detailsCompanys", company);
			model.addAttribute("url",config.getUrl());
			superAdminService.findCompanyAdmin(id, model);
			return "saCompanyDetails";
		}
	}

	// update company details
	@RequestMapping("updateCompanyDetails")
	public String updateCompanyDetails(int objId, String name, String companyEmail, String phoneNumber, String address1,
			String address2, String city, String state, String country) {
		// update
		superAdminService.SAcompanyUpdateDetails(objId, name, companyEmail, phoneNumber, address1, address2, city,
				state, country);
		return "redirect:companyDetails?id=" + objId;
	}

	// SA_admin
	@RequestMapping("saAdmin")
	public String saAdmin(Model model, HttpSession session) {
		if (!superAdminService.checkSession(session)) {
			return "login";
		} else {
			model.addAttribute("companyData", superAdminService.findCompanyData());
			superAdminService.findAllAdmin(model);
			return "saAdmin";
		}

	}

	// add admin
	@RequestMapping("addAdmin")
	public ModelAndView addAdmin(String cPass, int companyId, Appuser appuser, Model model) {
		// save
		superAdminService.findAllAdmin(model);
		return superAdminService.addAdminData(cPass, companyId, appuser, model);
	}

	// edit form admin
	@RequestMapping("edit")
	public String saAdminEdit(int id, Model model, HttpSession session) {
		if (!superAdminService.checkSession(session)) {
			return "pageNotfound";
		} else {
			Appuser appuser = superAdminService.findByIdAdmin(id);
			char gender = appuser.getGender();
			if (gender == 'M') {
				model.addAttribute("gender", 1);
			} else {
				model.addAttribute("gender", 2);
			}
			model.addAttribute("editadmins", appuser);
			model.addAttribute("editclick", 1);
			model.addAttribute("companyData", superAdminService.findCompanyData());
			superAdminService.findAllAdmin(model);
			return "saAdmin";
		}
	}

	// update admin
	@RequestMapping("editAdmin")
	public String SA_adminUpdate(int id, String fname, String lname, String email, int cName, char optradio,
			String phone, String add1, String add2, String pinCode, String city, String state, String country,
			HttpSession session) {
		// update
		superAdminService.SAadminUpdate(id, fname, lname, email, cName, optradio, phone, add1, add2, pinCode, city,
				state, country);
		return "redirect:saAdmin";
	}

	// delete admin
	@RequestMapping("success")
	public String SA_admindelete(int delete, Model model, HttpSession session) {
		if (!superAdminService.checkSession(session)) {
			return "pageNotfound";
		} else {
			superAdminService.deleteAdminData(delete);
			return "redirect:saAdmin";
		}
	}
	
	//upload image SA
	@RequestMapping("updatePhotoSA")
	public String uploadImage(MultipartFile imageFile,HttpSession session,HttpServletRequest request)
	{	
		try {
			superAdminService.saveImage(imageFile,session,request);
		} catch (Exception e) {
			System.out.println(e);
		}
		return "redirect:superAdminProfile";
	}
	
	//upload Image Company
	@RequestMapping("updatePhotoCompany")
	public String updatePhotoCompany(int objId,MultipartFile imageFile,HttpSession session,HttpServletRequest request)
	{	
		try {
			superAdminService.saveImageCompany(objId,imageFile,session,request);
		} catch (Exception e) {
			System.out.println(e);
		}
		return "redirect:companyDetails?id=" + objId;
	}
	
	
	
	
}
