package com.example.rm.admin.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {

    @RequestMapping(
            value = "/admin/users",
            method = RequestMethod.GET
    )
    public String users(){

        return "admin/users";
    }
}
