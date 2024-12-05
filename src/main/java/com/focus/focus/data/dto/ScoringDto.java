package com.focus.focus.data.dto;

import com.focus.focus.data.entity.User;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScoringDto {
    private long id;
    private String content;
    private LocalTime start;
    private LocalTime end;
    private Long totaltime;
    private Long uid;
    private User user;

}
