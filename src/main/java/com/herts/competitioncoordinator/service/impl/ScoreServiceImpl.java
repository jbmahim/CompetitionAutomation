package com.herts.competitioncoordinator.service.impl;

import com.herts.competitioncoordinator.model.Score;
import com.herts.competitioncoordinator.repository.impl.ScoreRepository;
import com.herts.competitioncoordinator.service.ScoreService;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ScoreServiceImpl implements ScoreService {

    private final ScoreRepository scoreRepository;
    public ScoreServiceImpl(ScoreRepository scoreRepository) {
        this.scoreRepository = scoreRepository;
    }

    public Score saveScore(Score score) throws Exception {
        return scoreRepository.saveScore(score);
    }

    @Override
    public Optional<Score> findByCompetitorId(String competitorId) throws Exception {
        List<Score> scores = scoreRepository.findAll();
        return scores.stream()
                .filter(score -> competitorId.equals(score.getCompetitorId()))
                .findFirst();
    }

    @Override
    public List<Score> findByCompetitionId(String competitionId) throws Exception {
        List<Score> scores = scoreRepository.findAll();
        return scores.stream()
                .filter(score -> competitionId.equals(score.getCompetitionId()))
                .toList();
    }

    public List<Score> findAll() throws Exception {
        return scoreRepository.findAll();
    }

}

