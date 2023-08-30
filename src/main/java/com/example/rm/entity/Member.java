package com.example.rm.entity;

import com.example.rm.enums.RoleType;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Member {

    @Id //Primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 프로젝트에서 연결된 DB 넘버링 전략을 따라간다.
    private Long id; // 시퀀스, auto-increment

    @Column(nullable = false, length = 100, unique = true)
    private String loginId; // 아이디

    @Column(nullable = false, length = 100) // hash 암호화 예정
    private String password; // 비밀번호

    @Column(nullable = false, length = 100)
    private String userName; // 사용자 이름

    @Column(nullable = false, length = 50)
    private String email; // 이메일

    @Column(nullable = false, length = 50)
    private String gender; // 이메일

    @Column(nullable = false, length = 50)
    private String phone; // 연락처

    @Enumerated(EnumType.STRING)
    private RoleType role;

    @CreationTimestamp
    private Timestamp createDate;

    @CreationTimestamp
    private Timestamp lastLoginDate;

    private String oauth; // kakao


}
