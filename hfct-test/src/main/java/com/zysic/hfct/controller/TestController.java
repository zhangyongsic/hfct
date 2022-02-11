package com.zysic.hfct.controller;

import com.zysic.hfct.entity.Psn;
import com.zysic.hfct.entity.PsnBiz;
import com.zysic.hfct.entity.PsnH;
import com.zysic.hfct.extension.response.ResponseResult;
import com.zysic.hfct.service.PsnService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

@Api(tags = "测试")
@RestController
@RequestMapping("test")
public class TestController {

    @Autowired
    private PsnService psnService;

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

    @GetMapping("psn/{name}")
    @ApiOperation("psn")
    public ResponseResult psnUpdate(@PathVariable String name){
        PsnBiz psnBiz = new PsnBiz();
        psnBiz.setRoot(new Psn());
        psnBiz.getRoot().setId(1);
        psnBiz.getRoot().setName(name);
        psnBiz.setHist(new PsnH());
        psnService.update(psnBiz);
        return ResponseResult.ok();
    }
}
