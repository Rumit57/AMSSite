package com.AMS.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Version;

@Entity
@Table(name = "PUNCH_LOGS")
public class PunchLogs {
	
	@Id
	@GeneratedValue
	@Column(name ="OBJ_ID")
	private int objId;
	
	@Version
	@Column(name ="OBJ_VERSION")
	private int objVersion;
	
	@ManyToOne
	@JoinColumn(name ="EMPLOYEE_ID",referencedColumnName = "OBJ_ID")
	private Employee employee;
	
	@Transient
    private String status;
	
	
	@Column(name ="PUNCH_TIMESTAMP")
	private Timestamp punchTimestamp;
	
	@ManyToOne
	@JoinColumn(name ="MACHINE_ID",referencedColumnName = "OBJ_ID")
	private PunchMachine machine;

	public int getObjId() {
		return objId;
	}

	public void setObjId(int objId) {
		this.objId = objId;
	}

	public int getObjVersion() {
		return objVersion;
	}

	public void setObjVersion(int objVersion) {
		this.objVersion = objVersion;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public PunchMachine getMachine() {
		return machine;
	}

	public void setMachine(PunchMachine machine) {
		this.machine = machine;
	}

	public Timestamp getPunchTimestamp() {
		return punchTimestamp;
	}

	public void setPunchTimestamp(Timestamp punchTimestamp) {
		this.punchTimestamp = punchTimestamp;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}


}
