package com.example.rm.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Notice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(length = 200)
    private String title;

    @Lob
    private String textContent;

    @JsonIgnore
    @OneToMany(mappedBy = "notice", cascade = CascadeType.ALL)
    private List<Reply> replyList = new ArrayList<>();

    @Column
    private boolean isPopup;

    @Column
    private boolean isImportant;

    @Column
    private int viewCount;

    @Column
    private int likeCount;

    @CreationTimestamp
    private Timestamp createdDate;

    @CreationTimestamp
    private Timestamp editedDate;

}
