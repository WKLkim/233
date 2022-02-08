package com.example.demo.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.Result;
import com.example.demo.entity.Employee;
import com.example.demo.mapper.EmployeeMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/employee")
public class Employeecontroller {
    @Resource
    EmployeeMapper employeeMapper;

    @PostMapping   //post接口
    public Result<?> save(@RequestBody Employee employee){
        employeeMapper.insert(employee);
        return Result.success();
    }

    @PutMapping   //put接口
    public Result<?> update(@RequestBody Employee employee){
        System.out.print(employee);
        employeeMapper.updateById(employee);
        return Result.success();
    }

    @DeleteMapping("/{gid}")   //delete接口
    public Result<?> update(@PathVariable String gid){
        employeeMapper.deleteById(gid);
        return Result.success();
    }

    @GetMapping   //get接口
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer currentPage,
                              @RequestParam(defaultValue = "8") Integer pageSize,
                              @RequestParam(defaultValue = "") String search){
        LambdaQueryWrapper<Employee> wrapper = Wrappers.<Employee>lambdaQuery();
        if (StrUtil.isNotBlank(search)){                            //判断search是否为空
            wrapper.like(Employee::getName,search);
        }
        Page<Employee> userPage = employeeMapper.selectPage(new Page<>(currentPage,pageSize),wrapper);
        return Result.success(userPage);
    }
}
