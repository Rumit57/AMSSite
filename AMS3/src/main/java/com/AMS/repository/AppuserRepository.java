package com.AMS.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.AMS.model.Appuser;

@Repository
public interface AppuserRepository extends JpaRepository<Appuser, Integer> {

	// authentication check
	@Query("SELECT u FROM Appuser u where u.email=:email and u.password=:password")
	List<Appuser> authentication(@Param("email") String email, @Param("password") String pass);

	// check email is available in database
	@Query("SELECT u FROM Appuser u where u.email=:email")
	List<Appuser> checkEmail(@Param("email") String to);

	// change password(code data)
	@Query("SELECT u FROM Appuser u where u.code=:code")
	List<Appuser> dataGet(@Param("code") String code);

	// find Admin
	@Query("SELECT u FROM Appuser u where u.role.objId=:role")
	List<Appuser> dataOfAdmin(@Param("role") int role);

	// find Company Admin
	@Query("SELECT u FROM Appuser u where u.company.objId=:companyId")
	List<Appuser> dataOfCompanyAdmin(@Param("companyId") int companyId);

	// delete company with admin
	@Query("SELECT u FROM Appuser u where u.company.objId=:companyId")
	List<Appuser> CompanyAdminDataDelete(@Param("companyId") int companyId);


	// Find by ID
	@Query("SELECT u FROM Appuser u where u.objId=:Id")
	List<Appuser> checkId(@Param("Id") int Id);
	
	
	//count no. of HR
	@Query("SELECT u FROM Appuser u where u.company.objId=:cId AND u.role.objId=:role")
	List<Appuser> countHR(@Param("cId") int cId,@Param("role") int role);
}
