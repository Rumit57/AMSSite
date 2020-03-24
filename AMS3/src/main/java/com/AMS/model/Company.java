package com.AMS.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "COMPANY")
public class Company {
	
	@Id
	@GeneratedValue
	@Column(name ="OBJ_ID")
	private int objId;
	
	@Version
	@Column(name ="OBJ_VERSION")
	private int objVersion;
	
	@Column(name ="NAME",length = 50)
	private String name;
	
	@Column(name ="TAX_ID",length = 20)
	private String taxId;
	
	@Column(name ="COMPANY_EMAIL",length = 90)
	private String companyEmail;
	
	@Column(name ="ADDRESS1",length = 125)
	private String address1;
	
	@Column(name ="ADDRESS2",length = 130)
	private String address2;
	
	@Column(name ="PHONE_NUMBER",length = 15)
	private String phoneNumber;
	
	@Column(name ="CITY",length = 30)
	private String city;
	
	@Column(name ="STATE",length = 30)
	private String state;
	
	@Column(name ="COUNTRY",length = 30)
	private String country;
	
	@Column(name ="PINCODE",length = 15)
	private String pincode;
	
	@ManyToOne
	@JoinColumn(name ="STATUS_ID",referencedColumnName = "OBJ_ID")
	private Status status;
	
	@Column(name ="COMPANY_PHOTO",length = 30)
	private String companyPhoto;
	
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTaxId() {
		return taxId;
	}

	public void setTaxId(String taxId) {
		this.taxId = taxId;
	}

	public String getCompanyEmail() {
		return companyEmail;
	}

	public void setCompanyEmail(String companyEmail) {
		this.companyEmail = companyEmail;
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

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
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

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
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

	public String getCompanyPhoto() {
		return companyPhoto;
	}

	public void setCompanyPhoto(String companyPhoto) {
		this.companyPhoto = companyPhoto;
	}

	

	@Override
	public String toString() {
		return "Company [objId=" + objId + ", objVersion=" + objVersion + ", name=" + name
				+ ", taxId=" + taxId + ", companyEmail=" + companyEmail + ", address1=" + address1 + ", address2="
				+ address2 + ", phoneNumber=" + phoneNumber + ", city=" + city + ", state=" + state + ", country="
				+ country + ", pincode=" + pincode + ", status=" + status + ", companyPhoto=" + companyPhoto
				+ ", createdBy=" + createdBy + ", lastUpdatedBy=" + lastUpdatedBy + ", lastUpdatedTimestamp="
				+ lastUpdatedTimestamp + ", creationTimestamp=" + creationTimestamp + "]";
	}

	

	
	
}
