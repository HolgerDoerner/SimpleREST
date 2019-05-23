package com.example;

import java.util.List;

import com.example.bean.EmployeeBean;

public interface UserService {
    public List<EmployeeBean> getEmployees();
    public EmployeeBean getEmployeeById(Integer id);
    public EmployeeBean deleteEmployee(Integer id);
    public EmployeeBean updateEmployee(EmployeeBean employee);
}