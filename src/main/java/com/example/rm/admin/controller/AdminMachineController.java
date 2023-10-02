package com.example.rm.admin.controller;

import com.example.rm.entity.Machine;
import com.example.rm.entity.Paging;
import com.example.rm.machine.service.MachineService;
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
public class AdminMachineController {

    private final MachineService machineService;

    @RequestMapping(
            value = "/admin/machine",
            method = RequestMethod.GET
    )
    public String machineList(
            @RequestParam(value="nowPage", required = false) String nowPage,
            @RequestParam(value="rowSize",required = false) String rowSize,
            Model model
    ){
        double totalCount = machineService.getTotalCount();
        Paging paging = new Paging();
        if(null != nowPage){
            paging.setNowPage(Integer.valueOf(nowPage));
        }
        if(null != rowSize){
            paging.setRowSize(Integer.valueOf(rowSize));
        }
        PageRequest pageRequest = PageRequest.of(paging.getNowPage()-1,paging.getRowSize());
        List<Machine> machineList = machineService.findAll(pageRequest);
        pagination(model, totalCount, paging);
        model.addAttribute("machineList",machineList);
        model.addAttribute("paging",paging);
        return "admin/machine";
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
            value = "/admin/machine/detail/{id}",
            method = RequestMethod.GET
    )
    public String machineDetail(
            @PathVariable("id") int id,
            Model model
    ){
        Machine machine = machineService.findById(Long.valueOf(id));
        model.addAttribute("machine",machine);
        return "admin/machine-detail";
    }

    @RequestMapping(
            value="/admin/machine/register",
            method = RequestMethod.GET
    )
    public String machineRegister(){
        return "admin/machine-detail";
    }
}
