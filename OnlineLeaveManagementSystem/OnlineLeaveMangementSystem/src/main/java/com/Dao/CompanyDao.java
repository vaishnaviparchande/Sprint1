package com.Dao;

import java.util.List;

import com.Entity.Company;

public interface CompanyDao {
	
	void saveCompany(Company company);
    Company getCompanyById(int companyId);
//    List<Company> getAllCompanies();
//    void updateCompany(Company company);
//    void deleteCompany(int companyId);
	
//	    void addCompany(Company company);
//	    Company getCompanyById(int compId);
//	    List<Company> getAllCompanies();
//	    void updateCompany(Company company);
//	    void deleteCompany(int compId);
	}



