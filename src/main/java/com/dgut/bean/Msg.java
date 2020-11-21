package com.dgut.bean;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Baller_Xiao
 * @Description
 * @create_time 2020-11-09 21:16
 * @return
 * @Version
 */
public class Msg
{
    private int code;
    private String msg;

   private Map<String,Object> extend= new HashMap();

   public static Msg success()
   {
       Msg result = new Msg();
       result.setCode(100);
       result.setMsg("处理成功！");
       return result;
   }

   public Msg add(String key,Object value)
   {
       this.getExtend().put(key, value);
       return this;
   }


   public static Msg fail()
   {
       Msg result=new Msg();
       result.setCode(200);
       result.setMsg("处理失败！");
       return result;
   }
    public int getCode()
    {
        return code;
    }

    public void setCode(int code)
    {
        this.code = code;
    }

    public String getMsg()
    {
        return msg;
    }

    public void setMsg(String msg)
    {
        this.msg = msg;
    }

    public Map<String, Object> getExtend()
    {
        return extend;
    }

    public void setExtend(Map<String, Object> extend)
    {
        this.extend = extend;
    }
}
