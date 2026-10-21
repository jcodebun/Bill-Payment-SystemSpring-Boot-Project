package com.bill.payment.system.form;

import javax.validation.constraints.NotEmpty;

import com.bill.payment.system.dto.BaseDTO;
import com.bill.payment.system.dto.HelpDTO;
import com.bill.payment.system.dto.UserDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HelpForm extends BaseForm {

	@NotEmpty(message = "Question is required")
	private String question;
	
	private String comment;
	

	@Override
	public BaseDTO getDTO() {
		HelpDTO bean=new HelpDTO();
		bean.setId(id);
		bean.setQuestion(question);
		bean.setComment(comment);
		bean.setCreatedBy(createdBy);
		bean.setModifiedBy(modifiedBy);
		bean.setCreatedDatetime(createdDateTime);
		bean.setModifiedDatetime(modifiedDateTime);
		return bean;
	}

	@Override
	public void populate(BaseDTO bdto) {
		HelpDTO bean=(HelpDTO) bdto;
		id=bean.getId();
		question=bean.getQuestion();
		comment=bean.getComment();
		createdBy=bean.getCreatedBy();
		modifiedBy=bean.getModifiedBy();
		createdDateTime=bean.getCreatedDatetime();
		modifiedDateTime=bean.getModifiedDatetime();
	}

}
