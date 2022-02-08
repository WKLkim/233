package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("partner")        //数据库表名
@Data
public class Partner {
    @TableId(value = "id",type = IdType.AUTO)
    private String id;
    private String name;
    private String type;
    private String manager;
    private String phone;


    public Object getName() {
        return this.name;
    }
}
