package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("goods")        //数据库表名
@Data
public class Goods {
    @TableId(value = "Gid",type = IdType.AUTO)
    private String Gid;
    private String Gname;
    private Integer Gprice;
    private Integer Gnum;
    private String Gtext;


    public Object getGname() {
        return this.Gname;
    }
}
