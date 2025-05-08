package com.Entity;

import java.util.List;



import javax.persistence.*;


/**
 * Company entity represents a company where employees work.
 * A company can have multiple departments and employees.
 */
@Entity
@Table(name = "Company")
public class Company {
    
    @Id
    @Column
    private int companyId;

    @Column( nullable = false, unique = true)
    private String companyName;

    public Company() {}

    public Company(int companyId, String companyName) {
        this.companyId = companyId;
        this.companyName = companyName;
    }

    // Getters and Setters
    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @Override
    public String toString() {
        return "Company [companyId=" + companyId + ", companyName=" + companyName + "]";
    }
}
//@Entity
//@Table(name = "Company")
//public class Company {
//
//    @Id
//    private int compId;  // Unique company ID
//    
//    @Column(name = "comp_name", nullable = false, length = 100)
//    private String compName;  // Company name
//    
//    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
//    private List<Employee> employees;  // Company has multiple employees
//
//    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
//    private List<Department> departments;  // Company has multiple departments
//
//    // **Constructors**
//    public Company() {}  // Default constructor
//    
//    public Company(int compId, String compName) {
//        this.compId = compId;
//        this.compName = compName;
//    }
//
//    // **Getters and Setters**
//    public int getCompId() { return compId; }
//    public void setCompId(int compId) { this.compId = compId; }
//
//    public String getCompName() { return compName; }
//    public void setCompName(String compName) { this.compName = compName; }
//
//    public List<Employee> getEmployees() { return employees; }
//    public void setEmployees(List<Employee> employees) { this.employees = employees; }
//
//    public List<Department> getDepartments() { return departments; }
//    public void setDepartments(List<Department> departments) { this.departments = departments; }
//}


