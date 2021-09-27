package com.livenow.swarmloginservice.common.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController implements ErrorController {

    @RequestMapping(value = "/error")
    public String error() {
        return "/404.html";
    }
}
