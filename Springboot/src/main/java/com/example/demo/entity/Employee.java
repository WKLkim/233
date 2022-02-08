package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("employee")        //数据库表名
@Data
public class Employee {
    @TableId(value = "id",type = IdType.AUTO)
    private String id;
    private String name;
    private String position;
    private String salary;
    private String phone;
    private String address;


    public Object getName() {
        return this.name;
    }
}
