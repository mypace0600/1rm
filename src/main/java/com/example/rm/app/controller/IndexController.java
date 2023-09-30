package com.example.rm.app.controller;

import com.example.rm.entity.Machine;
import com.example.rm.entity.Record;
import com.example.rm.machine.service.MachineService;
import com.example.rm.record.service.RecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class IndexController {

    private final MachineService machineService;
    private final RecordService recordService;

    @ResponseBody
    @RequestMapping(
            value = "/",
            method = RequestMethod.GET
    )
    public ResponseEntity<List<Machine>> mainMachineList(){
        List<Machine> machineList = machineService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(machineList);
    }

}
