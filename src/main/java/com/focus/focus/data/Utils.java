package com.focus.focus.data;

import com.focus.focus.data.dto.ScoringDto;
import com.focus.focus.data.entity.Scoring;

import java.time.LocalTime;

public class Utils {
    public static ScoringDto toDTO(Scoring entity) {
        return ScoringDto.builder()
                .id(entity.getId())
                .content(entity.getContent())
                .start(entity.getStart())
                .end(entity.getEnd())

                .build();
    }
    public static Scoring toEntity(ScoringDto dto) {
        return Scoring.builder()
                .id(dto.getId())
                .content(dto.getContent())
                .start(dto.getStart())
                .end(dto.getEnd())
                .build();
    }
}