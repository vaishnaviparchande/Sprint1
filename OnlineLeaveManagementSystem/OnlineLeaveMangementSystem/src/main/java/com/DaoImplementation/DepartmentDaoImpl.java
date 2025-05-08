package com.DaoImplementation;

import com.Dao.DepartmentDao;
import com.Entity.Department;
import com.Util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class DepartmentDaoImpl implements DepartmentDao {
	 @Override
	    public void addDepartment(Department department) {
	        Transaction transaction = null;
	        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
	            transaction = session.beginTransaction();
	            session.save(department);
	            transaction.commit();
	        } catch (Exception e) {
	            if (transaction != null) transaction.rollback();
	            e.printStackTrace();
	        }
	    }

	    @Override
	    public Department getDepartmentById(int deptId) {
	        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
	            return session.get(Department.class, deptId);
	        }
	    }

	    @Override
	    public List<Department> getAllDepartments() {
	        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
	            return session.createQuery("from Department", Department.class).list();
	        }
	    }

	    @Override
	    public void updateDepartment(Department department) {
	        Transaction transaction = null;
	        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
	            transaction = session.beginTransaction();
	            session.update(department);
	            transaction.commit();
	        } catch (Exception e) {
	            if (transaction != null) transaction.rollback();
	            e.printStackTrace();
	        }
	    }

	    @Override
	    public void deleteDepartment(int deptId) {
	        Transaction transaction = null;
	        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
	            transaction = session.beginTransaction();
	            Department department = session.get(Department.class, deptId);
	            if (department != null) {
	                session.delete(department);
	            }
	            transaction.commit();
	        } catch (Exception e) {
	            if (transaction != null) transaction.rollback();
	            e.printStackTrace();
	        }
	    }

//    @Override
//    public void addDepartment(Department department) {
//        Transaction transaction = null;
//        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
//            transaction = session.beginTransaction();
//            session.save(department);
//            transaction.commit();
//        } catch (Exception e) {
//            if (transaction != null) transaction.rollback();
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    public Department getDepartmentById(int depId) {
//        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
//            return session.get(Department.class, depId);
//        }
//    }
//
//    @Override
//    public List<Department> getAllDepartments() {
//        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
//            return session.createQuery("from Department", Department.class).list();
//        }
//    }
//
//    @Override
//    public void updateDepartment(Department department) {
//        Transaction transaction = null;
//        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
//            transaction = session.beginTransaction();
//            session.update(department);
//            transaction.commit();
//        } catch (Exception e) {
//            if (transaction != null) transaction.rollback();
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    public void deleteDepartment(int depId) {
//        Transaction transaction = null;
//        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
//            transaction = session.beginTransaction();
//            Department department = session.get(Department.class, depId);
//            if (department != null) {
//                session.delete(department);
//                transaction.commit();
//            }
//        } catch (Exception e) {
//            if (transaction != null) transaction.rollback();
//            e.printStackTrace();
//        }
//    }
}
