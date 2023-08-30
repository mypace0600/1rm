package com.example.rm.record.repository;

import com.example.rm.entity.Record;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface RecordRepository extends JpaRepository<Record,Long> {


}
