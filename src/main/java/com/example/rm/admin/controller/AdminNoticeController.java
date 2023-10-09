package com.example.rm.admin.controller;

import com.example.rm.entity.*;
import com.example.rm.member.service.MemberService;
import com.example.rm.notice.service.NoticeService;
import com.example.rm.reply.repository.ReplyRepository;
import com.example.rm.reply.service.ReplyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;


@Slf4j
@Controller
@RequiredArgsConstructor
public class AdminNoticeController {

    private final NoticeService noticeService;
    private final MemberService memberService;
    private final ReplyService replyService;

    @RequestMapping(
            value = "/admin/notice",
            method = RequestMethod.GET
    )
    public String noticeList(
            @RequestParam(value="nowPage", required = false) String nowPage,
            @RequestParam(value="rowSize",required = false) String rowSize,
            Model model,
            Authentication auth
    ){
        double totalCount = noticeService.getTotalCount();
        Paging paging = new Paging();
        if(null != nowPage){
            paging.setNowPage(Integer.valueOf(nowPage));
        }
        if(null != rowSize){
            paging.setRowSize(Integer.valueOf(rowSize));
        }
        PageRequest pageRequest = PageRequest.of(paging.getNowPage()-1,paging.getRowSize());
        List<Notice> noticeList = noticeService.findAll(pageRequest);
        pagination(model, totalCount, paging);
        model.addAttribute("noticeList",noticeList);
        model.addAttribute("paging",paging);
        model.addAttribute("auth",auth);
        return "admin/notice";
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
            value = "/admin/notice/{id}",
            method = RequestMethod.GET
    )
    public String noticeDetail(
            @PathVariable("id") int id,
            Model model,
            Authentication auth,
            @RequestParam(value="nowPage", required = false) String nowPage,
            @RequestParam(value="rowSize",required = false) String rowSize
    ){
        Paging paging = new Paging();
        if(null != nowPage){
            paging.setNowPage(Integer.valueOf(nowPage));
        }
        if(null != rowSize){
            paging.setRowSize(Integer.valueOf(rowSize));
        }
        PageRequest pageRequest = PageRequest.of(paging.getNowPage()-1,paging.getRowSize());
        Notice notice = noticeService.findById(Long.valueOf(id));
        model.addAttribute("notice",notice);
        Member member = memberService.findByUsername(auth.getName());
        model.addAttribute("principal",member);
        List<Reply> replyList = replyService.findAllByNoticeId(pageRequest,notice);
        model.addAttribute("replyList",replyList);
        int totalCount = replyService.getTotalCountByNoticeId(notice);
        pagination(model, totalCount, paging);
        model.addAttribute("paging",paging);
        return "admin/notice-detail";
    }

    @RequestMapping(
            value="/admin/notice/register",
            method = RequestMethod.GET
    )
    public String noticeRegister(Authentication auth, Model model){
        log.info("@@@@@@@ username :{}",auth.getName());
        Member member = memberService.findByUsername(auth.getName());
        model.addAttribute("principal",member);
        return "admin/notice-detail";
    }

    @RequestMapping(
            value="/admin/notice/{id}/reply",
            method = RequestMethod.GET
    )
    public ResponseEntity<Box> replyList(
            @PathVariable String id,
            @RequestParam(value="nowPage", required = false) String nowPage,
            @RequestParam(value="rowSize",required = false) String rowSize
    ){
        Box result = new Box();
        Paging paging = new Paging();
        if(null != nowPage){
            paging.setNowPage(Integer.valueOf(nowPage));
        }
        if(null != rowSize){
            paging.setRowSize(Integer.valueOf(rowSize));
        }

        Notice notice = noticeService.findById(Long.valueOf(id));
        PageRequest pageRequest = PageRequest.of(Integer.parseInt(nowPage)-1, Integer.parseInt(rowSize));
        List<Reply> replyList = replyService.findAllByNoticeId(pageRequest,notice);
        int totalCount = replyService.getTotalCountByNoticeId(notice);
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
        result.put("replyList",replyList);
        result.put("paging",paging);
        return ResponseEntity.ok(result);
    }

}
