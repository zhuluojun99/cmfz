package com.baizhi.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/Echarts")
public class Echarts {
    @RequestMapping("getvalue")
    public List<Object> getvalue(){
        List<Object> list = new ArrayList<>();
        list.add(new Random().nextInt(1000));
        list.add(new Random().nextInt(1000));
        list.add(new Random().nextInt(1000));
        list.add(new Random().nextInt(1000));
        list.add(new Random().nextInt(1000));
        list.add(new Random().nextInt(1000));
        return list;
    }
}
