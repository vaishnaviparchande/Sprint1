package com.Entity;


import java.util.Date;
import javax.persistence.*;


/**
 * Leave entity represents a leave request made by an employee.
 * The admin will approve or reject the leave request.
 */

@Entity
@Table
public class LeaveTable {
    @Id
    @Column
    private String leaveId;

    @ManyToOne
    @JoinColumn(name = "empId")
    private Employee employee;

    private String startDate;
    private String endDate;
    private String leaveType;
    private int totalDays;
    private String leaveStatus;

    public LeaveTable() {}

    public LeaveTable(String leaveId,Employee employee, String startDate, String endDate, String leaveType, int totalDays, String leaveStatus) {
    	this.leaveId=leaveId;
        this.employee = employee;
        this.startDate = startDate;
        this.endDate = endDate;
        this.leaveType = leaveType;
        this.totalDays = totalDays;
        this.leaveStatus = leaveStatus;
    }

    // getter setter method 
    public String getLeaveId() { return leaveId; }
    public Employee getEmployee() { return employee; }
    public String getStartDate() { return startDate; }
    public String getEndDate() { return endDate; }
    public String getLeaveType() { return leaveType; }
    public int getTotalDays() { return totalDays; }
    public String getLeaveStatus() { return leaveStatus; }
    public void setLeaveStatus(String leaveStatus) { this.leaveStatus = leaveStatus; }
}

//@Entity
//@Table(name = "Leave_Table")  // "Leave" is a reserved keyword, so we rename it
//public class Leave {
//
//    @Id
//    private int leaveId;  // Unique leave ID
//
//    @ManyToOne
//    @JoinColumn(name = "empid", nullable = false)
//    private Employee employee;  // The employee applying for leave
//
//    @ManyToOne
//    @JoinColumn(name = "adminId", nullable = false)
//    private Admin admin;  // The admin who approves/rejects the leave
//
//    @Column(name = "start_date", nullable = false)
//    @Temporal(TemporalType.DATE)
//    private Date startDate;  // Leave start date
//
//    @Column(name = "end_date", nullable = false)
//    @Temporal(TemporalType.DATE)
//    private Date endDate;  // Leave end date
//
//    @Column(name = "leave_type", nullable = false, length = 50)
//    private String leaveType;  // Type of leave (e.g., Sick Leave, Casual Leave)
//
//    @Column(name = "leave_status", nullable = false, length = 20)
//    private String leaveStatus;  // Leave status (Pending, Approved, Rejected)
//
//    // **Constructors**
//    public Leave() {}  // Default constructor
//
//    public Leave(int leaveId, Employee employee, Admin admin, Date startDate, Date endDate, String leaveType) {
//        this.leaveId = leaveId;
//        this.employee = employee;
//        this.admin = admin;
//        this.startDate = startDate;
//        this.endDate = endDate;
//        this.leaveType = leaveType;
//        this.leaveStatus = "Pending";
//    }
//
//    // **Getters and Setters**
//    public int getLeaveId() { return leaveId; }
//    public void setLeaveId(int leaveId) { this.leaveId = leaveId; }
//
//    public Employee getEmployee() { return employee; }
//    public void setEmployee(Employee employee) { this.employee = employee; }
//
//    public Admin getAdmin() { return admin; }
//    public void setAdmin(Admin admin) { this.admin = admin; }
//
//    public Date getStartDate() { return startDate; }
//    public void setStartDate(Date startDate) { this.startDate = startDate; }
//
//    public Date getEndDate() { return endDate; }
//    public void setEndDate(Date endDate) { this.endDate = endDate; }
//
//    public String getLeaveType() { return leaveType; }
//    public void setLeaveType(String leaveType) { this.leaveType = leaveType; }
//
//    public String getLeaveStatus() { return leaveStatus; }
//    public void setLeaveStatus(String leaveStatus) { this.leaveStatus = leaveStatus; }
//
//	
//	
//}
