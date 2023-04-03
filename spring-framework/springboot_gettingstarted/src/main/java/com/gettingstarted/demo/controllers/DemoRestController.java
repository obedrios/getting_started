package com.gettingstarted.demo.controllers;

import com.gettingstarted.demo.json.Greeting;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoRestController {

    @GetMapping("/rest")
    public Greeting greetings(
            @RequestParam(required = false, defaultValue = "World") String name){
        return new Greeting("Demo, " + name + "!");
    }

}
