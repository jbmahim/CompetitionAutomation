package com.herts.competitioncoordinator.service.impl;

import com.herts.competitioncoordinator.model.Competitor;
import com.herts.competitioncoordinator.model.Score;
import com.herts.competitioncoordinator.service.CompetitorService;
import com.herts.competitioncoordinator.service.ReportService;
import com.herts.competitioncoordinator.service.ScoreService;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Optional;

@Service
public class ReportServiceImpl implements ReportService {
    private final CompetitorServiceImpl competitorService;
    private final ScoreServiceImpl scoreService;

    public ReportServiceImpl(CompetitorServiceImpl competitorService, ScoreServiceImpl scoreService) {
        this.competitorService = competitorService;
        this.scoreService = scoreService;
    }

    public String generateReportForCompetitor(String competitorId) {
        StringBuilder report = new StringBuilder();
        DecimalFormat df = new DecimalFormat("0.0");

        try {
            Optional<Competitor> competitorOpt = Optional.ofNullable(competitorService.getCompetitor(competitorId));
            if (!competitorOpt.isPresent()) {
                return "Competitor not found.";
            }
            Competitor competitor = competitorOpt.get();

            Optional<Score> scoreOpt = scoreService.findByCompetitorId(competitorId);
            if (!scoreOpt.isPresent()) {
                return "Scores not found for competitor.";
            }
            Score score = scoreOpt.get();

            // Build the report
            report.append("Full details for ").append(competitorId).append(":\n");
            report.append("Competitor number ").append(competitorId).append(", name ")
                    .append(competitor.getName()).append(".\n");
            report.append(competitor.getName()).append(" is a ")
                    .append(competitor.getLevel()).append(" and received these scores: ")
                    .append((score.getResult())).append("\n");
            report.append("This gives him an overall score of ")
                    .append(df.format(score.getOverallScore())).append(".\n");
        } catch (Exception e) {
            report.append("Error generating report: ").append(e.getMessage());
        }

        return report.toString();
    }
}
