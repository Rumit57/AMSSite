package com.AMS.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import com.AMS.model.Employee;
import com.AMS.model.Leave;
import com.AMS.model.MissedPunch;
import com.AMS.services.HRService;

@Controller
public class HRController {

	@Autowired
	private HRService hrService;

	// header
	@RequestMapping("hrProfile")
	public ModelAndView hrProfile(HttpSession session) {
		ModelAndView model = new ModelAndView();
		if (!hrService.checkSession(session)) {
			model.setViewName("login");
		} else {
			String session1 = (String) session.getAttribute("MY_SESSION_EMAIL");
			model = hrService.dataGet(session1);
			model.setViewName("hrProfile");
		}
		return model;
	}

	// update profile
	@RequestMapping("hrProfileUpdate")
	public String Super_Admin_Profile_Update(int user_id, String fname, String lname, String email, String phone,
			String add1, String add2, String city, String state, String country, HttpServletRequest request,
			HttpSession session) {
		if (!hrService.checkSession(session)) {
			return "pageNotFound";
		} else {
			// save
			hrService.hrProfileUpdate(user_id, fname, lname, email, phone, add1, add2, city, state, country, session,
					request);
			return "redirect:hrProfile";
		}

	}

	// change password
	@RequestMapping("changePasswordHR")
	public String changePasswordHR(HttpSession session) {
		if (!hrService.checkSession(session)) {
			return "login";
		} else {
			return "changePasswordHR";
		}
	}

	@RequestMapping("changePasswordSubmitHR")
	public ModelAndView changePasswordSA(String oldPass, String newPass, String newCPass, HttpSession session) {
		ModelAndView model = new ModelAndView();
		if (!hrService.checkSession(session)) {
			model.setViewName("login");
		} else {
			int id = (Integer) session.getAttribute("MY_SESSION_ID");
			model = hrService.changePassword(id, oldPass, newPass, newCPass);
		}
		return model;
	}

	// dashboard action
	@RequestMapping("hrDashboard")
	public ModelAndView hrDashboard(HttpSession session, Model model1) {
		ModelAndView model = new ModelAndView();

		if (!hrService.checkSession(session)) {
			model.setViewName("login");
		} else {
			String session1 = (String) session.getAttribute("MY_SESSION_NAME");
			model.addObject("session", session1);
			hrService.findNumberOfRecord(model1, session);
			List<Employee> todayLateComing = hrService.findTodayLateComingData(model1, session);
			model1.addAttribute("todayLateComing", todayLateComing.size());
			List<Employee> yesterdayLateComing = hrService.findYesterdayLateComingData(model1, session);
			model1.addAttribute("yesterdayLateComing", yesterdayLateComing.size());
			List<Employee> findYesterdayLateStayingData = hrService.findYesterdayLateStayingData(model1, session);
			model1.addAttribute("yesterdayLateStaying", findYesterdayLateStayingData.size());
			int data = hrService.findTodayEarlyLeavingData(model1, session);
			model1.addAttribute("todayEarlyLeaving", data);
			int data1 = hrService.findYesterdayEarlyLeavingData(model1, session);
			model1.addAttribute("yesterdayEarlyLeaving", data1);
			hrService.findYesterdayMissedPunchData(model1, session);
			model.setViewName("hrDashboard");
		}
		return model;
	}

	// manage employee
	@RequestMapping("manageEmployee")
	public String manageEmployee(HttpSession session, Model model) {
		if (!hrService.checkSession(session)) {
			return "login";
		} else {
			model.addAttribute("employees", hrService.findAllEmployee(session));
			return "manageEmployee";
		}
	}

	// live activity
	@RequestMapping("liveActivity")
	public String liveActivity(Model model, HttpSession session) {
		if (!hrService.checkSession(session)) {
			return "login";
		} else {
			hrService.LiveDataGet(model, session);
			return "liveActivity";
		}
	}

	// live activity refresh
	@RequestMapping(value = "refresh", method = RequestMethod.POST)
	public @ResponseBody void refresh(Model model, HttpSession session) {
		hrService.findTodayAbsentData(model, session);
		// return hrService.tableDataRefresh();
	}

