package com.QuantomSoft.Controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/home")
public class HomeController {

    @GetMapping("/users")
    public String getAdmin()
    {
        System.out.println("getting users");
        return "Users";    }
}
