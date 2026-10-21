package com.bill.payment.system.service;

import java.util.List;

import com.bill.payment.system.dto.VendorDTO;
import com.bill.payment.system.exception.DuplicateRecordException;


public interface VendorServiceInt {

	public long add(VendorDTO dto) throws DuplicateRecordException;

	public void delete(VendorDTO dto);

	public VendorDTO findBypk(long pk);

	public VendorDTO findByUserName(String userName);

	public void update(VendorDTO dto) throws DuplicateRecordException;

	public List<VendorDTO> list();

	public List<VendorDTO> list(int pageNo, int pageSize);

	public List<VendorDTO> search(VendorDTO dto);

	public List<VendorDTO> search(VendorDTO dto, int pageNo, int pageSize);

	public VendorDTO authentication(VendorDTO dto);

	public boolean changePassword(Long id, String oldPassword, String newPassword);

	public boolean forgetPassword(String login);

	public long register(VendorDTO dto) throws DuplicateRecordException;

}
