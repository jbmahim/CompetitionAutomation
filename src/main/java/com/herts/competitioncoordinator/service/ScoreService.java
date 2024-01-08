package com.herts.competitioncoordinator.service;

import com.herts.competitioncoordinator.model.Score;

import java.util.List;
import java.util.Optional;

public interface ScoreService {
    Score saveScore(Score score) throws Exception;
    Optional<Score> findByCompetitorId(String competitorId) throws Exception;
    List<Score> findByCompetitionId(String competitionId) throws Exception;
}
