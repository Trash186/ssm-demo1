package com.dgut.controller;

import com.dgut.bean.Employee;
import com.dgut.bean.Msg;
import com.dgut.service.EmployeeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Baller_Xiao
 * @Description
 * @create_time 2020-11-09 19:11
 * @return
 * @Version
 */
@Controller
public class EmployeeController
{
    @Autowired
    EmployeeService employeeService;
//    @RequestMapping("/emps")
//    public String getEmps(@RequestParam(value="pn",defaultValue = "1") Integer pn,Model model)
//    {
//        PageHelper.startPage(pn,5);//传入页码，以及每页大小
//
//        List<Employee> emps= employeeService.getAll();
//        PageInfo page =new PageInfo(emps,5);//用pageInfo包装查询后的结果
//
//        model.addAttribute("pageInfo",page);
//
//
//        return "list";
//    }

    @RequestMapping("/emps")
    @ResponseBody
    public Msg getEmpsWithJson(@RequestParam (value = "pn",defaultValue = "1") Integer pn)
    {
        PageHelper.startPage(pn,5);
        List<Employee> emps = employeeService.getAll();
        PageInfo page=new PageInfo(emps,5);

        return Msg.success().add("pageInfo",page);


    }

    @ResponseBody
    @RequestMapping("/checkuser")
    public Msg checkuser(@RequestParam("empName")String empName)
    {
        String regx="(^[a-zA-Z0-9_-]{6,16}$)|(^[\u2E80-\u9FFF]{2,5})";
        if(!empName.matches(regx))
        {
            return Msg.fail().add("va_msg","用户名必须是6-16位数字和字母的组合或者2-5位中文");
        }
        boolean b=employeeService.checkUser(empName);
        if(b)
        {
            return Msg.success();
        }
        else
        {
            return Msg.fail().add("va_msg","用户名不可用");
        }
    }

    @RequestMapping(value = "/emp",method = RequestMethod.POST)
    @ResponseBody
    public Msg saveEmp(@Valid Employee employee, BindingResult result)//@Valid jsr校验，校验总会有成功失败，紧跟一个BindingResult
    {
        if(result.hasErrors())
        {
            Map<String,Object> map=new HashMap<>();
            List<FieldError> errors=result.getFieldErrors();
            for(FieldError fieldError :errors)
            {
                System.out.println("错误的字段名："+fieldError.getField());
                System.out.println("错误信息："+fieldError.getDefaultMessage());
                map.put(fieldError.getField(), fieldError.getDefaultMessage());
            }
            return Msg.fail().add("errorFields",map);

        }
        else
        {
            employeeService.saveEmp(employee);
            return Msg.success();
        }
    }
    @RequestMapping(value ="/emp/{id}",method = RequestMethod.GET)
    @ResponseBody
    public Msg getEmp(@PathVariable("id")Integer id)//从路径地址中取得id
    {
        Employee employee=employeeService.getEmp(id);
        return Msg.success().add("emp",employee);
    }



    @RequestMapping(value="/emp/{empId}",method=RequestMethod.PUT)
    @ResponseBody
    public Msg saveEmp(Employee employee, HttpServletRequest request){
        System.out.println("请求体中的值："+request.getParameter("gender"));
        System.out.println("将要更新的员工数据："+employee);
        employeeService.updateEmp(employee);
        return Msg.success()	;
    }

//    @RequestMapping(value = "/emp/{id}",method = RequestMethod.DELETE)
//    public Msg deleteByEmpId(@PathVariable("id") Integer id)
//    {
//        employeeService.deleteEmp(id);
//        return Msg.success();
//    }
    @ResponseBody
    @RequestMapping(value = "/emp/{ids}",method = RequestMethod.DELETE)
    public Msg deleteEmp(@PathVariable("ids") String ids)
    {

        if(ids.contains("-"))
        {
            List<Integer> del_ids = new ArrayList<>();
            String[] str_ids=ids.split("-");

            for(String string:str_ids)
            {
                del_ids.add(Integer.parseInt(string));
            }
            employeeService.deleteBatch(del_ids);
        }
        else
        {
            int id = Integer.parseInt(ids);
            employeeService.deleteEmp(id);
        }
      return Msg.success();
    }
}
