package com.wgx.ssm.service;

import com.wgx.ssm.bean.Employee;
import com.wgx.ssm.bean.EmployeeExample;
import com.wgx.ssm.dao.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * author:wgx
 * version:1.0
 */
@Service
public class EmployeeService {
    @Autowired
    private EmployeeMapper employeeMapper;
    public List<Employee> listEmployees() {
        return employeeMapper.selectByExampleWhitDept(null);
    }
    public void saveEmployee(Employee employee) {
        employeeMapper.insertSelective(employee);
    }
    public boolean validateEmpName(String empName) {
        EmployeeExample employeeExample = new EmployeeExample();
        EmployeeExample.Criteria criteria = employeeExample.createCriteria();
        criteria.andEmpNameEqualTo(empName);
        List<Employee> list = employeeMapper.selectByExample(employeeExample);
        if (list.isEmpty()) {
            return true;
        }else {
            return false;
        }
    }

    public Employee getEmployeeById(Integer id) {
        return employeeMapper.selectByPrimaryKeyWhitDept(id);
    }

    public void updateEmployeeById(Employee employee) {
        employeeMapper.updateByPrimaryKey(employee);
    }

    public void deleteEmployeeById(Integer empId) {
        employeeMapper.deleteByPrimaryKey(empId);
    }

    public void deleteEmployeeBatch(List<Integer> intIds) {
        EmployeeExample employeeExample = new EmployeeExample();
        EmployeeExample.Criteria criteria = employeeExample.createCriteria();
        criteria.andEmpIdIn(intIds);
        employeeMapper.deleteByExample(employeeExample);
    }
}
