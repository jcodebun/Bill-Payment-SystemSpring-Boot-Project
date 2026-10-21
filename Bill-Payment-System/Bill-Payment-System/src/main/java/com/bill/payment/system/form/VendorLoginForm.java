package com.bill.payment.system.form;

import javax.validation.constraints.NotEmpty;

import com.bill.payment.system.dto.BaseDTO;
import com.bill.payment.system.dto.UserDTO;
import com.bill.payment.system.dto.VendorDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VendorLoginForm extends BaseForm {

	@NotEmpty(message = "User Name is required")
	private String userName;
	@NotEmpty(message = "Password is required")
	private String password;
	

	@Override
	public BaseDTO getDTO() {
		VendorDTO bean=new VendorDTO();
		bean.setUserName(userName);
		bean.setPassword(password);
		return bean;
	}

	@Override
	public void populate(BaseDTO bdto) {
		VendorDTO entity=(VendorDTO) bdto;
		userName=entity.getUserName();
		password=entity.getPassword();
	}

}
