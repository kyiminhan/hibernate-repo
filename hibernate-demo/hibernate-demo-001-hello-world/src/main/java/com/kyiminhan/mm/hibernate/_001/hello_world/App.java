package com.kyiminhan.mm.hibernate._001.hello_world;

import org.hibernate.Session;

import com.kyiminhan.mm.hibernate.entity.EmployeeEntity;
import com.kyiminhan.mm.hibernate.utils.DBUtil;
import com.kyiminhan.mm.hibernate.utils.HibernateUtil;

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
public class App {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(final String... args) {

		DBUtil.getInstance().deleteEmployeeEntity();

		final Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();

		final EmployeeEntity emp = EmployeeEntity.builder().email("kyiminhan@gmail.com").firstName("Kyi Min ")
				.lastName("Han").build();

		session.save(emp);

		session.getTransaction().commit();
		HibernateUtil.shutdown();
	}
}