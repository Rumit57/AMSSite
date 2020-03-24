package com.AMS.services;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.AMS.model.Appuser;
import com.AMS.model.Company;
import com.AMS.model.Status;


public interface SuperAdminService {

	//check session
	public boolean checkSession(HttpSession session);
	
	//session data
	public ModelAndView dataGet(String email);
	
	//number of records
	public void findNumberOfRecord(Model model);
	
	//update data 
	public void superAdminProfileUpdate(int user_id,String fname,String lname,String email,
			String phone,String add1,String add2,String city,String state,String country);
	
	
	//change password
	public ModelAndView changePassword(int id,String oldPass,String newPass,String newCPass);
	
	
	//find all admin
	public void findAllAdmin(Model model);
	
	//find Company Data
	public List<Company> findCompanyData();
	
	//find by id
	public Appuser findByIdAdmin(int id);
	
	//update Admin data 
	public void SAadminUpdate(int id,String fname,String lname,String email,int cName,char optradio,
				String phone,String add1,String add2,String pinCode,String city,String state,String country);
		
	//add Admin data 
	public ModelAndView addAdminData(String cPass,int companyId,Appuser appuser,Model model);
	
	//delete admin data
	public void deleteAdminData(int id);
	
	
	//View Company
	List<Company> findAll1();
	
	//add Company
	public ModelAndView addCompany(Company company);
	
	//delete company data
	public void deleteCompanyData(int id);
		
	//find by company id
	public Company findByIdCompany(int id);
	
	//find company admin
	public void findCompanyAdmin(int id,Model model);
	
	//update company data 
	public void SAcompanyUpdate(int objId, String name, String taxId, String companyEmail, String address1,
			String address2, String phoneNumber, String city, String state, String pincode, String country,
			int status);
	
	//update company details
	public void SAcompanyUpdateDetails(int objId, String name, String companyEmail, String phoneNumber, String address1,
			String address2, String city, String state, String country);
		
	//Status Data
	public List<Status> findStatusData();

	//upload image SA
	public void saveImage(MultipartFile imageFile,HttpSession session,HttpServletRequest request) throws Exception;
	
	//upload image Company
	public void saveImageCompany(int cid,MultipartFile imageFile,HttpSession session,HttpServletRequest request) throws Exception;
}
