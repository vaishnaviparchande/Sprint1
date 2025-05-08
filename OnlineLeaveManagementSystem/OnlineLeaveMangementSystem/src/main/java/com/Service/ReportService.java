package com.Service;

import com.Entity.*;
import java.util.List;

public interface ReportService {
	 void saveReport(Report report);
	    List<Report> getAllReports();
	    List<Report> getReportsByEmpId(String empId);
	   // void addReport(Report report);
	    Report getReportByLeaveId(String leaveId);  
	    void updateReport(Report report); 

    
}

//	void addReport(Report report);
//    List<Report> getAllReports();
//    void deleteReport(int reportId);

