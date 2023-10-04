package com.example.rm.machine.service;

import com.example.rm.entity.Machine;
import com.example.rm.machine.repository.MachineRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Criteria;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MachineService {

    private final MachineRepository machineRepository;

    public List<Machine> findAll(PageRequest pageRequest){
        List<Machine> resultList = new ArrayList<>();
        for(Machine item : machineRepository.findAll(pageRequest)){
            resultList.add(item);
        }
        return resultList;
    }

    public double getTotalCount(){
        return machineRepository.count();
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
