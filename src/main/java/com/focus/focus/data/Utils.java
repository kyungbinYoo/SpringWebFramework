package com.focus.focus.data;

import com.focus.focus.data.dto.ScoringDto;
import com.focus.focus.data.dto.UserDto;
import com.focus.focus.data.entity.Scoring;
import com.focus.focus.data.entity.User;

import java.time.Duration;
import java.time.LocalTime;

public class Utils {
    public static ScoringDto toDTO(Scoring entity) {
        return ScoringDto.builder()
                .id(entity.getId())
                .content(entity.getContent())
                .start(entity.getStart())
                .end(entity.getEnd())
                .totaltime(entity.getTotaltime())
                .uid(entity.getUser().getId())
                .user(entity.getUser())
                .build();
    }
    public static Scoring toEntity(ScoringDto dto) {
        LocalTime start = dto.getStart();
        LocalTime end = dto.getEnd();

        if (start != null && end != null) {
            // 시간 차이를 구해서 totaltime에 반영
            Duration duration = Duration.between(start, end);
            Long totaltime = duration.toMinutes();


            return Scoring.builder()
                .id(dto.getId())
                .content(dto.getContent())
                .start(start)
                .end(end)
                .totaltime(totaltime)
                .uid(dto.getUser().getId())
                .user(dto.getUser())
                .build();
        }
        else {
            // start와 end가 null일 경우 적절한 예외 처리
            throw new IllegalArgumentException("Start and end times must not be null");
        }
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