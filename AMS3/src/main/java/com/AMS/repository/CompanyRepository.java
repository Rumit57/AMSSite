package com.AMS.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.AMS.model.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer> {
	
	@Query("SELECT u FROM Company u where u.objId=:objId")
	public Company findByobjId(@Param("objId")int objId);
	
	
	@Query("SELECT u FROM Company u ")
	public List<Company> findCompany();
	
	
	@Query("SELECT u FROM Company u where u.objId=:Id")
	Optional<Company> checkId(@Param("Id") int Id);
	
	@Query("SELECT u FROM Company u where u.objId=:Id")
	List<Company> checkCompanyId(@Param("Id") int Id);
}
