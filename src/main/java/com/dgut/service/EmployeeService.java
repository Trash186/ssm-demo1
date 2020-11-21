package com.dgut.service;

import com.dgut.bean.Employee;
import com.dgut.bean.EmployeeExample;
import com.dgut.dao.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Baller_Xiao
 * @Description
 * @create_time 2020-11-09 19:14
 * @return
 * @Version
 */
@Service
public class EmployeeService
{

    @Autowired
    EmployeeMapper employeeMapper;
    public List<Employee> getAll()
    {
        return employeeMapper.selectByExampleWithDept(null);
    }

    public boolean checkUser(String empName)
    {
        EmployeeExample example = new EmployeeExample();
        EmployeeExample.Criteria criteria = example.createCriteria();
        criteria.andEmpNameEqualTo(empName);
        long count=employeeMapper.countByExample(example);//统计符合条件的数值
        return count==0;
    }

    public void saveEmp(Employee employee)
    {
        employeeMapper.insertSelective(employee);
    }

    public Employee getEmp(Integer id)
    {
        Employee employee = employeeMapper.selectByPrimaryKey(id);
        return employee;
    }

    public void updateEmp(Employee employee)
    {
        employeeMapper.updateByPrimaryKeySelective(employee);//id已经写死，不需要更新，因此用selective


    }

    public void deleteEmp(Integer id)
    {
        employeeMapper.deleteByPrimaryKey(id);
    }

    public void deleteBatch(List<Integer> ids)
    {
        EmployeeExample example = new EmployeeExample();
        EmployeeExample.Criteria criteria = example.createCriteria();
        criteria.andEmpIdIn(ids);

        employeeMapper.deleteByExample(example);


    }
}
