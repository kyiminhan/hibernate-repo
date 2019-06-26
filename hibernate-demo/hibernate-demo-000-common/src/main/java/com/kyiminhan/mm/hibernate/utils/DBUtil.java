package com.kyiminhan.mm.hibernate.utils;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.Root;

import org.hibernate.Session;

import com.kyiminhan.mm.hibernate.entity.EmployeeEntity;

import lombok.extern.log4j.Log4j2;

/**
 * The Class DBUtil.<BR>
 *
 * @author KYIMINHAN <BR>
 * @version 1.0 <BR>
 * @since Jun 26, 2019 <BR>
 *        hibernate-demo-000-common system <BR>
 *        com.kyiminhan.mm.hibernate.utils <BR>
 *        DBUtil.java <BR>
 */
@Log4j2
public class DBUtil {

	/** The instance. */
	private volatile static DBUtil instance = null;

	/**
	 * Instantiates a new DB util.
	 */
	private DBUtil() {
	}

	public static DBUtil getInstance() {
		if (null == DBUtil.instance) {
			synchronized (DBUtil.class) {
				DBUtil.instance = new DBUtil();
			}
		}
		return DBUtil.instance;
	}

	/**
	 * Delete employee entity.
	 */
	public void deleteEmployeeEntity() {

		DBUtil.log.info("*********************************** START deleteEmployeeEntity() method " + this.getClass());

		final Session session = HibernateUtil.getSessionFactory().openSession();

		final EntityManager em = session.getEntityManagerFactory().createEntityManager();
		em.getTransaction().begin();
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaDelete<EmployeeEntity> criteriaDelete = cb.createCriteriaDelete(EmployeeEntity.class);
		@SuppressWarnings("unused")
		final Root<EmployeeEntity> root = criteriaDelete.from(EmployeeEntity.class);
		em.createQuery(criteriaDelete).executeUpdate();
		em.getTransaction().commit();

		DBUtil.log.info("*********************************** END deleteEmployeeEntity() method " + this.getClass());

	}
}