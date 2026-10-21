package com.bill.payment.system.dto;


import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "USER")
@Getter
@Setter
public class UserDTO extends BaseDTO {

	@Column(name = "FIRST_NAME", length = 225)
	private String firstName;

	@Column(name = "LAST_NAME", length = 225)
	private String lastName;

	@Column(name = "USER_NAME", length = 225)
	private String userName;

	@Column(name = "PASSWORD", length = 225)
	private String password;

	@Transient
	private String confirmPassword;
	
	@Column(name="AADHAR",length = 225)
	private String aadhar;
	
	@Column(name="AGE",length = 225)
	private String age;
	
	@Column(name="CONTACT_NO",length = 225)
	private String contactNo;
	
	@Column(name="GEDNER",length = 225)
	private String gender;
	
	@Column(name="PAN",length = 225)
	private String pan;

	@Column(name = "ROLE_ID", length = 225)
	private long roleId;

	@OneToMany(cascade = CascadeType.ALL,mappedBy = "user")
	private Set<BillDTO> bills;
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "user")
	private Set<InvoiceDTO> invoices;
	

	public String getKey() {
		return id + "";
	}

	public String getValue() {

		return firstName + " " + lastName;
	}


}
