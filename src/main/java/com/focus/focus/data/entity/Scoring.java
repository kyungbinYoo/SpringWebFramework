package com.focus.focus.data.entity;

import jakarta.persistence.*;
import lombok.*;

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
    private int start;
    @Column(nullable = false)
    private int end;

}
