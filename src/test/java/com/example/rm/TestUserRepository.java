package com.example.rm;

import com.example.rm.admin.user.entity.User;
import com.example.rm.admin.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

@RunWith(SpringRunner.class)
@Slf4j
@Commit
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestUserRepository {

    @Autowired
    private UserRepository repository;

    @Test
    public void A001_User_데이터_삽입(){
        User user = new User();
        user.setUserId("ID2308201");
        user.setLoginId("test");
        user.setLoginPw("123");
        user.setUserName("test");
        user.setEmail("test@test.com");
        user.setPhone("123");
        user.setAge(29);
        user.setGender("male");
        user.setRegisteredData(LocalDateTime.now());
        user.setRegisteredId("test");
        user.setEditedData(LocalDateTime.now());
        user.setEditedId("test");

        repository.save(user);
    }

    @Test
    public void A002_User_데이터_조회(){
        User user = repository.findByUserId("ID2308201");
        if(user != null){
            log.debug(user.toString());
        } else {
            log.error("not found");
        }
    }

    @Test
    public void A003_USER_데이터_삭제(){
        User user = new User();
        user.setUserId("ID2308201");

        repository.delete(user);
    }
}


