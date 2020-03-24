package com.AMS.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "APPUSER")
public class Appuser {
	
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

	@Column(name ="EMAIL",length = 90)
	private String email;
	
	@Column(name ="PASSWORD",length = 40)
	private String password;
	
	@Column(name ="CODE",length = 10)
	private String code;
	
	@Column(name ="CODE_STATUS",length = 10)
	private String codeStatus;

	@ManyToOne
	@JoinColumn(name ="STATUS_ID",referencedColumnName = "OBJ_ID")
	private Status status;
	
	@OneToOne
	@JoinColumn(name ="ROLE_ID",referencedColumnName = "OBJ_ID")
	private Role role;
	
	@ManyToOne
	@JoinColumn(name ="COMPANY_ID",referencedColumnName = "OBJ_ID")
	private Company company;
	
	@Column(name ="MOBILE",length = 15)
	private String mobile;
	
	@Column(name ="GENDER")
	private char gender;
	
	@OneToOne
	@JoinColumn(name ="DESIGNATION_ID",referencedColumnName = "OBJ_ID")
	private Designation designation;

	@Column(name ="ADDRESS1",length = 125)
	private String address1;
	
	@Column(name ="ADDRESS2",length = 130)
	private String address2;
	
	@Column(name ="PINCODE",length = 10)
	private String pincode;
	
	@Column(name ="CITY",length = 30)
	private String city;
	
	@Column(name ="STATE",length = 30)
	private String state;
	
	@Column(name ="COUNTRY",length = 30)
	private String country;
	
	@Column(name ="PROFILE_PHOTO",length = 30)
	private String profilePhoto;

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCodeStatus() {
		return codeStatus;
	}

	public void setCodeStatus(String codeStatus) {
		this.codeStatus = codeStatus;
	}

	
	
	
	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public char getGender() {
		return gender;
	}

	public void setGender(char gender) {
		this.gender = gender;
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

	public void setAddress2(String address2) {
		this.address2 = address2;
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

	public String getProfilePhoto() {
		return profilePhoto;
	}

	public void setProfilePhoto(String profilePhoto) {
		this.profilePhoto = profilePhoto;
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

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Designation getDesignation() {
		return designation;
	}

	public void setDesignation(Designation designation) {
		this.designation = designation;
	}

	@Override
	public String toString() {
		return "Appuser [objId=" + objId + ", objVersion=" + objVersion + ", firstName=" + firstName + ", lastName="
				+ lastName + ", email=" + email + ", password=" + password + ", code=" + code + ", codeStatus="
				+ codeStatus + ", status=" + status + ", role=" + role + ", company=" + company + ", mobile=" + mobile
				+ ", gender=" + gender + ", designation=" + designation + ", address1=" + address1 + ", address2="
				+ address2 + ", pincode=" + pincode + ", city=" + city + ", state=" + state + ", country=" + country
				+ ", profilePhoto=" + profilePhoto + ", createdBy=" + createdBy + ", lastUpdatedBy=" + lastUpdatedBy
				+ ", lastUpdatedTimestamp=" + lastUpdatedTimestamp + ", creationTimestamp=" + creationTimestamp + "]";
	}

	

	
}
