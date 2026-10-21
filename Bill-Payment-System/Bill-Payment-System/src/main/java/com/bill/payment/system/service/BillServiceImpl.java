package com.bill.payment.system.service;

import java.util.List;
import java.util.logging.Logger;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bill.payment.system.dao.BillDAOInt;
import com.bill.payment.system.dto.BillDTO;
import com.bill.payment.system.exception.DuplicateRecordException;




@Service
public class BillServiceImpl implements BillServiceInt {

	private static Logger log = Logger.getLogger(BillServiceImpl.class.getName());

	@Autowired
	private BillDAOInt dao;

	@Autowired
	private JavaMailSenderImpl mailSender;

	@Override
	@Transactional
	public long add(BillDTO dto) throws DuplicateRecordException {
		log.info("BillServiceImpl Add method start");
		long pk = dao.add(dto);
		log.info("BillServiceImpl Add method end");
		return pk;
	}

	@Override
	@Transactional
	public void delete(BillDTO dto) {
		log.info("BillServiceImpl Delete method start");
		dao.delete(dto);
		log.info("BillServiceImpl Delete method end");

	}

	@Override
	@Transactional
	public BillDTO findBypk(long pk) {
		log.info("BillServiceImpl findBypk method start");
		BillDTO dto = dao.findBypk(pk);
		log.info("BillServiceImpl findBypk method end");
		return dto;
	}

	@Override
	@Transactional
	public BillDTO findByBillName(String BillName) {
		log.info("BillServiceImpl findByBillName method start");
		BillDTO dto = dao.findByBillName(BillName);
		log.info("BillServiceImpl findByBillName method end");
		return dto;
	}

	@Override
	@Transactional
	public void update(BillDTO dto) throws DuplicateRecordException {
		log.info("BillServiceImpl update method start");
		
		dao.update(dto);
		log.info("BillServiceImpl update method end");
	}

	@Override
	@Transactional
	public List<BillDTO> list() {
		log.info("BillServiceImpl list method start");
		List<BillDTO> list = dao.list();
		log.info("BillServiceImpl list method end");
		return list;
	}

	@Override
	@Transactional
	public List<BillDTO> list(int pageNo, int pageSize) {
		log.info("BillServiceImpl list method start");
		List<BillDTO> list = dao.list(pageNo, pageSize);
		log.info("BillServiceImpl list method end");
		return list;
	}

	@Override
	@Transactional
	public List<BillDTO> search(BillDTO dto) {
		log.info("BillServiceImpl search method start");
		List<BillDTO> list = dao.search(dto);
		log.info("BillServiceImpl search method end");
		return list;
	}

	@Override
	@Transactional
	public List<BillDTO> search(BillDTO dto, int pageNo, int pageSize) {
		log.info("BillServiceImpl search method start");
		List<BillDTO> list = dao.search(dto, pageNo, pageSize);
		log.info("BillServiceImpl search method end");
		return list;
	}

}
