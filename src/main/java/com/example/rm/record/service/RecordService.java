package com.example.rm.record.service;

import com.example.rm.entity.Record;
import com.example.rm.record.repository.RecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RecordService {

    private final RecordRepository recordRepository;

    public List<Record> findAll(PageRequest pageRequest){
        List<Record> resultList = new ArrayList<>();
        for(Record item : recordRepository.findAll(pageRequest)){
            resultList.add(item);
        }
        return resultList;
    }
    public double getTotalCount(){ return recordRepository.count();}

    public void save(Record record){
        recordRepository.save(record);
    }
}
