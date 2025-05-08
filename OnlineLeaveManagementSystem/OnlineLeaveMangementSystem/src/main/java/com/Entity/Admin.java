package com.Entity;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;


@Entity
@Table(name = "Admin")
public class Admin {
    @Id
    @Column
    private String adminId;
    @Column
    private String adminPass;
    
    @OneToMany(mappedBy = "admin", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Report> reports = new ArrayList<>();


    public Admin() {}

    public Admin(String adminId, String adminPass) {
        this.adminId = adminId;
        this.adminPass = adminPass;
    }
    
    // getter setter method 

    public List<Report> getReports() {
        return reports;
    }

    public void setReports(List<Report> reports) {
        this.reports = reports;
    }
    public String getAdminId() { return adminId; }
    public void setAdminId(String adminId) { this.adminId = adminId; }
    public String getAdminPass() { return adminPass; }
    public void setAdminPass(String adminPass) { this.adminPass = adminPass; }
    
    @Override
    public String toString() {
        return "Admin [adminId=" + adminId + ", adminPass=" + adminPass + "]";
    }
}


//import java.util.List;
//
//import javax.persistence.CascadeType;
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.Id;
//import javax.persistence.OneToMany;
//import javax.persistence.Table;
//
//@Entity
//@Table(name = "Admin")
//public class Admin {
//
//    @Id
//    @Column  // (name = "adminID")
//    private int adminID;
//
//    @Column(name = "admin_name", nullable = false)
//    private String adminName;
//
//    @Column(name = "admin_email", unique = true, nullable = false)
//    private String adminEmail;
//
//    @Column(name = "admin_pass", nullable = false)
//    private String adminPassword;
//
//    @OneToMany(mappedBy = "admin", cascade = CascadeType.ALL)
//    private List<Department> departments;
//
//    @OneToMany(mappedBy = "admin", cascade = CascadeType.ALL)
//    private List<Employee> employees;
//
//    @OneToMany(mappedBy = "admin", cascade = CascadeType.ALL)
//    private List<Leave> leaveRequests;
//
//    // Constructors
//    public Admin() {}
//
//    public Admin(int adminID, String adminName, String adminEmail, String adminPassword) {
//        this.adminID = adminID;
//        this.adminName = adminName;
//        this.adminEmail = adminEmail;
//        this.adminPassword = adminPassword;
//    }
//
//    // Getters and Setters
//    public int getAdminID() { return adminID; }
//    public void setAdminID(int adminID) { this.adminID = adminID; }
//
//    public String getAdminName() { return adminName; }
//    public void setAdminName(String adminName) { this.adminName = adminName; }
//
//    public String getAdminEmail() { return adminEmail; }
//    public void setAdminEmail(String adminEmail) { this.adminEmail = adminEmail; }
//
//    public String getAdminPassword() { return adminPassword; }
//    public void setAdminPassword(String adminPassword) { this.adminPassword = adminPassword; }
//
//    public List<Department> getDepartments() { return departments; }
//    public void setDepartments(List<Department> departments) { this.departments = departments; }
//
//    public List<Employee> getEmployees() { return employees; }
//    public void setEmployees(List<Employee> employees) { this.employees = employees; }
//
//    public List<Leave> getLeaveRequests() { return leaveRequests; }
//    public void setLeaveRequests(List<Leave> leaveRequests) { this.leaveRequests = leaveRequests; }
//}



