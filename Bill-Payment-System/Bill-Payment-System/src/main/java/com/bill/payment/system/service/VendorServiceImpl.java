package com.bill.payment.system.service;

import java.util.List;
import java.util.logging.Logger;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.bill.payment.system.dao.VendorDAOInt;
import com.bill.payment.system.dto.VendorDTO;
import com.bill.payment.system.exception.DuplicateRecordException;




@Service
public class VendorServiceImpl implements VendorServiceInt {

	private static Logger log = Logger.getLogger(VendorServiceImpl.class.getName());

	@Autowired
	private VendorDAOInt dao;

	@Autowired
	private JavaMailSenderImpl mailSender;

	@Override
	@Transactional
	public long add(VendorDTO dto) throws DuplicateRecordException {
		log.info("VendorServiceImpl Add method start");
		VendorDTO existDTO = dao.findByUserName(dto.getUserName());
		if (existDTO != null)
			throw new DuplicateRecordException("User Name Id Already Exist");
		long pk = dao.add(dto);
		log.info("VendorServiceImpl Add method end");
		return pk;
	}

	@Override
	@Transactional
	public void delete(VendorDTO dto) {
		log.info("VendorServiceImpl Delete method start");
		dao.delete(dto);
		log.info("VendorServiceImpl Delete method end");

	}

	@Override
	@Transactional
	public VendorDTO findBypk(long pk) {
		log.info("VendorServiceImpl findBypk method start");
		VendorDTO dto = dao.findBypk(pk);
		log.info("VendorServiceImpl findBypk method end");
		return dto;
	}

	@Override
	@Transactional
	public VendorDTO findByUserName(String userName) {
		log.info("VendorServiceImpl findByVendorName method start");
		VendorDTO dto = dao.findByUserName(userName);
		log.info("VendorServiceImpl findByVendorName method end");
		return dto;
	}

	@Override
	@Transactional
	public void update(VendorDTO dto) throws DuplicateRecordException {
		log.info("VendorServiceImpl update method start");
		VendorDTO existDTO = dao.findByUserName(dto.getUserName());
		if (existDTO != null && dto.getId() != existDTO.getId())
			throw new DuplicateRecordException("User Name  Already Exist");
		dao.update(dto);
		log.info("VendorServiceImpl update method end");
	}

	@Override
	@Transactional
	public List<VendorDTO> list() {
		log.info("VendorServiceImpl list method start");
		List<VendorDTO> list = dao.list();
		log.info("VendorServiceImpl list method end");
		return list;
	}

	@Override
	@Transactional
	public List<VendorDTO> list(int pageNo, int pageSize) {
		log.info("VendorServiceImpl list method start");
		List<VendorDTO> list = dao.list(pageNo, pageSize);
		log.info("VendorServiceImpl list method end");
		return list;
	}

	@Override
	@Transactional
	public List<VendorDTO> search(VendorDTO dto) {
		log.info("VendorServiceImpl search method start");
		List<VendorDTO> list = dao.search(dto);
		log.info("VendorServiceImpl search method end");
		return list;
	}

	@Override
	@Transactional
	public List<VendorDTO> search(VendorDTO dto, int pageNo, int pageSize) {
		log.info("VendorServiceImpl search method start");
		List<VendorDTO> list = dao.search(dto, pageNo, pageSize);
		log.info("VendorServiceImpl search method end");
		return list;
	}

	@Override
	@Transactional
	public VendorDTO authentication(VendorDTO dto) {
		log.info("VendorServiceImpl authentication method start");
		dto = dao.authentication(dto);
		log.info("VendorServiceImpl authentication method end");
		return dto;
	}

	@Override
	@Transactional
	public boolean changePassword(Long id, String oldPassword, String newPassword) {
		log.info("VendorServiceImpl  changePassword method start");
		VendorDTO dtoExist = findBypk(id);
		if (dtoExist != null && dtoExist.getPassword().equals(oldPassword)) {
			dtoExist.setPassword(newPassword);
			dao.update(dtoExist);
			log.info("VendorServiceImpl  changePassword method end");
			return true;
		} else {
			return false;
		}
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public boolean forgetPassword(String email) {

		VendorDTO dtoExist = dao.findByUserName(email);

		/*
		 * if (dtoExist != null) {
		 * 
		 * HashMap<String, String> map = new HashMap<String, String>();
		 * map.put("firstName", dtoExist.getFirstName()); map.put("lastName",
		 * dtoExist.getLastName()); map.put("login", dtoExist.getVendorName());
		 * map.put("password", dtoExist.getPassword());
		 * 
		 * String message = EmailBuilder.getForgetPasswordMessage(map);
		 * 
		 * MimeMessage msg = mailSender.createMimeMessage();
		 * 
		 * try { MimeMessageHelper helper = new MimeMessageHelper(msg);
		 * helper.setTo(dtoExist.getVendorName());
		 * helper.setSubject("E-Learning Forget Password "); helper.setText(message,
		 * true); mailSender.send(msg); } catch (MessagingException e) {
		 * e.printStackTrace(); return false; } } else { return false; }
		 */		return true;
	}

	@Override
	public long register(VendorDTO dto) throws DuplicateRecordException {
		long pk = dao.add(dto);
		/*
		 * HashMap<String, String> map = new HashMap<String, String>();
		 * map.put("firstName", dto.getFirstName()); map.put("lastName",
		 * dto.getLastName()); map.put("login", dto.getLogin()); map.put("password",
		 * dto.getPassword()); String message = EmailBuilder.getVendorRegistration(map);
		 * MimeMessage msg = mailSender.createMimeMessage(); try { MimeMessageHelper
		 * helper = new MimeMessageHelper(msg); helper.setTo(dto.getEmailId());
		 * helper.setSubject("Online Food Delivery Registration Successfully!!!");
		 * helper.setText(message, true); mailSender.send(msg); } catch
		 * (MessagingException e) { e.printStackTrace(); }
		 */
		return pk;
	}
}
