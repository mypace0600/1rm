package com.example.rm.member.service;

import com.example.rm.entity.Member;
import com.example.rm.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository userRepository;

    public List<Member> findAll(){
        return userRepository.findAll();
    }
}
