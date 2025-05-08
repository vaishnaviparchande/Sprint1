package com.Dao;

import com.Entity.Department;
import java.util.List;

public interface DepartmentDao {
	
	void addDepartment(Department department);
    Department getDepartmentById(int deptId);
    List<Department> getAllDepartments();
    void updateDepartment(Department department);
    void deleteDepartment(int deptId);
//    void addDepartment(Department department);
//    Department getDepartmentById(int depId);
//    List<Department> getAllDepartments();
//    void updateDepartment(Department department);
//    void deleteDepartment(int depId);
}

