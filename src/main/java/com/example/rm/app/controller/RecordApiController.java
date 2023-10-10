package com.example.rm.app.controller;

import com.example.rm.entity.Machine;
import com.example.rm.entity.Member;
import com.example.rm.entity.Record;
import com.example.rm.machine.service.MachineService;
import com.example.rm.member.service.MemberService;
import com.example.rm.record.service.RecordService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class RecordApiController {

    private final RecordService recordService;
    private final MemberService memberService;
    private final MachineService machineService;

    @RequestMapping(
            value = "/record",
            method = RequestMethod.POST
    )
    public ResponseEntity<String> recordInsert(@ModelAttribute Record record, Authentication auth){
        if(auth.isAuthenticated()) {
            Member member = memberService.findByUsername(auth.getName());
            Machine machine = machineService.findById(record.getMachineId());
            record.setMachine(machine);
            record.setMember(member);
            recordService.save(record);
            return new ResponseEntity("Resource saved successfully",HttpStatus.OK);
        } else {
            return new ResponseEntity("Auth is empty",HttpStatus.BAD_REQUEST);
        }
    }
}
