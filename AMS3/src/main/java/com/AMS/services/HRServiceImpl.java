package com.AMS.services;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.AMS.configs.ApplicationConfig;
import com.AMS.model.Appuser;
import com.AMS.model.Company;
import com.AMS.model.Employee;
import com.AMS.model.Leave;
import com.AMS.model.MissedPunch;
import com.AMS.model.PunchLogs;
import com.AMS.model.PunchMachine;
import com.AMS.model.Report;
import com.AMS.model.Status;
import com.AMS.repository.AppuserRepository;
import com.AMS.repository.CompanyRepository;
import com.AMS.repository.EmployeeRepository;
import com.AMS.repository.LeaveRepository;
import com.AMS.repository.MissedPunchRepository;
import com.AMS.repository.PunchLogsRepository;
import com.AMS.repository.PunchMachineRepository;
import com.AMS.repository.StatusRepository;

@Service
public class HRServiceImpl implements HRService {

	@Autowired
	private ApplicationConfig config;

	@Autowired
	private AppuserRepository repo;

	@Autowired
	private CompanyRepository companyRepository;

	@Autowired
	private PunchLogsRepository punchLogsRepository;

	@Autowired
	private StatusRepository statusRepository;

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private LeaveRepository leaveRepository;

	@Autowired
	private MissedPunchRepository missedPunchRepository;

	@Autowired
	private PunchMachineRepository machineRepository;

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
				String profilePhoto = user.getProfilePhoto();

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
				model.addObject("url", config.getUrl());

