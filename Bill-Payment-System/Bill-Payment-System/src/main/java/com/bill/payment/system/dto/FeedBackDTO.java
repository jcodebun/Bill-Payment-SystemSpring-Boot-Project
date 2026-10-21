package com.bill.payment.system.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="FEED_BACK")
public class FeedBackDTO extends BaseDTO{

	@Column(name="FEEDBACK",length = 225)
	private String feedback;
	
	@Column(name="FEED_BY",length = 225)
	private String feedBy;
	
	@Override
	public String getKey() {
		return null;
	}

	@Override
	public String getValue() {
		return null;
	}

}
