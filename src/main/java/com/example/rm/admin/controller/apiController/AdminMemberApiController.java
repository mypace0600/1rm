package com.example.rm.admin.controller.apiController;

import com.example.rm.entity.Member;
import com.example.rm.enums.RoleType;
import com.example.rm.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AdminMemberApiController {

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
            value = "/admin/member/{id}",
            method = RequestMethod.PUT
    )
    public String memberRoleUpdate(@PathVariable("id")Long id, @RequestParam String role, Model model) {
        Member selectedMember = service.findById(id);
        RoleType roleType = RoleType.valueOf(role);
        selectedMember.setRole(roleType);
        service.roleUpdate(selectedMember);
        return "redirect:admin/member";
    }
}
