package com.example.rm.admin.controller.apiController;

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
public class MemberApiController {

    private final MemberService service;

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
