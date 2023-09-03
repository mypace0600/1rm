package com.example.rm.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
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

    @JsonIgnore
    @OneToMany(mappedBy = "machine", cascade = CascadeType.ALL)
    private List<Record> records = new ArrayList<>();
}
