package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("login")        //数据库表名
@Data
public class Login {
    @TableId(value = "username",type = IdType.AUTO)
    private String username;
    private String text;
    public Object getUsername() {
        return this.username;
    }
}
