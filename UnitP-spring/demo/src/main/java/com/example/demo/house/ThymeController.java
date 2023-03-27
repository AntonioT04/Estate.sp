package com.example.demo.house;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ThymeController {
    @RequestMapping(value = "/index")
    public String index() {
        return "index";
    }
}
