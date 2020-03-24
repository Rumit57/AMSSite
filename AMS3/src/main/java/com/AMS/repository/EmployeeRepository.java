package com.AMS.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.AMS.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	// count employee company wise
	@Query("SELECT u FROM Employee u where u.company.objId=:cId")
	List<Employee> countEmp(@Param("cId") int cId);

	// FindALL
	@Query("SELECT u FROM Employee u")
	Employee findAllEmp();

	// FindALL
	@Query("SELECT u FROM Employee u")
	List<Employee> findAllEmployee();

	// check email is available in database
	@Query("SELECT u FROM Employee u where u.email=:email")
	List<Employee> checkEmail(@Param("email") String to);

	// Update Import data
	@Modifying
	@Transactional
	@Query("update Employee e set e.firstName = :firstName,e.lastName = :lastName, "
			+ "e.mobile = :mobile, e.gender = :gender,e.userType = :userType,"
			+ "e.address1 = :address1, e.address2 = :address2,e.pincode = :pincode,"
			+ "e.city = :city,e.state = :state,e.country = :country where e.objId = :objId")
	void updateEmp(@Param("firstName") String firstName,@Param("lastName") String lastName, 
			@Param("mobile") String mobile,@Param("gender") char gender,@Param("userType") String userType,
			@Param("address1") String address1,@Param("address2") String address2,@Param("pincode") String pincode,
			@Param("city") String city,@Param("state") String state,@Param("country") String country,
			@Param("objId") int objId);

}
