package com.bill.payment.system.dao;

import java.util.List;

import com.bill.payment.system.dto.VendorDTO;


public interface VendorDAOInt {

	public long add(VendorDTO dto);
	
	public void delete(VendorDTO dto);
	
	public VendorDTO findBypk(long pk);
	
	public VendorDTO findByUserName(String userName);
	
	public void update(VendorDTO dto);
	
	public List<VendorDTO> list();
	
	public List<VendorDTO>list(int pageNo,int pageSize);
	
	public List<VendorDTO> search(VendorDTO dto);
	
	public List<VendorDTO> search(VendorDTO dto,int pageNo,int pageSize);
	
	public VendorDTO authentication(VendorDTO dto);
	
}
