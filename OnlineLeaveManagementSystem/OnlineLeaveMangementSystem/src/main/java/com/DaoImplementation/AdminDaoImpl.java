package com.DaoImplementation;



import com.Dao.*;
import com.Entity.*;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.Util.HibernateUtil;
import java.util.List;

public class AdminDaoImpl implements AdminDao {
	
	 @Override
	    public Admin getAdminById(String adminId) {
	        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
	            return session.get(Admin.class, adminId);
	        }
	    }
	 
	 //To fetch a pre-defined default Admin from initial login 
	 @Override
	 public Admin getDefaultAdmin() {
	     Transaction tx = null;
	     Admin admin = null;

	     try (Session session = HibernateUtil.getSessionFactory().openSession()) {
	         tx = session.beginTransaction();
	         
	         // Assuming default admin ID is "admin1"
	         String defaultAdminId = "admin1";
	         admin = session.get(Admin.class, defaultAdminId);
	         
	         tx.commit();
	     } catch (Exception e) {
	         if (tx != null) tx.rollback();
	         e.printStackTrace();
	     }

	     return admin;
	 }
	 
	 @Override
	 public void saveAdmin(Admin admin) {
	     Transaction tx = null;
	     try (Session session = HibernateUtil.getSessionFactory().openSession()) {
	         tx = session.beginTransaction();
	         session.save(admin);
	         tx.commit();
	     } catch (Exception e) {
	         if (tx != null) tx.rollback();
	         e.printStackTrace();
	     }
	 }

	 


}

//    @Override
//    
//    public void addAdmin(Admin admin) {
//        Transaction transaction = null;
//        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
//            transaction = session.beginTransaction();
//            session.save(admin);
//            session.getTransaction().commit();  // ✅ Explicit commit
//            System.out.println("✅ Admin added successfully!");
//        } catch (Exception e) {
//            if (transaction != null) transaction.rollback();
//            e.printStackTrace();
//        }
//    }



//    @Override
//    public Admin getAdminById(int adminID) {
//        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
//            return session.get(Admin.class, adminID);
//        }
//    }
//
//    @Override
//    public List<Admin> getAllAdmins() {
//        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
//            return session.createQuery("FROM Admin", Admin.class).list();
//        }
//    }
//
//    @Override
//    public void updateAdmin(Admin admin) {
//        Transaction transaction = null;
//        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
//            transaction = session.beginTransaction();
//            session.update(admin);  // Updating Admin entity
//            transaction.commit();
//            System.out.println("Admin updated successfully!");
//            session.close();
//        } catch (Exception e) {
//            if (transaction != null) transaction.rollback();
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    public void deleteAdmin(int adminID) {
//        Transaction transaction = null;
//        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
//            transaction = session.beginTransaction();
//            Admin admin = session.get(Admin.class, adminID);
//            if (admin != null) {
//                session.delete(admin);  // Deleting Admin entity
//                System.out.println("Admin deleted successfully!");
//            }
//            transaction.commit();
//            session.close();
//        } catch (Exception e) {
//            if (transaction != null) transaction.rollback();
//            e.printStackTrace();
//        }
//    }
//}
