package com.example.rm.admin.user.entity;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="user")
@Data
@ToString
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String userId;

    private String loginId;

    private String password;

    private String userName;

    private String email;

    private String phone;

    private String gender;

    private int age;

    @Builder
    public User(String userId, String loginId, String password,String userName, String email, String phone, String gender, int age){
        this.userId = userId;
        this.loginId = loginId;
        this.password = password;
        this.userName = userName;
        this.email = email;
        this.phone = phone;
        this.gender = gender;
        this.age = age;
    }
}
