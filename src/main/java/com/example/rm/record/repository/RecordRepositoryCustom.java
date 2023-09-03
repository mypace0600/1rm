package com.example.rm.record.repository;

import com.example.rm.entity.Machine;
import com.example.rm.entity.Member;
import com.example.rm.entity.Record;

import java.util.List;

public interface RecordRepositoryCustom {
    List<Record> findAllByMember(Member member);
    void deleteAllByMember(Member member);
    List<Record> findAllByMachine(Machine machine);
    void deleteAllByMachine(Machine machine);
    List<Record> findAllByMemberAndMachine(Member member, Machine machine);
    void deleteAllByMemberAndMachine(Member member, Machine machine);
}
