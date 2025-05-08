package com.Dao;

import com.Entity.LeaveTable;
import java.util.List;

public interface LeaveDao {
	
	void applyLeave(LeaveTable leave);
    
    void updateLeave(LeaveTable leave);
	List<LeaveTable> getPendingLeaves();
	LeaveTable getLeaveById(String leaveId);
}
//	    void applyLeave(Leave leave);
//	    void approveLeave(int leaveId, int adminId);
//	    List<Leave> getAllLeaves();
	



