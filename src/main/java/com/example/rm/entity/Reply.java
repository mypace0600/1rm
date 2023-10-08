package com.example.rm.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Reply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member")
    private Member member;

    @Column
    private Long memberId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "notice")
    private Notice notice;

    @Column
    private Long noticeId;

    @Lob
    private String textContent;

    @Column
    private int likeCount;

    @CreationTimestamp
    private Timestamp createdDate;

    @CreationTimestamp
    private Timestamp editedDate;

}
