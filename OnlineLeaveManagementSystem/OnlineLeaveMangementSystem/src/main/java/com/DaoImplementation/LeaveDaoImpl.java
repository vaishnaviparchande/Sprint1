package com.DaoImplementation;

import com.Dao.LeaveDao;
import com.Entity.LeaveTable;
import com.Util.HibernateUtil;


import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;



public class LeaveDaoImpl implements LeaveDao {
	
	//To save a new leave application into the database.
	@Override
    public void applyLeave(LeaveTable leave) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(leave);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

	//To fetch all leave requests that have status 'Pending'.
	@Override
    public List<LeaveTable> getPendingLeaves() {
        List<LeaveTable> pendingLeaves = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "FROM LeaveTable WHERE leaveStatus = 'Pending'";
            Query<LeaveTable> query = session.createQuery(hql, LeaveTable.class);
            pendingLeaves = query.list();
        } catch (Exception e) {
            e.printStackTrace(); // Log the exception for debugging
        }
        return pendingLeaves;
    }

    @Override
    public LeaveTable getLeaveById(String leaveId) {
        LeaveTable leave = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            leave = session.get(LeaveTable.class, leaveId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return leave;
    }

    //To update a leave record in the database.
    @Override
    public void updateLeave(LeaveTable leave) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.update(leave);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback(); // Rollback transaction if any error occurs
            }
            e.printStackTrace();
        }
    }

}
//	    @Override
//	    public void applyLeave(Leave leave) {
//	        Transaction transaction = null;
//	        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
//	            transaction = session.beginTransaction();
//	            session.save(leave);
//	            transaction.commit();
//	        } catch (Exception e) {
//	            if (transaction != null) transaction.rollback();
//	            e.printStackTrace();
//	        }
//	    }
//
//	    @Override
//	    public void approveLeave(int leaveId, int adminId) {
//	        Transaction transaction = null;
//	        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
//	            transaction = session.beginTransaction();
//	            Leave leave = session.get(Leave.class, leaveId);
//	            if (leave != null && leave.getAdmin().getAdminID() == adminId) {
//	                leave.setLeaveStatus("Approved");
//	                session.update(leave);
//	                transaction.commit();
//	            } else {
//	                System.out.println("Leave request not found or Admin ID mismatch.");
//	            }
//	        } catch (Exception e) {
//	            if (transaction != null) transaction.rollback();
//	            e.printStackTrace();
//	        }
//	    }
//
//	    @Override
//	    public List<Leave> getAllLeaves() {
//	        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
//	            return session.createQuery("from Leave", Leave.class).list();
//	        }
//	    }
//	}

	


