package com.DaoImplementation;

import com.Dao.*;
import com.Entity.*;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.Util.*;

import java.util.ArrayList;
import java.util.List;

public class ReportDaoImpl implements ReportDao {

	// Saves a new report record into the database and uses hibernate transaction to save data 
	 @Override
	    public void saveReport(Report report) {
	        Transaction tx = null;
	        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
	            tx = session.beginTransaction();
	            session.save(report);// Insert operation
	            tx.commit();// Commit transaction
	        } catch (Exception e) {
	            if (tx != null) tx.rollback();// rollback if error occurs
	            e.printStackTrace();// print error to console
	        }
	    }

	 
	    @Override
	    public List<Report> getAllReports() {
	        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
	            return session.createQuery("from Report", Report.class).list(); // fetch all reports using HQL query 
	        }
	    }
	    
	    //Useful for checking report existence for a leave
	    @Override
	    public Report getReportByLeaveId(String leaveId) {
	        Session session = HibernateUtil.getSessionFactory().openSession();
	        Report report = null;
	        try {
	            Query<Report> query = session.createQuery("FROM Report WHERE leave.leaveId = :leaveId", Report.class);
	            query.setParameter("leaveId", leaveId);
	            report = query.uniqueResult();
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            session.close();
	        }
	        return report;
	    }


    //Used in Employee-side report viewing
	    @Override
	    public List<Report> getReportsByEmpId(String empId) {
	        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
	            Query<Report> query = session.createQuery("from Report where empId = :empId", Report.class);
	            query.setParameter("empId", empId);
	            return query.list();
	        }
	    }

//	    @Override
//	    public void addReport(Report report) {
//	        Transaction tx = null;
//	        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
//	            tx = session.beginTransaction();
//	            session.save(report);
//	            tx.commit();
//	        } catch (Exception e) {
//	            if (tx != null) tx.rollback();
//	            e.printStackTrace();
//	        }
//	    }
	    
	    //Required when modifying existing reports
	    @Override
	    public void updateReport(Report report) {
	        Transaction tx = null;
	        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
	            tx = session.beginTransaction();
	            session.update(report);
	            tx.commit();
	        } catch (Exception e) {
	            if (tx != null) tx.rollback();
	            e.printStackTrace();
	        }
	    }


}


//	    @Override
//	    public void addReport(Report report) {
//	        Session session = HibernateUtil.getSessionFactory().openSession();
//	        Transaction transaction = session.beginTransaction();
//
//	        session.save(report);  // Save the Report entity
//
//	        transaction.commit();
//	        session.close();
//	    }
//
//	    @Override
//	    public List<Report> getAllReports() {
//	        Session session = HibernateUtil.getSessionFactory().openSession();
//	        List<Report> reports = session.createQuery("FROM Report", Report.class).list();
//	        session.close();
//	        return reports;
//	    }
//
//	    @Override
//	    public void deleteReport(int reportId) {
//	        Session session = HibernateUtil.getSessionFactory().openSession();
//	        Transaction transaction = session.beginTransaction();
//
//	        Report report = session.get(Report.class, reportId);
//	        if (report != null) {
//	            session.delete(report);
//	        }
//
//	        transaction.commit();
//	        session.close();
//	    }
//	}


    

