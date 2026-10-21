package com.bill.payment.system.dto;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="VENDOR")
@Getter
@Setter
public class VendorDTO extends BaseDTO{	

	@Column(name="NAME",length = 225)
	private String name;
	
	@Column(name="USER_NAME",length = 225)
	private String userName;
	
	@Column(name="PASSWORD",length = 225)
	private String password;
	
	@Column(name="COMPANY_REG_NO")
	private long companyRegNo;
	
	@Column(name="VENDOR_TYPE",length = 225)
	private String vendorType;
	
	@Column(name="ADDRESS",length = 225)
	private String address;
	
	@Column(name="COUNTRY",length = 225)
	private String country;
	
	@Column(name="STATE",length = 225)
	private String state;
	
	@Column(name="EMAIL_ID",length = 225)
	private String emailId;
	
	@Column(name="CONTACT_NO",length = 225)
	private String contactNo;
	
	@Column(name="WEB_SITE",length = 225)
	private String webSite;
	
	@Column(name="CERTIFICATE_ISSEED_DATE")
	@Temporal(TemporalType.DATE)
	private Date certificateIssuedDate;
	
	@Column(name="CERTIFICATE_VALIDITY_DATE",length = 225)
	@Temporal(TemporalType.DATE)
	private Date certificateValidityDate;
	
	@Column(name="YEAR_OF_ESTABLISHMENT",length = 225)
	private Date yearOfEstablishment;
	
	@Column(name="PAYMENT_GATEWAY",length = 225)
	private String paymentGateWay;
	
	@Column(name="STATUS",length = 255)
	private String status;
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "vendor")
	private Set<BillDTO> bills;

	@Override
	public String getKey() {
		return String.valueOf(id);
	}

	@Override
	public String getValue() {
		return name;
	}

}
