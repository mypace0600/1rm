package com.example.rm.notice.repository;

import com.example.rm.entity.Notice;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface NoticeRepositoryCustom {
    List<Notice> findAllPopupNotice(PageRequest pageRequest);
}
