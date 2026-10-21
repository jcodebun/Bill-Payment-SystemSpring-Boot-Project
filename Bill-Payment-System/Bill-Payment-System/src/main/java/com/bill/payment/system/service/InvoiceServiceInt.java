package com.bill.payment.system.service;

import java.util.List;

import com.bill.payment.system.dto.InvoiceDTO;
import com.bill.payment.system.exception.DuplicateRecordException;


public interface InvoiceServiceInt {

	public long add(InvoiceDTO dto) throws DuplicateRecordException;

	public void delete(InvoiceDTO dto);

	public InvoiceDTO findBypk(long pk);

	public InvoiceDTO findByInvoiceName(String InvoiceName);

	public void update(InvoiceDTO dto) throws DuplicateRecordException;

	public List<InvoiceDTO> list();

	public List<InvoiceDTO> list(int pageNo, int pageSize);

	public List<InvoiceDTO> search(InvoiceDTO dto);

	public List<InvoiceDTO> search(InvoiceDTO dto, int pageNo, int pageSize);


}
