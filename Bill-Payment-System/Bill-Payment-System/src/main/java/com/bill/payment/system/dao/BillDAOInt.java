package com.bill.payment.system.dao;

import java.util.List;

import com.bill.payment.system.dto.BillDTO;
import com.bill.payment.system.dto.BillDTO;


public interface BillDAOInt {

	public long add(BillDTO dto);
	
	public void delete(BillDTO dto);
	
	public BillDTO findBypk(long pk);
	
	public BillDTO findByBillName(String BillName);
	
	public void update(BillDTO dto);
	
	public List<BillDTO> list();
	
	public List<BillDTO>list(int pageNo,int pageSize);
	
	public List<BillDTO> search(BillDTO dto);
	
	public List<BillDTO> search(BillDTO dto,int pageNo,int pageSize);
	
}
