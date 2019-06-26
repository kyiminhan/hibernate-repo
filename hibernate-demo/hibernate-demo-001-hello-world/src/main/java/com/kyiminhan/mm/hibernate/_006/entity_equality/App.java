package com.kyiminhan.mm.hibernate._006.entity_equality;

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
 *        com.kyiminhan.mm.hibernate._006.entity_equality <BR>
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
		final Session sessionOne = HibernateUtil.getSessionFactory().openSession();

		DBUtil.getInstance().deleteEmployeeEntity();

		sessionOne.beginTransaction();

		// Create new Employee object
		final EmployeeEntity emp = EntityUtil.getInstance().createEmpEntity();

		// Save employee
		sessionOne.save(emp);

		sessionOne.getTransaction().commit();

		// Get employee id
		final Integer genEmpId = emp.getEmployeeId();

		// New session where we will fetch the employee two times and compare the
		// objects
		final Session sessionTwo = HibernateUtil.getSessionFactory().openSession();
		sessionTwo.beginTransaction();

		// Find by ID employee object with using session get method.
		final EmployeeEntity employeeObj1 = sessionTwo.get(EmployeeEntity.class, genEmpId);
		// Find by ID employee object with using session load method.
		final EmployeeEntity employeeObj2 = sessionTwo.load(EmployeeEntity.class, genEmpId);

		// Checking equality
		App.log.info("@@@@@ Equal two object : " + (employeeObj1 == employeeObj2));

		HibernateUtil.shutdown();
	}
}