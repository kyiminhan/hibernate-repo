package com.kyiminhan.mm.hibernate.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * The Class EmployeeEntity.<BR>
 *
 * @author KYIMINHAN <BR>
 * @version 1.0 <BR>
 * @since Jun 26, 2019 <BR>
 *        hibernate-demo-000-common system <BR>
 *        com.kyiminhan.mm.hibernate.entity <BR>
 *        EmployeeEntity.java <BR>
 */
@Entity
@Table(name = "employee", uniqueConstraints = { @UniqueConstraint(columnNames = "ID"),
		@UniqueConstraint(columnNames = "EMAIL") })

@Getter
@Setter

/**
 * Instantiates a new employee entity.
 */

/**
 * Instantiates a new employee entity.
 */
@NoArgsConstructor

/**
 * Instantiates a new employee entity.
 *
 * @param employeeId the employee id
 * @param email      the email
 * @param firstName  the first name
 * @param lastName   the last name
 */

/**
 * Instantiates a new employee entity.
 *
 * @param employeeId the employee id
 * @param email      the email
 * @param firstName  the first name
 * @param lastName   the last name
 */
@AllArgsConstructor
@ToString

/**
 * Builds the.
 *
 * @return EmployeeEntity
 */

/**
 * Builds the.
 *
 * @return EmployeeEntity
 */
@Builder
public class EmployeeEntity implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The employee id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	private Integer employeeId;

	/** The email. */
	@Column(name = "EMAIL", unique = true, nullable = false, length = 100)
	private String email;

	/** The first name. */
	@Column(name = "FIRST_NAME", unique = false, nullable = false, length = 100)
	private String firstName;

	/** The last name. */
	@Column(name = "LAST_NAME", unique = false, nullable = false, length = 100)
	private String lastName;

}