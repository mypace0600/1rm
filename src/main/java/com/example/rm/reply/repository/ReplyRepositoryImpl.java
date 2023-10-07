package com.example.rm.reply.repository;

import com.example.rm.entity.*;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;

import java.util.List;


@RequiredArgsConstructor
public class ReplyRepositoryImpl implements ReplyRepositoryCustom {

    final JPAQueryFactory queryFactory;
    QReply qReply = QReply.reply;

    @Override
    public List<Reply> findAllByNoticeId(PageRequest pageRequest, Notice notice) {
        return queryFactory
                .selectFrom(qReply)
                .where(qReply.notice.eq(notice))
                .offset(pageRequest.getOffset())
                .limit(pageRequest.getPageSize())
                .fetch();
    }
}
