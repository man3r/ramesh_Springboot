package service;




import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import entity.Teacher;

public class Test {
	
	
	public static void main(String[] args) {
		System.out.println("Connecting to database...");
		SessionFactory factory = null;
		Session session = null;
		
		factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.buildSessionFactory();
		System.out.println("Connected to Database...");
		
		/*
		Teacher t1 = new Teacher("Ramesh", "Mantripragda", "abc123@abc.com");
		Teacher t2 = new Teacher("Ramesh", "Mantripragda", "abc123@abc.com");
		Teacher t3 = new Teacher("Ramesh", "Mantripragda", "abc123@abc.com");
		Teacher t4 = new Teacher("Ramesh", "Mantripragda", "abc123@abc.com");
		*/
		
		Transaction txn = null;
		try {
			session = factory.getCurrentSession();
			txn = session.beginTransaction();
			Teacher t = session.get(Teacher.class, 2);
			if (t != null) {
				System.out.println("Teacher " + t);
				/*t.setF_name("Ananya");
				session.update(t);
				session.createQuery("update Teacher set email='anan@abc.com' where f_name='Ananya'")
						.executeUpdate();
				*/
				session.createQuery("delete from Teacher where email='abc123@abc.com' ").executeUpdate();
				txn.commit();
			} else {
				System.out.println("Record does not exist");
			}			
		}catch(Exception e) {
			System.out.println("Errors..");
			e.printStackTrace();
			txn.rollback();
		} finally {
			session.close();
		}
	}

}
