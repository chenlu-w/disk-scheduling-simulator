package com.chenlu.disk.scheduling;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;

@Controller
public class DiskSchedulingController {

    @RequestMapping("/")
//    public String getGreeting(){
//        return "Hello World!";
//    }
    public String index() {
        return "index";
    }
}
