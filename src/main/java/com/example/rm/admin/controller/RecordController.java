package com.example.rm.admin.controller;

import com.example.rm.entity.Paging;
import com.example.rm.entity.Record;
import com.example.rm.record.service.RecordService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
    public String recordList(Paging paging, Model model){

        double totalCount = service.getTotalCount();
        log.info("@@@@@@@@ totalCount :{}",totalCount);
        PageRequest pageRequest = PageRequest.of(paging.getNowPage(), paging.getPageSize());
        List<Record> recordList = service.findAll(pageRequest);
        log.info("@@@@@@@@ recordList :{}",recordList.toString());
        double rowSize = paging.getRowSize();
        double pageSize = paging.getPageSize();
        double nowPage = paging.getNowPage();
        double totalPage = Math.ceil(totalCount/rowSize);
        double pageGroup = Math.ceil(nowPage/totalPage);
        double lastPage = pageGroup * pageSize > totalPage ? totalPage : pageGroup * pageSize;
        double firstPage = lastPage - pageSize - 1 <= 0 ? 1 : lastPage - pageSize - 1;
        double lastRow = nowPage*rowSize>totalCount?totalCount:nowPage*rowSize;
        double firstRow = lastRow - pageGroup*rowSize + 1;


        paging.setTotalCount((int) totalCount);
        paging.setTotalPage((int) totalPage);
        paging.setFirstPage((int) firstPage);
        paging.setLastPage((int) lastPage);
        paging.setLastRow((int) lastRow);
        paging.setFirstRow((int) firstRow);

        model.addAttribute("paging",paging);
        model.addAttribute("recordList",recordList);
        return "admin/record";
    }


}
