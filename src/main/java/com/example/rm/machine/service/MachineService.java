package com.example.rm.machine.service;

import com.example.rm.entity.Machine;
import com.example.rm.machine.repository.MachineRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.Criteria;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MachineService {

    private final MachineRepository machineRepository;

    public List<Machine> findAll(){
        return machineRepository.findAll();
    }

    public void save(Machine machine){
        machineRepository.save(machine);
    }

    public Machine findById(Long id){
        return machineRepository.findById(id).orElseThrow(()->new IllegalArgumentException("Not Found"));
    }

    public void delete(Long id){
        machineRepository.deleteById(id);
    }

    public void update(Machine machine){
        machineRepository.save(machine);
    }
}
