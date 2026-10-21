package com.bill.payment.system.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
@Entity
@Getter
@Setter
@Table(name="HELP")
public class HelpDTO extends BaseDTO {
	
	@Column(name="QUESTIONS",length = 755)
	private String question;
	@Column(name="COMMENT",length = 2000)
	private String comment;
	
	@Override
	public String getKey() {
		return null;
	}
	@Override
	public String getValue() {
		return null;
	}

	
}
