package com.Entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Report")
public class Report {

    @Id
    @Column
    private String reportId;

    @Column
    private String empName;

    @Column
    private String leaveType;

    @Column
    private String startDate;

    @Column
    private String endDate;

    @Column
    private Integer totalDays;

    @Column
    private String status;

    @Column
    private String remark;

    @Temporal(TemporalType.DATE)
    private Date generatedDate;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "leaveId", referencedColumnName = "leaveId")
    private LeaveTable leave;

    // ✅ Proper FK Mapping
    @ManyToOne
    @JoinColumn(name = "empId", referencedColumnName = "empId")
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "adminId", referencedColumnName = "adminId")
    private Admin admin;


    public Report() {}

    public Report(String reportId, String empName, String leaveType, String startDate, String endDate,
                  Integer totalDays, String status, String remark, Date generatedDate,
                  LeaveTable leave, Employee employee, Admin admin) {
        this.reportId = reportId;
        this.empName = empName;
        this.leaveType = leaveType;
        this.startDate = startDate;
        this.endDate = endDate;
        this.totalDays = totalDays;
        this.status = status;
        this.remark = remark;
        this.generatedDate = generatedDate;
        this.leave = leave;
        this.employee = employee;
        this.admin = admin;
    }

    // Getters and Setters (All fields)

    public String getReportId() {
        return reportId;
    }

    public void setReportId(String reportId) {
        this.reportId = reportId;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getLeaveType() {
        return leaveType;
    }

    public void setLeaveType(String leaveType) {
        this.leaveType = leaveType;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Integer getTotalDays() {
        return totalDays;
    }

    public void setTotalDays(Integer totalDays) {
        this.totalDays = totalDays;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getComment() {
        return remark;
    }

    public void setComment(String remark) {
        this.remark = remark;
    }

    public Date getGeneratedDate() {
        return generatedDate;
    }

    public void setGeneratedDate(Date generatedDate) {
        this.generatedDate = generatedDate;
    }

    public LeaveTable getLeave() {
        return leave;
    }

    public void setLeave(LeaveTable leave) {
        this.leave = leave;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }
}



//package com.Entity;
//
//import javax.persistence.*;
//
//@Entity
//@Table(name = "Report")
//public class Report {
//
//    @Id
//    @Column
//    private String reportId;
//   
//    @Column// Snapshot of Employee ID
//    private String empName;          // Snapshot of Employee Name
//    @Column
//    private String leaveType;        // Snapshot of Leave Type
//    @Column
//    private String startDate;        // Snapshot of Leave Start Date
//    @Column
//    private String endDate;          // Snapshot of Leave End Date
//    @Column
//    private Integer totalDays;           // Snapshot of number of days
//    @Column
//    private String status;           // Approved / Rejected
//    @Column
//    private String remark;          // Optional comment (like "Approved by admin on X date")
//    @Column(insertable = false, updatable = false)
//    private String empId;
//   
//
//    // Optional: FK mappings (not required for snapshots but can be used if needed)
//     @ManyToOne
//     @JoinColumn(name = "leaveId")
//     private Leave leave;
//
//     @ManyToOne
//     @JoinColumn(name = "empId", insertable = false, updatable = false)
//     private Employee employee;
//
//     @ManyToOne
//     @JoinColumn(name = "adminId", insertable = false, updatable = false)
//     private Admin admin;
//	
//
//    // Constructors
//    public Report() {}
//
//    public Report(String reportId , String empName, String leaveType, String startDate, String endDate, Integer totalDays, String status, String remark,String empId) {
//        
//        this.empName = empName;
//        this.leaveType = leaveType;
//        this.startDate = startDate;
//        this.endDate = endDate;
//        this.totalDays = totalDays;
//        this.status = status;
//        this.remark = remark;
//        this.reportId=reportId;
//        this.empId=empId;
//       
//    }
//
//    // Getters and Setters
//    public String getReportId() {
//        return reportId;
//    }
//
//    public void setReportId(String reportId) {
//        this.reportId = reportId;
//    }
//
//    
//
//    public String getEmpName() {
//        return empName;
//    }
//
//    public void setEmpName(String empName) {
//        this.empName = empName;
//    }
//
//    public String getLeaveType() {
//        return leaveType;
//    }
//
//    public void setLeaveType(String leaveType) {
//        this.leaveType = leaveType;
//    }
//
//    public String getStartDate() {
//        return startDate;
//    }
//
//    public void setStartDate(String startDate) {
//        this.startDate = startDate;
//    }
//
//    public String getEndDate() {
//        return endDate;
//    }
//
//    public void setEndDate(String endDate) {
//        this.endDate = endDate;
//    }
//
//    public Integer getTotalDays() {
//        return totalDays;
//    }
//
//    public void setTotalDays(Integer totalDays) {
//        this.totalDays = totalDays;
//    }
//
//    public String getStatus() {
//        return status;
//    }
//
//    public void setStatus(String status) {
//        this.status = status;
//    }
//
//    public String getComment() {
//        return remark;
//    }
//
//    public void setComment(String remark) {
//        this.remark = remark;
//    }
//
// // ✅ Getter
//    public Leave getLeave() {
//        return leave;
//    }
//
//    // ✅ Setter
//    public void setLeave(Leave leave) {
//        this.leave = leave;
//    }
//
//    public Employee getEmployee() {
//        return employee;
//    }
//
//    public void setEmployee(Employee employee) {
//        this.employee = employee;
//    }
//
//    public String getEmpId() {
//        return empId;
//    }
//
//    public void setEmpId(String empId) {
//        this.empId = empId;
//    }
//	
//}


//@Entity
//@Table(name = "Report")
//public class Report {
//
//    @Id
//    private int reportId;  // Unique report ID
//
//    @ManyToOne
//    @JoinColumn(name = "empid", nullable = false)
//    private Employee employee;  // Employee whose leave report is generated
//    
//    @OneToOne
//    @JoinColumn(name = "Leaveid")
//    private Leave leave;
//
//    @Column //(name = "total_leaves_taken", nullable = false)
//    private int totalLeavesTaken;  // Total leaves taken by the employee
//
//    @Column //(name = "remaining_leaves", nullable = false)
//    private int remainingLeaves;  // Remaining leaves for the employee
//
//    @Column
//	private String leaveType;
//
//    // **Constructors**
//    public Report() {}  // Default constructor
//
//    public Report(int reportId, Employee employee, int totalLeavesTaken) {
//        this.reportId = reportId;
//        this.employee = employee;
//        //this.leave=leave;
//        this.totalLeavesTaken = totalLeavesTaken;
//        this.remainingLeaves = 5-totalLeavesTaken;
//    }
//
//    // **Getters and Setters**
//    public int getReportId() { return reportId; }
//    public void setReportId(int reportId) { this.reportId = reportId; }
//
//    public Employee getEmployee() { return employee; }
//    public void setEmployee(Employee employee) { this.employee = employee; }
//
//    public int getTotalLeavesTaken() { return totalLeavesTaken; }
//    public void setTotalLeavesTaken(int totalLeavesTaken) { this.totalLeavesTaken = totalLeavesTaken; }
//
//    public int getRemainingLeaves() { return remainingLeaves; }
//    public void setRemainingLeaves(int remainingLeaves) { this.remainingLeaves = remainingLeaves; }
//
//    public String getLeaveType() { return getLeaveType(); }
//    public void setLeaveType(String leaveType) { this.leaveType = leaveType; }
//    
//    public Leave getLeave() { return leave; }
//    public void setLeave(Leave leave) { this.leave = leave; }
//
//}