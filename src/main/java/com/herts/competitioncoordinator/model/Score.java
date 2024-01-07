package com.herts.competitioncoordinator.model;

public class Score {
    private String scoreId;
    private String competitorId;
    private String officialId;
    private String sportsName;
    private String category;
    private String result;

    public Score() {
    }

    public Score(String scoreId, String competitorId, String officialId, String sportsName, String category, String result) {
        this.scoreId = scoreId;
        this.competitorId = competitorId;
        this.officialId = officialId;
        this.sportsName = sportsName;
        this.category = category;
        this.result = result;
    }

    public Score(String competitorId, String officialId, String sportsName, String category, String result) {
        this.competitorId = competitorId;
        this.officialId = officialId;
        this.sportsName = sportsName;
        this.category = category;
        this.result = result;
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

    public String getOfficialId() {
        return officialId;
    }

    public void setOfficialId(String officialId) {
        this.officialId = officialId;
    }

    public String getSportsName() {
        return sportsName;
    }

    public void setSportsName(String sportsName) {
        this.sportsName = sportsName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "Score{" +
                "scoreId='" + scoreId + '\'' +
                ", competitorId='" + competitorId + '\'' +
                ", officialId='" + officialId + '\'' +
                ", sportsName='" + sportsName + '\'' +
                ", category='" + category + '\'' +
                ", result='" + result + '\'' +
                '}';
    }
}
