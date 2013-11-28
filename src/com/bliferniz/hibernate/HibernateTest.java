/**
 * 
 */
package com.bliferniz.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.bliferniz.dto.UserDetails;

public class HibernateTest {

	private static final SessionFactory factory = new Configuration().configure().buildSessionFactory();
	
	public static void main(String[] args) {

		Session session = factory.openSession();
		session.beginTransaction();
		UserDetails user = (UserDetails) session.get(UserDetails.class, 1);
		session.close();
		
		//Detached object
		user.setUserName("Paul Panzer");
		
		session = factory.openSession();
		session.beginTransaction();
		session.update(user); //Runs the update query also without changes, because Hibernate don't know how the  
							  //corresponding value looks in the DB, until @org.hibernate.annotations.Entity(selectBeforeUpdate=true) 
							  //is included in the entity class.
		session.getTransaction().commit();
		session.close();
	}
	
}
