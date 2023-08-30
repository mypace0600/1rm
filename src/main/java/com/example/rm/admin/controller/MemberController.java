package com.example.rm.admin.controller;

import com.example.rm.entity.Member;
import com.example.rm.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService service;

    @RequestMapping(
            value = "/admin/members",
            method = RequestMethod.GET
    )
    public String members(Model model){
        List<Member> memberList = service.findAll();
        log.info("@@@@@@@@@@@ memberList :{}",memberList.toString());
        model.addAttribute("memberList",memberList);
        return "admin/members";
    }
}
