package com.wang.springbootstudy.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service(value = "girlService")
public class GirlServiceImpl implements GirlService {

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void makeTrouble() {
        System.out.println("GirlService#makeTrouble()");
    }
}
