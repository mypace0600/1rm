package com.example.rm.record.repository;

import com.example.rm.entity.Member;
import com.example.rm.entity.Record;

import java.util.List;

public interface RecordRepositoryCustom {
    List<Record> findAllByMember(Member member);
}
