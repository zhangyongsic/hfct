package com.zysic.hfct.controller;

import com.zysic.hfct.extension.response.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "测试")
@RestController
@RequestMapping("test")
public class TestController {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @GetMapping("index")
    @ApiOperation("index")
    public ResponseResult index(){
        return ResponseResult.ok("index");
    }

    @PutMapping("redis")
    @ApiOperation("redis")
    public ResponseResult redis(){
        stringRedisTemplate.opsForValue().set("ke","222");
        return ResponseResult.ok("index");
    }

    @GetMapping("redis")
    @ApiOperation("redis")
    public ResponseResult getredis(){
        String val = stringRedisTemplate.opsForValue().get("ke");
        return ResponseResult.ok(val);
    }
}
