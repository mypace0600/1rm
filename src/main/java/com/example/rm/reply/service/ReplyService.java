package com.example.rm.reply.service;

import com.example.rm.entity.Notice;
import com.example.rm.entity.Reply;
import com.example.rm.reply.repository.ReplyRepository;
import com.example.rm.reply.repository.ReplyRepositoryCustom;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReplyService {

    private final ReplyRepository replyRepository;
    private final ReplyRepositoryCustom replyRepositoryCustom;

    public List<Reply> findAllByNoticeId(PageRequest pageRequest, Notice notice){
        List<Reply> resultList = new ArrayList<>();
        for(Reply item : replyRepositoryCustom.findAllByNoticeId(pageRequest,notice)){
            resultList.add(item);
        }
        return resultList;
    }

    public double getTotalCountByNoticeId(Notice notice){
        return replyRepositoryCustom.getTotalCountByNoticeId(notice);
    }

    public void save(Reply reply){
        replyRepository.save(reply);
    }

    public Reply findById(Long id){
        return replyRepository.findById(id).orElseThrow(()->new IllegalArgumentException("Not Found"));
    }

    public void delete(Long id){
        replyRepository.deleteById(id);
    }

    public void update(Reply reply){
        replyRepository.save(reply);
    }
}
