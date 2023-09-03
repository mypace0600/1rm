package com.example.rm.machine.service;

import com.example.rm.entity.Machine;
import com.example.rm.machine.repository.MachineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MachineService {

    private final MachineRepository machineRepository;

    public List<Machine> findAll(){
        return machineRepository.findAll();
    }
}
