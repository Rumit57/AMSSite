package com.AMS.services;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.AMS.model.Appuser;
import com.AMS.model.Employee;
import com.AMS.model.Status;

public interface AdminService {

	// number of records
	public void findNumberOfRecord(Model model, HttpSession session);

	// check session
	public boolean checkSession(HttpSession session);

	// Admin data
	public ModelAndView dataGet(int id, Model model);

	// update Admin Profile data
	public void adminProfileUpdate(int user_id, String fname, String lname, String email, String phone, String add1,
			String add2, String city, String state, String country);

	// change password
	public ModelAndView changePasswordAdmin(int id, String oldPass, String newPass, String newCPass);

	// Company data
	public ModelAndView companyDataGet(int Id, Model model);

	// Company Data Update
	public void updateCompanyData(int id, String companyname, String gstin, String mobile, String email, String add1,
			String add2, String city, String state, String pincode, String country);

	// find all HR
	public List<Appuser> findAllHR(HttpSession session);

	// find by HR id Wise
	public Appuser findByIdHR(int id);

	// AddHR
	public ModelAndView addHR(Appuser appuser, String cpass, HttpSession session);

	// UpdateHR
	public void updateHR(int id, String fname, String lname, String email, int sid, char optradio, String phone,
			String add1, String add2, String pinCode, String city, String state, String country);

	// DeleteHR
	public void deleteHR(int id);

	// Status Data
	public List<Status> findStatusData();

	// upload image Admin
	public void saveImage(MultipartFile imageFile, HttpSession session, HttpServletRequest request) throws Exception;

	// upload image Company
	public void saveImageCompany(int cid, MultipartFile imageFile, HttpSession session, HttpServletRequest request)
			throws Exception;
	
	// list of all employee
	public List<Employee> findAllEmployee(HttpSession session);
}
