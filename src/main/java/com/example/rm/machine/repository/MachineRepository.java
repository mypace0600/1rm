package com.example.rm.machine.repository;

import com.example.rm.entity.Machine;
import com.example.rm.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface MachineRepository extends JpaRepository<Machine,Long> {


}
