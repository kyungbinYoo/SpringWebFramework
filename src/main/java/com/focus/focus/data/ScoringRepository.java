package com.focus.focus.data;

import com.focus.focus.data.entity.Scoring;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScoringRepository extends JpaRepository<Scoring, Long> {
    List<Scoring> findByContentContaining(String content);

}
