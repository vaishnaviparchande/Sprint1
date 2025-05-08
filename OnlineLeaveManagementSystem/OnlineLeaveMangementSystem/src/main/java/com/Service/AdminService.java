package com.Service;



import com.Entity.*;
import java.util.List;

public interface AdminService {
	
	 void addAdmin(Admin admin);
	    Admin findAdminById(String adminId);
	 // In AdminService.java
	    Admin getDefaultAdmin();
		Admin getAdminById(String adminid);

	    
	    
//    void addAdmin(Admin admin);      // Create
//    Admin getAdminById(int adminID); // Read
//    List<Admin> getAllAdmins();      // Read All
//    void updateAdmin(Admin admin);   // Update
//    void deleteAdmin(int adminID);   // Delete
}


