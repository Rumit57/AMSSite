package com.AMS.model;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Version;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "EMPLOYEE")
public class Employee {
	
	@Id
	@GeneratedValue
	@Column(name ="OBJ_ID")
	private int objId;
	
	@Version
	@Column(name ="OBJ_VERSION")
	private int objVersion;
	
	@Column(name ="FIRST_NAME",length = 30)
	private String firstName;
	
	@Column(name ="LAST_NAME",length = 30)
	private String lastName;
	
	@Column(name ="ADDRESS1",length = 125)
	private String address1;
	
	@Column(name ="ADDRESS2",length = 130)
	private String address2;
	
	@Column(name ="EMAIL",length = 90)
	private String email;
	
	@Column(name ="MOBILE",length = 15)
	private String mobile;
	
	@Column(name ="GENDER")
	private char gender;
	
	@Column(name ="USER_TYPE",length = 15)
	private String userType;
	
	@Column(name ="PINCODE",length = 10)
	private String pincode;
	
	@Column(name ="CITY",length = 30)
	private String city;
	
	@Column(name ="STATE",length = 30)
	private String state;
	
	@Column(name ="COUNTRY",length = 30)
	private String country;
	
	@Transient
    private String slot1;
	
	@Transient
    private String slot2;
	
	@Transient
    private Timestamp punchTimestamp;
	
	@Transient
    private String timeDifference;
	
	@OneToOne
	@JoinColumn(name ="STATUS_ID",referencedColumnName = "OBJ_ID")
	private Status status;
	
	@ManyToOne
	@JoinColumn(name ="COMPANY_ID",referencedColumnName = "OBJ_ID")
	private Company company;
	
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

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public char getGender() {
		return gender;
	}

	public void setGender(char gender) {
		this.gender = gender;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
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

	public Date getLastUpdatedTimestamp() {
		return lastUpdatedTimestamp;
	}

	public void setLastUpdatedTimestamp(Date lastUpdatedTimestamp) {
		this.lastUpdatedTimestamp = lastUpdatedTimestamp;
	}

	public Date getCreationTimestamp() {
		return creationTimestamp;
	}

	public void setCreationTimestamp(Date creationTimestamp) {
		this.creationTimestamp = creationTimestamp;
	}
	
	public String getSlot1() {
		return slot1;
	}

	public void setSlot1(String slot1) {
		this.slot1 = slot1;
	}

	public String getSlot2() {
		return slot2;
	}

	public void setSlot2(String slot2) {
		this.slot2 = slot2;
	}
	
	public Timestamp getPunchTimestamp() {
		return punchTimestamp;
	}

	public void setPunchTimestamp(Timestamp punchTimestamp) {
		this.punchTimestamp = punchTimestamp;
	}

	public String getTimeDifference() {
		return timeDifference;
	}

	public void setTimeDifference(String timeDifference) {
		this.timeDifference = timeDifference;
	}

	@Override
	public String toString() {
		return "Employee [objId=" + objId + ", objVersion=" + objVersion + ", firstName=" + firstName + ", lastName="
				+ lastName + ", address1=" + address1 + ", address2=" + address2 + ", email=" + email + ", mobile="
				+ mobile + ", userType=" + userType + ", pincode=" + pincode + ", city=" + city + ", state=" + state
				+ ", country=" + country + ", status=" + status + ", createdBy=" + createdBy + ", lastUpdatedBy="
				+ lastUpdatedBy + ", lastUpdatedTimestamp=" + lastUpdatedTimestamp + ", creationTimestamp="
				+ creationTimestamp + "]";
	}


	

	
}