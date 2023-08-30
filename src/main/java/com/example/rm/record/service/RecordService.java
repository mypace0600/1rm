package com.example.rm.record.service;

import com.example.rm.entity.Record;
import com.example.rm.record.repository.RecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecordService {

    private final RecordRepository recordRepository;

    public List<Record> findAll(){
        return recordRepository.findAll();
    }

    public void save(Record record){ recordRepository.save(record); }
}
