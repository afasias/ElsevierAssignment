package com.elsevier.tweetsapp.data;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/*
 * A Utility class for Hibernate.
 */
public class HibernateUtil {

	private static SessionFactory sessionFactory = null;

	private static SessionFactory getSessionFactory() {
		if (sessionFactory == null) {
			synchronized (HibernateUtil.class) {
				if (sessionFactory == null) {
					sessionFactory = new Configuration().configure().buildSessionFactory();
				}
			}
		}
		return sessionFactory;
	}

	public static Session openSession() {
		return getSessionFactory().openSession();
	}

}