	// report1
	@RequestMapping(value = "report1")
	public String report1(Model model, HttpSession session) {
		hrService.report1(model, session);
		model.addAttribute("employees", hrService.findAllEmployee(session));
		return "report1";
	}

	// reportrange1
	@RequestMapping(value = "filter1")
	public String reportrange1(String month1, String year, String employeeId, String dateRange, Model model,
			HttpSession session) {
		hrService.workingHourRange(year, month1, employeeId, dateRange, model, session);
		model.addAttribute("employees", hrService.findAllEmployee(session));
		model.addAttribute("filter", "1");
		return "report1";
	}

	// report2
	@RequestMapping(value = "report2")
	public String report2(Model model, HttpSession session) {
		model.addAttribute("employees", hrService.findAllEmployee(session));
		model.addAttribute("reportData", hrService.findYesterdayLateComingData(model, session));
		return "report2";
	}

	// reportrange2
	@RequestMapping(value = "filter2")
	public String reportrange2(String month1, String year, String employeeId, String dateRange, Model model,
			HttpSession session) {
		hrService.lateComingRange(year, month1, employeeId, dateRange, model, session);
		model.addAttribute("employees", hrService.findAllEmployee(session));
		model.addAttribute("filter", "1");
		return "report2";
	}

	// report3
	@RequestMapping(value = "report3")
	public String report3(Model model, HttpSession session) {
		model.addAttribute("employees", hrService.findAllEmployee(session));
		model.addAttribute("reportData", hrService.findYesterdayLateStayingData(model, session));
		return "report3";
	}

	// reportrange3
	@RequestMapping(value = "filter3")
	public String reportrange3(String month1, String year, String employeeId, String dateRange, Model model,
			HttpSession session) {
		hrService.lateStayingRange(year, month1, employeeId, dateRange, model, session);
		model.addAttribute("employees", hrService.findAllEmployee(session));
		model.addAttribute("filter", "1");
		return "report3";
	}

	// report4
	@RequestMapping(value = "report4")
	public String report4(Model model, HttpSession session) {
		model.addAttribute("employees", hrService.findAllEmployee(session));
		model.addAttribute("reportData", hrService.findFixedMissPunchData(model, session));
		return "report4";
	}

	// reportrange4
	@RequestMapping(value = "filter4")
	public String reportrange4(String month1, String year, String employeeId, String dateRange, Model model,
			HttpSession session) {
		hrService.fixedMissPunchRange(year, month1, employeeId, dateRange, model, session);
		model.addAttribute("employees", hrService.findAllEmployee(session));
		model.addAttribute("filter", "1");
		return "report4";
	}

	// Reports
	@RequestMapping("reports")
	public String reports(Model model, HttpSession session) {
		if (!hrService.checkSession(session)) {
			return "login";
		} else {
			return "reports";
		}
	}

	// today_present
	@RequestMapping("todayPresent")
	public String todayPresent(Model model, HttpSession session) {
		if (!hrService.checkSession(session)) {
			return "login";
		} else {
			hrService.findTodayPresentData(0, model, session);
			return "todayPresent";
		}
	}

	// today present Status
	@RequestMapping("todayPresentStatus")
	public String todayPresentStatus(int id, Model model, HttpSession session) {
		if (!hrService.checkSession(session)) {
			return "login";
		} else {
			model.addAttribute("showStatus", 1);
			hrService.findTodayPresentData(id, model, session);
			return "todayPresent";
		}
	}

	// today_absent
	@RequestMapping("todayAbsent")
	public String todayAbsent(Model model, HttpSession session) {
		if (!hrService.checkSession(session)) {
			return "login";
		} else {
			hrService.findTodayAbsentData(model, session);
			return "todayAbsent";
		}
	}

	// today_late_coming
	@RequestMapping("todayLateComing")
	public String todayLateComing(Model model, HttpSession session) {
		if (!hrService.checkSession(session)) {
			return "login";
		} else {

			model.addAttribute("employeePresent", hrService.findTodayLateComingData(model, session));
			return "todayLateComing";
		}
	}

