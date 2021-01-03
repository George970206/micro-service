package com.lhc.config;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ConfigClientApplicationTest {

    @Value("${auther}")
    private String auther;

    @Test
    public void test(){
        assert auther.equals("lhc");
    }
}