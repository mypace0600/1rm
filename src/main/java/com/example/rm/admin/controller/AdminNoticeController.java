package com.example.rm.admin.controller;

import com.example.rm.entity.Notice;
import com.example.rm.entity.Paging;
import com.example.rm.notice.service.NoticeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Slf4j
@Controller
@RequiredArgsConstructor
public class AdminNoticeController {

    private final NoticeService noticeService;

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
            Authentication auth
    ){
        Notice notice = noticeService.findById(Long.valueOf(id));
        model.addAttribute("notice",notice);
        model.addAttribute("auth",auth);
        return "admin/notice-detail";
    }

    @RequestMapping(
            value="/admin/notice/register",
            method = RequestMethod.GET
    )
    public String noticeRegister(){
        return "admin/notice-detail";
    }
}
