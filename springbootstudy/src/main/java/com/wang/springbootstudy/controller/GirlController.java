package com.wang.springbootstudy.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class GirlController {

    @RequestMapping(path = "/makeTrouble")
    public HashMap<String, Object> makeTrouble(){
        HashMap<String, Object> result = new HashMap<>();
        result.put("success", "true");
        return result;
    }
}
