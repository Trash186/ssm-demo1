package com.dgut.service;

import com.dgut.bean.Department;
import com.dgut.dao.DepartmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Baller_Xiao
 * @Description
 * @create_time 2020-11-11 23:51
 * @return
 * @Version
 */
@Service
public class DepartmentService
{
    @Autowired
    DepartmentMapper departmentMapper;

    public List<Department> getDepts()
    {
        List<Department> list = departmentMapper.selectByExample(null);

        return list;

    }
}
