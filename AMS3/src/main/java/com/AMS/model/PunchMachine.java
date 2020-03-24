package com.AMS.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;


@Entity
@Table(name = "PUNCH_MACHINE")
public class PunchMachine {
	
	@Id
	@GeneratedValue
	@Column(name ="OBJ_ID")
	private int objId;
	
	@Version
	@Column(name ="OBJ_VERSION")
	private int objVersion;
	
	@Column(name ="SERIAL_NO",length = 30)
	private String serialNo;
	
	@Column(name ="MACHINE_NAME",length = 30)
	private String machineName;
	
	@Column(name ="MACHINE_LOCATION",length = 30)
	private String machineLocation;

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

	public String getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}

	public String getMachineName() {
		return machineName;
	}

	public void setMachineName(String machineName) {
		this.machineName = machineName;
	}

	public String getMachineLocation() {
		return machineLocation;
	}

	public void setMachineLocation(String machineLocation) {
		this.machineLocation = machineLocation;
	}

	
	
}
