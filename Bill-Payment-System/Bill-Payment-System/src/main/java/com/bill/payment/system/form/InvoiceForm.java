package com.bill.payment.system.form;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;

import com.bill.payment.system.dto.BaseDTO;
import com.bill.payment.system.dto.BillDTO;
import com.bill.payment.system.dto.HelpDTO;
import com.bill.payment.system.dto.InvoiceDTO;
import com.bill.payment.system.dto.UserDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InvoiceForm extends BaseForm {

	private long invoiceNo;
	
	private long paidAmount;
	
	private long userId;
	
	private long  billId;
	
	private String vendorType;
	

	@Override
	public BaseDTO getDTO() {
		InvoiceDTO bean=new InvoiceDTO();
		bean.setId(id);
		bean.setInvoiceNo(invoiceNo);
		bean.setPaidAmount(paidAmount);
		bean.setUserId(userId);
		bean.setBillId(billId);
		bean.setVendorType(vendorType);
		bean.setCreatedBy(createdBy);
		bean.setModifiedBy(modifiedBy);
		bean.setCreatedDatetime(createdDateTime);
		bean.setModifiedDatetime(modifiedDateTime);
		return bean;
	}

	@Override
	public void populate(BaseDTO bdto) {
		InvoiceDTO bean=(InvoiceDTO) bdto;
		id=bean.getId();
		invoiceNo=bean.getInvoiceNo();
		paidAmount=bean.getPaidAmount();
		userId=bean.getUserId();
		billId=bean.getBillId();
		createdBy=bean.getCreatedBy();
		modifiedBy=bean.getModifiedBy();
		createdDateTime=bean.getCreatedDatetime();
		modifiedDateTime=bean.getModifiedDatetime();
	}

}
