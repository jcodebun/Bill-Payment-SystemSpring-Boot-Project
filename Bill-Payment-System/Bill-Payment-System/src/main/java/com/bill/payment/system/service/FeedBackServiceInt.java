package com.bill.payment.system.service;

import java.util.List;

import com.bill.payment.system.dto.FeedBackDTO;
import com.bill.payment.system.exception.DuplicateRecordException;


public interface FeedBackServiceInt {

	public long add(FeedBackDTO dto) throws DuplicateRecordException;

	public void delete(FeedBackDTO dto);

	public FeedBackDTO findBypk(long pk);

	public FeedBackDTO findByFeedBackName(String FeedBackName);

	public void update(FeedBackDTO dto) throws DuplicateRecordException;

	public List<FeedBackDTO> list();

	public List<FeedBackDTO> list(int pageNo, int pageSize);

	public List<FeedBackDTO> search(FeedBackDTO dto);

	public List<FeedBackDTO> search(FeedBackDTO dto, int pageNo, int pageSize);


}
