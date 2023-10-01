package com.example.rm.admin.controller;

import com.example.rm.entity.Paging;
import com.example.rm.entity.Record;
import com.example.rm.record.service.RecordService;
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
public class RecordController {

    private final RecordService service;

    @RequestMapping(
            value = "/admin/record",
            method = RequestMethod.GET
    )
    public String recordList(
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
        List<Record> recordList = service.findAll(pageRequest);

        double totalPage = Math.ceil(totalCount/paging.getRowSize());
        double nowPageD = paging.getNowPage();
        double pageSizeD = paging.getPageSize();
        double pageGroup = Math.ceil(nowPageD/pageSizeD);
        log.info("@@@@@@@ pageGroup :{}",pageGroup);
        double lastPage = pageGroup * paging.getPageSize() > totalPage ? totalPage : pageGroup * paging.getPageSize();
        double firstPage = (pageGroup-1)*paging.getPageSize()+1;
        log.info("@@@@@@@ firstPage :{}",firstPage);
        log.info("@@@@@@@ lastPage :{}",lastPage);
        paging.setTotalCount((int) totalCount);
        paging.setTotalPage((int) totalPage);
        paging.setFirstPage((int) firstPage);
        paging.setLastPage((int) lastPage);
        model.addAttribute("paging",paging);
        model.addAttribute("recordList",recordList);
        return "admin/record";
    }


}
