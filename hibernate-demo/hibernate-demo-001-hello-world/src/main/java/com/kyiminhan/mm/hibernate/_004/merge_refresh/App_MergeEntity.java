package com.kyiminhan.mm.hibernate._004.merge_refresh;

import org.hibernate.Session;

import com.kyiminhan.mm.hibernate.entity.EmployeeEntity;
import com.kyiminhan.mm.hibernate.utils.DBUtil;
import com.kyiminhan.mm.hibernate.utils.EntityUtil;
import com.kyiminhan.mm.hibernate.utils.HibernateUtil;

import lombok.extern.log4j.Log4j2;

/**
 * The Class App_MergeEntity.<BR>
 *
 * @author KYIMINHAN <BR>
 * @version 1.0 <BR>
 * @since Jun 26, 2019 <BR>
 *        hibernate-demo-001-hello-world system <BR>
 *        com.kyiminhan.mm.hibernate._004.merge_refresh <BR>
 *        App_MergeEntity.java <BR>
 */
@Log4j2
public class App_MergeEntity {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(final String[] args) {

		App_MergeEntity.log.info("*********************************** START main() method " + App_MergeEntity.class);

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

		App_MergeEntity.log.info(
				"@@@@@ verifyEmployeeFirstName : " + App_MergeEntity.verifyEmployeeFirstName(genEmpId, "Kyi Min"));

		final Session sessionTwo = HibernateUtil.getSessionFactory().openSession();
		sessionTwo.beginTransaction();

		// Set new first name
		emp.setFirstName("Kyi");

		// Merge the emp object using merge() method
		@SuppressWarnings("unused")
		final EmployeeEntity mergedPersistentEmpEntity = (EmployeeEntity) sessionTwo.merge(emp);

		sessionTwo.getTransaction().commit();
		sessionTwo.close();

		// Verify employee's firstname again in database
		App_MergeEntity.log
				.info("@@@@@ verifyEmployeeFirstName : " + App_MergeEntity.verifyEmployeeFirstName(genEmpId, "Kyi"));

		HibernateUtil.shutdown();

		App_MergeEntity.log.info("*********************************** START main() method " + App_MergeEntity.class);

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
