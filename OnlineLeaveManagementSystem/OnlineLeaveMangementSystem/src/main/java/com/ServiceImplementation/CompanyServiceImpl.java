package com.ServiceImplementation;

import java.util.List;


import com.Entity.Company;
import com.Service.CompanyService;
import com.Dao.*;
import com.DaoImplementation.*;

public class CompanyServiceImpl implements CompanyService {
	    
	private CompanyDao companyDao = new CompanyDaoImpl();

    @Override
    public void addCompany(Company company) {
        companyDao.saveCompany(company);
    }

    @Override
    public Company getCompany(int companyId) {
        return companyDao.getCompanyById(companyId);
    }
}

//    @Override
//    public List<Company> getAllCompanies() {
//        return companyDao.getAllCompanies();
//    }
//
//    @Override
//    public void updateCompany(Company company) {
//        companyDao.updateCompany(company);
//    }
//
//    @Override
//    public void removeCompany(int companyId) {
//        companyDao.deleteCompany(companyId);
//    }
//	    private CompanyDao companyDao = new CompanyDaoImpl();
//
//	    @Override
//	    public void addCompany(Company company) {
//	        companyDao.addCompany(company);
//	    }
//
//	    @Override
//	    public Company getCompanyById(int compId) {
//	        return companyDao.getCompanyById(compId);
//	    }
//
//	    @Override
//	    public List<Company> getAllCompanies() {
//	        return companyDao.getAllCompanies();
//	    }
//
//	    @Override
//	    public void updateCompany(Company company) {
//	        companyDao.updateCompany(company);
//	    }
//
//	    @Override
//	    public void deleteCompany(int compId) {
//	        companyDao.deleteCompany(compId);
//	    }
	


