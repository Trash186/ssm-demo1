package com.dgut;

import com.dgut.bean.Department;
import com.dgut.bean.Employee;
import com.dgut.dao.DepartmentMapper;
import com.dgut.dao.EmployeeMapper;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.UUID;

/**
 * @author Baller_Xiao
 * @Description
 * @create_time 2020-11-08 13:33
 * @return
 * @Version
 */
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class MapperTest
{
    @Autowired
    DepartmentMapper departmentMapper;

    @Autowired
    EmployeeMapper employeeMapper;

    @Autowired
    SqlSession sqlSession;
    @Test
    public void testCRUD()
    {
//        ApplicationContext ioc=new ClassPathXmlApplicationContext("applicationContext.xml");
//        DepartmentMapper bean = ioc.getBean(DepartmentMapper.class);
//
//        System.out.println(bean);
        System.out.println(departmentMapper);

        //departmentMapper.insertSelective(new Department(null,"开发部"));
        //departmentMapper.insertSelective(new Department(null,"测试部"));

        //employeeMapper.insertSelective(new Employee(null,"Jerry","M","thunderpg@163.com",1));

        //批量
        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);

        for (int i = 0; i < 1000; i++)
        {
            String uid = UUID.randomUUID().toString().substring(0, 5)+i;
            mapper.insertSelective(new Employee(null,uid,"M",uid+"@dgut.com",1));

        }
        System.out.println("success!");

    }

}
