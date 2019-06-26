package com.kyiminhan.mm.hibernate._001.hello_world;

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
 *        com.kyiminhan.mm.hibernate._001.hello_world <BR>
 *        App.java <BR>
 */
@Log4j2
public class App {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(final String... args) {

		App.log.info("*********************************** START main() method " + App.class);

		DBUtil.getInstance().deleteEmployeeEntity();

		final Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();

		final EmployeeEntity emp = EntityUtil.getInstance().createEmpEntity();

		session.save(emp);

		session.getTransaction().commit();
		HibernateUtil.shutdown();
	}
}