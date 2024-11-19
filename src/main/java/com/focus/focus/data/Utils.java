package com.focus.focus.data;

import com.focus.focus.data.dto.ScoringDto;
import com.focus.focus.data.dto.UserDto;
import com.focus.focus.data.entity.Scoring;
import com.focus.focus.data.entity.User;

import java.time.LocalTime;

public class Utils {
    public static ScoringDto toDTO(Scoring entity) {
        return ScoringDto.builder()
                .id(entity.getId())
                .content(entity.getContent())
                .start(entity.getStart())
                .end(entity.getEnd())
                .uid(entity.getUser().getId())
                .user(entity.getUser())
                .build();
    }
    public static Scoring toEntity(ScoringDto dto) {
        return Scoring.builder()
                .id(dto.getId())
                .content(dto.getContent())
                .start(dto.getStart())
                .end(dto.getEnd())
                .uid(dto.getUser().getId())
                .user(dto.getUser())
                .build();
    }

    public static UserDto toDTO2(User entity) {
        return UserDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .age(entity.getAge())
                .build();
    }
    public static User toEntity2(UserDto dto) {
        return User.builder()
                .id(dto.getId())
                .name(dto.getName())
                .age(dto.getAge())
                .build();
    }
}