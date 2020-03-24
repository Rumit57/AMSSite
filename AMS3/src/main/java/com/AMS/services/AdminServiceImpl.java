package com.AMS.services;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.AMS.configs.ApplicationConfig;
import com.AMS.model.Appuser;
import com.AMS.model.Company;
import com.AMS.model.Employee;
import com.AMS.model.Role;
import com.AMS.model.Status;
import com.AMS.repository.AppuserRepository;
import com.AMS.repository.CompanyRepository;
import com.AMS.repository.EmployeeRepository;
import com.AMS.repository.RoleRepository;
import com.AMS.repository.StatusRepository;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
    private ApplicationConfig config;
	
	@Autowired
	public AppuserRepository repo;

	@Autowired
	public CompanyRepository companyRepository;

	@Autowired
	public RoleRepository roleRepository;

	@Autowired
	private StatusRepository statusRepository;

	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public ModelAndView addHR(Appuser appuser, String cpass,HttpSession session) {
		ModelAndView model = new ModelAndView();
		int countError = 0;
		if (appuser.getFirstName().equals("") || appuser.getLastName().equals("") || appuser.getEmail().equals("")
				|| appuser.getMobile().equals("") || appuser.getAddress1().equals("")
				|| appuser.getAddress2().equals("") || appuser.getPincode().equals("") || appuser.getCity().equals("")
				|| appuser.getState().equals("") || appuser.getCountry().equals("")) {
			model.addObject("errors", "*All Filed Required");
			countError++;
		}
		if (!appuser.getPassword().equals(cpass)) {
			model.addObject("passwordError", "*Password and confirm password not match.");
			countError++;
		}
		if (countError > 0) {
			model.addObject("error", "1");
			model.addObject("data", appuser);
			model.addObject("cpass", cpass);
			model.setViewName("manageHR");
		} else {
			int id = (Integer) session.getAttribute("MY_SESSION_ID");
			Appuser appuser2=repo.getOne(id);
			Company company=companyRepository.getOne(appuser2.getCompany().getObjId());
			appuser.setCompany(company);
			Status status=statusRepository.getOne(1);
			appuser.setStatus(status);
			Role role = roleRepository.getOne(3);
			appuser.setRole(role);
			appuser.setProfilePhoto("profile.jpg");
			appuser.setPassword(SHA1_Encrypt.encryptThisString(appuser.getPassword()));
			repo.save(appuser);
			model.setViewName("redirect:manageHR");
		}
		return model;

	}

	@Override
	public void deleteHR(int id) {
		repo.deleteById(id);

	}

	@Override
	public List<Appuser> findAllHR(HttpSession session) {
		int id = (Integer) session.getAttribute("MY_SESSION_ID");
		Appuser appuser1 = repo.getOne(id);
		List<Appuser> appuser2 = repo.countHR(appuser1.getCompany().getObjId(), 3);
		return appuser2;
	}

	@Override
	public Appuser findByIdHR(int id) {
		Optional<Appuser> appuser = repo.findById(id);
		return appuser.get();
	}

	@Override
	public void updateHR(int id, String fname,String lname, String email, int sid, char optradio, String phone, String add1,
			String add2, String pinCode, String city, String state, String country) {
		Appuser appuser1 = repo.findById(id).orElse(null);
		appuser1.setFirstName(fname);
		appuser1.setLastName(lname);
		appuser1.setEmail(email);
		Status status = statusRepository.getOne(sid);
		appuser1.setStatus(status);
		appuser1.setGender(optradio);
		appuser1.setMobile(phone);
		appuser1.setAddress1(add1);
		appuser1.setAddress2(add2);
		appuser1.setPincode(pinCode);
		appuser1.setCity(city);
		appuser1.setState(state);
		appuser1.setCountry(country);
		repo.save(appuser1);
	}

	@Override
	public ModelAndView dataGet(int id, Model model) {
		ModelAndView mv = new ModelAndView();
		List<Appuser> appuser = repo.checkId(id);
		if (appuser.isEmpty()) {
			mv.setViewName("login");
		} else {
			for (Appuser appuser2 : appuser) {
				model.addAttribute("appuser1", appuser2);
				model.addAttribute("url",config.getUrl());
			}
			mv.setViewName("adminProfile");
		}
		return mv;
	}

	@Override
	public void adminProfileUpdate(int user_id, String fname, String lname, String email, String phone, String add1,
			String add2, String city, String state, String country) {
		Appuser newuser2 = repo.findById(user_id).orElse(null);
		newuser2.setFirstName(fname);
		newuser2.setLastName(lname);
		newuser2.setEmail(email);
		newuser2.setMobile(phone);
		newuser2.setAddress1(add1);
		newuser2.setAddress2(add2);
		newuser2.setCity(city);
		newuser2.setState(state);
		newuser2.setCountry(country);
		repo.save(newuser2);

	}

	@Override
	public ModelAndView changePasswordAdmin(int id, String oldPass, String newPass, String newCPass) {
		ModelAndView model = new ModelAndView();
		Appuser user = repo.findById(id).orElse(null);
		String password = user.getPassword();

		if (SHA1_Encrypt.encryptThisString(oldPass).equals(password)) {
			if (newPass.equals(newCPass)) {
				user.setPassword(SHA1_Encrypt.encryptThisString(newCPass));
				repo.save(user);
				model.addObject("success",1);
				model.setViewName("changePasswordAdmin");
			} else {
				model.addObject("error", "New password and confirm password not match.");
				model.addObject("oldPass", oldPass);
				model.addObject("newPass", newPass);
				model.addObject("newCPass", newCPass);
				model.setViewName("changePasswordAdmin");
			}
		} else {
			model.addObject("error", "Old Password is wrong.");
			model.addObject("oldPass", oldPass);
			model.addObject("newPass", newPass);
			model.addObject("newCPass", newCPass);
			model.setViewName("changePasswordAdmin");
		}
		return model;
	}

	@Override
	public ModelAndView companyDataGet(int Id, Model model) {
		ModelAndView mv = new ModelAndView();
		List<Appuser> companys = repo.checkId(Id);
		if (companys.isEmpty()) {
			mv.setViewName("login");
		} else {
			for (Appuser company2 : companys) {
				int companyid = company2.getCompany().getObjId();
				List<Company> com = companyRepository.checkCompanyId(companyid);
				for (Company company : com) {
					model.addAttribute("company1", company);
					model.addAttribute("url",config.getUrl());
				}
			}
			mv.setViewName("adminCompanyDetails");
		}
		return mv;
	}

	@Override
	public void updateCompanyData(int id, String companyname, String gstin, String mobile, String email, String add1,
			String add2, String city, String state, String pincode, String country) {
		Company c = companyRepository.findById(id).orElse(null);
		c.setName(companyname);
		c.setTaxId(gstin);
		c.setPhoneNumber(mobile);
		c.setCompanyEmail(email);
		c.setAddress1(add1);
		c.setAddress2(add2);
		c.setCity(city);
		c.setState(state);
		c.setPincode(pincode);
		c.setCountry(country);
		companyRepository.save(c);

	}

	@Override
	public void findNumberOfRecord(Model model,HttpSession session) {
		
		//HR
		int id = (Integer) session.getAttribute("MY_SESSION_ID");
		Appuser appuser1 = repo.getOne(id);
		List<Appuser> appuser2 = repo.countHR(appuser1.getCompany().getObjId(), 3);
		model.addAttribute("nofHR", appuser2.size());
		
		//employee
		List<Employee> emp = employeeRepository.countEmp(appuser1.getCompany().getObjId());
		model.addAttribute("nofEmp", emp.size());

	}

	@Override
	public boolean checkSession(HttpSession session) {
		if (session.getAttribute("MY_SESSION_ROLE") != null) {
			int session1 = (Integer) session.getAttribute("MY_SESSION_ROLE");
			if (session1 != 2) {
				return false;
			} else {
				return true;
			}
		} else {
			return false;
		}
	}
	
	@Override
	public List<Status> findStatusData() {
		List<Status> status=statusRepository.findAll();
		return status;
	}
	
	
	@Override
	public void saveImage(MultipartFile imageFile,HttpSession session,HttpServletRequest request) throws Exception {
		int session1 = (Integer) session.getAttribute("MY_SESSION_ID");
//		String folder= "/home/rumit/Data/profilePhoto";
		byte[] bytes = imageFile.getBytes();
		String ImageName=session1+"_profile.jpg";
		Path path = Paths.get(config.getPhotoPath(),ImageName);
		Files.write(path,bytes);
		Appuser appuser=repo.getOne(session1);
		appuser.setProfilePhoto(ImageName);
		repo.save(appuser);
	}
	
	@Override
	public void saveImageCompany(int cid,MultipartFile imageFile,HttpSession session,HttpServletRequest request) throws Exception {
		byte[] bytes = imageFile.getBytes();
		String ImageName=cid+"_Company.jpg";
		Path path = Paths.get(config.getPhotoPath(),ImageName);
		Files.write(path,bytes);
		Company company=companyRepository.getOne(cid);
		company.setCompanyPhoto(ImageName);
		companyRepository.save(company);
	}
	
	@Override
	public List<Employee> findAllEmployee(HttpSession session) {
		if (session.getAttribute("MY_SESSION_COMPANY_ID") == null) {
			return null;
		} else {
			int cId = (Integer) session.getAttribute("MY_SESSION_COMPANY_ID");
			List<Employee> emp = employeeRepository.countEmp(cId);
			return emp;
		}

	}
}
