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

import com.bill.payment.system.dto.InvoiceDTO;



@Repository
public class InvoiceDAOImpl implements InvoiceDAOInt {

	private static Logger log = Logger.getLogger(InvoiceDAOImpl.class.getName());

	@Autowired
	private EntityManager entityManager;

	@Override
	public long add(InvoiceDTO dto) {
		log.info("InvoiceDAOImpl Add method Start");
		Session session = entityManager.unwrap(Session.class);
		long pk = (long) session.save(dto);
		log.info("InvoiceDAOImpl Add method End");
		return pk;
	}

	@Override
	public void delete(InvoiceDTO dto) {
		log.info("InvoiceDAOImpl Delete method Start");
		entityManager.remove(entityManager.contains(dto) ? dto : entityManager.merge(dto));
		log.info("InvoiceDAOImpl Delete method End");

	}

	@Override
	public InvoiceDTO findBypk(long pk) {
		log.info("InvoiceDAOImpl FindByPk method Start");
		Session session = entityManager.unwrap(Session.class);
		InvoiceDTO dto = (InvoiceDTO) session.get(InvoiceDTO.class, pk);
		log.info("InvoiceDAOImpl FindByPk method End");
		return dto;
	}

	@Override
	public InvoiceDTO findByInvoiceName(String InvoiceName) {
		log.info("InvoiceDAOImpl FindByLogin method Start");
		Session session = entityManager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(InvoiceDTO.class);
		criteria.add(Restrictions.eq("InvoiceName",InvoiceName));
		InvoiceDTO dto = (InvoiceDTO) criteria.uniqueResult();
		log.info("InvoiceDAOImpl FindByLogin method End");
		return dto;
	}

	@Override
	public void update(InvoiceDTO dto) {
		log.info("InvoiceDAOImpl Update method Start");
		Session session = entityManager.unwrap(Session.class);
		session.merge(dto);
		log.info("InvoiceDAOImpl update method End");
	}

	@Override
	public List<InvoiceDTO> list() {
		return list(0, 0);
	}

	@Override
	public List<InvoiceDTO> list(int pageNo, int pageSize) {
		log.info("InvoiceDAOImpl List method Start");
		Session session = entityManager.unwrap(Session.class);
		Query<InvoiceDTO> query = session.createQuery("from InvoiceDTO", InvoiceDTO.class);
		List<InvoiceDTO> list = query.getResultList();
		log.info("InvoiceDAOImpl List method End");
		return list;
	}

	@Override
	public List<InvoiceDTO> search(InvoiceDTO dto) {
		return search(dto, 0, 0);
	}

	@Override
	public List<InvoiceDTO> search(InvoiceDTO dto, int pageNo, int pageSize) {
		log.info("InvoiceDAOImpl Search method Start");
		Session session = entityManager.unwrap(Session.class);
		StringBuffer hql = new StringBuffer("from InvoiceDTO as u where 1=1 ");
		if (dto != null) {
			if (dto.getId() > 0) {
				hql.append("and u.id = " + dto.getId());
			}
			if (dto.getInvoiceNo() > 0) {
				hql.append("and u.invoiceNo = " + dto.getInvoiceNo());
			}
			
			
		}
		Query<InvoiceDTO> query = session.createQuery(hql.toString(), InvoiceDTO.class);
		if (pageNo > 0) {
			pageNo = (pageNo - 1) * pageSize;
			query.setFirstResult(pageNo);
			query.setMaxResults(pageSize);
		}
		List<InvoiceDTO> list = query.getResultList();
		log.info("InvoiceDAOImpl Search method End");
		return list;
	}
}
