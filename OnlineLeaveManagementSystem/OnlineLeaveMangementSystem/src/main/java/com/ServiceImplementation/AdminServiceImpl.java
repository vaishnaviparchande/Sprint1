package com.ServiceImplementation;




import com.Dao.*;
import com.DaoImplementation.*;
import com.Entity.*;
import com.Service.AdminService;
import java.util.List;

public class AdminServiceImpl implements AdminService {
	
	 private AdminDao adminDao;

	    public AdminServiceImpl(AdminDao adminDao) {
	        this.adminDao = adminDao;
	    }

	    @Override
	    public void addAdmin(Admin admin) {
	        adminDao.saveAdmin(admin);
	    }

	    @Override
	    public Admin findAdminById(String adminId) {
	        return adminDao.getAdminById(adminId);
	    }
	    
	 @Override
	    public Admin getDefaultAdmin() {
	        return new Admin("admin1", "admin123");
	    }
	    
	 @Override
	 public Admin getAdminById(String adminId) {
	     return adminDao.getAdminById(adminId);
	 }

}

//    private AdminDao adminDao = new AdminDaoImpl(); // Using DAO Implementation
//
//    @Override
//    public void addAdmin(Admin admin) {
//        adminDao.addAdmin(admin);
//    }
//
//    @Override
//    public Admin getAdminById(int adminID) {
//        return adminDao.getAdminById(adminID);
//    }
//
//    @Override
//    public List<Admin> getAllAdmins() {
//        return adminDao.getAllAdmins();
//    }
//
//    @Override
//    public void updateAdmin(Admin admin) {
//        adminDao.updateAdmin(admin);
//    }
//
//    @Override
//    public void deleteAdmin(int adminID) {
//        adminDao.deleteAdmin(adminID);
//    }



