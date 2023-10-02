package com.example.rm.admin.controller;

import com.example.rm.config.auth.PrincipalDetailService;
import com.example.rm.entity.Member;
import com.example.rm.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class AdminIndexController {

    private final PrincipalDetailService principalDetailService;
    private final MemberService memberService;

    @RequestMapping(
            value = "/admin",
            method = RequestMethod.GET
    )
    public String index(Authentication auth){

        return "admin/index";
    }

    @RequestMapping(
            value="/admin/loginPage",
            method = RequestMethod.GET
    )
    public String getLoginForm(){
        return "admin/loginPage";
    }



}
