package com.AMS.services;

import java.nio.channels.SeekableByteChannel;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import com.AMS.model.Employee;
import com.AMS.model.Leave;
import com.AMS.model.MissedPunch;

public interface HRService {

	// check session
	public boolean checkSession(HttpSession session);

	// session data
	public ModelAndView dataGet(String email);

	// update data
	public void hrProfileUpdate(int user_id, String fname, String lname, String email, String phone, String add1,
			String add2, String city, String state, String country, HttpSession session, HttpServletRequest request);

	// change password
	public ModelAndView changePassword(int id, String oldPass, String newPass, String newCPass);

	// upload image HR
	public void saveImage(MultipartFile imageFile, HttpSession session, HttpServletRequest request) throws Exception;

	// add employee
	public ModelAndView addEmployee(Employee employee, HttpSession session);

	// list of all employee
	public List<Employee> findAllEmployee(HttpSession session);

	// find by employee id wise
	public Employee findByIdEmployee(int id);

	// update employee
	public void updateEmp(int id, String fname, String lname, String add1, char optradio, String userType, String add2,
			String email, String pincode, String phone, String city, String state, String country);

	// Delete Employee
	public void deleteEmployee(int id);

	// live activity data
	public void LiveDataGet(Model model, HttpSession session);

	// refresh
//	public String tableDataRefresh();

	// import Employee data
	public void importEmpData(HttpSession session);

	// number of records
	public void findNumberOfRecord(Model model, HttpSession session);

	// find Today Present Data
	public void findTodayPresentData(int id, Model model, HttpSession session);

	// find Today Absent Data
	public int findTodayAbsentData(Model model, HttpSession session);

	// find Today Yesterday Data
	public void findYesterdayPresentData(int id, Model model, HttpSession session);

	// find Yesterday Absent Data
	public void findYesterdayAbsentData(Model model, HttpSession session);

	// find Today Late coming Data
	public List<Employee> findTodayLateComingData(Model model, HttpSession session);

	// find Today Early Leaving Data
	public int findTodayEarlyLeavingData(Model model, HttpSession session);

	// find Yesterday Late coming Data
	public List<Employee> findYesterdayLateComingData(Model model, HttpSession session);

	// find Yesterday Late Staying Data
	public List<Employee> findYesterdayLateStayingData(Model model, HttpSession session);

	// find Yesterday Early Leaving Data
	public int findYesterdayEarlyLeavingData(Model model, HttpSession session);

	// find Yesterday Missed Punch Data
	public void findYesterdayMissedPunchData(Model model, HttpSession session);

	// find Employee Data
	public List<Employee> findEmployeeData(HttpSession session);

	// add Today Early Leaving
	public boolean addTodayEarlyLeaving(int employeeId, Leave leave, Model model, HttpSession session);

	// add Yesterday Early Leaving
	public boolean addYesterdayEarlyLeaving(int employee, Leave leave, Model model, HttpSession session);

	// add Yesterday Missed Punch record
	public boolean addYesterdayMissedPunchRecord(int employee, String punchTime, MissedPunch missedPunch, Model model,
			HttpSession session);

	// find Data report1
	public void report1(Model model, HttpSession session);

	// Range wise data get Report1
	public void workingHourRange(String year, String month1, String employeeId, String dateRange, Model model,
			HttpSession session);

	// Range wise data get Report2
	public void lateComingRange(String year, String month1, String employeeId, String dateRange, Model model,
			HttpSession session);

	// Range wise data get Report3
	public void lateStayingRange(String year, String month1, String employeeId, String dateRange, Model model,
			HttpSession session);

	// import employee Data
	public void storeFile(MultipartFile file, HttpSession session) throws Exception;

	// find Fixed missed punch Data
	public List<MissedPunch> findFixedMissPunchData(Model model, HttpSession session);

	// Range wise data get Report4
	public void fixedMissPunchRange(String year, String month1, String employeeId, String dateRange, Model model,
			HttpSession session);
	
	// find Missed Punch Data
	public void findMissedPunchData(Model model, HttpSession session);
}
