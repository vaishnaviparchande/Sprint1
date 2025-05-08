package com.ServiceImplementation;

import com.Dao.*;
import com.DaoImplementation.*;
import com.Entity.*;
import com.Service.EmployeeService;
import java.util.List;

public class EmployeeServiceImpl implements EmployeeService {
	private EmployeeDao employeeDao = new EmployeeDaoImpl();

    @Override
    public void addEmployee(Employee employee) {
        employeeDao.addEmployee(employee);
    }

    @Override
    public Employee getEmployeeById(String empId) {
        return employeeDao.getEmployeeById(empId);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeDao.getAllEmployees();
    }
    
    @Override
    public void updateEmployee(Employee employee) {
        employeeDao.updateEmployee(employee);
    }
    
    @Override
    public void deleteEmployee(String empId) {
        employeeDao.deleteEmployee(empId);
    }



//    private EmployeeDao employeeDao = new EmployeeDaoImpl(); // Using DAO Implementation
//
//    @Override
//    public void addEmployee(Employee employee) {
//        employeeDao.addEmployee(employee);
//    }
//
//    @Override
//    public Employee getEmployeeById(int empId) {
//        return employeeDao.getEmployeeById(empId);
//    }
//
//    @Override
//    public List<Employee> getAllEmployee() {
//        return employeeDao.getAllEmployee();
//    }
//
//    @Override
//    public void updateEmployee(Employee employee) {
//        employeeDao.updateEmployee(employee);
//    }
//
//    @Override
//    public void deleteEmployee(int empID) {
//        employeeDao.deleteEmployee(empID);
//    }
}


