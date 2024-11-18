package com.focus.focus.service;

import com.focus.focus.data.ScoringRepository;
import com.focus.focus.data.Utils;
import com.focus.focus.data.dto.ScoringDto;
import com.focus.focus.data.entity.Scoring;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ScoringService {
    @Autowired
    ScoringRepository scoringRepository;


    public List<ScoringDto> findAll() {
        return scoringRepository.findAll().stream().map(v -> Utils.toDTO(v))
                .collect(Collectors.toList());
    }

    public ScoringDto findById(long id) {
        return scoringRepository.findById(id).map(v -> Utils.toDTO(v)).orElse(null);
    }

    public void save(ScoringDto scoringDto) {
        Scoring scoring = Utils.toEntity(scoringDto);
        scoringRepository.save(scoring);
    }

    public void deleteById(long id) {
        scoringRepository.deleteById(id);
    }

    public List<ScoringDto> searchByContent(String content) {
        return scoringRepository.findByContentContaining(content).stream()
                .map(v -> Utils.toDTO(v))
                .collect(Collectors.toList());
    }
}
