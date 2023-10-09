package com.example.rm.reply.repository;

import com.example.rm.entity.*;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ReplyRepositoryCustom {
    List<Reply> findAllByNoticeId(PageRequest pageRequest, Notice notice);
    double getTotalCountByNoticeId(Notice notice);
}
