package com.AMS.repository;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.AMS.model.Leave;

@Repository
public interface LeaveRepository extends JpaRepository<Leave, Integer> {

	// find by employee id
	@Query("SELECT l FROM Leave l where l.employee.objId=:eId AND l.creationTimestamp BETWEEN :startDate AND :endDate")
	List<Leave> findByEmpId(@Param("eId") int eId, @Param("startDate") Timestamp startDate,
			@Param("endDate") Timestamp endDate);

	// find by date
	@Query("SELECT l FROM Leave l where l.fixedBy.company.objId=:cid AND l.creationTimestamp BETWEEN :startDate AND :endDate")
	List<Leave> findAllData(@Param("cid") int cid,@Param("startDate") Timestamp startDate, @Param("endDate") Timestamp endDate);

}
