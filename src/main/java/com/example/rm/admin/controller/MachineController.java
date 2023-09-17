package com.example.rm.admin.controller;

import com.example.rm.entity.Machine;
import com.example.rm.machine.service.MachineService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Criteria;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
        model.addAttribute("machineList",machineList);
        return "admin/machine";
    }

    @RequestMapping(
            value = "/admin/machine",
            method = RequestMethod.POST
    )
    public String machineInsert(@ModelAttribute Machine machine){
        machineService.save(machine);
        return "admin/machine";
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
            value = "/admin/machine/{id}",
            method = RequestMethod.DELETE
    )
    public String machineDelete(
            @PathVariable("id") int id
    ){
        machineService.delete(Long.valueOf(id));
        return "redirect:/admin/machine";
    }

    @RequestMapping(
            value = "/admin/machine/{id}",
            method = RequestMethod.PATCH
    )
    public String machineUpdate(
            @RequestBody Machine machine
    ) {
        machineService.update(machine);
        return "redirect:/admin/machine";
    }
}
