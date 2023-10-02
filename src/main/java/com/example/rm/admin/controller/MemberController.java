package com.example.rm.admin.controller;

import com.example.rm.entity.Member;
import com.example.rm.entity.Paging;
import com.example.rm.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    public String memberList(
            @RequestParam(value="nowPage", required = false) String nowPage,
            @RequestParam(value="rowSize",required = false) String rowSize,
            Model model
    ){
        double totalCount = service.getTotalCount();
        Paging paging = new Paging();
        if(null != nowPage){
            paging.setNowPage(Integer.valueOf(nowPage));
        }
        if(null != rowSize){
            paging.setRowSize(Integer.valueOf(rowSize));
        }
        PageRequest pageRequest = PageRequest.of(paging.getNowPage()-1,paging.getRowSize());
        List<Member> memberList = service.findAll(pageRequest);
        pagination(model, totalCount, paging);
        model.addAttribute("memberList",memberList);
        model.addAttribute("paging",paging);
        return "admin/member";
    }

    static void pagination(Model model, double totalCount, Paging paging) {
        double totalPage = Math.ceil(totalCount/paging.getRowSize());
        double nowPageD = paging.getNowPage();
        double pageSizeD = paging.getPageSize();
        double pageGroup = Math.ceil(nowPageD/pageSizeD);
        double lastPage = pageGroup * paging.getPageSize() > totalPage ? totalPage : pageGroup * paging.getPageSize();
        double firstPage = (pageGroup-1)*paging.getPageSize()+1;
        paging.setTotalCount((int) totalCount);
        paging.setTotalPage((int) totalPage);
        paging.setFirstPage((int) firstPage);
        paging.setLastPage((int) lastPage);
    }

    @RequestMapping(
            value = "/admin/member/{id}",
            method = RequestMethod.GET
    )
    public String memberDetail(@PathVariable("id")Long id, Model model){
        Member member = service.findById(id);
        String roleType = member.getRole().toString();

        model.addAttribute("roleType",roleType);
        model.addAttribute("member",member);
        return "admin/member-detail";
    }

}
