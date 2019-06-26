package com.kyiminhan.mm.hibernate._002.get_fetch;

import org.hibernate.Session;

import com.kyiminhan.mm.hibernate.entity.EmployeeEntity;
import com.kyiminhan.mm.hibernate.utils.DBUtil;
import com.kyiminhan.mm.hibernate.utils.HibernateUtil;

import lombok.extern.log4j.Log4j2;

/**
 * The Class App.<BR>
 *
 * @author KYIMINHAN <BR>
 * @version 1.0 <BR>
 * @since Jun 26, 2019 <BR>
 *        hibernate-demo-001-hello-world system <BR>
 *        com.kyiminhan.mm.hibernate._002.get_fetch <BR>
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

		final Session sessionOne = HibernateUtil.getSessionFactory().openSession();
		sessionOne.beginTransaction();

		// Create new Employee object
		final EmployeeEntity emp = EmployeeEntity.builder().firstName("Kyi Min").lastName("Han")
				.email("kyiminhan@gmail.com").build();

		// Save employee
		sessionOne.save(emp);
		// store the employee id generated for future use
		final Integer empId = emp.getEmployeeId();
		sessionOne.getTransaction().commit();

		/************************************************************************/

		// Let's open a new session to test load() methods
		final Session sessionTwo = HibernateUtil.getSessionFactory().openSession();
		sessionTwo.beginTransaction();

		// first load() method example
		final EmployeeEntity emp1 = sessionTwo.load(EmployeeEntity.class, empId);
		App.log.info(emp1.getFirstName() + " - " + emp1.getLastName());

		// Let's verify the entity name
		App.log.info(sessionTwo.getEntityName(emp1));

		sessionTwo.getTransaction().commit();

		/************************************************************************/

		final Session sessionThree = HibernateUtil.getSessionFactory().openSession();
		sessionThree.beginTransaction();

		// second load() method example
		final EmployeeEntity emp2 = (EmployeeEntity) sessionThree
				.load("com.kyiminhan.mm.hibernate.entity.EmployeeEntity", empId);
		App.log.info(emp2.getFirstName() + " - " + emp2.getLastName());

		sessionThree.getTransaction().commit();

		/************************************************************************/

		final Session sessionFour = HibernateUtil.getSessionFactory().openSession();
		sessionFour.beginTransaction();

		// third load() method example
		final EmployeeEntity emp3 = new EmployeeEntity();
		sessionFour.load(emp3, empId);
		App.log.info(emp3.getFirstName() + " - " + emp3.getLastName());

		sessionFour.getTransaction().commit();

		HibernateUtil.shutdown();

		App.log.info("*********************************** END main() method " + App.class);
	}
}