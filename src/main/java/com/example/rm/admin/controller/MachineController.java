package com.example.rm.admin.controller;

import com.example.rm.entity.Machine;
import com.example.rm.machine.service.MachineService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MachineController {

    private final MachineService machineService;

    @RequestMapping(
            value = "/admin/machine",
            method = RequestMethod.GET
    )
    public String machineList(Model model){
        List<Machine> machineList = machineService.findAll();
        log.info("@@@@@@@@@@@ machineList :{}",machineList.toString());
        model.addAttribute("machineList",machineList);
        return "admin/machine";
    }
}
