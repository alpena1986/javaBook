package com.wang.springbootstudy.service;

import org.springframework.stereotype.Service;

@Service(value = "girlService")
public class GirlServiceImpl implements GirlService {

    @Override
    public void makeTrouble() {
        System.out.println("GirlService#makeTrouble()");
    }
}
