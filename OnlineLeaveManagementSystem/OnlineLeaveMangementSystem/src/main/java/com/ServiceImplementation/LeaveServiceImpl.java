package com.ServiceImplementation;

import com.Dao.LeaveDao;
import com.DaoImplementation.LeaveDaoImpl;
import com.Entity.LeaveTable;
import com.Service.LeaveService;
import java.util.List;
import com.Service.*;

public class LeaveServiceImpl implements LeaveService {
	private LeaveDao leaveDao = new LeaveDaoImpl();
	 private final ReportService reportService = new ReportServiceImpl();

    @Override
    public void applyLeave(LeaveTable leave) {
        leaveDao.applyLeave(leave);
    }

   

    @Override
    public void approveLeave(String leaveId) {
        LeaveTable leave = leaveDao.getLeaveById(leaveId);
        if (leave != null && leave.getLeaveStatus().equals("Pending")) {
            leave.setLeaveStatus("Approved");
            leaveDao.updateLeave(leave);
        }
    }
    
    public List<LeaveTable> getPendingLeaves() {
        return leaveDao.getPendingLeaves();
    }

    public LeaveTable getLeaveById(String leaveId) { // Changed to accept String
        return leaveDao.getLeaveById(leaveId);
    }

    public void updateLeave(LeaveTable leave) {
        leaveDao.updateLeave(leave);
    }

}
//    private final LeaveDao leaveDao = new LeaveDaoImpl();
//    
//    
//    public void applyLeave(Leave leave) {
//        leaveDao.applyLeave(leave);
//    }
//    
//    @Override
//    public List<Leave> getAllLeaves() {
//        return leaveDao.getAllLeaves();
//    }
//    
//    @Override
//    public void approveLeave(int leaveId, int adminId) {
//        leaveDao.approveLeave(leaveId, adminId);
//    }
//}

//   