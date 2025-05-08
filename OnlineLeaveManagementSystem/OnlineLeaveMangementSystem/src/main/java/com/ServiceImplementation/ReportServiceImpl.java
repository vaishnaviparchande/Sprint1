package com.ServiceImplementation;

import com.Dao.*;
import com.DaoImplementation.*;
import com.Entity.*;
import com.Service.*;
import java.util.List;

public class ReportServiceImpl implements ReportService {

    private final ReportDao reportDao = new ReportDaoImpl();

   

    @Override
    public void saveReport(Report report) {
        reportDao.saveReport(report);
    }

    @Override
    public List<Report> getAllReports() {
        return reportDao.getAllReports();
    }

    @Override
    public List<Report> getReportsByEmpId(String empId) {
        return reportDao.getReportsByEmpId(empId);
    }
    
//    @Override
//    public void addReport(Report report) {
//        reportDao.addReport(report);
//    }
    
    @Override
    public Report getReportByLeaveId(String leaveId) {
        return reportDao.getReportByLeaveId(leaveId);
    }

    @Override
    public void updateReport(Report report) {
        reportDao.updateReport(report);
    }

}


//    private ReportDao reportDao = new ReportDaoImpl();
//
//    @Override
//    public void addReport(Report report) {
//        reportDao.addReport(report);
//    }
//
//    @Override
//    public List<Report> getAllReports() {
//        return reportDao.getAllReports();
//    }
//
//    @Override
//    public void deleteReport(int reportId) {
//        reportDao.deleteReport(reportId);
//    }
//}
