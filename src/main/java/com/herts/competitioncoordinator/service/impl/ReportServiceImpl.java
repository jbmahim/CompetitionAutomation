package com.herts.competitioncoordinator.service.impl;

import com.herts.competitioncoordinator.exception.CustomException;
import com.herts.competitioncoordinator.model.Competitor;
import com.herts.competitioncoordinator.model.Score;
import com.herts.competitioncoordinator.service.CompetitorService;
import com.herts.competitioncoordinator.service.ReportService;
import com.herts.competitioncoordinator.service.ScoreService;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class ReportServiceImpl implements ReportService {
    private final CompetitorServiceImpl competitorService;
    private final ScoreServiceImpl scoreService;

    public ReportServiceImpl(CompetitorServiceImpl competitorService, ScoreServiceImpl scoreService) {
        this.competitorService = competitorService;
        this.scoreService = scoreService;
    }

    @Override
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

    @Override
    public String generateReportForAllCompetitors() throws Exception {
        StringBuilder report = new StringBuilder();
        DecimalFormat df = new DecimalFormat("0.0");

        List<Competitor> competitors = competitorService.findAllCompetitor();
        List<Score> scores = scoreService.findAll();

        for (Competitor competitor : competitors) {
            Score competitorScore = scores.stream()
                    .filter(score -> score.getCompetitorId().equals(competitor.getId()))
                    .findFirst()
                    .orElse(null);

            String scoresString = "No scores";
            String overallScore = "N/A";

            if (competitorScore != null) {
                scoresString = Arrays.stream(competitorScore.getScores())
                        .map(score -> score != null ? df.format(score) : "N/A")
                        .collect(Collectors.joining(" "));
                overallScore = df.format(competitorScore.calculateAverageScore());
            }

            report.append(String.format("%-12s %-7s %-15s %-5s%n",
                    competitor.getId(),
                    competitor.getLevel(),
                    scoresString,
                    overallScore));
        }

        return report.toString();
    }

    @Override
    public String generateShortReportForCompetitor(String competitorId) {
        StringBuilder report = new StringBuilder();
        DecimalFormat df = new DecimalFormat("0.0");

        try {
            Optional<Competitor> competitorOpt = competitorService.findById(competitorId);
            if (!competitorOpt.isPresent()) {
                return "Competitor not found.";
            }
            Competitor competitor = competitorOpt.get();

            Optional<Score> scoreOpt = scoreService.findByCompetitorId(competitorId);
            if (!scoreOpt.isPresent()) {
                return "Scores not found for competitor.";
            }
            Score score = scoreOpt.get();

            String initials = Arrays.stream(competitor.getName().split(" "))
                    .map(name -> name.substring(0, 1))
                    .collect(Collectors.joining());

            report.append("Short details for ").append(competitorId).append(":\n");
            report.append("CN ").append(competitorId)
                    .append(" (").append(initials).append(")")
                    .append(" has overall score ")
                    .append(df.format(score.getOverallScore())).append(".");

        } catch (Exception e) {
            report.append("Error generating short report: ").append(e.getMessage());
        }

        return report.toString();
    }

    @Override
    public String generateStatisticsReport() throws Exception {
        StringBuilder report = new StringBuilder();

        // Retrieve all competitors and scores
        List<Competitor> competitors = competitorService.findAllCompetitor();
        List<Score> scores = scoreService.findAll();

        // Count the number of competitors
        int numberOfCompetitors = competitors.size();
        report.append("There are ").append(numberOfCompetitors).append(" competitors\n");

        // Find the highest score
        Score highestScoringEntry = scores.stream()
                .max(Comparator.comparingDouble(Score::getOverallScore))
                .orElse(null);

        if (highestScoringEntry != null) {
            Competitor highestScorer = competitorService.findById(highestScoringEntry.getCompetitorId())
                    .orElse(null);
            String highestScorerName = highestScorer != null ? highestScorer.getName() : "Unknown";
            report.append("The person with the highest score is ")
                    .append(highestScorerName)
                    .append(" with a score of ")
                    .append(highestScoringEntry.getOverallScore())
                    .append(".\n");
        }

        // Analyze the frequency of individual scores
        Map<Double, Long> scoreFrequency = scores.stream()
                .flatMap(score -> Arrays.stream(score.getScores()))
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        IntSummaryStatistics scoreStats = scores.stream()
                .flatMapToInt(score -> Arrays.stream(score.getScores()).mapToInt(Double::intValue))
                .summaryStatistics();

        report.append("The following individual scores were awarded:\nScore: ");
        scoreStats.getMin();
        for (int i = 1; i <= 5; i++) {
            report.append(i).append(" ");
        }
        report.append("\nFrequency: ");
        for (int i = 1; i <= 5; i++) {
            report.append(scoreFrequency.getOrDefault(i, 0L)).append(" ");
        }

        return report.toString();
    }
}
