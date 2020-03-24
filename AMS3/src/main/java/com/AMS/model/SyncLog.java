package com.AMS.model;

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

@Entity
@Table(name = "SYNC_LOG")
public class SyncLog {
	
	@Id
	@GeneratedValue
	@Column(name ="OBJ_ID")
	private int objId;
	
	@Version
	@Column(name ="OBJ_VERSION")
	private int objVersion;
	
	@CreationTimestamp
	@Column(name ="SYNC_TIMESTAMP")
	private Date syncTimestamp;
	
	@OneToOne
	@JoinColumn(name ="MACHINE_ID",referencedColumnName = "OBJ_ID")
	private PunchMachine machine;
	
	@OneToOne
	@JoinColumn(name ="SYNC_BY",referencedColumnName = "OBJ_ID")
	private Appuser syncBy;
	
	@Column(name ="SYNC_STATUS")
	private char syncStatus;
	
	@Column(name ="TOTAL_SYNC_RECORD")
	private int totalSyncRecord;

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

	public Date getSyncTimestamp() {
		return syncTimestamp;
	}

	public void setSyncTimestamp(Date syncTimestamp) {
		this.syncTimestamp = syncTimestamp;
	}

	public char getSyncStatus() {
		return syncStatus;
	}

	public void setSyncStatus(char syncStatus) {
		this.syncStatus = syncStatus;
	}

	public int getTotalSyncRecord() {
		return totalSyncRecord;
	}

	public void setTotalSyncRecord(int totalSyncRecord) {
		this.totalSyncRecord = totalSyncRecord;
	}

	public PunchMachine getMachine() {
		return machine;
	}

	public void setMachine(PunchMachine machine) {
		this.machine = machine;
	}

	public Appuser getSyncBy() {
		return syncBy;
	}

	public void setSyncBy(Appuser syncBy) {
		this.syncBy = syncBy;
	}

	
	
}
