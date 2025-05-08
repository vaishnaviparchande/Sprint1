package com.Dao;


import java.util.List;

import com.Entity.Employee;

public interface EmployeeDao {
	 void addEmployee(Employee employee);
	    Employee getEmployeeById(String empId);
	    List<Employee> getAllEmployees();
	    public void updateEmployee(Employee employee);
	    public void deleteEmployee(String empId);

}  
//	void addEmployee(Employee employee);      // Create
//    Employee getEmployeeById(int empID); // Read
//    List<Employee> getAllEmployee();      // Read All
//    void updateEmployee(Employee employee);   // Update
//    void deleteEmployee(int empID);   // Delete


