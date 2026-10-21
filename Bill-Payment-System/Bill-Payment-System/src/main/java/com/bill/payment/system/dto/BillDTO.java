package com.bill.payment.system.dto;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="BILL")
@Getter
@Setter
public class BillDTO extends BaseDTO{
	
	@Column(name = "USER_ID")
	private long userId;
	@Column(name="VENDOR_ID")
	private long vendorId;
	
	@Column(name="STATUS",length = 255)
	private String status;
	@Column(name="DATE")
	@Temporal(TemporalType.DATE)
	private Date date;
	
	@ManyToOne
	@JoinColumn(name ="USER" ,nullable = false)
	private UserDTO user;
	
	@ManyToOne
	@JoinColumn(name ="VENDOR" ,nullable = false)
	private VendorDTO vendor;
	
	@Column(name="AMOUNT")
	private long amount;
	
	@Column(name="VENDOR_TYPE",length = 225)
	private String vendorType;
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "bill")
	private Set<InvoiceDTO> invoices;

	@Override
	public String getKey() {
		return null;
	}

	@Override
	public String getValue() {
		return null;
	}
	

}
