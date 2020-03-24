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
import com.AMS.model.Role;
import com.AMS.model.Status;
import com.AMS.repository.AppuserRepository;
import com.AMS.repository.CompanyRepository;
import com.AMS.repository.RoleRepository;
import com.AMS.repository.StatusRepository;


@Service
public class SuperAdminServiceImpl implements SuperAdminService {

	@Autowired
    private ApplicationConfig config;
	
	@Autowired
	private AppuserRepository repo;

	@Autowired
	private CompanyRepository companyRepository;

	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private StatusRepository statusRepository;
	
	@Override
	public ModelAndView dataGet(String email) {
		ModelAndView model = new ModelAndView();
		List<Appuser> appuser = repo.checkEmail(email);
		if (appuser.isEmpty()) {
			model.setViewName("login");
		} else {
			for (Appuser user : appuser) {
				int id = user.getObjId();
				String fname = user.getFirstName();
				String lname = user.getLastName();
				String name = fname + " " + lname;
				String email1 = user.getEmail();
				String phone = user.getMobile();
				String add1 = user.getAddress1();
				String add2 = user.getAddress2();
				String address = add1 + ", " + add2;
				char gender = user.getGender();
				String city = user.getCity();
				String state = user.getState();
				String country = user.getCountry();
				String profilePhoto =user.getProfilePhoto();

				model.addObject("userId", id);
				model.addObject("name", name);
				model.addObject("fname", fname);
				model.addObject("lname", lname);
				model.addObject("email", email1);
				model.addObject("phone", phone);
				model.addObject("address", address);
				model.addObject("add1", add1);
				model.addObject("add2", add2);
				model.addObject("gender", gender);
				model.addObject("city", city);
				model.addObject("state", state);
				model.addObject("country", country);
				model.addObject("profilePhoto", profilePhoto);
				model.addObject("url",config.getUrl());

				model.setViewName("superAdminProfile");
			}
		}
		return model;
	}

