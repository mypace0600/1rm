package com.example.rm.member.service;

import com.example.rm.entity.Member;
import com.example.rm.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public double getTotalCount(){
        return memberRepository.count();
    }
    public List<Member> findAll(PageRequest pageRequest){
        List<Member> resultList = new ArrayList<>();
        for(Member item : memberRepository.findAll(pageRequest)){
            resultList.add(item);
        }
        return resultList;
    }

    public Member findById(Long id){
        return memberRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("not found"));
    }

    public void deleteById(Long id){
        memberRepository.deleteById(id);
    }

    public void roleUpdate(Member member){
        memberRepository.saveAndFlush(member);
    }
}
