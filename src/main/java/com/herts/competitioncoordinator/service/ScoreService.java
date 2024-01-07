package com.herts.competitioncoordinator.service;

import com.herts.competitioncoordinator.model.Score;

import java.util.List;

public interface ScoreService {
    Score saveScore(Score score) throws Exception;
    List<Score> calculateOverallScores(String competitionId) throws Exception;
}