	@Override
	public void superAdminProfileUpdate(int user_id, String fname, String lname, String email, String phone,
			String add1, String add2, String city, String state, String country) {

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
	public ModelAndView changePassword(int id, String oldPass, String newPass, String newCPass) {
		ModelAndView model = new ModelAndView();
		Appuser user = repo.findById(id).orElse(null);
		String password = user.getPassword();

		if (SHA1_Encrypt.encryptThisString(oldPass).equals(password)) {
			if (newPass.equals(newCPass)) {
				user.setPassword(SHA1_Encrypt.encryptThisString(newCPass));
				repo.save(user);
				model.addObject("success",1);
				model.setViewName("changePasswordSuperAdmin");
			} else {
				model.addObject("error", "New password and confirm password not match.");
				model.addObject("oldPass",oldPass);
				model.addObject("newPass",newPass);
				model.addObject("newCPass",newCPass);
				model.setViewName("changePasswordSuperAdmin");
			}
		} else {
			model.addObject("error", "Old Password is wrong.");
			model.addObject("oldPass",oldPass);
			model.addObject("newPass",newPass);
			model.addObject("newCPass",newCPass);
			model.setViewName("changePasswordSuperAdmin");
		}
		return model;
	}

	
	
	@Override
	public void findAllAdmin(Model model) {
		List<Appuser> appuser = repo.dataOfAdmin(2);
		model.addAttribute("admins",appuser);
		
	}

	
	
	@Override
	public Appuser findByIdAdmin(int id) {
		Optional<Appuser> appuser = repo.findById(id);
		return appuser.get();
	}

	@Override
	public void SAadminUpdate(int id, String fname, String lname, String email, int cName, char optradio, String phone,
			String add1, String add2, String pinCode, String city, String state, String country) {
		Appuser newuser2 = repo.findById(id).orElse(null);
		Company company2 = companyRepository.findByobjId(cName);
		newuser2.setFirstName(fname);
		newuser2.setLastName(lname);
		newuser2.setEmail(email);
		newuser2.setGender(optradio);
		newuser2.setCompany(company2);
		newuser2.setMobile(phone);
		newuser2.setAddress1(add1);
		newuser2.setAddress2(add2);
		newuser2.setPincode(pinCode);
		newuser2.setCity(city);
		newuser2.setState(state);
		newuser2.setCountry(country);
		repo.save(newuser2);

	}

	@Override
	public ModelAndView addAdminData(String cPass,int companyId, Appuser appuser,Model model1) {
		ModelAndView model = new ModelAndView();
		int countError = 0;
		List<Appuser> appuser1 = repo.dataOfAdmin(2);
		for (Appuser appuser2 : appuser1) {
			if(appuser2.getCompany().getObjId() == companyId)
			{
				model.addObject("companyError", "*Select Different Company");
				countError++;
			}
		}
		List<Appuser> appusers=repo.findAll();
		for (Appuser appuser2 : appusers) {
			if(appuser2.getEmail().equalsIgnoreCase(appuser.getEmail()))
			{
				model.addObject("emailError", "*Email already exists ");
				countError++;
			}
		}
		if (!appuser.getPassword().equals(cPass)) {
			model.addObject("passwordError", "*Password and confirm password not match.");
			countError++;
		}
		else if (companyId == 0) {
			model.addObject("companyError", "*Select company.");
			countError++;	
		}
		
		else if (appuser.getFirstName().equals("") || appuser.getLastName().equals("")
				|| appuser.getEmail().equals("") || appuser.getMobile().equals("") || appuser.getAddress1().equals("")
				|| appuser.getAddress2().equals("") || appuser.getPincode().equals("") || appuser.getCity().equals("")
				|| appuser.getState().equals("") || appuser.getCountry().equals("")) {
			model.addObject("Errors", "*All Field required");
			countError++;
		}
		if (countError > 0) {
			model.addObject("error", "1");
			model.addObject("data", appuser);
			model.addObject("cpass", cPass);
			List<Company> company= companyRepository.findCompany();
			model1.addAttribute("companyData",company);
			model.setViewName("saAdmin");
		} else {
			Company company=companyRepository.findByobjId(companyId);
			Status status=statusRepository.getOne(1);
			appuser.setStatus(status);
			Role role=roleRepository.getOne(2);
			appuser.setRole(role);
			appuser.setCompany(company);
			appuser.setProfilePhoto("profile.jpg");
			appuser.setPassword(SHA1_Encrypt.encryptThisString(appuser.getPassword()));
			repo.save(appuser);
			model.setViewName("redirect:saAdmin");
		}
		return model;
	}

	@Override
	public void deleteAdminData(int id) {
		List<Appuser> appuser=repo.CompanyAdminDataDelete(id);
		for (Appuser appuser2 : appuser) {
			repo.delete(appuser2);
		}
		//repo.deleteById(id);
	}

	@Override
	public List<Company> findAll1() {
		List<Company> compnaylist = (List<Company>) companyRepository.findAll();
		return compnaylist;
	}

	@Override
	public ModelAndView addCompany(Company company) {
		ModelAndView model = new ModelAndView();
		int countError = 0;
		if (company.getName().equals("") || company.getTaxId().equals("") || company.getCompanyEmail().equals("")
				|| company.getAddress1().equals("") || company.getAddress2().equals("")
				|| company.getPhoneNumber().equals("") || company.getCity().equals("") || company.getState().equals("")
				|| company.getPincode().equals("") || company.getCountry().equals(""))
				 {

			model.addObject("errors", "*All Required Field");
			countError++;
		}
		List<Company> company1=companyRepository.findAll();
		for (Company company2 : company1) {
			if(company2.getCompanyEmail().equalsIgnoreCase(company.getCompanyEmail()))
			{
				model.addObject("emailError", "*Email already exists ");
				countError++;
			}
		}
		if (countError > 0) {
			model.addObject("error", "1");
			model.addObject("data", company);
			model.setViewName("saCompany");
		} else {
			Status status=statusRepository.getOne(1);
			company.setStatus(status);
			company.setCompanyPhoto("profile.jpg");
			companyRepository.save(company);
			model.setViewName("redirect:saCompany");

		}
		return model;
	}

	@Override
	public void deleteCompanyData(int id) {
		
		List<Appuser> appuser=repo.CompanyAdminDataDelete(id);
		for (Appuser appuser2 : appuser) {
			repo.delete(appuser2);
		}
		companyRepository.deleteById(id);
	}

	@Override
	public Company findByIdCompany(int id) {
		Optional<Company> company = companyRepository.findById(id);
		return company.get();
	}

	@Override
	public void SAcompanyUpdate(int objId, String name, String taxId, String companyEmail, String address1,
			String address2, String phoneNumber, String city, String state, String pincode, String country,
			int status) {
		
		Company company2 = companyRepository.findByobjId(objId);
		company2.setName(name);
		company2.setTaxId(taxId);
		company2.setCompanyEmail(companyEmail);
		company2.setAddress1(address1);
		company2.setAddress2(address2);
		company2.setPhoneNumber(phoneNumber);
		company2.setCity(city);
		company2.setState(state);
		company2.setPincode(pincode);
		company2.setCountry(country);
		Status status1=statusRepository.getOne(status);
		company2.setStatus(status1);
		companyRepository.save(company2);

	}

	@Override
	public void SAcompanyUpdateDetails(int objId, String name, String companyEmail, String phoneNumber, String address1,
			String address2, String city, String state, String country) {
		Company company2 = companyRepository.findByobjId(objId);
		company2.setObjId(objId);
		company2.setName(name);
		company2.setCompanyEmail(companyEmail);
		company2.setPhoneNumber(phoneNumber);
		company2.setAddress1(address1);
		company2.setAddress2(address2);
		company2.setCity(city);
		company2.setState(state);
		company2.setCountry(country);
		companyRepository.save(company2);
	}

	@Override
	public List<Company> findCompanyData() {
		List<Company> company= companyRepository.findCompany();
		return company;
	}

	@Override
	public void findCompanyAdmin(int id,Model model) {
		List<Appuser> appuser = repo.dataOfCompanyAdmin(id);
		for (Appuser appuser2 : appuser) {
			if(appuser2.getRole().getObjId()==2)
			{
				model.addAttribute("detailsCompanyAdminFirstName",appuser2.getFirstName() +" "+appuser2.getLastName());
				model.addAttribute("detailsCompanyAdminMobile",appuser2.getMobile());
			}
		}
	}

	@Override
	public List<Status> findStatusData() {
		List<Status> status=statusRepository.findAll();
		return status;
	}

	@Override
	public void findNumberOfRecord(Model model) {
		
		long company=companyRepository.count();
		model.addAttribute("nofCompany",company);
		List<Appuser> appuser=repo.dataOfAdmin(2);
		model.addAttribute("nofAdmin",appuser.size());
		List<Appuser> appuser1=repo.dataOfAdmin(3);
		model.addAttribute("nofHR",appuser1.size());
		
	}
	
	@Override
	public boolean checkSession(HttpSession session) {
		if (session.getAttribute("MY_SESSION_ROLE") != null) {
			int session1 = (Integer) session.getAttribute("MY_SESSION_ROLE");
			if (session1 != 1) {
				return false;
			} else {
				return true;
			}
		} else {
			return false;
		}
	}
	
	//public static String uploadDirectory=System.getProperty("user.dir")+"/src/main/resources/static/uploads";

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
}
