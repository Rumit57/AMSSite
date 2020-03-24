package com.AMS.repository;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.AMS.model.MissedPunch;

@Repository
public interface MissedPunchRepository extends JpaRepository<MissedPunch, Integer> {

	// report4 data get
	@Query("SELECT m FROM MissedPunch m where m.employee.objId=:eId AND m.punchTimestamp BETWEEN :startDate AND :endDate")
	List<MissedPunch> findByCId(@Param("eId") int eId, @Param("startDate") Timestamp startDate,
			@Param("endDate") Timestamp endDate);

}
