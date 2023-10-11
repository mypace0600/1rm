package com.example.rm.record.repository;

import com.example.rm.entity.Machine;
import com.example.rm.entity.Member;
import com.example.rm.entity.QRecord;
import com.example.rm.entity.Record;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;


@RequiredArgsConstructor
public class RecordRepositoryImpl implements RecordRepositoryCustom{

    final JPAQueryFactory queryFactory;
    QRecord qRecord = QRecord.record;

    @Override
    public Page<Record> findAllByMember(Member member, PageRequest pageRequest) {
        return (Page<Record>) queryFactory
                .selectFrom(qRecord)
                .where(qRecord.member.eq(member))
                .offset(pageRequest.getOffset())
                .limit(pageRequest.getPageSize())
                .fetch();
    }

    @Override
    public void deleteAllByMember(Member member) {
        queryFactory
                .delete(qRecord)
                .where(qRecord.member.eq(member))
                .execute();
    }

    @Override
    public List<Record> findAllByMachine(Machine machine) {
        return queryFactory
                .selectFrom(qRecord)
                .where(qRecord.machine.eq(machine))
                .fetch();
    }

    @Override
    public void deleteAllByMachine(Machine machine) {
        queryFactory
                .delete(qRecord)
                .where(qRecord.machine.eq(machine))
                .execute();
    }

    @Override
    public List<Record> findAllByMemberAndMachine(Member member, Machine machine) {
        return queryFactory
                .selectFrom(qRecord)
                .where(qRecord.member.eq(member))
                .where(qRecord.machine.eq(machine))
                .fetch();
    }

    @Override
    public void deleteAllByMemberAndMachine(Member member, Machine machine) {
        queryFactory
                .delete(qRecord)
                .where(qRecord.member.eq(member))
                .where(qRecord.machine.eq(machine))
                .execute();
    }

}
