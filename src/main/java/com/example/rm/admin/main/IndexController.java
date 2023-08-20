package com.example.rm.admin.main;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IndexController {

    @RequestMapping(
            value = "/admin",
            method = RequestMethod.GET
    )
    public String index(){
        return "admin/index";
    }
}
