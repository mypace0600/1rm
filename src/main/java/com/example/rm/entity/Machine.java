package com.example.rm.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Machine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String machineName;

    @Column(length = 100)
    private String machineType;

    @Column(length = 500)
    private String imgUrl;

    @Column(length = 500)
    private String thumbImgUrl;

    @Column(length = 500)
    private String videoUrl;

    @Column(length = 100)
    private String stimulatePoint;

    @OneToMany(mappedBy = "machine")
    private List<Record> records = new ArrayList<>();
}
