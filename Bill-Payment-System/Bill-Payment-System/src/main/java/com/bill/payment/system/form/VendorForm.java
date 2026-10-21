package com.bill.payment.system.form;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;

import com.bill.payment.system.dto.BaseDTO;
import com.bill.payment.system.dto.UserDTO;
import com.bill.payment.system.dto.VendorDTO;
import com.bill.payment.system.util.DataUtility;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VendorForm extends BaseForm {

	@NotEmpty(message = "Name is required")
	private String name;
	@NotEmpty(message = "User Name is required")
	private String userName;
	@NotEmpty(message = "Password is required")
	private String password;
	@NotEmpty(message = "Confirm Password is required")
	private String confirmPassword;
	@NotEmpty(message = "Company Registration No is required")
	private String companyRegNo;
	@NotEmpty(message = "Vendor Type is required")
	private String vendorType;
	@NotEmpty(message = "Address is required")
	private String address;
	@NotEmpty(message = "Country is required")
	private String country;
	@NotEmpty(message = "State is required")
	private String state;
	@NotEmpty(message = "Email Id is required")
	private String emailId;
	@NotEmpty(message = "Contact No is required")
	private String contactNo;
	@NotEmpty(message = "Web Site is required")
	private String webSite;
	@NotEmpty(message = "Certificate Issues Date is required")
	private String certificateIssuedDate;
	@NotEmpty(message = "Certification Validity Date is required")
	private String certificateValidityDate;
	@NotEmpty(message = "Year of Establishment is required")
	private String yearOfEstablishment;
	@NotEmpty(message = "PaymentGateWay is required")
	private String paymentGateWay;

	@Override
	public BaseDTO getDTO() {
		VendorDTO bean=new VendorDTO();
		bean.setId(id);
		bean.setName(name);
		bean.setUserName(userName);
		bean.setPassword(password);
		bean.setContactNo(contactNo);
		bean.setCompanyRegNo(DataUtility.getLong(companyRegNo));
		bean.setVendorType(vendorType);
		bean.setAddress(address);
		bean.setCountry(country);
		bean.setState(state);
		bean.setEmailId(emailId);
		bean.setWebSite(webSite);
		bean.setCertificateIssuedDate(DataUtility.getDate(certificateIssuedDate));
		bean.setCertificateValidityDate(DataUtility.getDate(certificateValidityDate));
		bean.setYearOfEstablishment(DataUtility.getDate(yearOfEstablishment));
		bean.setPaymentGateWay(paymentGateWay);
		bean.setCreatedBy(createdBy);
		bean.setModifiedBy(modifiedBy);
		bean.setCreatedDatetime(createdDateTime);
		bean.setModifiedDatetime(modifiedDateTime);
		return bean;
	}
	@Override
	public void populate(BaseDTO baseBean) {
		VendorDTO bean=(VendorDTO)baseBean;
		id=bean.getId();
		name=bean.getName();
		userName=bean.getUserName();
		password=bean.getPassword();
		contactNo=bean.getContactNo();
		companyRegNo=String.valueOf(bean.getCompanyRegNo());
		vendorType=bean.getVendorType();
		address=bean.getAddress();
		country=bean.getCountry();
		state=bean.getState();
		emailId=bean.getEmailId();
		webSite=bean.getWebSite();
		certificateIssuedDate=DataUtility.getDateString(bean.getCertificateIssuedDate());
		certificateValidityDate=DataUtility.getDateString(bean.getCertificateValidityDate());
		yearOfEstablishment=DataUtility.getDateString(bean.getYearOfEstablishment());
		paymentGateWay=bean.getPaymentGateWay();
		createdBy=bean.getCreatedBy();
		modifiedBy=bean.getModifiedBy();
		createdDateTime=bean.getCreatedDatetime();
		modifiedDateTime=bean.getModifiedDatetime();
	}
}
