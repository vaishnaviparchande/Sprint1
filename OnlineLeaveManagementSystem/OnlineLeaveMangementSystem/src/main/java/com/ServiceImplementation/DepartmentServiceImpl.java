package com.ServiceImplementation;

import com.Dao.DepartmentDao;
import com.DaoImplementation.DepartmentDaoImpl;
import com.Entity.Department;
import com.Service.DepartmentService;
import java.util.List;

public class DepartmentServiceImpl implements DepartmentService {
	private DepartmentDao departmentDao = new DepartmentDaoImpl();

    @Override
    public void addDepartment(Department department) {
        departmentDao.addDepartment(department);
    }

    @Override
    public Department getDepartmentById(int deptId) {
        return departmentDao.getDepartmentById(deptId);
    }

    @Override
    public List<Department> getAllDepartments() {
        return departmentDao.getAllDepartments();
    }

    @Override
    public void updateDepartment(Department department) {
        departmentDao.updateDepartment(department);
    }

    @Override
    public void deleteDepartment(int deptId) {
        departmentDao.deleteDepartment(deptId);
    }
//    private final DepartmentDao departmentDao = new DepartmentDaoImpl();
//
//    @Override
//    public void addDepartment(Department department) {
//        departmentDao.addDepartment(department);
//    }
//
//    @Override
//    public Department getDepartmentById(int depId) {
//        return departmentDao.getDepartmentById(depId);
//    }
//
//    @Override
//    public List<Department> getAllDepartments() {
//        return departmentDao.getAllDepartments();
//    }
//
//    @Override
//    public void updateDepartment(Department department) {
//        departmentDao.updateDepartment(department);
//    }
//
//    @Override
//    public void deleteDepartment(int depId) {
//        departmentDao.deleteDepartment(depId);
//    }
}