	// today_early_leaving
	@RequestMapping("todayEarlyLeaving")
	public String todayEarlyLeaving(Model model, HttpSession session) {
		if (!hrService.checkSession(session)) {
			return "login";
		} else {
			hrService.findTodayEarlyLeavingData(model, session);
			model.addAttribute("employeeData", hrService.findEmployeeData(session));
			return "todayEarlyLeaving";
		}
	}

	// add Today Early Leaving Data
	@RequestMapping("addTodayEarlyLeaving")
	public String addTodayEarlyLeaving(int employeeId, Leave leave, Model model, HttpSession session) {
		if (!hrService.checkSession(session)) {
			return "login";
		} else {
			model.addAttribute("employeeData", hrService.findEmployeeData(session));
			if (hrService.addTodayEarlyLeaving(employeeId, leave, model, session)) {
				return "redirect:todayEarlyLeaving";
			} else {
				return "pageNotFound";
			}

		}
	}

	// yesterday_present
	@RequestMapping("yesterdayPresent")
	public String yesterdayPresent(Model model, HttpSession session) {
		if (!hrService.checkSession(session)) {
			return "login";
		} else {
			hrService.findYesterdayPresentData(0, model, session);
			return "yesterdayPresent";
		}
	}

	// yesterday present Status
	@RequestMapping("yesterdayPresentStatus")
	public String yesterdayPresentStatus(int id, Model model, HttpSession session) {
		if (!hrService.checkSession(session)) {
			return "login";
		} else {
			model.addAttribute("showStatus", 1);
			hrService.findYesterdayPresentData(id, model, session);
			return "yesterdayPresent";
		}
	}

	// yesterday_absent
	@RequestMapping("yesterdayAbsent")
	public String yesterdayAbsent(Model model, HttpSession session) {
		if (!hrService.checkSession(session)) {
			return "login";
		} else {
			hrService.findYesterdayAbsentData(model, session);
			return "yesterdayAbsent";
		}
	}

	// yesterday_late_coming
	@RequestMapping("yesterdayLateComing")
	public String yesterdayLateComing(Model model, HttpSession session) {
		if (!hrService.checkSession(session)) {
			return "login";
		} else {
			model.addAttribute("employeePresent", hrService.findYesterdayLateComingData(model, session));
			return "yesterdayLateComing";
		}
	}

	// yesterday_late_staying
	@RequestMapping("yesterdayLateStaying")
	public String yesterdayLateStaying(Model model, HttpSession session) {
		if (!hrService.checkSession(session)) {
			return "login";
		} else {
			model.addAttribute("employeePresent", hrService.findYesterdayLateStayingData(model, session));
			return "yesterdayLateStaying";
		}
	}

	// yesterday_missed_punch
	@RequestMapping("yesterdayMissedPunch")
	public String yesterdayMissedPunch(Model model, HttpSession session) {
		if (!hrService.checkSession(session)) {
			return "login";
		} else {
			hrService.findYesterdayMissedPunchData(model, session);
			return "yesterdayMissedPunch";
		}
	}

	// yesterday Missed punch fix
	@RequestMapping("yesterdayMissedPunchFix")
	public String yesterdayMissedPunchFix(int id, Model model, HttpSession session) {
		if (!hrService.checkSession(session)) {
			return "login";
		} else {
			hrService.findYesterdayMissedPunchData(model, session);
			model.addAttribute("fix", 1);
			model.addAttribute("eid", id);
			return "yesterdayMissedPunch";
		}
	}

	// yesterday Missed punch fix submit
	@RequestMapping("yesterdayMissedPunchFixSubmit")
	public String yesterdayMissedPunchFixSubmit(int employee, String punchTime, MissedPunch missedPunch, Model model,
			HttpSession session) {
		if (!hrService.checkSession(session)) {
			return "login";
		} else {
			if (hrService.addYesterdayMissedPunchRecord(employee, punchTime, missedPunch, model, session)) {
				return "redirect:yesterdayMissedPunch";
			} else {
				return "pageNotFound";
			}
		}
	}

