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
@ToString
public class Record {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="member")
    private Member member;

    @ManyToOne
    @JoinColumn(name="machine")
    private Machine machine;

    @CreationTimestamp
    private Timestamp recordDate;

    @Column(length = 50)
    private int weight;

    @Column(length = 50)
    private int count;

    @Column(length = 50)
    private int setCount;
}
