package com.AMS.repository;

import java.sql.Timestamp;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.AMS.model.PunchLogs;

@Repository
public interface PunchLogsRepository extends JpaRepository<PunchLogs, Integer> {

	//live activity data get
	@Query("SELECT p FROM PunchLogs p where p.employee.company.objId=:cId AND p.punchTimestamp BETWEEN :startDate AND :endDate")
	List<PunchLogs> liveActivityDataGet(@Param("cId") int cId,@Param("startDate") Timestamp startDate,@Param("endDate") Timestamp endDate);
	
	@Query("SELECT p FROM PunchLogs p where p.employee.objId=:empId AND p.punchTimestamp BETWEEN :startDate AND :endDate")
	List<PunchLogs> findByEmpId(@Param("empId") int empId,@Param("startDate") Timestamp startDate,@Param("endDate") Timestamp endDate);
	
	@Query("SELECT p FROM PunchLogs p where p.objId=:punchId AND p.punchTimestamp BETWEEN :startDate AND :endDate")
	List<PunchLogs> findByPunchId(@Param("punchId") int punchId,@Param("startDate") Timestamp startDate,@Param("endDate") Timestamp endDate);

	//find by employee data on system date
	@Query("SELECT p FROM PunchLogs p where p.employee.objId=:eId ")
	List<PunchLogs> liveActivityDataGet(@Param("eId") int eId);
	
}
