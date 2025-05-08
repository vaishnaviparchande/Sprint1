package com.Util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.Entity.LeaveTable;
import com.Entity.Report;
import com.Entity.Admin;
import com.Entity.Company;
import com.Entity.Department;
import com.Entity.Employee;

public class HibernateUtil {
	
	
	

	/**
	 * Utility class to configure Hibernate SessionFactory.
	 */
	
	    private static SessionFactory sessionFactory;

	    static {
	        try {
	            // Load configuration and build session factory
	            sessionFactory = new Configuration().configure("hibernate.cfg.xml")
	            		.addAnnotatedClass(Admin.class)
	                    .addAnnotatedClass(Employee.class)
	                    .addAnnotatedClass(Company.class)
	                    .addAnnotatedClass(LeaveTable.class)
	                    .addAnnotatedClass(Department.class)
	                    .addAnnotatedClass(Report.class)
	            		.buildSessionFactory();
	            System.out.println("Hibernate SessionFactory initialized successfully!");
	        } catch (Throwable ex) {
	            System.err.println("SessionFactory creation failed: " + ex);
	            throw new ExceptionInInitializerError(ex);
	        }
	    }

	    public static SessionFactory getSessionFactory() {
	        if (sessionFactory == null) {
	            throw new IllegalStateException("SessionFactory is not initialized!");
	        }
	        return sessionFactory;
	    }

	    public static void close() {
	        if (sessionFactory != null) {
	            sessionFactory.close();
	        }
	    }
	}

//    private static SessionFactory sessionFactory;
//
//    static {
//        try {
//            sessionFactory = new Configuration()
//                    .configure("Hibernate.cfg.xml")
//                    .addAnnotatedClass(Admin.class)
//                    .addAnnotatedClass(Employee.class)
//                    .addAnnotatedClass(Company.class)
//                    .addAnnotatedClass(Leave.class)
//                    .addAnnotatedClass(Department.class)
//                    .addAnnotatedClass(Report.class)
//                    .buildSessionFactory();
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw new ExceptionInInitializerError("SessionFactory creation failed!");
//        }
//    }
//
//    public static SessionFactory getSessionFactory() {
//        return sessionFactory;
//    }

