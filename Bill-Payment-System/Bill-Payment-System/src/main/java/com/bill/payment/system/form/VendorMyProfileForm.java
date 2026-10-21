package com.bill.payment.system.form;
import javax.validation.constraints.NotEmpty;

import com.bill.payment.system.dto.BaseDTO;
import com.bill.payment.system.dto.UserDTO;
import com.bill.payment.system.dto.VendorDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VendorMyProfileForm extends BaseForm {

	@NotEmpty(message = "Name is required")
	private String name;

	@NotEmpty(message = "User Name is required")
	private String userName;
	@NotEmpty(message = "ContactNo is required")
	private String contactNo;
	
	@NotEmpty(message = "Email Id is required")
	private String emailId;
	
	
	public BaseDTO getDTO() {
		VendorDTO bean = new VendorDTO();
		bean.setId(id);
		bean.setName(name);
		bean.setUserName(userName);
		bean.setEmailId(emailId);
		bean.setContactNo(contactNo);
		bean.setCreatedBy(createdBy);
		bean.setModifiedBy(modifiedBy);
		bean.setCreatedDatetime(createdDateTime);
		bean.setModifiedDatetime(modifiedDateTime);
		return bean;
	}

	
	public void populate(BaseDTO bDto) {
		VendorDTO bean = (VendorDTO) bDto;
		id=bean.getId();
		name=bean.getName();
		emailId=bean.getEmailId();
		userName=bean.getUserName();
		contactNo=bean.getContactNo();
		createdBy=bean.getCreatedBy();
		modifiedBy=bean.getModifiedBy();
		createdDateTime=bean.getCreatedDatetime();
		modifiedDateTime=bean.getModifiedDatetime();

	}

	

}
