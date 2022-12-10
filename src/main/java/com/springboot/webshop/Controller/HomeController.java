package com.springboot.webshop.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
@RequestMapping(path = "/")
public class HomeController {
    @GetMapping(value="")
    public String getHome(){
        return "home";
    }
}
