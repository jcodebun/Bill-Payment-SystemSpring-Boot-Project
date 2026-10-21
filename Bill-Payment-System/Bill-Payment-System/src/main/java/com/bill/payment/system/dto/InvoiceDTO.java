package com.bill.payment.system.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="INVOICE")
public class InvoiceDTO extends BaseDTO {
	
	
	@Column(name="INVOICE_NO")
	private long invoiceNo;
	
	@Column(name="PAID_AMOUNT")
	private long paidAmount;
	
	@Column(name="USRE_ID")
	private long userId;
	
	@Column(name="BILL_ID")
	private long  billId;
	
	@ManyToOne
	@JoinColumn(name ="USER" ,nullable = false)
	private UserDTO user;
	
	@ManyToOne
	@JoinColumn(name ="BILL" ,nullable = false)
	private BillDTO bill;
	
	@Column(name="VENDOR_TYPE",length = 225)
	private String vendorType;

	@Override
	public String getKey() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return null;
	}

}
