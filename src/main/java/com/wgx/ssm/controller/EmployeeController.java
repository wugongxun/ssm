package com.wgx.ssm.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wgx.ssm.bean.Employee;
import com.wgx.ssm.bean.Message;
import com.wgx.ssm.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * author:wgx
 * version:1.0
 */
@Controller
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @RequestMapping(value = "/employees", method = RequestMethod.GET)
    @ResponseBody
    public Message listEmployeesWhitJson(@RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo) {
        PageHelper.startPage(pageNo, 5);
        List<Employee> employees = employeeService.listEmployees();
        PageInfo<Employee> pageInfo = new PageInfo<>(employees, 5);
        return Message.success().add("pageInfo", pageInfo);
    }

    @RequestMapping(value = "/employee", method = RequestMethod.POST)
    @ResponseBody
    public Message saveEmployee(@Valid Employee employee, BindingResult result) {
        if (result.hasErrors()) {
            List<FieldError> fieldErrors = result.getFieldErrors();
            //错误的字段和错误的信息
            Map<String, String> map = new HashMap<>();
            for (FieldError fieldError : fieldErrors) {
                map.put(fieldError.getField(), fieldError.getDefaultMessage());
            }
            return Message.fail().add("errorFields", map);
        } else {
            employeeService.saveEmployee(employee);
            return Message.success();
        }
    }

    @RequestMapping("/validateEmpName")
    @ResponseBody
    public Message validateEmpName(String empName) {
        boolean validateEmpName = employeeService.validateEmpName(empName);
        if (validateEmpName) {
            return Message.success();
        } else {
            return Message.fail();
        }
    }

    @RequestMapping(value = "/employee/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Message getEmployeeById(@PathVariable Integer id) {
        Employee employee = employeeService.getEmployeeById(id);
        return Message.success().add("employee", employee);
    }
    @RequestMapping(value = "/employee/{empId}", method = RequestMethod.PUT)
    @ResponseBody
    public Message updateEmployeeById(@Valid Employee employee, BindingResult result) {
        if (result.hasErrors()) {
            List<FieldError> fieldErrors = result.getFieldErrors();
            //错误的字段和错误的信息
            Map<String, String> map = new HashMap<>();
            for (FieldError fieldError : fieldErrors) {
                map.put(fieldError.getField(), fieldError.getDefaultMessage());
            }
            return Message.fail().add("errorFields", map);
        } else {
            employeeService.updateEmployeeById(employee);
            return Message.success();
        }
    }
    @RequestMapping(value = "/employee/{empId}", method = RequestMethod.DELETE)
    @ResponseBody
    public Message deleteEmployeeById(@PathVariable("empId") Integer empId) {
        employeeService.deleteEmployeeById(empId);
        return Message.success();
    }

    @RequestMapping(value = "employees", method = RequestMethod.DELETE)
    @ResponseBody
    public Message deleteEmployeeBatch(String empIds) {
        String[] ids = empIds.split("-");
        List<Integer> intIds = new ArrayList<>();
        for (int i = 0; i < ids.length; i++) {
            intIds.add(Integer.parseInt(ids[i]));
        }
        employeeService.deleteEmployeeBatch(intIds);
        return Message.success();
    }

//    @RequestMapping("/employees")
//    public ModelAndView listEmployees(@RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo) {
//        ModelAndView mav = new ModelAndView();
//        PageHelper.startPage(pageNo, 5);
//        List<Employee> employees = employeeService.listEmployees();
//        PageInfo<Employee> pageInfo = new PageInfo<>(employees, 5);
//        mav.addObject("pageInfo", pageInfo);
//        mav.setViewName("list");
//        return mav;
//    }

}
