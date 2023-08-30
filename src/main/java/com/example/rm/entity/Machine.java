package com.example.rm.entity;

import lombok.*;

import javax.persistence.*;

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

    @Column(nullable = false, length = 100)
    private String machineType;

    @Column(nullable = false, length = 500)
    private String imgUrl;

    @Column(nullable = false, length = 500)
    private String thumbImgUrl;

    @Column(nullable = false, length = 500)
    private String videoUrl;

    @Column(nullable = false, length = 100)
    private String stimulatePoint;
}
