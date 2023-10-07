package com.example.rm.notice.repository;

import com.example.rm.entity.Machine;
import com.example.rm.entity.Notice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface NoticeRepository extends JpaRepository<Notice,Long> {


}
