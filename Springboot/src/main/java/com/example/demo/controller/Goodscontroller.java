package com.example.demo.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.Result;
import com.example.demo.entity.Goods;
import com.example.demo.mapper.GoodsMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/goods")
public class Goodscontroller {
    @Resource
    GoodsMapper goodsMapper;

    @PostMapping   //post接口
    public Result<?> save(@RequestBody Goods goods){
        goodsMapper.insert(goods);
        return Result.success();
    }

    @PutMapping   //put接口
    public Result<?> update(@RequestBody Goods goods){
        System.out.print(goods);
        goodsMapper.updateById(goods);
        return Result.success();
    }

    @DeleteMapping("/{gid}")   //delete接口
    public Result<?> update(@PathVariable String gid){
        goodsMapper.deleteById(gid);
        return Result.success();
    }

    @GetMapping   //get接口
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer currentPage,
                              @RequestParam(defaultValue = "8") Integer pageSize,
                              @RequestParam(defaultValue = "") String search){
        LambdaQueryWrapper<Goods> wrapper = Wrappers.<Goods>lambdaQuery();
        if (StrUtil.isNotBlank(search)){                            //判断search是否为空
            wrapper.like(Goods::getGname,search);
        }
        Page<Goods> userPage = goodsMapper.selectPage(new Page<>(currentPage,pageSize),wrapper);
        System.out.println(userPage);
        return Result.success(userPage);
    }
}
