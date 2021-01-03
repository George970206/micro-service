package com.lhc.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lhc
 */
@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello(){
        return "Hello world, docker";
    }

}
