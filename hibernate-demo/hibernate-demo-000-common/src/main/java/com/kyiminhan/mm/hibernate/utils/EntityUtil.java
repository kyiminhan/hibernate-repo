package com.kyiminhan.mm.hibernate.utils;

import com.kyiminhan.mm.hibernate.entity.EmployeeEntity;

/**
 * The Class EntityUtil.<BR>
 *
 * @author KYIMINHAN <BR>
 * @version 1.0 <BR>
 * @since Jun 27, 2019 <BR>
 *        hibernate-demo-000-common system <BR>
 *        com.kyiminhan.mm.hibernate.utils <BR>
 *        EntityUtil.java <BR>
 */
public class EntityUtil {

	/** The instance. */
	private volatile static EntityUtil instance = null;

	/**
	 * Instantiates a new entity util.
	 */
	private EntityUtil() {
	}

	public static EntityUtil getInstance() {
		if (null == EntityUtil.instance) {
			synchronized (EntityUtil.class) {
				EntityUtil.instance = new EntityUtil();
			}
		}
		return EntityUtil.instance;
	}

	/**
	 * Creates the emp entity.
	 *
	 * @return EmployeeEntity
	 */
	public EmployeeEntity createEmpEntity() {
		return EmployeeEntity.builder().email("kyiminhan@gmail.com").firstName("Kyi Min ").lastName("Han").build();
	}
}