package com.kyiminhan.mm.hibernate._004.merge_refresh;

import org.hibernate.Session;

import com.kyiminhan.mm.hibernate.entity.EmployeeEntity;
import com.kyiminhan.mm.hibernate.utils.DBUtil;
import com.kyiminhan.mm.hibernate.utils.EntityUtil;
import com.kyiminhan.mm.hibernate.utils.HibernateUtil;

import lombok.extern.log4j.Log4j2;

/**
 * The Class App_RefreshEntity.<BR>
 *
 * @author KYIMINHAN <BR>
 * @version 1.0 <BR>
 * @since Jun 26, 2019 <BR>
 *        hibernate-demo-001-hello-world system <BR>
 *        com.kyiminhan.mm.hibernate._004.merge_refresh <BR>
 *        App_RefreshEntity.java <BR>
 */
@Log4j2
public class App_RefreshEntity {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(final String[] args) {

		App_RefreshEntity.log
				.info("*********************************** START main() method " + App_RefreshEntity.class);

		DBUtil.getInstance().deleteEmployeeEntity();

		final Session sessionOne = HibernateUtil.getSessionFactory().openSession();
		sessionOne.beginTransaction();

		// Create new Employee object
		final EmployeeEntity emp = EntityUtil.getInstance().createEmpEntity();

		// Save employee
		sessionOne.save(emp);
		sessionOne.getTransaction().commit();
		sessionOne.close();

		final Integer genEmpId = emp.getEmployeeId();

		// Verify employee's firstname
		App_RefreshEntity.log.info(
				"@@@@@ verifyEmployeeFirstName : " + App_RefreshEntity.verifyEmployeeFirstName(genEmpId, "Kyi Min"));

		final Session sessionTwo = HibernateUtil.getSessionFactory().openSession();
		sessionTwo.beginTransaction();

		// This
		emp.setFirstName("Kyi");
		sessionTwo.refresh(emp);

		sessionTwo.getTransaction().commit();
		sessionTwo.close();

		App_RefreshEntity.log.info(emp.getFirstName().equals("Kyi"));

		HibernateUtil.shutdown();

		App_RefreshEntity.log
				.info("*********************************** START main() method " + App_RefreshEntity.class);

	}

	/**
	 * Verify employee first name.
	 *
	 * @param employeeId the employee id
	 * @param firstName  the first name
	 * @return true, if successful
	 */
	private static boolean verifyEmployeeFirstName(final Integer employeeId, final String firstName) {
		final Session session = HibernateUtil.getSessionFactory().openSession();
		final EmployeeEntity employee = session.load(EmployeeEntity.class, employeeId);
		// Verify first name
		final boolean result = firstName.equals(employee.getFirstName());
		session.close();
		// Return verification result
		return result;
	}
}
