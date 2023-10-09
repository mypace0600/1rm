package com.example.rm.notice.service;

import com.example.rm.entity.Notice;
import com.example.rm.notice.repository.NoticeRepository;
import com.example.rm.notice.repository.NoticeRepositoryCustom;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class NoticeService {

    private final NoticeRepository noticeRepository;
    private final NoticeRepositoryCustom noticeRepositoryCustom;

    public List<Notice> findAll(PageRequest pageRequest){
        List<Notice> resultList = new ArrayList<>();
        for(Notice item : noticeRepository.findAll(pageRequest)){
            resultList.add(item);
        }
        return resultList;
    }

    public double getTotalCount(){
        return noticeRepository.count();
    }

    public void save(Notice notice){
        noticeRepository.save(notice);
    }

    public Notice findById(Long id){
        return noticeRepository.findById(id).orElseThrow(()->new IllegalArgumentException("Not Found"));
    }

    public void delete(Long id){
        noticeRepository.deleteById(id);
    }

    public void update(Notice notice){
        noticeRepository.save(notice);
    }

    public List<Notice> findAllPopupNotice(PageRequest pageRequest){
        return noticeRepositoryCustom.findAllPopupNotice(pageRequest);
    }
}
