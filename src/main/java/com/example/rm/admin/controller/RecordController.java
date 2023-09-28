package com.example.rm.admin.controller;

import com.example.rm.entity.Member;
import com.example.rm.entity.Record;
import com.example.rm.record.service.RecordService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
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
    public String recordList(Model model){
        List<Record> recordList = service.findAll();
        model.addAttribute("recordList",recordList);
        return "admin/record";
    }


}
