package com.example.rm.member.service;

import com.example.rm.entity.Member;
import com.example.rm.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public List<Member> findAll(){
        return memberRepository.findAll();
    }

    public Member findById(Long id){
        return memberRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("not found"));
    }

    public void deleteById(Long id){
        memberRepository.deleteById(id);
    }
}
