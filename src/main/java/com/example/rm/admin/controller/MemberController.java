package com.example.rm.admin.controller;

import com.example.rm.entity.Member;
import com.example.rm.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService service;

    @RequestMapping(
            value = "/admin/member",
            method = RequestMethod.GET
    )
    public String memberList(Model model){
        List<Member> memberList = service.findAll();
        log.info("@@@@@@@@@@@ memberList :{}",memberList.toString());
        model.addAttribute("memberList",memberList);
        return "admin/member";
    }

    @RequestMapping(
            value = "/admin/member/{id}",
            method = RequestMethod.GET
    )
    public String memberDetail(@PathVariable("id")Long id, Model model){
        Member member = service.findById(id);
        model.addAttribute("member",member);
        return "admin/member-detail";
    }

    @RequestMapping(
            value = "/admin/member/{id}",
            method = RequestMethod.DELETE
    )
    public String memberDelete(@PathVariable("id")Long id, Model model){
        service.deleteById(id);
        return "redirect:admin/member";
    }

    @RequestMapping(
            value = "/admin/member",
            method = RequestMethod.PATCH
    )
    public String memberRoleUpdate(@RequestBody Member member, Model model) {
        service.roleUpdate(member);
        return "redirect:admin/member";
    }
}
