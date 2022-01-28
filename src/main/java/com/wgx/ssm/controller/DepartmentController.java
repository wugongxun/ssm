package com.wgx.ssm.controller;

import com.wgx.ssm.bean.Department;
import com.wgx.ssm.bean.Message;
import com.wgx.ssm.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * author:wgx
 * version:1.0
 */
@Controller
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    @ResponseBody
    @RequestMapping("/getDepts")
    public Message getDepts() {
        List<Department> depts = departmentService.getDepts();
        return Message.success().add("depts", depts);
    }
}
