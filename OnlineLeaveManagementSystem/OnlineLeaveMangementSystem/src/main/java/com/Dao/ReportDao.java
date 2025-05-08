package com.Dao;

import com.Entity.*;
import java.util.List;

public interface ReportDao {
	
	    void saveReport(Report report);
	    List<Report> getAllReports();
	    List<Report> getReportsByEmpId(String empId);
	   // void addReport(Report report);
	    void updateReport(Report report);
	  public  Report getReportByLeaveId(String leaveId);
	}




//	void addReport(Report report);
//    List<Report> getAllReports();
//    void deleteReport(int reportId);
