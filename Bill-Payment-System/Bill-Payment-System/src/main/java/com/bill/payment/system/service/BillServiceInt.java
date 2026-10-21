package com.bill.payment.system.service;

import java.util.List;

import com.bill.payment.system.dto.BillDTO;
import com.bill.payment.system.exception.DuplicateRecordException;


public interface BillServiceInt {

	public long add(BillDTO dto) throws DuplicateRecordException;

	public void delete(BillDTO dto);

	public BillDTO findBypk(long pk);

	public BillDTO findByBillName(String BillName);

	public void update(BillDTO dto) throws DuplicateRecordException;

	public List<BillDTO> list();

	public List<BillDTO> list(int pageNo, int pageSize);

	public List<BillDTO> search(BillDTO dto);

	public List<BillDTO> search(BillDTO dto, int pageNo, int pageSize);



}
