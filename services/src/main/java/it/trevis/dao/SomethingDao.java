package it.trevis.dao;

import java.io.Serializable;
import java.lang.invoke.MethodHandles;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.trevis.beans.DataExample;

//import it.secservizi.w4.kpi.beans.DataFromHomeBanking;

@Stateless
@LocalBean
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class SomethingDao implements Serializable {

	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass().getSimpleName());

	private static final long serialVersionUID = 1L;

	protected static final String PERSISTENCE_UNIT_NAME = "db_something";

	@PersistenceContext(unitName = PERSISTENCE_UNIT_NAME)
	protected EntityManager entityManager;

	public SomethingDao() {
		logger.trace("SomethingDao: creato oggetto SomethingDao..");
	}

	// save
	public DataExample save(DataExample entity) {
		try {
			entityManager.persist(entity);
		} catch (Exception e) {
			logger.error("Errore nel tentativo di inserire i dati in DB..", e);
		}
		return entity;
	}

}

//	public BigInteger getId(DataFromHomeBanking entity) {
//		return entity.getId();
//	}

// save
//	public DataFromHomeBanking save(DataFromHomeBanking entity) {
//		try {
//			entityManager.persist(entity);
//		}
//		catch(Exception e) {
//			log.error("Errore nel tentativo di inserire i dati in DB..", e);
//		}
//		return entity;
//	}

// read
//	public DataFromHomeBanking read(DataFromHomeBanking entity) {
//		try {	
//			entityManager.find(entity.getClass(), entity.getId());
//		}
//		catch(Exception e) {
//			e.printStackTrace();
//		}
//		return entity;
//	}