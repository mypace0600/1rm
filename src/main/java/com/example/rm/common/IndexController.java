package com.example.rm.common;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequiredArgsConstructor
public class IndexController {

    @RequestMapping(
            value = "/",
            method = RequestMethod.GET
    )
    public String index() {
        return "user/index";
    }

    @RequestMapping(
            value = "/admin",
            method = RequestMethod.GET
    )
    public String adminIndex(Authentication auth) {
        return "admin/index";
    }

    @RequestMapping(
            value = "/login",
            method = RequestMethod.GET
    )
    public String login(){
        return "user/login";
    }

   @RequestMapping(
            value = "/join",
            method = RequestMethod.GET
    )
    public String join(){
        return "user/join";
    }

    @RequestMapping(
            value = "/joinProc",
            method = RequestMethod.POST
    )
    public String joinProc(){
        return "user/joinProc";
    }

}