				model.setViewName("hrProfile");
			}
		}
		return model;
	}

	@Override
	public void hrProfileUpdate(int user_id, String fname, String lname, String email, String phone, String add1,
			String add2, String city, String state, String country, HttpSession session, HttpServletRequest request) {

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
//		request.getSession().invalidate();
		request.getSession().setAttribute("MY_SESSION_EMAIL", email);
		request.getSession().setAttribute("MY_SESSION_ID", user_id);
		request.getSession().setAttribute("MY_SESSION_ROLE", 3);
		request.getSession().setAttribute("MY_SESSION_NAME", fname + " " + lname);
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
				model.addObject("success", 1);
				model.setViewName("changePasswordHR");
			} else {
				model.addObject("error", "New password and confirm password not match.");
				model.addObject("oldPass", oldPass);
				model.addObject("newPass", newPass);
				model.addObject("newCPass", newCPass);
				model.setViewName("changePasswordHR");
			}
		} else {
			model.addObject("error", "Old Password is wrong.");
			model.addObject("oldPass", oldPass);
			model.addObject("newPass", newPass);
			model.addObject("newCPass", newCPass);
			model.setViewName("changePasswordHR");
		}
		return model;
	}

	@Override
	public boolean checkSession(HttpSession session) {
		if (session.getAttribute("MY_SESSION_ROLE") != null) {
			int session1 = (Integer) session.getAttribute("MY_SESSION_ROLE");
			if (session1 != 3) {
				return false;
			} else {
				return true;
			}
		} else {
			return false;
		}
	}

	@Override
	public void saveImage(MultipartFile imageFile, HttpSession session, HttpServletRequest request) throws Exception {
		int session1 = (Integer) session.getAttribute("MY_SESSION_ID");
		byte[] bytes = imageFile.getBytes();
		String ImageName = session1 + "_profile.jpg";
		Path path = Paths.get(config.getPhotoPath(), ImageName);
		Files.write(path, bytes);
		Appuser appuser = repo.getOne(session1);
		appuser.setProfilePhoto(ImageName);
		repo.save(appuser);
	}

	@Override
	public ModelAndView addEmployee(Employee employee, HttpSession session) {
		ModelAndView mv = new ModelAndView();

		int countError = 0;
		if (employee.getFirstName().equals("") || employee.getLastName().equals("") || employee.getEmail().equals("")
				|| employee.getMobile().equals("") || employee.getAddress1().equals("")
				|| employee.getAddress2().equals("") || employee.getPincode().equals("")
				|| employee.getCity().equals("") || employee.getCountry().equals("")
				|| employee.getUserType().equals("")) {
			mv.addObject("errors", "*All Filed Required");
			countError++;
		}
		if (countError > 0) {
			mv.addObject("error", "1");
			mv.addObject("data", employee);
			mv.setViewName("manageEmployee");
		} else {
			int session1 = (Integer) session.getAttribute("MY_SESSION_ID");
			Appuser appuser = repo.getOne(session1);
			Company company = companyRepository.findByobjId(appuser.getCompany().getObjId());
			Status status = statusRepository.getOne(1);
			employee.setStatus(status);
			employee.setCompany(company);
			employeeRepository.save(employee);
			mv.setViewName("redirect:manageEmployee");
		}
		return mv;
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

	@Override
	public Employee findByIdEmployee(int id) {
		Optional<Employee> emp = employeeRepository.findById(id);
		return emp.get();
	}

	@Override
	public void updateEmp(int id, String fname, String lname, String add1, char optradio, String userType, String add2,
			String email, String pincode, String phone, String city, String state, String country) {
		Employee emp = employeeRepository.findById(id).orElse(null);
		emp.setFirstName(fname);
		emp.setLastName(lname);
		emp.setEmail(email);
		emp.setGender(optradio);
		emp.setUserType(userType);
		emp.setMobile(phone);
		emp.setAddress1(add1);
		emp.setAddress2(add2);
		emp.setPincode(pincode);
		emp.setCity(city);
		emp.setState(state);
		emp.setCountry(country);
		employeeRepository.save(emp);

	}

	@Override
	public void deleteEmployee(int id) {
		employeeRepository.deleteById(id);

	}

	@Override
	public void LiveDataGet(Model model, HttpSession session) {
		if (session.getAttribute("MY_SESSION_COMPANY_ID") == null) {

		} else {
			int cId = (Integer) session.getAttribute("MY_SESSION_COMPANY_ID");
			Date date = Calendar.getInstance().getTime();
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String strDate = dateFormat.format(date);
			Timestamp todayTimestamp1 = Timestamp.valueOf(strDate + " 00:00:00");
			Timestamp todayTimestamp2 = Timestamp.valueOf(strDate + " 23:59:59");
			List<PunchLogs> list = punchLogsRepository.liveActivityDataGet(cId, todayTimestamp1, todayTimestamp2);
			HashMap<Integer, Integer> idsList = new HashMap<Integer, Integer>();
			for (PunchLogs punchLogs : list) {
				idsList.put(punchLogs.getEmployee().getObjId(), punchLogs.getObjId());
			}
			final Iterator<Entry<Integer, Integer>> iter = idsList.entrySet().iterator();
			final HashSet<Integer> valueSet = new HashSet<Integer>();
			while (iter.hasNext()) {
				final Entry<Integer, Integer> next = iter.next();
				if (!valueSet.add(next.getValue())) {
					iter.remove();
				}
			}
			List<PunchLogs> punchLogs2 = punchLogsRepository.findAllById(valueSet);
			for (PunchLogs punchLogs : punchLogs2) {
				List<PunchLogs> punchLogs3 = punchLogsRepository.findByEmpId(punchLogs.getEmployee().getObjId(),
						todayTimestamp1, todayTimestamp2);
				int size1 = punchLogs3.size();
				if (size1 % 2 == 0) {
					punchLogs.setStatus("OUT");
				} else {
					punchLogs.setStatus("IN");
				}
			}
			// model.addAttribute("count", valueSet);
			model.addAttribute("employees", punchLogs2);
		}

	}

	@Override
	public void importEmpData(HttpSession session) {
		if (session.getAttribute("MY_SESSION_COMPANY_ID") == null) {
		} else {
			int cId = (Integer) session.getAttribute("MY_SESSION_COMPANY_ID");
			String line = "";
			int count = 0;
			String url = config.getImportUrl() + cId + "_data.csv";
			BufferedReader br = null;
			try {
				URL oracle = new URL(url);
				URLConnection yc = oracle.openConnection();
				br = new BufferedReader(new InputStreamReader(yc.getInputStream()));
				br.readLine();
				while ((line = br.readLine()) != null) {
					String[] data = line.split(",");
					Employee e = new Employee();
					int eid = 0;
					List<Employee> employees = employeeRepository.checkEmail(data[2]);
					for (Employee employee : employees) {

						if (employee.getEmail().equalsIgnoreCase(data[2])) {
							count = count + 2;
							eid = employee.getObjId();
						}

					}
					if (count > 0) {
						Employee e1 = employeeRepository.getOne(eid);
						if (data[0].equalsIgnoreCase(""))
							data[0] = e1.getFirstName();
						if (data[1].equalsIgnoreCase(""))
							data[1] = e1.getLastName();
						if (data[3].equalsIgnoreCase(""))
							data[3] = e1.getMobile();
						if (data[4].equalsIgnoreCase(""))
							data[4] = Character.toString(e1.getGender());
						if (data[5].equalsIgnoreCase(""))
							data[5] = e1.getUserType();
						if (data[6].equalsIgnoreCase(""))
							data[6] = e1.getAddress1();
						if (data[7].equalsIgnoreCase(""))
							data[7] = e1.getAddress2();
						if (data[8].equalsIgnoreCase(""))
							data[8] = e1.getPincode();
						if (data[9].equalsIgnoreCase(""))
							data[9] = e1.getCity();
						if (data[10].equalsIgnoreCase(""))
							data[10] = e1.getState();
						if (data[11].equalsIgnoreCase(""))
							data[11] = e1.getCountry();
						employeeRepository.updateEmp(data[0], data[1], data[3], data[4].charAt(0), data[5], data[6],
								data[7], data[8], data[9], data[10], data[11], e1.getObjId());

					} else {
						e.setFirstName(data[0]);
						e.setLastName(data[1]);
						e.setEmail(data[2]);
						e.setMobile(data[3]);
						if (data[4].equalsIgnoreCase("M")) {
							e.setGender('M');
						} else if (data[4].equalsIgnoreCase("F")) {
							e.setGender('F');
						}

						if (data[5].equalsIgnoreCase("Employee")) {
							e.setUserType("Employee");
						} else if (data[5].equalsIgnoreCase("Intern")) {
							e.setUserType("Intern");
						}
						e.setAddress1(data[6]);
						e.setAddress2(data[7]);
						e.setPincode(data[8]);
						e.setCity(data[9]);
						e.setState(data[10]);
						e.setCountry(data[11]);

						Company c = companyRepository.getOne(cId);
						e.setCompany(c);
						Status status = statusRepository.getOne(1);
						e.setStatus(status);
						employeeRepository.save(e);
					}
					line = "";
					count = 0;
				}
				br.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}

	private Date yesterday(Date date) {
		final Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -1);
		return cal.getTime();
	}

	// Date and timestamp pass and get data
	public HashMap<Integer, Integer> checkdateToday(int cId) {

		Date date = Calendar.getInstance().getTime();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String strDate = dateFormat.format(date);
		Timestamp todayTimestamp1 = Timestamp.valueOf(strDate + " 00:00:00");
		Timestamp todayTimestamp2 = Timestamp.valueOf(strDate + " 23:59:59");
		List<PunchLogs> list = punchLogsRepository.liveActivityDataGet(cId, todayTimestamp1, todayTimestamp2);
		HashMap<Integer, Integer> idsList = new HashMap<Integer, Integer>();
		for (PunchLogs punchLogs : list) {
			idsList.put(punchLogs.getEmployee().getObjId(), punchLogs.getObjId());
		}
		final Iterator<Entry<Integer, Integer>> iter = idsList.entrySet().iterator();
		final HashSet<Integer> valueSet = new HashSet<Integer>();
		while (iter.hasNext()) {
			final Entry<Integer, Integer> next = iter.next();
			if (!valueSet.add(next.getValue())) {
				iter.remove();
			}
		}
		return idsList;
	}

	// Date and timestamp pass and get data Yesterday
	public HashMap<Integer, Integer> checkDateYesterday(int cId) {

		Date date = Calendar.getInstance().getTime();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String strDate = dateFormat.format(yesterday(date));
		Timestamp todayTimestamp1 = Timestamp.valueOf(strDate + " 00:00:00");
		Timestamp todayTimestamp2 = Timestamp.valueOf(strDate + " 23:59:59");
		List<PunchLogs> list = punchLogsRepository.liveActivityDataGet(cId, todayTimestamp1, todayTimestamp2);
		HashMap<Integer, Integer> idsList = new HashMap<Integer, Integer>();
		for (PunchLogs punchLogs : list) {
			idsList.put(punchLogs.getEmployee().getObjId(), punchLogs.getObjId());
		}
		final Iterator<Entry<Integer, Integer>> iter = idsList.entrySet().iterator();
		final HashSet<Integer> valueSet = new HashSet<Integer>();
		while (iter.hasNext()) {
			final Entry<Integer, Integer> next = iter.next();
			if (!valueSet.add(next.getValue())) {
				iter.remove();
			}
		}
		return idsList;
	}

	@Override
	public void findNumberOfRecord(Model model, HttpSession session) {

		int id = (Integer) session.getAttribute("MY_SESSION_ID");
		Appuser appuser1 = repo.getOne(id);

		// today present
		HashMap<Integer, Integer> idsList = checkdateToday(appuser1.getCompany().getObjId());
		model.addAttribute("todayPresent", idsList.size());

		// today absent
		List<Employee> emp = employeeRepository.countEmp(appuser1.getCompany().getObjId());
		ArrayList<Integer> ids = new ArrayList<Integer>();
		for (Employee employee : emp) {
			ids.add(employee.getObjId());
		}
		for (Map.Entry<Integer, Integer> entry : idsList.entrySet()) {
			if (ids.contains(entry.getKey())) {
				ids.remove(entry.getKey());
			}
		}
		model.addAttribute("todayAbsent", ids.size());

		// yesterday present
		HashMap<Integer, Integer> idsList2 = checkDateYesterday(appuser1.getCompany().getObjId());
		model.addAttribute("yesterdayPresent", idsList2.size());

		// yesterday absent
		List<Employee> emp1 = employeeRepository.countEmp(appuser1.getCompany().getObjId());
		ArrayList<Integer> ids1 = new ArrayList<Integer>();
		for (Employee employee : emp1) {
			ids1.add(employee.getObjId());
		}
		for (Map.Entry<Integer, Integer> entry : idsList2.entrySet()) {
			if (ids1.contains(entry.getKey())) {
				ids1.remove(entry.getKey());
			}
		}
		model.addAttribute("yesterdayAbsent", ids1.size());
	}

	@Override
	public void findTodayPresentData(int id, Model model, HttpSession session) {

		int id1 = (Integer) session.getAttribute("MY_SESSION_ID");
		Appuser appuser1 = repo.getOne(id1);

		HashMap<Integer, Integer> idsList = checkdateToday(appuser1.getCompany().getObjId());
		List<PunchLogs> logs = punchLogsRepository.findAllById(idsList.values());
		model.addAttribute("employeePresent", logs);

		Date date = Calendar.getInstance().getTime();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String strDate = dateFormat.format(date);
		Timestamp todayTimestamp1 = Timestamp.valueOf(strDate + " 00:00:00");
		Timestamp todayTimestamp2 = Timestamp.valueOf(strDate + " 23:59:59");
		List<PunchLogs> list = punchLogsRepository.findByEmpId(id, todayTimestamp1, todayTimestamp2);
		model.addAttribute("employeePresentStatus", list);

	}

	@Override
	public int findTodayAbsentData(Model model, HttpSession session) {

		int id = (Integer) session.getAttribute("MY_SESSION_ID");
		Appuser appuser1 = repo.getOne(id);

		List<Employee> emp = employeeRepository.countEmp(appuser1.getCompany().getObjId());
		ArrayList<Integer> ids = new ArrayList<Integer>();
		for (Employee employee : emp) {
			ids.add(employee.getObjId());
		}

		HashMap<Integer, Integer> idsList = checkdateToday(appuser1.getCompany().getObjId());

		for (Map.Entry<Integer, Integer> entry : idsList.entrySet()) {
			if (ids.contains(entry.getKey())) {
				ids.remove(entry.getKey());
			}
		}
		List<Employee> logs = employeeRepository.findAllById(ids);
		model.addAttribute("employeeAbsent", logs);
		return logs.size();

	}

	@Override
	public void findYesterdayPresentData(int id, Model model, HttpSession session) {

		int id1 = (Integer) session.getAttribute("MY_SESSION_ID");
		Appuser appuser1 = repo.getOne(id1);
		Date date = Calendar.getInstance().getTime();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String strDate = dateFormat.format(yesterday(date));
		HashMap<Integer, Integer> idsList = checkDateYesterday(appuser1.getCompany().getObjId());
		List<Employee> logs = employeeRepository.findAllById(idsList.keySet());
		for (Employee employee : logs) {
			Timestamp firstSlot1 = Timestamp.valueOf(strDate + " 00:00:00");
			Timestamp firstSlot2 = Timestamp.valueOf(strDate + " 12:59:59");
			List<PunchLogs> list1 = punchLogsRepository.findByEmpId(employee.getObjId(), firstSlot1, firstSlot2);
			if (!list1.isEmpty()) {
				employee.setSlot1("Y");
			}
			Timestamp secondSlot1 = Timestamp.valueOf(strDate + " 13:00:00");
			Timestamp secondSlot2 = Timestamp.valueOf(strDate + " 23:59:59");
			List<PunchLogs> list2 = punchLogsRepository.findByEmpId(employee.getObjId(), secondSlot1, secondSlot2);
			if (!list2.isEmpty()) {
				employee.setSlot2("Y");
			}
		}
		model.addAttribute("employeePresent", logs);

		Timestamp todayTimestamp1 = Timestamp.valueOf(strDate + " 00:00:00");
		Timestamp todayTimestamp2 = Timestamp.valueOf(strDate + " 23:59:59");
		List<PunchLogs> list = punchLogsRepository.findByEmpId(id, todayTimestamp1, todayTimestamp2);

		model.addAttribute("employeePresentStatus", list);

	}

	@Override
	public void findYesterdayAbsentData(Model model, HttpSession session) {

		int id = (Integer) session.getAttribute("MY_SESSION_ID");
		Appuser appuser1 = repo.getOne(id);

		List<Employee> emp = employeeRepository.countEmp(appuser1.getCompany().getObjId());
		ArrayList<Integer> ids = new ArrayList<Integer>();
		for (Employee employee : emp) {
			ids.add(employee.getObjId());
		}

		HashMap<Integer, Integer> idsList = checkDateYesterday(appuser1.getCompany().getObjId());

		for (Map.Entry<Integer, Integer> entry : idsList.entrySet()) {
			if (ids.contains(entry.getKey())) {
				ids.remove(entry.getKey());
			}
		}
		List<Employee> logs = employeeRepository.findAllById(ids);
		model.addAttribute("employeeAbsent", logs);

	}

	@Override
	public List<Employee> findTodayLateComingData(Model model, HttpSession session) {
		int id1 = (Integer) session.getAttribute("MY_SESSION_ID");
		Appuser appuser1 = repo.getOne(id1);
		Date date = Calendar.getInstance().getTime();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String strDate = dateFormat.format(date);
		HashMap<Integer, Integer> idsList = checkdateToday(appuser1.getCompany().getObjId());

		ArrayList<Integer> ids = new ArrayList<Integer>();
		for (Map.Entry<Integer, Integer> entry : idsList.entrySet()) {
			List<PunchLogs> logs = punchLogsRepository.liveActivityDataGet(entry.getKey());
			for (PunchLogs employee : logs) {
				Timestamp firstSlot1_1 = Timestamp.valueOf(strDate + " 00:00:00");
				Timestamp firstSlot2_1 = Timestamp.valueOf(strDate + " 12:59:59");
				List<PunchLogs> list1_1 = punchLogsRepository.findByEmpId(employee.getEmployee().getObjId(),
						firstSlot1_1, firstSlot2_1);
				if (!list1_1.isEmpty()) {
					Timestamp firstSlot1 = Timestamp.valueOf(strDate + " 00:00:00");
					Timestamp firstSlot2 = Timestamp.valueOf(strDate + " 09:59:59");
					List<PunchLogs> list1 = punchLogsRepository.findByEmpId(employee.getEmployee().getObjId(),
							firstSlot1, firstSlot2);
					if (list1.isEmpty()) {
						ids.add(employee.getEmployee().getObjId());
						continue;
					}
				} else {
					Timestamp secondSlot1 = Timestamp.valueOf(strDate + " 13:00:00");
					Timestamp secondSlot2 = Timestamp.valueOf(strDate + " 13:59:59");
					List<PunchLogs> list2 = punchLogsRepository.findByEmpId(employee.getEmployee().getObjId(),
							secondSlot1, secondSlot2);
					if (list2.isEmpty()) {
						ids.add(employee.getEmployee().getObjId());
					}
				}
			}
		}

		List<Employee> logs1 = employeeRepository.findAllById(ids);
		for (Employee employee : logs1) {
			Timestamp firstSlot1_1 = Timestamp.valueOf(strDate + " 00:00:00");
			Timestamp firstSlot2_1 = Timestamp.valueOf(strDate + " 23:59:59");
			List<PunchLogs> list1_1 = punchLogsRepository.findByEmpId(employee.getObjId(), firstSlot1_1, firstSlot2_1);

			for (PunchLogs punchLogs : list1_1) {
				employee.setPunchTimestamp(punchLogs.getPunchTimestamp());
				Timestamp firstSlot1_11 = Timestamp.valueOf(strDate + " 00:00:00");
				Timestamp firstSlot2_11 = Timestamp.valueOf(strDate + " 12:59:59");
				List<PunchLogs> list1_11 = punchLogsRepository.findByPunchId(punchLogs.getObjId(), firstSlot1_11,
						firstSlot2_11);
				if (!list1_11.isEmpty()) {

					Timestamp firstSlot1_111 = Timestamp.valueOf(strDate + " 09:00:00");
					long milliseconds = punchLogs.getPunchTimestamp().getTime() - firstSlot1_111.getTime();
					int seconds = (int) milliseconds / 1000;
					int hours = seconds / 3600;
					int minutes = (seconds % 3600) / 60;
					seconds = (seconds % 3600) % 60;
					employee.setTimeDifference(hours + ":" + minutes + ":" + seconds);
				} else {
					Timestamp firstSlot1_111 = Timestamp.valueOf(strDate + " 13:00:00");
					long milliseconds = punchLogs.getPunchTimestamp().getTime() - firstSlot1_111.getTime();
					int seconds = (int) milliseconds / 1000;
					int hours = seconds / 3600;
					int minutes = (seconds % 3600) / 60;
					seconds = (seconds % 3600) % 60;
					employee.setTimeDifference(hours + ":" + minutes + ":" + seconds);
				}
				break;
			}
		}
		return logs1;
	}

	@Override
	public List<Employee> findYesterdayLateComingData(Model model, HttpSession session) {
		int id1 = (Integer) session.getAttribute("MY_SESSION_ID");
		Appuser appuser1 = repo.getOne(id1);
		Date date = Calendar.getInstance().getTime();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String strDate = dateFormat.format(yesterday(date));
		HashMap<Integer, Integer> idsList = checkDateYesterday(appuser1.getCompany().getObjId());

		ArrayList<Integer> ids = new ArrayList<Integer>();
		for (Map.Entry<Integer, Integer> entry : idsList.entrySet()) {
			List<PunchLogs> logs = punchLogsRepository.liveActivityDataGet(entry.getKey());
			for (PunchLogs employee : logs) {
				Timestamp firstSlot1_1 = Timestamp.valueOf(strDate + " 00:00:00");
				Timestamp firstSlot2_1 = Timestamp.valueOf(strDate + " 12:59:59");
				List<PunchLogs> list1_1 = punchLogsRepository.findByEmpId(employee.getEmployee().getObjId(),
						firstSlot1_1, firstSlot2_1);
				if (!list1_1.isEmpty()) {
					Timestamp firstSlot1 = Timestamp.valueOf(strDate + " 00:00:00");
					Timestamp firstSlot2 = Timestamp.valueOf(strDate + " 09:59:59");
					List<PunchLogs> list1 = punchLogsRepository.findByEmpId(employee.getEmployee().getObjId(),
							firstSlot1, firstSlot2);
					if (list1.isEmpty()) {
						ids.add(employee.getEmployee().getObjId());
						continue;
					}
				} else {
					Timestamp secondSlot1 = Timestamp.valueOf(strDate + " 13:00:00");
					Timestamp secondSlot2 = Timestamp.valueOf(strDate + " 13:59:59");
					List<PunchLogs> list2 = punchLogsRepository.findByEmpId(employee.getEmployee().getObjId(),
							secondSlot1, secondSlot2);
					if (list2.isEmpty()) {
						ids.add(employee.getEmployee().getObjId());
					}
				}
			}
		}

		List<Employee> logs1 = employeeRepository.findAllById(ids);
		for (Employee employee : logs1) {
			Timestamp firstSlot1_1 = Timestamp.valueOf(strDate + " 00:00:00");
			Timestamp firstSlot2_1 = Timestamp.valueOf(strDate + " 23:59:59");
			List<PunchLogs> list1_1 = punchLogsRepository.findByEmpId(employee.getObjId(), firstSlot1_1, firstSlot2_1);

			for (PunchLogs punchLogs : list1_1) {
				employee.setPunchTimestamp(punchLogs.getPunchTimestamp());
				Timestamp firstSlot1_11 = Timestamp.valueOf(strDate + " 00:00:00");
				Timestamp firstSlot2_11 = Timestamp.valueOf(strDate + " 12:59:59");
				List<PunchLogs> list1_11 = punchLogsRepository.findByPunchId(punchLogs.getObjId(), firstSlot1_11,
						firstSlot2_11);
				if (!list1_11.isEmpty()) {

					Timestamp firstSlot1_111 = Timestamp.valueOf(strDate + " 09:00:00");
					long milliseconds = punchLogs.getPunchTimestamp().getTime() - firstSlot1_111.getTime();
					int seconds = (int) milliseconds / 1000;
					int hours = seconds / 3600;
					int minutes = (seconds % 3600) / 60;
					seconds = (seconds % 3600) % 60;
					employee.setTimeDifference(hours + ":" + minutes + ":" + seconds);
				} else {
					Timestamp firstSlot1_111 = Timestamp.valueOf(strDate + " 13:00:00");
					long milliseconds = punchLogs.getPunchTimestamp().getTime() - firstSlot1_111.getTime();
					int seconds = (int) milliseconds / 1000;
					int hours = seconds / 3600;
					int minutes = (seconds % 3600) / 60;
					seconds = (seconds % 3600) % 60;
					employee.setTimeDifference(hours + ":" + minutes + ":" + seconds);
				}
				break;
			}
		}
		return logs1;
	}

	@Override
	public List<Employee> findYesterdayLateStayingData(Model model, HttpSession session) {
		int id1 = (Integer) session.getAttribute("MY_SESSION_ID");
		Appuser appuser1 = repo.getOne(id1);
		Date date = Calendar.getInstance().getTime();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String strDate = dateFormat.format(yesterday(date));
		HashMap<Integer, Integer> idsList = checkDateYesterday(appuser1.getCompany().getObjId());

		ArrayList<Integer> ids = new ArrayList<Integer>();
		for (Map.Entry<Integer, Integer> entry : idsList.entrySet()) {
			List<PunchLogs> logs = punchLogsRepository.liveActivityDataGet(entry.getKey());
			for (PunchLogs employee : logs) {
				Timestamp firstSlot1_1 = Timestamp.valueOf(strDate + " 19:00:00");
				Timestamp firstSlot2_1 = Timestamp.valueOf(strDate + " 23:59:59");
				List<PunchLogs> list1_1 = punchLogsRepository.findByEmpId(employee.getEmployee().getObjId(),
						firstSlot1_1, firstSlot2_1);
				if (!list1_1.isEmpty()) {
					ids.add(employee.getEmployee().getObjId());
				}
			}
		}
		List<Employee> logs1 = employeeRepository.findAllById(ids);
		for (Employee employee : logs1) {
			Timestamp firstSlot1_1 = Timestamp.valueOf(strDate + " 00:00:00");
			Timestamp firstSlot2_1 = Timestamp.valueOf(strDate + " 23:59:59");
			List<PunchLogs> list1_1 = punchLogsRepository.findByEmpId(employee.getObjId(), firstSlot1_1, firstSlot2_1);
			for (PunchLogs punchLogs : list1_1) {
				employee.setPunchTimestamp(punchLogs.getPunchTimestamp());
				Timestamp firstSlot1_11 = Timestamp.valueOf(strDate + " 19:00:00");
				Timestamp firstSlot2_11 = Timestamp.valueOf(strDate + " 23:59:59");
				List<PunchLogs> list1_11 = punchLogsRepository.findByPunchId(punchLogs.getObjId(), firstSlot1_11,
						firstSlot2_11);
				if (!list1_11.isEmpty()) {
					Timestamp firstSlot1_111 = Timestamp.valueOf(strDate + " 19:00:00");
					long milliseconds = punchLogs.getPunchTimestamp().getTime() - firstSlot1_111.getTime();
					int seconds = (int) milliseconds / 1000;
					int hours = seconds / 3600;
					int minutes = (seconds % 3600) / 60;
					seconds = (seconds % 3600) % 60;
					employee.setTimeDifference(hours + ":" + minutes + ":" + seconds);
				}
			}
		}
		return logs1;
	}

	@Override
	public List<Employee> findEmployeeData(HttpSession session) {
		int id = (Integer) session.getAttribute("MY_SESSION_ID");
		Appuser appuser1 = repo.getOne(id);
		List<Employee> employee = employeeRepository.countEmp(appuser1.getCompany().getObjId());
		return employee;
	}

	@Override
	public boolean addTodayEarlyLeaving(int employeeId, Leave leave, Model model, HttpSession session) {
		Date date = Calendar.getInstance().getTime();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String strDate = dateFormat.format(date);

		Timestamp firstSlot1_1 = Timestamp.valueOf(strDate + " 00:00:00");
		Timestamp firstSlot2_1 = Timestamp.valueOf(strDate + " 23:59:59");
		List<Leave> leave2 = leaveRepository.findByEmpId(employeeId, firstSlot1_1, firstSlot2_1);
		if (leave2.isEmpty()) {
			int session1 = (Integer) session.getAttribute("MY_SESSION_ID");
			Appuser appuser = repo.getOne(session1);
			leave.setFixedBy(appuser);

			Employee employee = employeeRepository.getOne(employeeId);
			leave.setEmployee(employee);
			leave.setCreationTimestamp(firstSlot2_1);
			leave.setLastUpdatedTimestamp(firstSlot2_1);
			leaveRepository.save(leave);
			return true;
		} else {
			model.addAttribute("error", 1);
			return false;
		}

	}

	@Override
	public int findTodayEarlyLeavingData(Model model, HttpSession session) {
		int id1 = (Integer) session.getAttribute("MY_SESSION_ID");
		Appuser appuser1 = repo.getOne(id1);
		HashMap<Integer, Integer> idsList = checkdateToday(appuser1.getCompany().getObjId());
		// List<PunchLogs> logs1 = punchLogsRepository.findAllById(idsList.values());
		ArrayList<Integer> ids = new ArrayList<Integer>(idsList.keySet());
		for (Integer integer : idsList.values()) {
			ids.add(integer);
		}
		int cid = (Integer) session.getAttribute("MY_SESSION_COMPANY_ID");
		Date date = Calendar.getInstance().getTime();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String strDate = dateFormat.format(date);
		Timestamp firstSlot1_1 = Timestamp.valueOf(strDate + " 00:00:00");
		Timestamp firstSlot2_1 = Timestamp.valueOf(strDate + " 23:59:59");
		List<Leave> leave = leaveRepository.findAllData(cid, firstSlot1_1, firstSlot2_1);
		for (Leave leave2 : leave) {
			if (ids.contains(leave2.getEmployee().getObjId())) {
				Object id = leave2.getEmployee().getObjId();
				ids.remove(id);
			}
			List<PunchLogs> logs = punchLogsRepository.findByEmpId(leave2.getEmployee().getObjId(), firstSlot1_1,
					firstSlot2_1);
			int count = 0;
			for (PunchLogs logs2 : logs) {
				String punchtime = logs2.getPunchTimestamp().toString();
				leave2.setPunchTimestamp(punchtime);
				count++;
			}
			if (count % 2 != 0) {
				leave2.setPunchTimestamp("-");
			}
		}

		Timestamp Time1 = Timestamp.valueOf(strDate + " 00:00:00");
		Timestamp Time2 = Timestamp.valueOf(strDate + " 18:00:00");
		ArrayList<Integer> punchIds = new ArrayList<Integer>();
		for (Integer integer : ids) {
			List<PunchLogs> logs = punchLogsRepository.findByEmpId(integer, Time1, Time2);
			int count = 0;
			int punchId = 0;
			for (PunchLogs logs2 : logs) {
				punchId = logs2.getObjId();
				count++;
			}
			if (count % 2 == 0) {
				punchIds.add(punchId);
			}

		}

		List<PunchLogs> logs = punchLogsRepository.findAllById(punchIds);
		model.addAttribute("employeePresent", logs);
		model.addAttribute("employeeReasonData", leave);
		int dataSize = logs.size() + leave.size();
		return dataSize;
	}

	@Override
	public int findYesterdayEarlyLeavingData(Model model, HttpSession session) {
		int id1 = (Integer) session.getAttribute("MY_SESSION_ID");
		Appuser appuser1 = repo.getOne(id1);
		HashMap<Integer, Integer> idsList = checkDateYesterday(appuser1.getCompany().getObjId());
		// List<PunchLogs> logs1 = punchLogsRepository.findAllById(idsList.values());
		ArrayList<Integer> ids = new ArrayList<Integer>();
		for (Integer integer : idsList.keySet()) {
			ids.add(integer);
		}
		int cid = (Integer) session.getAttribute("MY_SESSION_COMPANY_ID");
		Date date = Calendar.getInstance().getTime();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String strDate = dateFormat.format(yesterday(date));
		Timestamp firstSlot1_1 = Timestamp.valueOf(strDate + " 00:00:00");
		Timestamp firstSlot2_1 = Timestamp.valueOf(strDate + " 23:59:59");
		List<Leave> leave = leaveRepository.findAllData(cid, firstSlot1_1, firstSlot2_1);
		for (Leave leave2 : leave) {
			if (ids.contains(leave2.getEmployee().getObjId())) {
				Object id = leave2.getEmployee().getObjId();
				ids.remove(id);
			}
			List<PunchLogs> logs = punchLogsRepository.findByEmpId(leave2.getEmployee().getObjId(), firstSlot1_1,
					firstSlot2_1);
			int count = 0;
			for (PunchLogs logs2 : logs) {
				String punchtime = logs2.getPunchTimestamp().toString();
				leave2.setPunchTimestamp(punchtime);
				count++;
			}
			if (count % 2 != 0) {
				leave2.setPunchTimestamp("-");
			}
		}

		Timestamp Time1 = Timestamp.valueOf(strDate + " 00:00:00");
		Timestamp Time2 = Timestamp.valueOf(strDate + " 18:00:00");
		ArrayList<Integer> punchIds = new ArrayList<Integer>();
		for (Integer integer : ids) {
			List<PunchLogs> logs = punchLogsRepository.findByEmpId(integer, Time1, Time2);
			int count = 0;
			int punchId = 0;
			for (PunchLogs logs2 : logs) {
				punchId = logs2.getObjId();
				count++;
			}
			if (count % 2 == 0) {
				punchIds.add(punchId);
			}

		}

		List<PunchLogs> logs = punchLogsRepository.findAllById(punchIds);
		model.addAttribute("employeePresent", logs);
		model.addAttribute("employeeReasonData", leave);
		int dataSize = logs.size() + leave.size();
		return dataSize;
	}

	@Override
	public boolean addYesterdayEarlyLeaving(int employeeId, Leave leave, Model model, HttpSession session) {
		Date date = Calendar.getInstance().getTime();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String strDate = dateFormat.format(yesterday(date));

		Timestamp firstSlot1_1 = Timestamp.valueOf(strDate + " 00:00:00");
		Timestamp firstSlot2_1 = Timestamp.valueOf(strDate + " 23:59:59");
		List<Leave> leave2 = leaveRepository.findByEmpId(employeeId, firstSlot1_1, firstSlot2_1);
		if (leave2.isEmpty()) {
			int session1 = (Integer) session.getAttribute("MY_SESSION_ID");
			Appuser appuser = repo.getOne(session1);
			leave.setFixedBy(appuser);

			Employee employee = employeeRepository.getOne(employeeId);
			leave.setEmployee(employee);
			leave.setCreationTimestamp(firstSlot2_1);
			leave.setLastUpdatedTimestamp(firstSlot2_1);
			leaveRepository.save(leave);
			return true;
		} else {
			model.addAttribute("error", 1);
			return false;
		}

	}

	@Override
	public void findYesterdayMissedPunchData(Model model, HttpSession session) {
		int id1 = (Integer) session.getAttribute("MY_SESSION_ID");
		Appuser appuser1 = repo.getOne(id1);
		HashMap<Integer, Integer> idsList = checkDateYesterday(appuser1.getCompany().getObjId());

		Date date = Calendar.getInstance().getTime();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String strDate = dateFormat.format(yesterday(date));
		Timestamp Time1 = Timestamp.valueOf(strDate + " 00:00:00");
		Timestamp Time2 = Timestamp.valueOf(strDate + " 23:59:59");
		ArrayList<Integer> punchIds = new ArrayList<Integer>();
		for (Integer integer : idsList.keySet()) {
			List<PunchLogs> logs = punchLogsRepository.findByEmpId(integer, Time1, Time2);
			int count = 0;
			int punchId = 0;
			for (PunchLogs logs2 : logs) {
				punchId = logs2.getObjId();
				count++;
			}
			if (count % 2 != 0) {
				punchIds.add(punchId);
			}
		}

		List<PunchLogs> logs = punchLogsRepository.findAllById(punchIds);
		model.addAttribute("employeePresent", logs);
		model.addAttribute("yesterdayMissedPunch", logs.size());
	}

	@Override
	public boolean addYesterdayMissedPunchRecord(int employeeId, String punchTime, MissedPunch missedPunch, Model model,
			HttpSession session) {
		Date date = Calendar.getInstance().getTime();
		Date newDate = yesterday(date);
		String punchTimeNew = punchTime + ":00";
		final Timestamp timestamp = Timestamp
				.valueOf(new SimpleDateFormat("yyyy-MM-dd ").format(newDate).concat(punchTimeNew));
		int session1 = (Integer) session.getAttribute("MY_SESSION_ID");
		Appuser appuser = repo.getOne(session1);
		missedPunch.setFixedBy(appuser);
		Employee employee = employeeRepository.getOne(employeeId);
		missedPunch.setEmployee(employee);
		PunchMachine punchMachine = machineRepository.getOne(1);
		missedPunch.setMachine(punchMachine);
		missedPunch.setPunchTimestamp(timestamp);
		missedPunchRepository.save(missedPunch);

		PunchLogs logs = new PunchLogs();
		logs.setEmployee(employee);
		logs.setMachine(punchMachine);
		logs.setPunchTimestamp(timestamp);
		punchLogsRepository.save(logs);
		return true;

	}

	@Override
	public void report1(Model model, HttpSession session) {

		int id1 = (Integer) session.getAttribute("MY_SESSION_ID");
		Appuser appuser1 = repo.getOne(id1);
		Date date = Calendar.getInstance().getTime();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String strDate = dateFormat.format(yesterday(date));
		List<Employee> logs = employeeRepository.countEmp(appuser1.getCompany().getObjId());
		for (Employee employee : logs) {
			Timestamp time1 = Timestamp.valueOf(strDate + " 00:00:00");
			Timestamp time2 = Timestamp.valueOf(strDate + " 23:59:59");
			List<PunchLogs> list1 = punchLogsRepository.findByEmpId(employee.getObjId(), time1, time2);
			ArrayList<Timestamp> timeStore = new ArrayList<Timestamp>();
			for (PunchLogs punchLogs : list1) {
				timeStore.add(punchLogs.getPunchTimestamp());
			}
			if (timeStore.size() % 2 == 0) {
				long milliseconds = 0;
				for (int i = 0; i < timeStore.size();) {
					long milliseconds1 = timeStore.get(i + 1).getTime() - timeStore.get(i).getTime();
					milliseconds = milliseconds + milliseconds1;
					i = i + 2;
				}
				int seconds = (int) milliseconds / 1000;
				int hours = seconds / 3600;
				int minutes = (seconds % 3600) / 60;
				seconds = (seconds % 3600) % 60;
				employee.setTimeDifference(hours + ":" + minutes + ":" + seconds);
			} else {
				employee.setTimeDifference("Missed Punch ");
				timeStore.remove(timeStore.size() - 1);
			}
		}
		model.addAttribute("date", strDate + " to " + strDate);
		model.addAttribute("reportData", logs);

	}

	// filter1
	public void filter1(Set<Integer> set, String year1, String month1, String dateRange, Model model,
			HttpSession session) {

		String message = filteMmessage(set, year1, month1, dateRange, session);
		ArrayList<String> date12 = dateRanges(year1, month1, dateRange);
		String strDate1 = date12.get(0);
		String strDate2 = date12.get(1);

		List<Employee> logs = employeeRepository.findAllById(set);
		for (Employee employee : logs) {
			Timestamp time1 = Timestamp.valueOf(strDate1 + " 00:00:00");
			Timestamp time2 = Timestamp.valueOf(strDate2 + " 23:59:59");
			List<PunchLogs> list1 = punchLogsRepository.findByEmpId(employee.getObjId(), time1, time2);
			ArrayList<Timestamp> timeStore = new ArrayList<Timestamp>();
			for (PunchLogs punchLogs : list1) {
				timeStore.add(punchLogs.getPunchTimestamp());
			}
			if (timeStore.size() % 2 == 0) {
				long milliseconds = 0;
				for (int i = 0; i < timeStore.size();) {
					long milliseconds1 = timeStore.get(i + 1).getTime() - timeStore.get(i).getTime();
					milliseconds = milliseconds + milliseconds1;
					i = i + 2;
				}
				int seconds = (int) milliseconds / 1000;
				int hours = seconds / 3600;
				int minutes = (seconds % 3600) / 60;
				seconds = (seconds % 3600) % 60;
				employee.setTimeDifference(hours + ":" + minutes + ":" + seconds);
			} else {
				employee.setTimeDifference("Missed Punch ");
				timeStore.remove(timeStore.size() - 1);
			}
		}
		model.addAttribute("date", strDate1 + " to " + strDate2);
		model.addAttribute("message", message);
		model.addAttribute("reportData", logs);

	}

	@Override
	public void workingHourRange(String year, String month1, String employeeId, String dateRange, Model model,
			HttpSession session) {
		int id1 = (Integer) session.getAttribute("MY_SESSION_ID");
		Appuser appuser1 = repo.getOne(id1);

		if (employeeId == null) {
			List<Employee> eid = employeeRepository.countEmp(appuser1.getCompany().getObjId());
			Set<Integer> ids = new HashSet<Integer>();
			for (Employee employee : eid) {
				ids.add(employee.getObjId());
			}
			filter1(ids, year, month1, dateRange, model, session);
		} else {
			String[] arr2 = employeeId.split(",");
			int size = arr2.length;
			Set<Integer> arr = new HashSet<Integer>();
			for (int i = 0; i < size; i++) {
				arr.add(Integer.parseInt(arr2[i]));
			}
			filter1(arr, year, month1, dateRange, model, session);
		}
	}

	// filter3
	public void filter3(Set<Integer> set, String year1, String month1, String dateRange, Model model,
			HttpSession session) {
		String message = filteMmessage(set, year1, month1, dateRange, session);
		ArrayList<String> date12 = dateRanges(year1, month1, dateRange);
		String strDate1 = date12.get(0);
		String strDate2 = date12.get(1);

		Date date1 = null;
		Date date2 = null;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		try {
			date1 = formatter.parse(strDate1);
			date2 = formatter.parse(strDate2);
		} catch (ParseException e) {
			System.out.print(e);
		}
		Calendar cStart = Calendar.getInstance();
		cStart.setTime(date1);
		Calendar cEnd = Calendar.getInstance();
		cEnd.setTime(date2);
		cEnd.add(Calendar.DAY_OF_MONTH, 1);
		ArrayList<Report> employeeTotal = new ArrayList<Report>();
		while (cStart.before(cEnd)) {
			List<Employee> logs = employeeRepository.findAllById(set);
			for (Employee employee : logs) {
				Timestamp firstSlot1_1 = Timestamp.valueOf(formatter.format(cStart.getTime()) + " 00:00:00");
				Timestamp firstSlot2_1 = Timestamp.valueOf(formatter.format(cStart.getTime()) + " 23:59:59");
				List<PunchLogs> list1_1 = punchLogsRepository.findByEmpId(employee.getObjId(), firstSlot1_1,
						firstSlot2_1);
				for (PunchLogs punchLogs : list1_1) {
					employee.setPunchTimestamp(punchLogs.getPunchTimestamp());
					Timestamp firstSlot1_11 = Timestamp.valueOf(formatter.format(cStart.getTime()) + " 19:00:00");
					Timestamp firstSlot2_11 = Timestamp.valueOf(formatter.format(cStart.getTime()) + " 23:59:59");
					List<PunchLogs> list1_11 = punchLogsRepository.findByPunchId(punchLogs.getObjId(), firstSlot1_11,
							firstSlot2_11);
					if (!list1_11.isEmpty()) {
						Timestamp firstSlot1_111 = Timestamp.valueOf(formatter.format(cStart.getTime()) + " 19:00:00");
						long milliseconds = punchLogs.getPunchTimestamp().getTime() - firstSlot1_111.getTime();
						int seconds = (int) milliseconds / 1000;
						int hours = seconds / 3600;
						int minutes = (seconds % 3600) / 60;
						seconds = (seconds % 3600) % 60;
						employee.setTimeDifference(hours + ":" + minutes + ":" + seconds);
						Report report3 = new Report();
						report3.setFirstName(employee.getFirstName());
						report3.setLastName(employee.getLastName());
						report3.setEmail(employee.getEmail());
						report3.setMobile(employee.getMobile());
						report3.setPunchTimestamp(employee.getPunchTimestamp().toString());
						report3.setTimeDifference(employee.getTimeDifference());
						employeeTotal.add(report3);
					}
				}
			}
			cStart.add(Calendar.DAY_OF_MONTH, 1);
		}
		model.addAttribute("message", message);
		model.addAttribute("reportData", employeeTotal);
	}

	@Override
	public void lateStayingRange(String year, String month1, String employeeId, String dateRange, Model model,
			HttpSession session) {
		int id1 = (Integer) session.getAttribute("MY_SESSION_ID");
		Appuser appuser1 = repo.getOne(id1);

		if (employeeId == null) {
			List<Employee> eid = employeeRepository.countEmp(appuser1.getCompany().getObjId());
			Set<Integer> ids = new HashSet<Integer>();
			for (Employee employee : eid) {
				ids.add(employee.getObjId());
			}
			filter3(ids, year, month1, dateRange, model, session);
		} else {
			String[] arr2 = employeeId.split(",");
			int size = arr2.length;
			Set<Integer> arr = new HashSet<Integer>();
			for (int i = 0; i < size; i++) {
				arr.add(Integer.parseInt(arr2[i]));
			}
			filter3(arr, year, month1, dateRange, model, session);
		}
	}

	// filter2
	public void filter2(Set<Integer> set, String year1, String month1, String dateRange, Model model,
			HttpSession session) {
		String message = filteMmessage(set, year1, month1, dateRange, session);
		ArrayList<String> date12 = dateRanges(year1, month1, dateRange);
		String strDate1 = date12.get(0);
		String strDate2 = date12.get(1);

		Date date1 = null;
		Date date2 = null;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		try {
			date1 = formatter.parse(strDate1);
			date2 = formatter.parse(strDate2);
		} catch (ParseException e) {
			System.out.print(e);
		}
		Calendar cStart = Calendar.getInstance();
		cStart.setTime(date1);
		Calendar cEnd = Calendar.getInstance();
		cEnd.setTime(date2);
		cEnd.add(Calendar.DAY_OF_MONTH, 1);
		ArrayList<Integer> ids = new ArrayList<Integer>();
		for (Integer i : set) {
			List<PunchLogs> logs1 = punchLogsRepository.liveActivityDataGet(i);
			for (PunchLogs employee : logs1) {
				Timestamp firstSlot1_1 = Timestamp.valueOf(formatter.format(cStart.getTime()) + " 00:00:00");
				Timestamp firstSlot2_1 = Timestamp.valueOf(formatter.format(cStart.getTime()) + " 12:59:59");
				List<PunchLogs> list1_1 = punchLogsRepository.findByEmpId(employee.getEmployee().getObjId(),
						firstSlot1_1, firstSlot2_1);
				if (!list1_1.isEmpty()) {
					Timestamp firstSlot1 = Timestamp.valueOf(formatter.format(cStart.getTime()) + " 00:00:00");
					Timestamp firstSlot2 = Timestamp.valueOf(formatter.format(cStart.getTime()) + " 09:59:59");
					List<PunchLogs> list1 = punchLogsRepository.findByEmpId(employee.getEmployee().getObjId(),
							firstSlot1, firstSlot2);
					if (list1.isEmpty()) {
						ids.add(employee.getEmployee().getObjId());
						continue;
					}
				} else {
					Timestamp secondSlot1 = Timestamp.valueOf(formatter.format(cStart.getTime()) + " 13:00:00");
					Timestamp secondSlot2 = Timestamp.valueOf(formatter.format(cStart.getTime()) + " 13:59:59");
					List<PunchLogs> list2 = punchLogsRepository.findByEmpId(employee.getEmployee().getObjId(),
							secondSlot1, secondSlot2);
					if (list2.isEmpty()) {
						ids.add(employee.getEmployee().getObjId());
					}
				}
			}
		}

		ArrayList<Report> employeeTotal = new ArrayList<Report>();
		while (cStart.before(cEnd)) {
			List<Employee> logs1 = employeeRepository.findAllById(ids);
			for (Employee employee : logs1) {
				Timestamp firstSlot1_1 = Timestamp.valueOf(formatter.format(cStart.getTime()) + " 00:00:00");
				Timestamp firstSlot2_1 = Timestamp.valueOf(formatter.format(cStart.getTime()) + " 23:59:59");
				List<PunchLogs> list1_1 = punchLogsRepository.findByEmpId(employee.getObjId(), firstSlot1_1,
						firstSlot2_1);

				for (PunchLogs punchLogs : list1_1) {
					employee.setPunchTimestamp(punchLogs.getPunchTimestamp());
					Timestamp firstSlot1_11 = Timestamp.valueOf(formatter.format(cStart.getTime()) + " 00:00:00");
					Timestamp firstSlot2_11 = Timestamp.valueOf(formatter.format(cStart.getTime()) + " 12:59:59");
					List<PunchLogs> list1_11 = punchLogsRepository.findByPunchId(punchLogs.getObjId(), firstSlot1_11,
							firstSlot2_11);
					if (!list1_11.isEmpty()) {

						Timestamp firstSlot1_111 = Timestamp.valueOf(formatter.format(cStart.getTime()) + " 10:00:00");
						long milliseconds = punchLogs.getPunchTimestamp().getTime() - firstSlot1_111.getTime();
						if (milliseconds > 0) {
							int seconds = (int) milliseconds / 1000;
							int hours = seconds / 3600;
							int minutes = (seconds % 3600) / 60;
							seconds = (seconds % 3600) % 60;
							employee.setTimeDifference(hours + ":" + minutes + ":" + seconds);
							Report report2 = new Report();
							report2.setFirstName(employee.getFirstName());
							report2.setLastName(employee.getLastName());
							report2.setEmail(employee.getEmail());
							report2.setMobile(employee.getMobile());
							report2.setPunchTimestamp(employee.getPunchTimestamp().toString());
							report2.setTimeDifference(employee.getTimeDifference());
							employeeTotal.add(report2);
						}
					} else {
						Timestamp firstSlot1_111 = Timestamp.valueOf(formatter.format(cStart.getTime()) + " 14:00:00");
						long milliseconds = punchLogs.getPunchTimestamp().getTime() - firstSlot1_111.getTime();
						if (milliseconds > 0) {
							int seconds = (int) milliseconds / 1000;
							int hours = seconds / 3600;
							int minutes = (seconds % 3600) / 60;
							seconds = (seconds % 3600) % 60;
							employee.setTimeDifference(hours + ":" + minutes + ":" + seconds);
							Report report2 = new Report();
							report2.setFirstName(employee.getFirstName());
							report2.setLastName(employee.getLastName());
							report2.setEmail(employee.getEmail());
							report2.setMobile(employee.getMobile());
							report2.setPunchTimestamp(employee.getPunchTimestamp().toString());
							report2.setTimeDifference(employee.getTimeDifference());
							employeeTotal.add(report2);
						}
					}
					break;
				}
			}
			cStart.add(Calendar.DAY_OF_MONTH, 1);
		}
		model.addAttribute("message", message);
		model.addAttribute("reportData", employeeTotal);
	}

	@Override
	public void lateComingRange(String year, String month1, String employeeId, String dateRange, Model model,
			HttpSession session) {
		int id1 = (Integer) session.getAttribute("MY_SESSION_ID");
		Appuser appuser1 = repo.getOne(id1);

		if (employeeId == null) {
			List<Employee> eid = employeeRepository.countEmp(appuser1.getCompany().getObjId());
			Set<Integer> ids = new HashSet<Integer>();
			for (Employee employee : eid) {
				ids.add(employee.getObjId());
			}
			filter2(ids, year, month1, dateRange, model, session);
		} else {
			String[] arr2 = employeeId.split(",");
			int size = arr2.length;
			Set<Integer> arr = new HashSet<Integer>();
			for (int i = 0; i < size; i++) {
				arr.add(Integer.parseInt(arr2[i]));
			}
			filter2(arr, year, month1, dateRange, model, session);
		}

	}

	@Override
	public void storeFile(MultipartFile file1, HttpSession session) throws Exception {
//		String fileName = StringUtils.cleanPath(file1.getOriginalFilename());
		int session1 = (Integer) session.getAttribute("MY_SESSION_ID");
		Appuser appuser = repo.getOne(session1);
		int cid = appuser.getCompany().getObjId();
		byte[] bytes = file1.getBytes();
		String ImageName = cid + "_data.csv";
		Path path = Paths.get(config.getImportData(), ImageName);
		Files.write(path, bytes);
	}

	@Override
	public List<MissedPunch> findFixedMissPunchData(Model model, HttpSession session) {
		ArrayList<MissedPunch> arrayList = new ArrayList<MissedPunch>();
		int cid = (Integer) session.getAttribute("MY_SESSION_COMPANY_ID");
		List<MissedPunch> missedPunchs = missedPunchRepository.findAll();
		for (MissedPunch missedPunch : missedPunchs) {
			if (missedPunch.getFixedBy().getCompany().getObjId() == cid) {
				arrayList.add(missedPunch);
			}
		}
		return arrayList;
	}

	@Override
	public void fixedMissPunchRange(String year, String month1, String employeeId, String dateRange, Model model,
			HttpSession session) {
		int id1 = (Integer) session.getAttribute("MY_SESSION_ID");
		Appuser appuser1 = repo.getOne(id1);

		if (employeeId == null) {
			List<Employee> eid = employeeRepository.countEmp(appuser1.getCompany().getObjId());
			Set<Integer> ids = new HashSet<Integer>();
			for (Employee employee : eid) {
				ids.add(employee.getObjId());
			}
			filter4(ids, year, month1, dateRange, model, session);
		} else {
			String[] arr2 = employeeId.split(",");
			int size = arr2.length;
			Set<Integer> arr = new HashSet<Integer>();
			for (int i = 0; i < size; i++) {
				arr.add(Integer.parseInt(arr2[i]));
			}
			filter4(arr, year, month1, dateRange, model, session);
		}
	}

	// filter4
	public void filter4(Set<Integer> set, String year1, String month1, String dateRange, Model model,
			HttpSession session) {
		String message = filteMmessage(set, year1, month1, dateRange, session);
		ArrayList<String> date12 = dateRanges(year1, month1, dateRange);
		String strDate1 = date12.get(0);
		String strDate2 = date12.get(1);
		int cid = (Integer) session.getAttribute("MY_SESSION_COMPANY_ID");
		ArrayList<MissedPunch> employeeTotal = new ArrayList<MissedPunch>();
		List<Employee> logs = employeeRepository.findAllById(set);
		for (Employee employee : logs) {
			Timestamp firstSlot1_1 = Timestamp.valueOf(strDate1 + " 00:00:00");
			Timestamp firstSlot2_1 = Timestamp.valueOf(strDate2 + " 23:59:59");
			if (cid == employee.getCompany().getObjId()) {
				List<MissedPunch> list1_1 = missedPunchRepository.findByCId(employee.getObjId(), firstSlot1_1,
						firstSlot2_1);
				for (MissedPunch punch : list1_1) {
					employeeTotal.add(punch);
				}
			}
		}
		model.addAttribute("message", message);
		model.addAttribute("reportData", employeeTotal);
	}

	// Date Range split
	public ArrayList<String> dateRanges(String year1, String month1, String dateRange) {
		ArrayList<String> date12 = new ArrayList<String>();
		if (dateRange != null) {
			String[] arr2 = dateRange.split("-");

			for (int i = 0; i < arr2.length; i++) {
				String trimdate = arr2[i].trim();
				String[] date1 = trimdate.split("/");
				date12.add(date1[2] + "-" + date1[0] + "-" + date1[1]);
			}
		} else if (year1 != null) {
			int year = Integer.parseInt(year1);
			if (!month1.equals("0")) {
				if (month1.equals("02")) {
					if (((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0)) {
						String arr1 = year + "-" + month1 + "-01";
						date12.add(arr1);
						String arr2 = year + "-" + month1 + "-29";
						date12.add(arr2);
					} else {
						String arr1 = year + "-" + month1 + "-01";
						date12.add(arr1);
						String arr2 = year + "-" + month1 + "-28";
						date12.add(arr2);
					}

				} else if (month1.equals("01") || month1.equals("03") || month1.equals("05") || month1.equals("07")
						|| month1.equals("08") || month1.equals("10") || month1.equals("12")) {
					String arr1 = year + "-" + month1 + "-01";
					date12.add(arr1);
					String arr2 = year + "-" + month1 + "-31";
					date12.add(arr2);
				} else {
					String arr1 = year + "-" + month1 + "-01";
					date12.add(arr1);
					String arr2 = year + "-" + month1 + "-30";
					date12.add(arr2);
				}

			} else {
				String arr1 = year + "-01-01";
				date12.add(arr1);
				String arr2 = year + "-12-31";
				date12.add(arr2);
			}
		}
		return date12;
	}

	// Filter Message
	public String filteMmessage(Set<Integer> set, String year1, String month1, String dateRange, HttpSession session) {
		String message = "";
		int cid = (Integer) session.getAttribute("MY_SESSION_COMPANY_ID");
		List<Employee> employee = employeeRepository.countEmp(cid);
		if (employee.size() == set.size()) {
			if (year1 == null) {
				message = dateRange;
			} else if (year1 != null && month1.equals("0")) {
				message = year1;
			} else {
				message = month1 + " - " + year1;
			}
		} else {
			if (year1 == null) {
				message = "Employee name with " + dateRange;
			} else if (year1 != null && month1.equals("0")) {
				message = "Employee name with year(" + year1 + ")";
			} else {
				message = "Employee name with month(" + month1 + ") and year(" + year1 + ")";
			}
		}
		return message;

	}

}
