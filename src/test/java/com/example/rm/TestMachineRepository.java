package com.example.rm;

import com.example.rm.entity.Machine;
import com.example.rm.entity.Member;
import com.example.rm.enums.RoleType;
import com.example.rm.machine.repository.MachineRepository;
import com.example.rm.member.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@Slf4j
@Commit
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestMachineRepository {

    @Autowired
    private MachineRepository repository;

    @Test
    public void A001_Machine_데이터_삽입(){
        Machine machine = Machine.builder()
                .machineName("test3")
                .machineType("test")
                .imgUrl("testtest")
                .thumbImgUrl("testtesttest")
                .videoUrl("testtesttesttest")
                .stimulatePoint("test")
                .build();

        repository.save(machine);
    }

    @Test
    public void A002_Machine_전체_조회(){
        List<Machine> machineList = repository.findAll();
        if(machineList.isEmpty()){
            log.error("not found");
        } else {
            log.info(machineList.toString());
        }
    }

    @Test
    public void A003_Machine_데이터_조회(){
        Optional<Machine> machine = repository.findById(1L);
        if(machine != null){
            log.debug(machine.toString());
        } else {
            log.error("not found");
        }
    }


    @Test
    public void A004_Machine_삭제() throws Exception {
        Machine machine = repository.findById(1L).orElseThrow(() -> new IllegalArgumentException("user doesn't exist"));
        if(machine != null){
            log.debug(machine.toString());
            repository.delete(machine);
        } else {
            log.error("not found");
        }
    }


}

