package com.kyiminhan.mm.hibernate.utils;

import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import com.kyiminhan.mm.hibernate.entity.EmployeeEntity;

import lombok.extern.log4j.Log4j2;

/**
 * The Class HibernateUtil.<BR>
 *
 * @author KYIMINHAN <BR>
 * @version 1.0 <BR>
 * @since Jun 26, 2019 <BR>
 *        hibernate-demo-000-common system <BR>
 *        com.kyiminhan.mm.hibernate.utils <BR>
 *        HibernateUtil.java <BR>
 */
@Log4j2
public class HibernateUtil {

	/** The session factory. */
	private static SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();

	/**
	 * Builds the session factory.
	 *
	 * @return SessionFactory
	 */
	private static SessionFactory buildSessionFactory() {

		HibernateUtil.log
				.info("*********************************** START buildSessionFactory() method " + HibernateUtil.class);

		if (null == HibernateUtil.sessionFactory) {

			final Configuration configuration = new Configuration();

			final Properties settings = new Properties();

			settings.put(AvailableSettings.DRIVER, "com.mysql.cj.jdbc.Driver");
			settings.put(AvailableSettings.URL,
					"jdbc:mysql://localhost:3306/kmh_hbn_db?serverTimezone=JST&useSSL=false");
			settings.put(AvailableSettings.USER, "root");
			settings.put(AvailableSettings.PASS, "rootR00T");
			settings.put(AvailableSettings.DIALECT, "org.hibernate.dialect.MySQL5InnoDBDialect");

			settings.put(AvailableSettings.SHOW_SQL, "true");
			settings.put(AvailableSettings.CURRENT_SESSION_CONTEXT_CLASS, "thread");
			settings.put(AvailableSettings.HBM2DDL_AUTO, "update");

			configuration.setProperties(settings);

			configuration.addAnnotatedClass(EmployeeEntity.class);

			final ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
					.applySettings(configuration.getProperties()).build();

			HibernateUtil.sessionFactory = configuration.buildSessionFactory(serviceRegistry);

		}
		HibernateUtil.log
				.info("*********************************** END buildSessionFactory() method " + HibernateUtil.class);
		return HibernateUtil.sessionFactory;
	}

	public static SessionFactory getSessionFactory() {
		HibernateUtil.log.info("######################### getSessionFactory() method " + HibernateUtil.class);
		return HibernateUtil.sessionFactory;
	}

	/**
	 * Shutdown.
	 */
	public static void shutdown() {
		HibernateUtil.getSessionFactory().close();
		HibernateUtil.log.info("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$ shutdown() method " + HibernateUtil.class);
	}
}