	// yesterday_early_leaving
	@RequestMapping("yesterdayEarlyLeaving")
	public String yesterdayEarlyLeaving(Model model, HttpSession session) {
		if (!hrService.checkSession(session)) {
			return "login";
		} else {
			hrService.findYesterdayEarlyLeavingData(model, session);
			return "yesterdayEarlyLeaving";
		}
	}

	// yesterday Reason Add
	@RequestMapping("yesterdayReasonAdd")
	public String yesterdayReasonAdd(int id, Model model, HttpSession session) {
		if (!hrService.checkSession(session)) {
			return "login";
		} else {
			hrService.findYesterdayEarlyLeavingData(model, session);
			model.addAttribute("reason", 1);
			model.addAttribute("eid", id);
			return "yesterdayEarlyLeaving";
		}
	}

	// yesterday Reason Add Submit
	@RequestMapping("yesterdayReasonAddSubmit")
	public String yesterdayReasonAddSubmit(int employee, Leave leave, Model model, HttpSession session) {
		if (!hrService.checkSession(session)) {
			return "login";
		} else {
			if (hrService.addYesterdayEarlyLeaving(employee, leave, model, session)) {
				return "redirect:yesterdayEarlyLeaving";
			} else {
				return "pageNotFound";
			}
		}
	}

	// upload image admin
	@RequestMapping("updatePhotoHR")
	public String uploadImage(MultipartFile imageFile, HttpSession session, HttpServletRequest request) {
		try {
			hrService.saveImage(imageFile, session, request);
		} catch (Exception e) {
			System.out.println(e);
		}
		return "redirect:hrProfile";
	}

	// add employee
	@RequestMapping("addEmployee")
	public ModelAndView addEmployee(HttpSession session, Employee employee, Model model1) {
		ModelAndView model = new ModelAndView();
		if (!hrService.checkSession(session)) {
			model.setViewName("login");
		} else {
			model = hrService.addEmployee(employee, session);
			model1.addAttribute("employees", hrService.findAllEmployee(session));
		}
		return model;
	}

	// edit employee
	@RequestMapping("editEmployee")
	public String editEmployee(Model model, HttpSession session, int id) {
		if (!hrService.checkSession(session)) {
			return "login";
		} else {
			Employee emp = hrService.findByIdEmployee(id);
			char gender = emp.getGender();
			if (gender == 'M') {
				model.addAttribute("gender", 1);
			} else {
				model.addAttribute("gender", 2);
			}
			model.addAttribute("editEmployee", emp);
			model.addAttribute("editemployee", 1);
			model.addAttribute("employees", hrService.findAllEmployee(session));

			return "manageEmployee";
		}

	}

	// Update employee
	@RequestMapping("updateEmployee")
	public String updateHR(int id, String fname, String lname, String email, char optradio, String userType,
			String phone, String add1, String add2, String pincode, String city, String state, String country,
			Model model, HttpSession session) {
		if (!hrService.checkSession(session)) {
			return "login";
		} else {
			hrService.updateEmp(id, fname, lname, add1, optradio, userType, add2, email, pincode, phone, city, state,
					country);
			return "redirect:manageEmployee";
		}
	}

	// delete employee
	@RequestMapping("deleteEmployee")
	public String deletHR(int delete, HttpSession session, Model model) {
		if (!hrService.checkSession(session)) {
			return "login";
		} else {
			hrService.deleteEmployee(delete);
			return "redirect:manageEmployee";
		}
	}

	// import
	@RequestMapping("importData1")
	public String setdata(MultipartFile file1, HttpSession session, Model model) throws Exception {
		if (!hrService.checkSession(session)) {
			return "login";
		} else {
			hrService.storeFile(file1, session);
			hrService.importEmpData(session);

			return "redirect:manageEmployee";
		}
	}

	// missed punch Data
	@RequestMapping("missedPunchHR")
	public String missedPunchData(Model model, HttpSession session) {
		if (!hrService.checkSession(session)) {
			return "login";
		} else {
			hrService.findMissedPunchData(model, session);
			return "missedPunchHR";
		}
	}
}
