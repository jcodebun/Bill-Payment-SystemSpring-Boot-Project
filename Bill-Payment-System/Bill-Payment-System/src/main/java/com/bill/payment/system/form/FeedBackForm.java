package com.bill.payment.system.form;

import javax.validation.constraints.NotEmpty;

import com.bill.payment.system.dto.BaseDTO;
import com.bill.payment.system.dto.FeedBackDTO;
import com.bill.payment.system.dto.HelpDTO;
import com.bill.payment.system.dto.UserDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FeedBackForm extends BaseForm {

	@NotEmpty(message = "FeedBack is required")
	private String feedBack;

	@Override
	public BaseDTO getDTO() {
		FeedBackDTO bean=new FeedBackDTO();
		bean.setId(id);
		bean.setFeedback(feedBack);
		bean.setCreatedBy(createdBy);
		bean.setModifiedBy(modifiedBy);
		bean.setCreatedDatetime(createdDateTime);
		bean.setModifiedDatetime(modifiedDateTime);
		return bean;
	}

	@Override
	public void populate(BaseDTO bdto) {
		FeedBackDTO bean=(FeedBackDTO) bdto;
		id=bean.getId();
		feedBack=bean.getFeedback();
		createdBy=bean.getCreatedBy();
		modifiedBy=bean.getModifiedBy();
		createdDateTime=bean.getCreatedDatetime();
		modifiedDateTime=bean.getModifiedDatetime();
	}

}
