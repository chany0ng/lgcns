package com.lgcns.jpadsl;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping("/hello")
    public String hello() {
        return "Hello world!";
    }

    @GetMapping("/say-hello")
    public String sayHello(@RequestParam(required = false) String name) {
        return "Hello, " + name;
    }
}
