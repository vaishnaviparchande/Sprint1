package com.Service;

import com.Entity.LeaveTable;
import java.util.List;

public interface LeaveService {
	  void applyLeave(LeaveTable leave);
	    LeaveTable getLeaveById(String leaveId);
	    void approveLeave(String leaveId);
		void updateLeave(LeaveTable leave);
		List<LeaveTable> getPendingLeaves();
}
//	void applyLeave(Leave leave);
//    void approveLeave(int leaveId, int adminId);
//    List<Leave> getAllLeaves();



