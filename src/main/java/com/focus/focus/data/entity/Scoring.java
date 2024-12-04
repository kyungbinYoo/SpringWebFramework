package com.focus.focus.data.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalTime;

@Entity
@Table(name = "scoring")
@ToString
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Scoring {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private String content;
    @Column(nullable = false)
    private LocalTime start;
    @Column(nullable = false)
    private LocalTime end;
    @Column(name = "uid", insertable=false, updatable=false)
    private long uid;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "uid")
    private User user;


}
