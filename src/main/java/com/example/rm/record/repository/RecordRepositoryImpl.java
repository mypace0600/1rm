package com.example.rm.record.repository;

import com.example.rm.entity.Member;
import com.example.rm.entity.QRecord;
import com.example.rm.entity.Record;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;


@RequiredArgsConstructor
public class RecordRepositoryImpl implements RecordRepositoryCustom{

    final JPAQueryFactory queryFactory;
    QRecord qRecord = QRecord.record;

    @Override
    public List<Record> findAllByMember(Member member) {
        return queryFactory
                .selectFrom(qRecord)
                .where(qRecord.member.eq(member))
                .fetch();
    }
}
