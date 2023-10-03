package com.example.rm.common;

import com.example.rm.entity.Member;
import com.example.rm.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Slf4j
@Controller
@RequiredArgsConstructor
public class IndexController {

    private final MemberService memberService;

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
            value = "/loginForm",
            method = RequestMethod.GET
    )
    public String loginForm(){
        return "user/loginForm";
    }

   @RequestMapping(
            value = "/joinForm",
            method = RequestMethod.GET
    )
    public String joinForm(){
        return "user/joinForm";
    }

    @RequestMapping(
            value = "/join",
            method = RequestMethod.POST
    )
    public String join(Member member){
        memberService.join(member);
        return "redirect:/loginForm";
    }

}
