package com.focus.focus.data.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScoringDto {
    private long id;
    private String content;
    private int start;
    private int end;
}
