package com.Entity;

import java.util.ArrayList;
import java.util.List;



import javax.persistence.*;

@Entity
@Table(name = "Employee")
public class Employee {
    @Id
    @Column
    private String empId;
    @Column
    private String empName;
    @Column
    private String empEmail;
    @Column
    private String empPass;
    @Column
    private String departmentName;
    @Column
    private String companyName;
    
    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Report> reports = new ArrayList<>();


    public Employee() {}

    public Employee(String empId, String empName, String empEmail, String empPass, String departmentName, String companyName) {
        this.empId = empId;
        this.empName = empName;
        this.empEmail = empEmail;
        this.empPass = empPass;
        this.departmentName = departmentName;
        this.companyName = companyName;
    }
    
    // getter setter method 

    public String getEmpId() { return empId; }
    public void setEmpId(String empId) { this.empId = empId; }
    public String getEmpName() { return empName; }
    public void setEmpName(String empName) { this.empName = empName; }
    public String getEmpEmail() { return empEmail; }
    public void setEmpEmail(String empEmail) { this.empEmail = empEmail; }
    public String getEmpPass() { return empPass; }
    public void setEmpPass(String empPass) { this.empPass = empPass; }
    public String getDepartmentName() { return departmentName; }
    public void setDepartmentName(String departmentName) { this.departmentName = departmentName; }
    public String getCompanyName() { return companyName; }
    public void setCompanyName(String companyName) { this.companyName = companyName; }
    
    public List<Report> getReports() {
        return reports;
    }

    public void setReports(List<Report> reports) {
        this.reports = reports;
    }
}



/**
 * Employee entity represents the employee details in the system.
 * It contains attributes like ID, name, email, department, and company details.
 */

//@Entity
////@Table(name = "Employee")
//public class Employee {
//
//    @Id
//    private int empId;  // Unique identifier for an employee
//    
//    @Column(name = "emp_name", nullable = false, length = 50)
//    private String empName;  // Employee's full name
//    
//    @Column(name = "emp_email", nullable = false, unique = true)
//    private String empEmail;  // Employee's email (unique)
//    
//    @Column(name = "emp_pass", nullable = false)
//    private String empPassword;  // Employee's login password
//    
//    @ManyToOne
//    @JoinColumn(name = "depid") 
//    private Department department;  // Employee belongs to a department
//    
//    @ManyToOne
//    @JoinColumn(name = "compid")
//    private Company company;  // Employee belongs to a company
//    
//    @ManyToOne
//    @JoinColumn(name = "adminid")
//    private Admin admin;  // Employee is managed by an admin
//    
//    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
//    private List<Leave> leaves;  // Employee can apply for multiple leaves
//
//    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
//    private List<Report> reports;  // Employee has multiple reports
//
//    // **Constructors**  
//    public Employee() {}  // Default constructor
//    
//    public Employee(int empId, String empName, String empEmail, String empPassword, Department department, Company company, Admin admin) {
//        this.empId = empId;
//        this.empName = empName;
//        this.empEmail = empEmail;
//        this.empPassword = empPassword;
//        this.department = department;
//        this.company = company;
//        this.admin = admin;
//    }
//
//    // **Getters and Setters**
//    public int getEmpId() { return empId; }
//    public void setEmpId(int empId) { this.empId = empId; }
//
//    public String getEmpName() { return empName; }
//    public void setEmpName(String empName) { this.empName = empName; }
//
//    public String getEmpEmail() { return empEmail; }
//    public void setEmpEmail(String empEmail) { this.empEmail = empEmail; }
//
//    public String getEmpPassword() { return empPassword; }
//    public void setEmpPassword(String empPassword) { this.empPassword = empPassword; }
//
//    public Department getDepartment() { return department; }
//    public void setDepartment(Department department) { this.department = department; }
//
//    public Company getCompany() { return company; }
//    public void setCompany(Company company) { this.company = company; }
//
//    public Admin getAdmin() { return admin; }
//    public void setAdmin(Admin admin) { this.admin = admin; }
//
//    public List<Leave> getLeaves() { return leaves; }
//    public void setLeaves(List<Leave> leaves) { this.leaves = leaves; }
//
//    public List<Report> getReports() { return reports; }
//    public void setReports(List<Report> reports) { this.reports = reports; }
//    
//    @Override
//    public String toString() {
//        return "Employee ID: " + empId + 
//               "\nName: " + empName + 
//               "\nEmail: " + empEmail + 
//               "\nDepartment: " + (department != null ? department.getDepName() : "Not Assigned") +
//               "\nCompany: " + (company != null ? company.getCompName() : "Not Assigned") +
//               "\nAdmin: " + (admin != null ? admin.getAdminName() : "Not Assigned") + 
//               "\n======================================";
//    }





