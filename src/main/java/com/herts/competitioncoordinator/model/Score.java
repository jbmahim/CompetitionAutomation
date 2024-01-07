package com.herts.competitioncoordinator.model;

import java.util.Arrays;
import java.util.List;

public class Score {
    private String scoreId;
    private String competitorId;
    private String competitionId;
    private Double[] scores = new Double[5];
    private String result;
    private Double overallScore;


    public Score() {
    }

    public Score(String scoreId, String competitorId, String competitionId, Double[] scores) {
        this.scoreId = scoreId;
        this.competitorId = competitorId;
        this.competitionId = competitionId;
        this.scores = scores;
    }

    public Score(String competitorId, String competitionId, String result) {
        this.competitorId = competitorId;
        this.competitionId = competitionId;
        this.scores = scores;
    }

    public String getScoreId() {
        return scoreId;
    }

    public void setScoreId(String scoreId) {
        this.scoreId = scoreId;
    }

    public String getCompetitorId() {
        return competitorId;
    }

    public void setCompetitorId(String competitorId) {
        this.competitorId = competitorId;
    }

    public String getCompetitionId() {
        return competitionId;
    }

    public void setCompetitionId(String competitionId) {
        this.competitionId = competitionId;
    }

    public Double[] getScores() {
        return scores;
    }

    public void setScores(Double[] scores) {
        this.scores = scores;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
        this.scores = parseScores(result);
    }


    @Override
    public String toString() {
        return "Score{" +
                "scoreId='" + scoreId + '\'' +
                ", competitorId='" + competitorId + '\'' +
                ", competitionId='" + competitionId + '\'' +
                ", scores=" + Arrays.toString(scores) +
                '}';
    }

    private Double[] parseScores(String scoreString) {
        return Arrays.stream(scoreString.split(","))
                .map(Double::valueOf)
                .toArray(Double[]::new);
    }

    public Double getOverallScore() {
        return overallScore;
    }
    public void setOverallScore(Double overallScore) {
        this.overallScore = overallScore;
    }
}
