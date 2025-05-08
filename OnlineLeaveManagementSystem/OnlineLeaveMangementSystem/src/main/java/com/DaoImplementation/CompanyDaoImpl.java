package com.DaoImplementation;

import com.Dao.*;
import com.Entity.Company;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.Util.HibernateUtil;

import java.util.List;

public class CompanyDaoImpl implements CompanyDao {
	
	@Override
    public void saveCompany(Company company) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(company);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public Company getCompanyById(int companyId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Company.class, companyId);
        }
    }

//    @Override
//    public List<Company> getAllCompanies() {
//        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
//            return session.createQuery("FROM Company", Company.class).list();
//        }
//    }
//
//    @Override
//    public void updateCompany(Company company) {
//        Transaction transaction = null;
//        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
//            transaction = session.beginTransaction();
//            session.update(company);
//            transaction.commit();
//        } catch (Exception e) {
//            if (transaction != null) transaction.rollback();
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    public void deleteCompany(int companyId) {
//        Transaction transaction = null;
//        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
//            transaction = session.beginTransaction();
//            Company company = session.get(Company.class, companyId);
//            if (company != null) {
//                session.delete(company);
//            }
//            transaction.commit();
//        } catch (Exception e) {
//            if (transaction != null) transaction.rollback();
//            e.printStackTrace();
//        }
//    }

//    @Override
//    public void addCompany(Company company) {
//        Transaction transaction = null;
//        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
//            transaction = session.beginTransaction();
//            session.save(company); // Saves the Company object in the database
//            transaction.commit();
//        } catch (Exception e) {
//            if (transaction != null) transaction.rollback();
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    public Company getCompanyById(int compId) {
//        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
//            return session.get(Company.class, compId);
//        }
//    }
//
//    @Override
//    public List<Company> getAllCompanies() {
//        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
//            return session.createQuery("FROM Company", Company.class).list();
//        }
//    }
//
//    @Override
//    public void updateCompany(Company company) {
//        Transaction transaction = null;
//        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
//            transaction = session.beginTransaction();
//            session.update(company); // Updates existing Company data
//            transaction.commit();
//        } catch (Exception e) {
//            if (transaction != null) transaction.rollback();
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    public void deleteCompany(int compId) {
//        Transaction transaction = null;
//        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
//            transaction = session.beginTransaction();
//            Company company = session.get(Company.class, compId);
//            if (company != null) {
//                session.delete(company); // Deletes company record
//            }
//            transaction.commit();
//        } catch (Exception e) {
//            if (transaction != null) transaction.rollback();
//            e.printStackTrace();
//        }
//    }
}
