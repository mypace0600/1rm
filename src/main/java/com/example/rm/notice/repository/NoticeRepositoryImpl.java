package com.example.rm.notice.repository;

import com.example.rm.entity.*;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;

import java.util.List;


@RequiredArgsConstructor
public class NoticeRepositoryImpl implements NoticeRepositoryCustom {

    final JPAQueryFactory queryFactory;
    QNotice qNotice = QNotice.notice;

    @Override
    public List<Notice> findAllPopupNotice(PageRequest pageRequest) {
        return queryFactory
                .selectFrom(qNotice)
                .where(qNotice.isPopup.eq(true))
                .offset(pageRequest.getOffset())
                .limit(pageRequest.getPageSize())
                .fetch();
    }
}
