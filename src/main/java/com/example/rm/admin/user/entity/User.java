package com.example.rm.admin.user.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@ToString
@Table(name="t_user")
@NoArgsConstructor
public class User {

    @Id
    private String userId;

    private String loginId;

    private String loginPw;

    private String userName;

    private String email;

    private String phone;

    private String gender;

    private int age;

    private LocalDateTime registeredData;

    private String registeredId;

    private LocalDateTime editedData;

    private String editedId;

    public User(String userId,String loginId,String loginPw,String userName,String email, String phone, String gender, int age, LocalDateTime registeredData, String registeredId, LocalDateTime editedData, String editedId){
        this.userId = userId;
        this.loginId = loginId;
        this.loginPw = loginPw;
        this.userName = userName;
        this.email = email;
        this.phone = phone;
        this.gender = gender;
        this.age = age;
        this.registeredData = registeredData;
        this.registeredId = registeredId;
        this.editedData = editedData;
        this.editedId = editedId;
    }

   /* public static User sample(){
        return new User(
                "ID0001",
                "test",
                "123",
                "test",
                "test@test.com",
                "123",
                "male",
                29,
                LocalDateTime.now(),
                "test",
                LocalDateTime.now(),
                "test"
        );
    }*/
}
