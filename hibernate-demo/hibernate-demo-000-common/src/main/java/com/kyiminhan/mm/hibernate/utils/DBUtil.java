package com.kyiminhan.mm.hibernate.utils;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.apache.commons.collections4.CollectionUtils;
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
		session.beginTransaction();

		final CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		final CriteriaQuery<EmployeeEntity> criteriaQuery = criteriaBuilder.createQuery(EmployeeEntity.class);
		final Root<EmployeeEntity> root = criteriaQuery.from(EmployeeEntity.class);
		criteriaQuery.select(root);
		final List<EmployeeEntity> list = session.createQuery(criteriaQuery).getResultList();

		if (!CollectionUtils.isEmpty(list)) {
			list.forEach(emp -> session.delete(emp));
		}

		session.getTransaction().commit();

		DBUtil.log.info("*********************************** END deleteEmployeeEntity() method " + this.getClass());

	}
}