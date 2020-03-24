package com.AMS.model;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Version;

@Entity
@Table(name = "LEAVE_AMS")
public class Leave {
	
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
	@JoinColumn(name ="FIXED_BY",referencedColumnName = "OBJ_ID")
	private Appuser fixedBy;
	
	@Column(name ="REASON",length = 150)
	private String reason;
	
	@Column(name ="CREATED_BY")
	private int createdBy;
	
	@Column(name ="LAST_UPDATED_BY")
	private int lastUpdatedBy;
	
	
	@Column(name ="LAST_UPDATED_TIMESTAMP")
    private Timestamp lastUpdatedTimestamp;
	
	
	@Column(name ="CREATION_TIMESTAMP")
    private Timestamp creationTimestamp;
	
	@Transient
    private String punchTimestamp;

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

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Appuser getFixedBy() {
		return fixedBy;
	}

	public void setFixedBy(Appuser fixedBy) {
		this.fixedBy = fixedBy;
	}

	public Timestamp getLastUpdatedTimestamp() {
		return lastUpdatedTimestamp;
	}

	public void setLastUpdatedTimestamp(Timestamp lastUpdatedTimestamp) {
		this.lastUpdatedTimestamp = lastUpdatedTimestamp;
	}

	public Timestamp getCreationTimestamp() {
		return creationTimestamp;
	}

	public void setCreationTimestamp(Timestamp creationTimestamp) {
		this.creationTimestamp = creationTimestamp;
	}

	public String getPunchTimestamp() {
		return punchTimestamp;
	}

	public void setPunchTimestamp(String punchTimestamp) {
		this.punchTimestamp = punchTimestamp;
	}

	
	
}
