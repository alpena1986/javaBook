package com.wang.springbootstudy.controller;

import com.wang.springbootstudy.service.GirlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class GirlController {

    private GirlService girlService;

    @Autowired
    public void setGirlService(GirlService service){
        this.girlService = service;
    }

    @RequestMapping(path = "/make")
    public HashMap<String, Object> makeTrouble(){
        HashMap<String, Object> result = new HashMap<>(4);
        girlService.makeTrouble();
        result.put("success", "true");
        return result;
    }
}
