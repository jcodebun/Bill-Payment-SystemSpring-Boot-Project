package com.bill.payment.system.dao;

import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bill.payment.system.dto.VendorDTO;



@Repository
public class VendorDAOImpl implements VendorDAOInt {

	private static Logger log = Logger.getLogger(VendorDAOImpl.class.getName());

	@Autowired
	private EntityManager entityManager;

	@Override
	public long add(VendorDTO dto) {
		log.info("VendorDAOImpl Add method Start");
		Session session = entityManager.unwrap(Session.class);
		long pk = (long) session.save(dto);
		log.info("VendorDAOImpl Add method End");
		return pk;
	}

	@Override
	public void delete(VendorDTO dto) {
		log.info("VendorDAOImpl Delete method Start");
		entityManager.remove(entityManager.contains(dto) ? dto : entityManager.merge(dto));
		log.info("VendorDAOImpl Delete method End");

	}

	@Override
	public VendorDTO findBypk(long pk) {
		log.info("VendorDAOImpl FindByPk method Start");
		Session session = entityManager.unwrap(Session.class);
		VendorDTO dto = (VendorDTO) session.get(VendorDTO.class, pk);
		log.info("VendorDAOImpl FindByPk method End");
		return dto;
	}

	@Override
	public VendorDTO findByUserName(String userName) {
		log.info("VendorDAOImpl FindByLogin method Start");
		Session session = entityManager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(VendorDTO.class);
		criteria.add(Restrictions.eq("userName",userName));
		VendorDTO dto = (VendorDTO) criteria.uniqueResult();
		log.info("VendorDAOImpl FindByLogin method End");
		return dto;
	}

	@Override
	public void update(VendorDTO dto) {
		log.info("VendorDAOImpl Update method Start");
		Session session = entityManager.unwrap(Session.class);
		session.merge(dto);
		log.info("VendorDAOImpl update method End");
	}

	@Override
	public List<VendorDTO> list() {
		return list(0, 0);
	}

	@Override
	public List<VendorDTO> list(int pageNo, int pageSize) {
		log.info("VendorDAOImpl List method Start");
		Session session = entityManager.unwrap(Session.class);
		Query<VendorDTO> query = session.createQuery("from VendorDTO", VendorDTO.class);
		List<VendorDTO> list = query.getResultList();
		log.info("VendorDAOImpl List method End");
		return list;
	}

	@Override
	public List<VendorDTO> search(VendorDTO dto) {
		return search(dto, 0, 0);
	}

	@Override
	public List<VendorDTO> search(VendorDTO dto, int pageNo, int pageSize) {
		log.info("VendorDAOImpl Search method Start");
		Session session = entityManager.unwrap(Session.class);
		StringBuffer hql = new StringBuffer("from VendorDTO as u where 1=1 ");
		if (dto != null) {
			if (dto.getId() > 0) {
				hql.append("and u.id = " + dto.getId());
			}
			
			if (dto.getCompanyRegNo() > 0) {
				hql.append("and u.companyRegNo = " + dto.getCompanyRegNo());
			}
			if (dto.getName() != null && dto.getName().length() > 0) {
				hql.append("and u.name like '%" + dto.getName() + "%'");
			}
			
			if (dto.getEmailId() != null && dto.getEmailId().length() > 0) {
				hql.append("and u.emailId like '%" + dto.getEmailId() + "%'");
			}
			
			
		
			
		
		}
		Query<VendorDTO> query = session.createQuery(hql.toString(), VendorDTO.class);
		if (pageNo > 0) {
			pageNo = (pageNo - 1) * pageSize;
			query.setFirstResult(pageNo);
			query.setMaxResults(pageSize);
		}
		List<VendorDTO> list = query.getResultList();
		log.info("VendorDAOImpl Search method End");
		return list;
	}

	@Override
	public VendorDTO authentication(VendorDTO dto) {
		log.info("VendorDAOImpl Authentication method Start");
		Session session = entityManager.unwrap(Session.class);
		Query<VendorDTO> query = session.createQuery("from VendorDTO where userName=:login and password=:password",
				VendorDTO.class);
		query.setParameter("login", dto.getUserName());
		query.setParameter("password", dto.getPassword());
		dto = null;
		try {
			dto = query.getSingleResult();
		} catch (NoResultException nre) {
		}
		log.info("VendorDAOImpl Authentication method End");
		return dto;
	}
}
