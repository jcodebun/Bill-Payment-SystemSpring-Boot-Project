package com.bill.payment.system.form;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import com.bill.payment.system.dto.BaseDTO;
import com.bill.payment.system.dto.BillDTO;
import com.bill.payment.system.util.DataUtility;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BillForm extends BaseForm {

	
	@Min(value = 1,message = "User is required")
	private long userId;
	@NotEmpty(message = "User is required")
	private String amount;
	@NotEmpty(message = "Vendor Type is required")
	private String vendorType;
	
	@Override
	public BaseDTO getDTO() {
		BillDTO bean=new BillDTO();
		bean.setId(id);
		bean.setUserId(userId);
		bean.setAmount(DataUtility.getLong(amount));
		bean.setVendorType(vendorType);
		bean.setCreatedBy(createdBy);
		bean.setModifiedBy(modifiedBy);
		bean.setCreatedDatetime(createdDateTime);
		bean.setModifiedDatetime(modifiedDateTime);
		return bean;
	}

	@Override
	public void populate(BaseDTO bDto) {
		BillDTO bean=(BillDTO)bDto;
		id=bean.getId();
		userId=bean.getUserId();
		amount=String.valueOf(bean.getAmount());
		vendorType=bean.getVendorType();
		createdBy=bean.getCreatedBy();
		modifiedBy=bean.getModifiedBy();
		createdDateTime=bean.getCreatedDatetime();
		modifiedDateTime=bean.getModifiedDatetime();
	}
	
	

}
