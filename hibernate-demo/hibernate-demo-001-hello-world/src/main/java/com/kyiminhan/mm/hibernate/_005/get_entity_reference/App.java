package com.kyiminhan.mm.hibernate._005.get_entity_reference;

import org.hibernate.Session;

import com.kyiminhan.mm.hibernate.entity.EmployeeEntity;
import com.kyiminhan.mm.hibernate.utils.DBUtil;
import com.kyiminhan.mm.hibernate.utils.EntityUtil;
import com.kyiminhan.mm.hibernate.utils.HibernateUtil;

import lombok.extern.log4j.Log4j2;

/**
 * The Class App.<BR>
 *
 * @author KYIMINHAN <BR>
 * @version 1.0 <BR>
 * @since Jun 26, 2019 <BR>
 *        hibernate-demo-001-hello-world system <BR>
 *        com.kyiminhan.mm.hibernate._005.get_entity_reference <BR>
 *        App.java <BR>
 */
@Log4j2
public class App {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(final String[] args) {

		App.log.info("*********************************** START main() method " + App.class);

		DBUtil.getInstance().deleteEmployeeEntity();

		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();

		// Add new Employee object
		final EmployeeEntity emp = EntityUtil.getInstance().createEmpEntity();

		// Save entity
		session.save(emp);

		session.getTransaction().commit();
		session.close();

		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();

		final Integer genEmpId = emp.getEmployeeId();

		// Get only the reference of EmployeeEntity for now
		final EmployeeEntity empGet = session.byId(EmployeeEntity.class).getReference(genEmpId);

		App.log.info("No data initialized till now; Lets fetch some data..");

		// Now EmployeeEntity will be loaded from database when we need it
		App.log.info(empGet.getFirstName());
		App.log.info(empGet.getLastName());

		session.getTransaction().commit();
		HibernateUtil.shutdown();

		App.log.info("*********************************** START main() method " + App.class);

	}
}