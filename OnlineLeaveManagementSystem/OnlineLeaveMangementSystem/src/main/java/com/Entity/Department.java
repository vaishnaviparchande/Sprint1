package com.Entity;

import java.util.List;
import javax.persistence.*;


/**
 * Department entity represents different departments in a company.
 * Each department belongs to a company and has an admin.
 */


@Entity
@Table(name = "Department")
public class Department {
    @Id
    private String deptId;
    @Column
    private String deptName;

    public Department() {}

    public Department(String deptId, String deptName) {
        this.deptId = deptId;
        this.deptName = deptName;
    }
    
    // getter setter 

    public String getDeptId() { return deptId; }
    public void setDeptId(String deptId) { this.deptId = deptId; }
    public String getDeptName() { return deptName; }
    public void setDeptName(String deptName) { this.deptName = deptName; }
}


//@Entity
//@Table(name = "Department")
//public class Department {
//
//    @Id
//    private int depId;  // Unique department ID
//    
//    @Column(name = "dep_name", nullable = false, length = 100)
//    private String depName;  // Department name
//    
//    @ManyToOne
//    @JoinColumn(name = "compid")
//    private Company company;  // Department belongs to a company
//    
//    @ManyToOne
//    @JoinColumn(name = "adminid")
//    private Admin admin;  // Department is managed by an admin
//
//    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
//    private List<Employee> employees;  // Department has multiple employees
//
//    // **Constructors**
//    public Department() {}  // Default constructor
//    
//    public Department(int depId, String depName, Company company, Admin admin) {
//        this.depId = depId;
//        this.depName = depName;
//        this.company = company;
//        this.admin = admin;
//    }
//
//    // **Getters and Setters**
//    public int getDepId() { return depId; }
//    public void setDepId(int depId) { this.depId = depId; }
//
//    public String getDepName() { return depName; }
//    public void setDepName(String depName) { this.depName = depName; }
//
//    public Company getCompany() { return company; }
//    public void setCompany(Company company) { this.company = company; }
//
//    public Admin getAdmin() { return admin; }
//    public void setAdmin(Admin admin) { this.admin = admin; }
//
//    public List<Employee> getEmployees() { return employees; }
//    public void setEmployees(List<Employee> employees) { this.employees = employees; }
//}


