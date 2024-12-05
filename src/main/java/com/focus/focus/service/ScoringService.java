package com.focus.focus.service;

import com.focus.focus.data.ScoringRepository;
import com.focus.focus.data.Utils;
import com.focus.focus.data.dto.ScoringDto;
import com.focus.focus.data.entity.Scoring;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Service
public class ScoringService {
    @Autowired
    ScoringRepository scoringRepository;

    private static final Logger logger = LoggerFactory.getLogger(ScoringService.class);



    public List<ScoringDto> findAll() {
        return scoringRepository.findAll().stream().map(v -> Utils.toDTO(v))
                .collect(Collectors.toList());
    }

    public ScoringDto findById(long id) {
        return scoringRepository.findById(id).map(v -> Utils.toDTO(v)).orElse(null);
    }

    public void save(ScoringDto scoringDto) {

        Scoring scoring = Utils.toEntity(scoringDto);
        if (scoring.getUser() == null) {
            throw new IllegalArgumentException("Scoring에 유저가 설정되지 않았습니다.");
        }

        scoringRepository.save(scoring);
    }

    public void deleteById(long id) {
        scoringRepository.deleteById(id);
    }

    public Map<String, Double> calculateAverageTotaltimeByAgeGroup(List<Scoring> scorings) {
        // 평균 시간 계산
        Map<String, Double> averageTotaltimeByAgeGroup = scorings.stream()
                .collect(Collectors.groupingBy(
                        scoring -> getAgeGroup(Integer.parseInt(scoring.getUser().getAge())), // 연령대별로 그룹화
                        Collectors.averagingLong(Scoring::getTotaltime) // totaltime 평균 계산
                ));

        // 그룹화된 평균값 출력
        logger.info("나이대별 평균 시간: {}", averageTotaltimeByAgeGroup);

        return averageTotaltimeByAgeGroup;
    }



    // 나이에 따른 연령대 그룹 반환
    private String getAgeGroup(int age) {
        String ageGroup;
        if (age >= 10 && age < 20) {
            ageGroup = "10대";
        } else if (age >= 20 && age < 30) {
            ageGroup = "20대";
        } else if (age >= 30 && age < 40) {
            ageGroup = "30대";
        } else if (age >= 40 && age < 50) {
            ageGroup = "40대";
        } else {
            ageGroup = "기타";
        }

        // 나이대 그룹화 확인
        logger.info("나이 {}는 '{}' 그룹에 속합니다.", age, ageGroup);

        return ageGroup;
    }


}
