package com.dgut.controller;

import com.dgut.bean.Department;
import com.dgut.bean.Msg;
import com.dgut.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author Baller_Xiao
 * @Description
 * @create_time 2020-11-11 23:50
 * @return
 * @Version
 */
@Controller
public class DepartmentController
{
    @Autowired
    private DepartmentService departmentService;
    @RequestMapping("/depts")
    @ResponseBody
    public Msg getDepts()
    {
       List<Department> list= departmentService.getDepts();
       return Msg.success().add("depts",list);
    }
}
