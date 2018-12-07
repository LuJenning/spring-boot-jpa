package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

@Controller
public class WebController {
    @RequestMapping("/admin")
    public String adminPage(){

        return "admin";
    }
    @RequestMapping("/")
    public String index(){

        return "redirect:/list";
    }
    @RequestMapping("/toSave")
    public String savePage(){
        return "user/saveUser";
    }
}
