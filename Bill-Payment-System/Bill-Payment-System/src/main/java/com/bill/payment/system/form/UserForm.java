package com.bill.payment.system.form;

import javax.persistence.Column;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;

import com.bill.payment.system.dto.BaseDTO;
import com.bill.payment.system.dto.UserDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserForm extends BaseForm {

	@NotEmpty(message = "First Name is required")
	private String firstName;

	@NotEmpty(message = "Last Name is required")
	private String lastName;

	@NotEmpty(message = "User Name is required")
	private String userName;

	@NotEmpty(message = "Password is required")
	private String password;

	@NotEmpty(message = "Confirm Password is required")
	private String confirmPassword;
	
	@NotEmpty(message = "Aadhar No. is required")
	private String aadhar;
	
	@NotEmpty(message = "Age is required")
	private String age;
	
	@NotEmpty(message = "ContactNo is required")
	private String contactNo;
	
	@NotEmpty(message = "Gender is required")
	private String gender;
	
	@NotEmpty(message = "Pan is required")
	private String pan;
	
	
	

	@Override
	public BaseDTO getDTO() {
		UserDTO bean=new UserDTO();
		bean.setId(id);
		bean.setFirstName(firstName);
		bean.setLastName(lastName);
		bean.setUserName(userName);
		bean.setPassword(password);
		bean.setContactNo(contactNo);
		bean.setGender(gender);
		bean.setAadhar(aadhar);
		bean.setAge(age);
		bean.setPan(pan);
		bean.setCreatedBy(createdBy);
		bean.setModifiedBy(modifiedBy);
		bean.setCreatedDatetime(createdDateTime);
		bean.setModifiedDatetime(modifiedDateTime);
		return bean;
	}
	@Override
	public void populate(BaseDTO baseBean) {
		UserDTO bean=(UserDTO)baseBean;
		id=bean.getId();
		firstName=bean.getFirstName();
		lastName=bean.getLastName();
		userName=bean.getUserName();
		password=bean.getPassword();
		contactNo=bean.getContactNo();
		gender=bean.getGender();
		aadhar=bean.getAadhar();
		age=bean.getAge();
		pan=bean.getPan();
		createdBy=bean.getCreatedBy();
		modifiedBy=bean.getModifiedBy();
		createdDateTime=bean.getCreatedDatetime();
		modifiedDateTime=bean.getModifiedDatetime();
	}
}
