package com.wgx.ssm.test;

import com.wgx.ssm.bean.Department;
import com.wgx.ssm.bean.Employee;
import com.wgx.ssm.dao.DepartmentMapper;
import com.wgx.ssm.dao.EmployeeMapper;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Random;
import java.util.UUID;

/**
 * author:wgx
 * version:1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class MapperTest {
    @Autowired
    DepartmentMapper departmentMapper;
    @Autowired
    EmployeeMapper employeeMapper;
    @Autowired
    SqlSession sqlSession;
    @Test
    public void testDept() {
        System.out.println(departmentMapper);
        departmentMapper.insertSelective(new Department(2, "测试部"));
    }
    @Test
    public void testEmployee() {
        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
        for (int i = 0 ; i < 1000 ; i++) {
            String uid = UUID.randomUUID().toString().substring(0, 5);
            int anInt = new Random().nextInt(2);
            mapper.insertSelective(new Employee(null, uid, "m", uid + "@qq.com", anInt + 1));
        }
    }
}
