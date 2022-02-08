package com.example.demo.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.Result;
import com.example.demo.entity.Partner;
import com.example.demo.mapper.PartnerMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/partner")
public class Partnercontroller {
    @Resource
    PartnerMapper partnerMapper;

    @PostMapping   //post接口
    public Result<?> save(@RequestBody Partner partner){
        partnerMapper.insert(partner);
        return Result.success();
    }

    @PutMapping   //put接口
    public Result<?> update(@RequestBody Partner partner){
        System.out.print(partner);
        partnerMapper.updateById(partner);
        return Result.success();
    }

    @DeleteMapping("/{gid}")   //delete接口
    public Result<?> update(@PathVariable String gid){
        partnerMapper.deleteById(gid);
        return Result.success();
    }

    @GetMapping   //get接口
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer currentPage,
                              @RequestParam(defaultValue = "8") Integer pageSize,
                              @RequestParam(defaultValue = "") String search){
        LambdaQueryWrapper<Partner> wrapper = Wrappers.<Partner>lambdaQuery();
        if (StrUtil.isNotBlank(search)){                            //判断search是否为空
            wrapper.like(Partner::getName,search);
        }
        Page<Partner> userPage = partnerMapper.selectPage(new Page<>(currentPage,pageSize),wrapper);
        return Result.success(userPage);
    }
}
