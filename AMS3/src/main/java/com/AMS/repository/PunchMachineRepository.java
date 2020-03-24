package com.AMS.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.AMS.model.PunchMachine;

@Repository
public interface PunchMachineRepository extends JpaRepository<PunchMachine, Integer> {

	

}
