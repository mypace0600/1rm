package com.example.rm.app.controller;

import com.example.rm.entity.Machine;
import com.example.rm.machine.service.MachineService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class IndexController {

    private final MachineService machineService;

    @ResponseBody
    @RequestMapping(
            value = "/",
            method = RequestMethod.GET
    )
    public ResponseEntity<List<Machine>> main(){
        List<Machine> machineList = machineService.getAllMachine();
        return ResponseEntity.status(HttpStatus.OK).body(machineList);
    }
}
