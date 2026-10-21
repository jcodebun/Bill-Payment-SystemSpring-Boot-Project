package com.bill.payment.system.form;


import javax.validation.constraints.NotEmpty;

import com.bill.payment.system.dto.BaseDTO;
import com.bill.payment.system.dto.UserDTO;
import com.bill.payment.system.dto.VendorDTO;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class VendorForgetPasswordForm extends BaseForm {

	@NotEmpty(message = "User Name is required")
	private String userName;
	
	@Override
	public BaseDTO getDTO() {
		VendorDTO dto = new VendorDTO();
		dto.setUserName(userName);
		return dto;
	}

	@Override
	public void populate(BaseDTO bDto) {
		
	}

}
