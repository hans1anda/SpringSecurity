package com.landa.SpringSecurityEx;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/hello")
    public String hello(HttpServletRequest httpServletRequest) {
        return "Welcome to Landa " + httpServletRequest.getSession().getId();
    }
}
