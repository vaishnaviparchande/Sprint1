package com.Dao;



import com.Entity.Admin;
import java.util.List;

public interface AdminDao {
	
	 Admin getAdminById(String adminId);

	void saveAdmin(Admin admin);
	
	Admin getDefaultAdmin();
		 
	 
}	 
//    void addAdmin(Admin admin);      // Create
//    Admin getAdminById(int adminID); // Read
//    List<Admin> getAllAdmins();      // Read All
//    void updateAdmin(Admin admin);   // Update
//    void deleteAdmin(int adminID);   // Delete

