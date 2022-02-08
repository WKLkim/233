package com.example.demo.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.Result;
import com.example.demo.entity.Login;
import com.example.demo.mapper.LoginMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/login")
public class Logincontroller {
    @Resource
    LoginMapper loginMapper;

//    @PostMapping   //post接口
//    public Result<?> save(@RequestBody Login login){
//        loginMapper.insert(login);
//        return Result.success();
//    }

//    @PutMapping   //post接口
//    public Result<?> update(@RequestBody Login login){
//        System.out.print(login);
//        loginMapper.updateById(login);
//        return Result.success();
//    }

//    @DeleteMapping("/{gid}")   //post接口
//    public Result<?> update(@PathVariable String gid){
//        loginMapper.deleteById(gid);
//        return Result.success();
//    }

    @GetMapping   //get接口
    public Result<?> getAdmin(@RequestParam(defaultValue = "2") String username,
                              @RequestParam(defaultValue = "1") Integer currentPage,
                              @RequestParam(defaultValue = "8") Integer pageSize){
        System.out.println(username);
        LambdaQueryWrapper<Login> wrapper = Wrappers.<Login>lambdaQuery();
        if (StrUtil.isNotBlank(username)){                            //判断search是否为空
            wrapper.eq(Login::getUsername,username);
        }
        Page<Login> userPage = loginMapper.selectPage(new Page<>(currentPage,pageSize),wrapper);
        return Result.success(userPage);
    }
}
