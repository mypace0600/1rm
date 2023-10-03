package com.example.rm.common;

import com.example.rm.entity.Member;
import com.example.rm.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
@RequiredArgsConstructor
public class IndexController {

    private final MemberService memberService;

    @RequestMapping(
            value = "/",
            method = RequestMethod.GET
    )
    public String index(Authentication auth) {
        return "user/index";
    }

    @RequestMapping(
            value = "/admin",
            method = RequestMethod.GET
    )
    public String adminIndex(Model model, Authentication auth) {

        model.addAttribute("auth",auth);
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

    @Secured("ROLE_ADMIN")
    @RequestMapping(
            value = "/info",
            method = RequestMethod.GET
    )
    public @ResponseBody String info(){
        return "개인정보";
    }


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(
            value = "/data",
            method = RequestMethod.GET
    )
    public @ResponseBody String data(){
        return "데이터";
    }
}
