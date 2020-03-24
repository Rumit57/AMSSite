package com.AMS.model;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;


@Entity
@Table(name = "MISSED_PUNCH")
public class MissedPunch {
	
	@Id
	@GeneratedValue
	@Column(name ="OBJ_ID")
	private int objId;
	
	@Version
	@Column(name ="OBJ_VERSION")
	private int objVersion;
	
	@OneToOne
	@JoinColumn(name ="EMPLOYEE_ID",referencedColumnName = "OBJ_ID")
	private Employee employee;
	
	@OneToOne
	@JoinColumn(name ="MACHINE_ID",referencedColumnName = "OBJ_ID")
	private PunchMachine machine;
	
	@Column(name ="PUNCH_TIMESTAMP")
	private Timestamp punchTimestamp;
	
	@OneToOne
	@JoinColumn(name ="FIXED_BY",referencedColumnName = "OBJ_ID")
	private Appuser fixedBy;
	
	@Column(name ="REASON",length = 150)
	private String reason;
	
	@Column(name ="CREATED_BY")
	private int createdBy;
	
	@Column(name ="LAST_UPDATED_BY")
	private int lastUpdatedBy;
	
	@UpdateTimestamp
	@Column(name ="LAST_UPDATED_TIMESTAMP")
    private Date lastUpdatedTimestamp;
	
	@CreationTimestamp
	@Column(name ="CREATION_TIMESTAMP")
    private Date creationTimestamp;

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

	public Appuser getFixedBy() {
		return fixedBy;
	}

	public void setFixedBy(Appuser fixedBy) {
		this.fixedBy = fixedBy;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public int getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}

	public int getLastUpdatedBy() {
		return lastUpdatedBy;
	}

	public void setLastUpdatedBy(int lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}

	
	
}
