package com.example.rm;

import com.example.rm.entity.Member;
import com.example.rm.member.repository.MemberRepository;
import com.example.rm.enums.RoleType;
import lombok.extern.slf4j.Slf4j;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@Slf4j
@Commit
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestMemberRepository {

    @Autowired
    private MemberRepository repository;

    @Test
    public void A001_Member_데이터_삽입(){
        Member member = Member.builder()
                .loginId("test1")
                .userName("test")
                .password("123")
                .gender("male")
                .role(RoleType.USER)
                .email("test@test.com")
                .phone("010-0000-0000")
                .oauth("kako")
                .build();

        repository.save(member);
    }

    @Test
    public void A002_Member_전체_조회(){
        List<Member> memberList = repository.findAll();
        if(memberList.isEmpty()){
            log.error("not found");
        } else {
            log.info("@@@@@@@ memberList.size() :{}",memberList.size());
        }
    }

    @Test
    public void A003_Member_데이터_조회(){
        Member member = repository.findByLoginId("test").orElseThrow(()-> new IllegalArgumentException("not found"));
        log.info("@@@@@@@@@ member :{}",member);
    }


    @Test
    public void A004_Member_삭제() throws Exception {
        Member member = repository.findByLoginId("test").orElseThrow(() -> new IllegalArgumentException("user doesn't exist"));
        if(member != null){
            log.info(member.toString());
            repository.delete(member);
        } else {
            log.error("not found");
        }
    }


}

