package com.longrise.myssm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DemoController {
    @GetMapping("/look")
    public ModelAndView demo(){
        ModelAndView mav = new ModelAndView("demo.html");
        mav.addObject("msg", "thymeleaf provider");
        return mav;
    }
}