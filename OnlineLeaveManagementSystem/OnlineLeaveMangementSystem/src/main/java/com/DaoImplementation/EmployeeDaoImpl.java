package com.DaoImplementation ;


import com.Dao.*;
import com.Entity.*;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.query.Query;

import com.Util.HibernateUtil;
import java.util.List;

public class EmployeeDaoImpl implements EmployeeDao {
	
	//Admin uses this method to register new employees in the system.
	@Override
    public void addEmployee(Employee employee) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(employee);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }
	
	// Used for login, view, update, or delete operations based on employee ID.
    @Override
    public Employee getEmployeeById(String empId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Employee.class, empId);
        }
    }

    // Admin uses this to view all employees in the system.
    @Override
    public List<Employee> getAllEmployees() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Employee", Employee.class).list();
        }
    }
    
    //Allows Admin to modify employee info (email, name, password ).
    @Override
    public void updateEmployee(Employee employee) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.update(employee);
            tx.commit();
            System.out.println("✅ Employee updated successfully.");
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }
    
    // Ensures complete cleanup of all related data (report, leave) when an employee is removed.
    @Override
    public void deleteEmployee(String empId) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();

            Employee emp = session.get(Employee.class, empId);
            if (emp == null) {
                System.out.println("❌ Employee with ID " + empId + " not found.");
                return;
            }

            // 🔁 Step 1: Delete Report entities associated with Employee
            List<Report> reports = session.createQuery("FROM Report WHERE empId = :empId", Report.class)
                    .setParameter("empId", empId)
                    .list();
            for (Report report : reports) {
                session.delete(report);
            }

            // 🔁 Step 2: Delete Leave entities associated with Employee
            List<LeaveTable> leaves = session.createQuery("FROM LeaveTable WHERE empId = :empId", LeaveTable.class)
                    .setParameter("empId", empId)
                    .list();
            for (LeaveTable leave : leaves) {
                session.delete(leave);
            }

            // 🗑️ Step 3: Now delete Employee
            session.delete(emp);

            tx.commit();
            System.out.println("✅ Employee and related data deleted successfully.");
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            System.out.println("❌ Error deleting employee: " + e.getMessage());
            e.printStackTrace();
        }
    }


//    @Override
//    public void deleteEmployee(String empId) {
//        Transaction tx = null;
//        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
//            tx = session.beginTransaction();
//            Employee employee = session.get(Employee.class, empId);
//            if (employee != null) {
//                session.delete(employee);
//                System.out.println("✅ Employee deleted successfully.");
//            } else {
//                System.out.println("❌ Employee not found.");
//            }
//            tx.commit();
//        } catch (Exception e) {
//            if (tx != null) tx.rollback();
//            e.printStackTrace();
//        }
//    }


//    @Override
//    
//    public void addEmployee(Employee employee) {
//        Transaction transaction = null;
//        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
//            transaction = session.beginTransaction();
//            session.save(employee);
//            session.getTransaction().commit();  // ✅ Explicit commit
//            System.out.println("✅ Employee added successfully!");
//        } catch (Exception e) {
//            if (transaction != null) transaction.rollback();
//            e.printStackTrace();
//        }
//    }
//
//
//
//    @Override
//    public Employee getEmployeeById(int empId) {
//        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
//            return session.get(Employee.class, empId);
//        }
//    }
//
//    @Override
//    public List<Employee> getAllEmployee() {
//        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
//            return session.createQuery("FROM Employee", Employee.class).list();
//        }
//    }
//
//    @Override
//    public void updateEmployee(Employee employee) {
//        Transaction transaction = null;
//        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
//            transaction = session.beginTransaction();
//            session.update(employee);  // Updating Employee entity
//            transaction.commit();
//            System.out.println("Employee updated successfully!");
//            session.close();
//        } catch (Exception e) {
//            if (transaction != null) transaction.rollback();
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    public void deleteEmployee(int empID) {
//        Transaction transaction = null;
//        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
//            transaction = session.beginTransaction();
//            Employee employee = session.get(Employee.class, empID);
//            if (employee != null) {
//                session.delete(employee);  // Deleting Employee entity
//                System.out.println("Employee deleted successfully!");
//            }
//            transaction.commit();
//            session.close();
//        } catch (Exception e) {
//            if (transaction != null) transaction.rollback();
//            e.printStackTrace();
//        }
//    }
}
