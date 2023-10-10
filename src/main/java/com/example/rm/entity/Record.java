package com.example.rm.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Record {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "machine")
    private Machine machine;

    @Column
    private long machineId;

    @CreationTimestamp
    private Timestamp recordDate;

    @Column(length = 50)
    private int weight;

    @Column(length = 50)
    private int count;

    @Column(length = 50)
    private int setCount;
}
